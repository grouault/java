package tudu.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import tudu.domain.model.TodoList;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class ShowTodoListsPdfView extends AbstractPdfView {

    protected void buildPdfDocument(
            Map model,
            Document doc,
            PdfWriter writer,
            HttpServletRequest req,
            HttpServletResponse resp)
            throws Exception {
 
    	TodoList todoList=(TodoList)model.get("todolist");
    	
    	doc.add(new Paragraph(todoList.getListId()));
    	doc.add(new Paragraph(todoList.getName()));
    }
}
