package tudu.jms;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class AsynchTuduJmsListener implements MessageListener {
	public void onMessage(Message message) {
		ObjectMessage objMessage=(ObjectMessage)message;
		JmsUtil.showMessageInformations(objMessage);
	}
}
