package business;

import service.TodoService;

import java.util.ArrayList;
import java.util.List;

public class TodoBusinessImpl {

    private TodoService todoService;

    public TodoBusinessImpl(TodoService todoService) {
        this.todoService = todoService;
    }

    public List<String> retrieveTodosRelatedToSpring(String user) {

        List<String> filteredTodos = new ArrayList<>();

        List<String> todos = todoService.retrieveTodos(user);

        todos.forEach(t -> {
            if(t.contains("Spring"))
                filteredTodos.add(t);
        });

        return filteredTodos;

    }

    public List<String> deleteTodosNotRelatedToSpring(String user) {

        List<String> filteredTodos = new ArrayList<>();

        List<String> todos = todoService.retrieveTodos(user);

        todos.forEach(t -> {
            if(!t.contains("Spring"))
                todoService.deleteTodo(t);
        });

        return filteredTodos;

    }
}
