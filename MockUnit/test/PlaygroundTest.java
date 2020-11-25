package test;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InOrder;

public class PlaygroundTest {

    @Test
    public void invocation() {
        //mock creation
        List<String> mockedList = mock(List.class);

        //using mock object
        mockedList.add("one");
        mockedList.clear();

        //verification
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }


    @Test(expected = RuntimeException.class)
    public void stubbing() {
        // You can mock concrete classes, not only interfaces
        @SuppressWarnings("unchecked")
        LinkedList<String> mockedList = mock(LinkedList.class);

        // stubbing
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException());

        Assert.assertEquals("first", mockedList.get(0));

        // "null" because get(999) was not stubbed
        Assert.assertEquals(null, mockedList.get(999));

        // following throws runtime exception
        System.out.println(mockedList.get(1));

        // Although it is possible to verify a stubbed invocation, usually it's
        // just redundant
        // If your code cares what get(0) returns then something else breaks
        // (often before even verify() gets executed).
        // If your code doesn't care what get(0) returns then it should not be
        // stubbed.
        verify(mockedList).get(0);
    }

    @Test
    public void matchers() {
        @SuppressWarnings("unchecked")
        LinkedList<String> mockedList = mock(LinkedList.class);
        // stubbing using built-in anyInt() argument matcher
        when(mockedList.get(anyInt())).thenReturn("element");

        Assert.assertEquals("element", mockedList.get(0));
        Assert.assertEquals("element", mockedList.get(999));

        // you can also verify using an argument matcher
        verify(mockedList, times(2)).get(anyInt());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void invocationTimes() {
        LinkedList<String> mockedList = mock(LinkedList.class);
        mockedList.add("once");
        mockedList.add("once again");

        mockedList.add("twice");
        mockedList.add("twice");

        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");

        // following two verifications work exactly the same - times(1) is used
        // by default
        verify(mockedList).add("once");
        verify(mockedList, times(1)).add("once");

        // exact number of invocations verification
        verify(mockedList, times(2)).add("twice");
        verify(mockedList, times(3)).add("three times");

        // verification using never(). never() is an alias to times(0)
        verify(mockedList, never()).add("never happened");

        // verification using atLeast()/atMost()
        verify(mockedList, atLeastOnce()).add("three times");
        verify(mockedList, atLeast(2)).add("three times");
        verify(mockedList, atMost(5)).add("three times");

        // create an inOrder verifier for a single mock
        InOrder inOrder = inOrder(mockedList);

        // following will make sure that add is first called with
        // "was added first, then with "was added second"
        inOrder.verify(mockedList).add("once");
        inOrder.verify(mockedList).add("once again");

        // verify that method was never called on a mock
        verify(mockedList, never()).add("two");

        List<String> firstMock = mock(List.class);
        List<String> secondMock = mock(List.class);

        // verify that other mocks were not interacted
        verifyZeroInteractions(firstMock, secondMock);
    }

}
