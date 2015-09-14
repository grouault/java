package tudu.service.impl;

import java.util.Calendar;
import java.util.Date;

import net.sf.acegisecurity.context.security.SecureContext;
import net.sf.acegisecurity.context.security.SecureContextUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.ObjectRetrievalFailureException;

import tudu.domain.RolesEnum;
import tudu.domain.dao.PropertyDAO;
import tudu.domain.dao.RoleDAO;
import tudu.domain.dao.TodoDAO;
import tudu.domain.dao.TodoListDAO;
import tudu.domain.dao.UserDAO;
import tudu.domain.model.Property;
import tudu.domain.model.Role;
import tudu.domain.model.Todo;
import tudu.domain.model.TodoList;
import tudu.domain.model.User;
import tudu.service.UserAlreadyExistsException;
import tudu.service.UserManager;

/**
 * Implementation of the tudu.service.UserManager interface.
 * 
 * @author Julien Dubois
 */
public class UserManagerImpl implements UserManager {

    private final Log log = LogFactory.getLog(UserManagerImpl.class);

    private UserDAO userDAO = null;

    private RoleDAO roleDAO = null;
    
    private TodoListDAO todoListDAO = null;
    
    private TodoDAO todoDAO = null;
    
    private PropertyDAO propertyDAO = null;
    
    public void setUserDAO(UserDAO userDao) {
        this.userDAO = userDao;
    }

    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    public void setTodoDAO(TodoDAO todoDAO) {
        this.todoDAO = todoDAO;
    }

    public void setTodoListDAO(TodoListDAO todoListDAO) {
        this.todoListDAO = todoListDAO;
    }

    public void setPropertyDAO(PropertyDAO propertyDAO) {
        this.propertyDAO = propertyDAO;
    }

    /**
     * Find a user by login.
     * 
     * @see tudu.service.UserManager#findUser(String)
     */
    public User findUser(String login) {
        User user = userDAO.getUser(login);
        if (user == null) {
            if (log.isDebugEnabled()) {
                log.debug("Could not find User ID=" + login);
            }
            throw new ObjectRetrievalFailureException(User.class, login);
        }
        if (log.isDebugEnabled()) {
            log.debug("User ID=" + login + " found, user is called "
                    + user.getFirstName() + " " + user.getLastName());
        }
        return user;
    }
    
    /**
     * Find the current Tudu List user.
     * 
     * @see tudu.service.UserManager#getCurrentUser()
     */
    public User getCurrentUser() {
        SecureContext secureContext = SecureContextUtils.getSecureContext();
        net.sf.acegisecurity.providers.dao.User acegiUser = 
            (net.sf.acegisecurity.providers.dao.User) 
            secureContext.getAuthentication().getPrincipal();
        
        tudu.domain.model.User user = this.findUser(acegiUser.getUsername());
        return user;
    }

    /**
     * Update a user's information.
     * 
     * @see tudu.service.UserManager#updateUser(tudu.domain.model.User)
     */
    public void updateUser(User user) {
        if (log.isDebugEnabled()) {
            log.debug("Updating user '" + user.getLogin() + "'.");
        }
        userDAO.updateUser(user);
    }

    /**
     * Create a new user.
     * 
     * @see tudu.service.UserManager#createUser(tudu.domain.model.User)
     */
    public void createUser(User user) throws UserAlreadyExistsException {
        if (log.isDebugEnabled()) {
            log.debug("Creating user '" + user.getLogin() + "'.");
        }
        
        User testUser = userDAO.getUser(user.getLogin());
        if (testUser != null) {
            if (log.isDebugEnabled()) {
                log.debug("User login '" 
                        + user.getLogin() + "' already exists.");
            }
            throw new UserAlreadyExistsException("User already exists.");
        }
        user.setEnabled(true);
        Date now = Calendar.getInstance().getTime();
        user.setCreationDate(now);
        user.setLastAccessDate(now);
        Role role = roleDAO.getRole(RolesEnum.ROLE_USER.toString());
        user.getRoles().add(role);
        userDAO.saveUser(user);
        
        TodoList todoList = new TodoList();
        todoList.setName("Welcome!");
        todoListDAO.saveTodoList(todoList);
        user.getTodoLists().add(todoList);
        todoList.getUsers().add(user);
        
        Todo welcomeTodo = new Todo();
        welcomeTodo.setDescription("Welcome to Tudu Lists!");
        welcomeTodo.setPriority(100);
        welcomeTodo.setCreationDate(now);
        welcomeTodo.setCompletionDate(now);
        welcomeTodo.setTodoList(todoList);
        todoList.getTodos().add(welcomeTodo);
        todoDAO.saveTodo(welcomeTodo);
        todoListDAO.updateTodoList(todoList);
    }

    /**
     * @see tudu.service.UserManager#sendPassword(tudu.domain.model.User)
     */
    public void sendPassword(User user) {
        if (log.isDebugEnabled()) {
            log.debug("Send password of user '" + user.getLogin() + "'.");
        }
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        Property smtpHost = propertyDAO.getProperty("smtp.host");
        sender.setHost(smtpHost.getValue());
        Property smtpPort = propertyDAO.getProperty("smtp.port");
        int port = 25;
        try {
            port = Integer.parseInt(smtpPort.getValue());
        } catch (NumberFormatException e) {
            log.error("The supplied SMTP port is not a number.");
        }
        sender.setPort(port);
        Property smtpUser = propertyDAO.getProperty("smtp.user");
        sender.setUsername(smtpUser.getValue());
        Property smtpPassword = propertyDAO.getProperty("smtp.password");
        sender.setPassword(smtpPassword.getValue());
        
        SimpleMailMessage message = new SimpleMailMessage();
        Property smtpFrom = propertyDAO.getProperty("smtp.from");
        message.setTo(user.getEmail());
        message.setFrom(smtpFrom.getValue());
        message.setSubject("Your Tudu Lists password");
        message.setText("Dear " + user.getFirstName() + " " + user.getLastName() + ",\n\n"
                + "Your Tudu Lists password is \"" + user.getPassword() + "\".\n"
                + "Now that this password has been sent by e-mail, we recommend that "
                + "you change it as soon as possible.\n\n"
                + "Regards,\n\n"
                + "Tudu Lists.");
        
        sender.send(message);
    }
}
