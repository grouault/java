package tudu.jms;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

public class JmsUtil {
	public static void showMessageInformations(ObjectMessage objMessage) {
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

	private static void doShowException(ObjectMessage objMessage) throws JMSException {
		Exception ex=(Exception)objMessage.getObject();
		ex.printStackTrace();
	}

	private static boolean containsException(ObjectMessage objMessage) throws JMSException {
		return (objMessage.getObject() instanceof JMSException);
	}

	private static void doShowMessageInformations(ObjectMessage objMessage) throws JMSException {
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
}
