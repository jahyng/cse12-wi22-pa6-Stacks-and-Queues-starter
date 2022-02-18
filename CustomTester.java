/**
 * TODO: Add your file header
 * Name:
 * ID:
 * Email:
 * Sources used: Put "None" if you did not have any external help
 * Some example of sources used would be Tutors, Zybooks, and Lecture Slides
 * 
 * 2-4 sentence file description here
 */

import org.junit.*;
import static org.junit.Assert.*;

import java.time.DayOfWeek;
import java.util.concurrent.DelayQueue;

/**
 * 
 * IMPORTANT: Do not change the method names and points are awarded
 * only if your test cases cover cases that the public tester file
 * does not take into account.
 */
public class CustomTester {
    // ----------------MyDeque class----------------
    /**
     * Test the constructor when [TODO: fill in a possible edge case here]
     */

     /**
     * Helper method to initialize all instance variables of MyDeque
     * @param deque The deque to initialize
     * @param data The data array
     * @param size The value for size
     * @param front The value for front
     * @param rear The value for rear
     */
    static void initDeque(MyDeque<Integer> deque, Object[] data, int size, 
            int front, int rear) {
        deque.data = data;
        deque.size = size;
        deque.front = front;
        deque.rear = rear;
    }
    @Test
    public void testMyDequeConstructor() {
        MyDeque<Integer> deque = new MyDeque<>(10);
        Integer[] orig = { 4, 5, 6, 7, 8, 9, 10, 1, 2, 3 };
        initDeque(deque, orig, 10, 7, 6);

        deque.expandCapacity();
        assertEquals(10, deque.size);
        assertEquals(20, deque.data.length);
        for (int i = 0; i < 10; i++) {
                assertEquals(i + 1, deque.data[i]);
        }
        assertEquals(0, deque.front);
        assertEquals(9, deque.rear);
    }

    /**
     * Test the expandCapacity method when [TODO]
     */
    @Test
    public void testMyDequeExpandCapacity() {

    }

    /**
     * Test the addFirst method when the start is after the end
     */
    @Test
    public void testAddFirst() {
        MyDeque<Integer> deque = new MyDeque<>(10);
        Integer[] orig = { 4, 5, 6, null, null, null, null, 1, 2, 3 };
        initDeque(deque, orig, 6, 7, 2);

        deque.addFirst(6);

        assertEquals("Capacity should not change if deque not full", 10,
                deque.data.length);
        assertEquals("Should increment size", 7, deque.size);
        assertEquals("Front should move one index when inserting into " +
                "non-empty deque", 6, deque.front);
        assertEquals("Rear shouldn't change when calling addFirst", 2,
                deque.rear);
        assertEquals("6 should have been inserted into index 2",
                Integer.valueOf(2), deque.data[8]);
        assertEquals("Index 3 should not have changed", Integer.valueOf(3),
                deque.data[9]);
        assertEquals("Index 4 should not have changed",
                Integer.valueOf(4), deque.data[0]);
        assertEquals("Index 5 should not have changed", Integer.valueOf(5),
                deque.data[1]);
    }

    /**
     * Test the addLast method when [TODO]
     */
    @Test
    public void testAddLast() {
        MyDeque<Integer> deque = new MyDeque<>(10);
        Integer[] orig = { 4, 5, 6, 7, 8, 9, null, 1, 2, 3 };
        initDeque(deque, orig, 9, 7, 5);
        boolean exceptionThrown = false;
        Integer[] expected = { 4, 5, 6, 7, 8, 9, 10, 1, 2, 3 };

        try {
                deque.addLast(null);
        }
        catch (NullPointerException e) {
                exceptionThrown = true;
        }
        assertTrue(exceptionThrown);

        deque.addLast(10);

        assertEquals(10, deque.size);
        assertEquals(10, deque.data.length);
        assertEquals(7, deque.front);
        assertEquals(6, deque.rear);
        assertEquals(4, deque.data[0]);
        for (int i = 0; i < 10; i++) {
                assertEquals("Failed at index" + i,expected[i], deque.data[i]);
        }

        deque.addLast(11);
        assertEquals(11, deque.size);
        assertEquals(20, deque.data.length);
        assertEquals(10, deque.rear);
        assertEquals(0, deque.front);
        for (int i = 0; i < deque.size; i++) {
                assertEquals(i + 1, deque.data[i]);
        }
        
    }

    /**
     * Test the removeFirst method when it removes the last element, empty
     * deque, removes from full list with circular behavior
     * 
     */
    @Test
    public void testRemoveFirst() {
        MyDeque<Integer> deque = new MyDeque<>(10);
        Integer[] orig = { null, null, null, null, 8, null, null, null, null, null };
        initDeque(deque, orig, 1, 4, 4);

        assertEquals(8, deque.removeFirst().intValue());
        assertEquals(0, deque.size);
        assertEquals(10, deque.data.length);
        for (int i = 0; i < 10; i++) {
                assertEquals(null, deque.data[i]);
        }

        // test empty deque
        assertNull(deque.removeFirst());

        // test full deque
        Integer[] orig2 = { 4, 5, 6, 7, 8, 9, 10, 1, 2, 3 };
        initDeque(deque, orig2, 10, 7, 6);

        assertEquals(1, deque.removeFirst().intValue());
        assertEquals(10, deque.data[deque.rear]);
        assertEquals(6, deque.rear);
        assertEquals(8, deque.front);
        assertEquals(2, deque.data[deque.front]);
    }

    /**
     * Test the removeLast method when there's only one element, no elements,
     * and when the deque is full;
     */
    @Test
    public void testRemoveLast() {
        MyDeque<Integer> deque = new MyDeque<>(10);
        Integer[] orig = { null, null, null, null, 8, null, null, null, null, null };
        initDeque(deque, orig, 1, 4, 4);

        assertEquals(8, deque.removeLast().intValue());
        assertEquals(0, deque.size);
        assertEquals(10, deque.data.length);
        // assertEquals(deque.front, deque.rear);
        for (int i = 0; i < 10; i++) {
                assertEquals(null, deque.data[i]);
        }

        // test empty deque
        assertNull(deque.removeLast());;

        // test full deque
        Integer[] orig2 = { 4, 5, 6, 7, 8, 9, 10, 1, 2, 3 };
        initDeque(deque, orig2, 10, 7, 6);

        assertEquals(10, deque.removeLast().intValue());
        assertEquals(9, deque.data[deque.rear]);
        assertEquals(5, deque.rear);
        assertEquals(7, deque.front);
        assertEquals(1, deque.data[deque.front]);
    }

    /**
     * Test the peekFirst method when deque is full
     */
    @Test
    public void testPeekFirst() {
        MyDeque<Integer> deque = new MyDeque<>(10);
        Integer[] orig = { 4, 5, 6, 7, 8, 9, 10, 1, 2, 3 };
        initDeque(deque, orig, 10, 7, 6);
        assertEquals(1, deque.peekFirst().intValue());
        assertEquals(10, deque.size);
        assertEquals(10, deque.data.length);
    }

    /**
     * Test the peekLast method when deque is full
     */
    @Test
    public void testPeekLast(){
        MyDeque<Integer> deque = new MyDeque<>(10);
        Integer[] orig = { 4, 5, 6, 7, 8, 9, 10, 1, 2, 3 };
        initDeque(deque, orig, 10, 7, 6);
        assertEquals(10, deque.peekLast().intValue());
        assertEquals(10, deque.size);
        assertEquals(10, deque.data.length);
    }

    // ----------------MyStack class----------------
    /**
     * Test MyStack when [TODO]
     */
    @Test
    public void testMyStack(){
        // You can test any method from MyStack or a combination of methods
    }

    // ----------------MyQueue class----------------
    /**
     * Test MyQueue when [TODO]
     */
    @Test
    public void testMyQueue(){
        // You can test any method from MyQueue or a combination of methods
    }
}
