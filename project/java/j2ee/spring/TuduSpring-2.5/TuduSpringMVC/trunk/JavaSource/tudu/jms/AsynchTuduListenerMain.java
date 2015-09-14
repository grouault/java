package tudu.jms;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AsynchTuduListenerMain {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context=null;
		try {
			context=new ClassPathXmlApplicationContext("/applicationContext-jca.xml");
			Thread.sleep(60000);
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
