package toDo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import com.google.gson.Gson;

public class AddToDo {

	public final static String DEFAULT_FILE_NAME = "todo_list.json";

	// This function fills in a Person message based on user input.
	static ToDo PromptForToDo(BufferedReader stdin, PrintStream stdout)
			throws IOException {
		ToDo todo = new ToDo();

		stdout.print("Enter ToDo task: ");
		todo.setTask(stdin.readLine());

		stdout.print("Enter ToDo context: ");
		todo.setContext(stdin.readLine());

		stdout.print("Enter ToDo project: ");
		todo.setProject(stdin.readLine());

		stdout.print("Enter ToDo priority: ");
		todo.setPriority((Integer.parseInt(stdin.readLine())));

		return todo;
	}

	// Main function: Reads the entire ToDo list from a file,
	// adds one ToDo based on user input, then writes it back out to the same
	// file.
	public static void main(String[] args) throws Exception {
		String filename = DEFAULT_FILE_NAME;
		if (args.length > 0) {
			filename = args[0];
		}

		ToDoList list = new ToDoList();
		Gson gson = new Gson();

		// Read the existing ToDo list.
		try {
			list = gson.fromJson(new FileReader(filename),
					ToDoList.class);
		} catch (FileNotFoundException e) {
			System.out.println(filename
					+ ": File not found.  Creating a new file.");
		}

		// Add a ToDo.
		list.addToDo((PromptForToDo(new BufferedReader(
				new InputStreamReader(System.in)), System.out)));

		// Write the new ToDo list back to disk.
		FileWriter output = new FileWriter(filename);
		output.write(gson.toJson(list));
		output.close();
	}
}

