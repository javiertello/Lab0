package toDo;

import java.io.FileReader;

import com.google.gson.Gson;

class ListToDo {
	public final static String DEFAULT_FILE_NAME = "todo_list.json";

	// Iterates though all ToDo's in the ToDoList and prints info about them.
	static void Print(ToDoList todolist) {
		for (ToDo todo : todolist.getList()) {
			System.out.println("ToDo task: " + todo.getTask());
			System.out.println("ToDo context: " + todo.getContext());
			System.out.println("ToDo project: " + todo.getProject());
			System.out.println("ToDo priority: " + todo.getPriority());
			System.out.println();
		}
	}

	// Main function: Reads the entire ToDo list from a file and prints all
	// the information inside.
	public static void main(String[] args) throws Exception {
		Gson gson = new Gson();
		String filename = DEFAULT_FILE_NAME;
		if (args.length > 0) {
			filename = args[0];
		}

		// Read the existing address book.
		ToDoList todolist = gson.fromJson(new FileReader(filename),
				ToDoList.class);

		Print(todolist);
	}
}
