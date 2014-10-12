package servlets;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import toDo.*;

import com.google.gson.Gson;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/todolist" })
public class ToDoListServlet extends HttpServlet {
	
	public final static String DEFAULT_FILE_NAME = "todo_list.json";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String property = req.getParameter("property");
		String text = req.getParameter("text");
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		Gson gson = new Gson();
		String list ="";
		boolean empty = true;
		try{
			ToDoList todolist = gson.fromJson(new FileReader(
					DEFAULT_FILE_NAME), ToDoList.class);
			//file exists
			resp.setStatus(HttpServletResponse.SC_OK);
			
			// Search the list for matches
			for(ToDo todo : todolist.getList()){
				if(property.toLowerCase().equals("task")){
					if(todo.getTask().toLowerCase().contains(text.toLowerCase())){
						list = addToToDoList(list, todo);
						empty = false;
					}
				}
				else if(property.toLowerCase().equals("context")){
					if(todo.getContext().toLowerCase().contains(text.toLowerCase())){
						list = addToToDoList(list, todo);
						empty = false;
					}
				}
				else if(property.toLowerCase().equals("project")){
					if(todo.getProject().toLowerCase().contains(text.toLowerCase())){
						list = addToToDoList(list, todo);
						empty = false;
					}
				}
				// Priority
				else{
					if(String.valueOf(todo.getPriority()).equals(text)){
						list = addToToDoList(list, todo);
						empty = false;
					}
				}
	
			}
			
			if (text.equals("")){
				out.println("<html><head><title>ToDo WebApp</title></head>"
						+ "<body>"
						+ "<h2>Please enter a text to filter!</h2>"
						+ "</body></html>");
			}
			else if(empty){
				out.println("<html><head><title>ToDo WebApp</title></head>"
						+ "<body>"
						+ "<h2>No matches were found</h2>"
						+ "</body></html>");
			}else{
				out.println("<html><head><title>ToDo WebApp</title></head>"
						+ "<body><h1>Tasks list</h1><br/>"
						 + list
						+ "</body></html>");
			}
			
			
		}catch(FileNotFoundException e){
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			out.println("<html><head><title>ToDo WebApp</title></head>"
					+ "<body><h1>Error</h1><br/>"
					+ "File " + DEFAULT_FILE_NAME + " does not exist."
					+ "</body></html>");
		}
	}

	private String addToToDoList(String list, ToDo todo) {
		list +=   "<b>Task: </b>" + todo.getTask()
				+ "<br/><b>Context: </b>" + todo.getContext()
				+ "<br/><b>Project: </b>" +  todo.getProject()
				+ "<br/><b>Priority (1-10): </b>" + todo.getPriority() + "<br/><hr>";	
		return list;
	}

}
