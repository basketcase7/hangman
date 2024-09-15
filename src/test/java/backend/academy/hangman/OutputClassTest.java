package backend.academy.hangman;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OutputClassTest {
    private PrintStream mockPrintStream;

    @BeforeEach
    public void setUp() {
        mockPrintStream = mock(PrintStream.class);

        OutputClass.output = mockPrintStream;
    }

    @Test
    public void testPrintSmth() {
        String message = "Hello, World!";
        OutputClass.printSmth(message);

        verify(mockPrintStream).println(message);
    }

    @Test
    public void testPrintMassive() {
        String[] massive = {"A", "B", "C"};
        OutputClass.printMassive(massive);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        verify(mockPrintStream, times(3)).print(captor.capture());

        List<String> capturedArguments = captor.getAllValues();

        assertEquals("A ", capturedArguments.get(0));
        assertEquals("B ", capturedArguments.get(1));
        assertEquals("C ", capturedArguments.get(2));

        verify(mockPrintStream).println();
    }

    @Test
    public void testPrintList() {
        List<String> list = Arrays.asList("X", "Y", "Z");
        OutputClass.printList(list);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        verify(mockPrintStream, times(3)).print(captor.capture());

        List<String> capturedArguments = captor.getAllValues();

        assertEquals("X ", capturedArguments.get(0));
        assertEquals("Y ", capturedArguments.get(1));
        assertEquals("Z ", capturedArguments.get(2));

        verify(mockPrintStream).println();
    }

}
