package mockito;

import business.TodoBusinessImpl;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import service.TodoService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

public class TodoBusinessImplMockTest {

    @Test
    public void testRetrieveTodosRelatedToSpring_usingAMock() {

        TodoService todoServiceMock = mock(TodoService.class);
        when(todoServiceMock.retrieveTodos("Dummy"))
            .thenReturn(Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance"));

        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
        List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
        assertEquals(2, todos.size());
    }

    @Test
    public void testRetrieveTodosRelatedToSpring_withEmptyList() {

        TodoService todoServiceMock = mock(TodoService.class);
        when(todoServiceMock.retrieveTodos("Dummy"))
            .thenReturn(Arrays.asList());

        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
        List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
        assertEquals(0, todos.size());
    }

    @Test
    public void testRetrieveTodosRelatedToSpring_usingBDD() {

        // Given
        TodoService todoServiceMock = mock(TodoService.class);
        given(todoServiceMock.retrieveTodos("Dummy"))
                .willReturn(Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance"));
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

        // When
        List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");

        // Then
        assertThat(todos.size(), is(2));
    }

    @Test
    public void testDeleteTodosNotRelatedToSpring_usingBDD() {

        // Given
        TodoService todoServiceMock = mock(TodoService.class);
        given(todoServiceMock.retrieveTodos("Dummy"))
                .willReturn(Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance"));
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

        // When
        List<String> todos = todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");

        // Then
        // verifica se o método está sendo chamado com este parâmetro
        verify(todoServiceMock).deleteTodo("Learn to Dance");
        then(todoServiceMock).should().deleteTodo("Learn to Dance");

        verify(todoServiceMock, times(1)).deleteTodo("Learn to Dance");
        then(todoServiceMock).should(times(1)).deleteTodo("Learn to Dance");

        verify(todoServiceMock, atLeastOnce()).deleteTodo("Learn to Dance");
        then(todoServiceMock).should(atLeastOnce()).deleteTodo("Learn to Dance");

        verify(todoServiceMock, never()).deleteTodo("Learn Spring");
        then(todoServiceMock).should(never()).deleteTodo("Learn Spring");
    }

    @Test
    public void testDeleteTodosNotRelatedToSpring_usingBDD_argumentCapture() {

        // Declare Argument Captor
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        // Given
        TodoService todoServiceMock = mock(TodoService.class);
        given(todoServiceMock.retrieveTodos("Dummy"))
                .willReturn(Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance"));
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

        // When
        List<String> todos = todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");

        // Then
        then(todoServiceMock).should().deleteTodo(stringArgumentCaptor.capture());

        assertThat(stringArgumentCaptor.getValue(), is("Learn to Dance"));

    }

    @Test
    public void testDeleteTodosNotRelatedToSpring_usingBDD_argumentCaptureMultipleTypes() {

        // Declare Argument Captor
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        // Given
        TodoService todoServiceMock = mock(TodoService.class);
        given(todoServiceMock.retrieveTodos("Dummy"))
                .willReturn(Arrays.asList("Learn to Rock and Roll", "Learn Spring", "Learn to Dance"));
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

        // When
        List<String> todos = todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");

        // Then
        then(todoServiceMock).should(times(2)).deleteTodo(stringArgumentCaptor.capture());

        assertThat(stringArgumentCaptor.getAllValues().size(), is(2));

    }
}
