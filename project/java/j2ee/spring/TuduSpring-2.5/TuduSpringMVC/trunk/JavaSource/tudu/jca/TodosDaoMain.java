package tudu.jca;

import java.util.Iterator;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import tudu.domain.model.Todo;

public class TodosDaoMain {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context=null;
		try {
			context=new ClassPathXmlApplicationContext("/applicationContext-cci.xml");
			TodosDaoImpl dao=(TodosDaoImpl)context.getBean("todosDao");

			//Approche basée sur le template
			System.out.println("Approche template");
			List todos=dao.getTodos();
			showTodos(todos);

			System.out.println("\n\n");

			//Approche objet
			System.out.println("Approche objet");
			todos=dao.getTodos();
			showTodos(todos);
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if( context!=null ) {
				context.close();
			}
		}
		System.exit(0);
	}

	private static void showTodos(List todos) {
		for(Iterator i=todos.iterator();i.hasNext();) {
			Todo todo=(Todo)i.next();
			System.out.println("- Todo : "+todo.getTodoId());
			System.out.println("    descriptif: "+todo.getDescription());
		}
	}

}
