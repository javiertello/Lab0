# ToDo list with JSON
This project contains a demonstration of [GSON](https://code.google.com/p/google-gson/). Gson is a Java library that can be used to convert arbitrary Java Objects into their JSON representation. It can also be used to convert a JSON string to an equivalent Java object.

The following examples are included:
* __AddToDo__ (```gradle -q addToDo```) tests first it the file ```todo_list.json``` exists. If it exists, the example unmarshalls its content into Java objects. Then, code asks to the user the details of a ```ToDo``` and then the ToDo is added to the "ToDo list". Finally, the "ToDo list" is marshalled into a JSON file with name ```todo_list.json```.
* __listToDo__ (```gradle -q listToDo```) unmarshalls ```todo_list.json``` into Java objects and then dump the objects to the console.
