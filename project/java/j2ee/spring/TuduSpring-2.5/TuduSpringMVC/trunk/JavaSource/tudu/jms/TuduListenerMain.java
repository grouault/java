package tudu.jms;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TuduListenerMain {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context=null;
		try {
			context=new ClassPathXmlApplicationContext("/applicationContext-jms.xml");
			TuduJmsListener listener=(TuduJmsListener)context.getBean("tuduJmsListener");
			listener.receive();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if( context!=null ) {
				context.close();
			}
		}
		System.exit(0);
	}

}
