package tudu.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import tudu.domain.model.Todo;

public class JmsInterceptor implements MethodInterceptor {
	private JmsTemplate template;
	
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object ret = invocation.proceed();
		TuduMessage message = createTuduMessage(invocation);
		sendMessage(message);
		return ret;
	}

	private TuduMessage createTuduMessage(MethodInvocation invocation) {
		String methodName = invocation.getMethod().getName();
		Object[] args = invocation.getArguments();
		if( args[0] instanceof String ) {
			String todoId = (String)args[0];

			TuduMessage message = new TuduMessage();
			message.setOperationType(methodName);
			message.setTodoId(todoId);
			return message;
		} else {
			Todo todo = (Todo)args[0];
		
			TuduMessage message = new TuduMessage();
			message.setOperationType(methodName);
			message.setTodoId(todo.getTodoId());
			message.setTodoCompleted(todo.isCompleted());
			message.setTodoCreationDate(todo.getCreationDate());
			message.setTodoDescription(todo.getDescription());
			message.setTodoPriority(todo.getPriority());
			return message;
		}
	}

	private void sendMessage(final TuduMessage message) {
		System.out.println("> sendMessage");
		template.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage objectMessage=session.createObjectMessage();
				objectMessage.setObject(message);
				return objectMessage;
			}
		});
		System.out.println("< sendMessage");
	}

	public JmsTemplate getTemplate() {
		return template;
	}

	public void setTemplate(JmsTemplate template) {
		this.template = template;
	}

}
