package mockito;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;
import org.mockito.BDDMockito;

import static org.mockito.Mockito.*;

import java.util.List;

public class ListTest {

    @Test
    public void letsMockListSizeMethod() {
        List listMock = mock(List.class);
        when(listMock.size()).thenReturn(2);
        assertEquals(2, listMock.size());
    }

    @Test
    public void letsMockListSize_ReturnMultipleValues() {
        List listMock = mock(List.class);
        when(listMock.size()).thenReturn(2).thenReturn(3).thenReturn(4);
        assertEquals(2, listMock.size());
        assertEquals(3, listMock.size());
        assertEquals(4, listMock.size());
    }

    @Test
    public void letsMockListGetMethod() {
        List listMock = mock(List.class);
        when(listMock.get(0)).thenReturn("value");
        when(listMock.get(1)).thenReturn("value1");
        when(listMock.get(2)).thenReturn("value2");
        assertEquals("value", listMock.get(0));
        assertEquals("value1", listMock.get(1));
        assertEquals("value2", listMock.get(2));
        assertEquals(null, listMock.get(3));
    }

    @Test
    public void letsMockListGetMethod_WithAnyInt() {
        List listMock = mock(List.class);
        when(listMock.get(anyInt())).thenReturn("value");
        assertEquals("value", listMock.get(31));
    }

    @Test(expected = Exception.class)
    public void letsMockListGetMethod_ThrowException() {
        List listMock = mock(List.class);
        when(listMock.get(anyInt())).thenThrow(new Exception());
        listMock.get(31);
    }

    @Test
    public void letsMockListGetMethod_UsingBDD() {
        // Given
        List listMock = mock(List.class);
        BDDMockito.given(listMock.get(0)).willReturn("value");

        // When
        String elem = (String) listMock.get(0);

        // Then
        assertThat(elem, is("value"));
    }
}
