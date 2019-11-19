package mockito;

import business.TodoBusinessImpl;
import stub.TodoServiceStub;
import org.junit.Assert;
import org.junit.Test;
import service.TodoService;

import java.util.List;

public class TodoBusinessImplStubTest {

    @Test
    public void testRetrieveTodosRelatedToSpring_usingAStub() {
        TodoService todoServiceStub = new TodoServiceStub();
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceStub);
        List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
        Assert.assertEquals(2, todos.size());
    }
}
