package tudu.jca;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.resource.ResourceException;
import javax.resource.cci.ConnectionFactory;
import javax.resource.cci.IndexedRecord;
import javax.resource.cci.InteractionSpec;
import javax.resource.cci.Record;
import javax.resource.cci.RecordFactory;
import javax.resource.cci.ResultSet;

import org.springframework.dao.DataAccessException;
import org.springframework.jca.cci.object.MappingRecordOperation;

import tudu.domain.model.Todo;

public class TodosOperation extends MappingRecordOperation {
    public TodosOperation(ConnectionFactory connectionFactory,
                           InteractionSpec interactionSpec) {
        super(connectionFactory,interactionSpec);
    }

    protected Record createInputRecord(
             RecordFactory recordFactory, Object inputObject)
                   throws ResourceException, DataAccessException {
        Integer id=(Integer)inputObject;
        IndexedRecord input = 
                 recordFactory.createIndexedRecord("input");
        input.add(id);
        return input;
    }

    protected Object extractOutputData(
                 Record outputRecord) throws ResourceException,
                               SQLException, DataAccessException {
        
        List todos=new ArrayList();
        ResultSet rs=(ResultSet)outputRecord;
        while( rs.next() ) {
            Todo todo=new Todo();
            todo.setTodoId(rs.getString("id"));
            todo.setDescription(rs.getString("description"));
            todos.add(todo);
        }
        return todos;
    }
}
