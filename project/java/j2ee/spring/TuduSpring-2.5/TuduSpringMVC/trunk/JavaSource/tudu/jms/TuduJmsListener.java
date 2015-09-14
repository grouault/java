package tudu.jms;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import org.springframework.jms.core.JmsTemplate;

public class TuduJmsListener {
	private JmsTemplate template;
	
	public void receive() {
		ObjectMessage objMessage=(ObjectMessage)template.receive();
		showMessageInformations(objMessage);
	}

	private void showMessageInformations(ObjectMessage objMessage) {
		try {
			if( containsException(objMessage) ) {
				doShowException(objMessage);
			} else {
				doShowMessageInformations(objMessage);
			}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void doShowException(ObjectMessage objMessage) {
		// TODO Auto-generated method stub
		
	}

	private boolean containsException(ObjectMessage objMessage) throws JMSException {
		return (objMessage.getObject() instanceof JMSException);
	}

	private void doShowMessageInformations(ObjectMessage objMessage) throws JMSException {
		if( objMessage.getObject() instanceof JMSException ) {
			JMSException ex = (JMSException)objMessage.getObject();
			ex.printStackTrace();
		} else {
			TuduMessage message = (TuduMessage)objMessage.getObject();
			System.out.println("Message JMS:");
			System.out.println(" - operationType : "+message.getOperationType());
			System.out.println(" - todoId : "+message.getTodoId());
			System.out.println(" - todoDescription : "+message.getTodoDescription());
			System.out.println(" - todoPriority : "+message.getTodoPriority());
			System.out.println(" - todoCreationDate : "+message.getTodoCreationDate());
			System.out.println(" - todoCompleted : "+message.isTodoCompleted());
		}
	}

	public JmsTemplate getTemplate() {
		return template;
	}

	public void setTemplate(JmsTemplate template) {
		this.template = template;
	}
}
