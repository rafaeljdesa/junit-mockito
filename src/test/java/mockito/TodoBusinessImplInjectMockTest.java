package mockito;

import business.TodoBusinessImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;
import service.TodoService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

// Se adicionar a RULE não precisa usar o EunWith
// @RunWith(MockitoJUnitRunner.class)
public class TodoBusinessImplInjectMockTest {


    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    TodoService todoServiceMock;

    @InjectMocks
    TodoBusinessImpl todoBusinessImpl;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @Test
    public void testRetrieveTodosRelatedToSpring_usingAMock() {

        when(todoServiceMock.retrieveTodos("Dummy"))
            .thenReturn(Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance"));
        
        List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
        assertEquals(2, todos.size());
    }

    @Test
    public void testRetrieveTodosRelatedToSpring_withEmptyList() {

        when(todoServiceMock.retrieveTodos("Dummy"))
            .thenReturn(Arrays.asList());
        
        List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
        assertEquals(0, todos.size());
    }

    @Test
    public void testRetrieveTodosRelatedToSpring_usingBDD() {

        // Given
        given(todoServiceMock.retrieveTodos("Dummy"))
                .willReturn(Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance"));

        // When
        List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");

        // Then
        assertThat(todos.size(), is(2));
    }

    @Test
    public void testDeleteTodosNotRelatedToSpring_usingBDD() {

        // Given
        given(todoServiceMock.retrieveTodos("Dummy"))
                .willReturn(Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance"));
        

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

        // Given
        given(todoServiceMock.retrieveTodos("Dummy"))
                .willReturn(Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance"));
        

        // When
        List<String> todos = todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");

        // Then
        then(todoServiceMock).should().deleteTodo(stringArgumentCaptor.capture());

        assertThat(stringArgumentCaptor.getValue(), is("Learn to Dance"));

    }

    @Test
    public void testDeleteTodosNotRelatedToSpring_usingBDD_argumentCaptureMultipleTypes() {

        // Given
        given(todoServiceMock.retrieveTodos("Dummy"))
                .willReturn(Arrays.asList("Learn to Rock and Roll", "Learn Spring", "Learn to Dance"));
        

        // When
        List<String> todos = todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");

        // Then
        then(todoServiceMock).should(times(2)).deleteTodo(stringArgumentCaptor.capture());

        assertThat(stringArgumentCaptor.getAllValues().size(), is(2));

    }
}
