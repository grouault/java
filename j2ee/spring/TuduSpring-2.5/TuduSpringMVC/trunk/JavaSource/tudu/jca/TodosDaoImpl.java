package tudu.jca;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.resource.ResourceException;
import javax.resource.cci.IndexedRecord;
import javax.resource.cci.Record;
import javax.resource.cci.RecordFactory;
import javax.resource.cci.ResultSet;

import org.springframework.dao.DataAccessException;
import org.springframework.jca.cci.core.RecordCreator;
import org.springframework.jca.cci.core.RecordExtractor;
import org.springframework.jca.cci.core.support.CciDaoSupport;

import tudu.domain.model.Todo;

import com.sun.connector.cciblackbox.CciInteractionSpec;

public class TodosDaoImpl extends CciDaoSupport {

	public List getTodos() {
		CciInteractionSpec interactionSpec=new CciInteractionSpec();
		interactionSpec.setSql("select * from todo");

		return (List)getCciTemplate().execute(
		               interactionSpec, new RecordCreator() {
		    public Record createRecord(RecordFactory recordFactory)
		                    throws ResourceException, DataAccessException {
		        IndexedRecord input = 
		                 recordFactory.createIndexedRecord("input");
		        //input.add(new Integer(id));
		        return input;
		    }
		}, new RecordExtractor() {
		    public Object extractData(Record record)
		      throws ResourceException, SQLException, DataAccessException {
		        List todos=new ArrayList();
		        ResultSet rs=(ResultSet)record;
		        while( rs.next() ) {
		            Todo todo=new Todo();
		            todo.setTodoId(rs.getString("id"));
		            todo.setDescription(rs.getString("description"));
		            todos.add(todo);
		        }
		        return todos;
		    }
		});
	}

	public List getTodosObject() {
		CciInteractionSpec interactionSpec=new CciInteractionSpec();
		interactionSpec.setSql("select * from todo");

		TodosOperation operation=new TodosOperation(
		                        getConnectionFactory(), interactionSpec);
		return (List)operation.execute(null);
	}
}
