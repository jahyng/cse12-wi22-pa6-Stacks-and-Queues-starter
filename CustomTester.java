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

/**
 * TODO: Add your class header
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

    }

    /**
     * Test the expandCapacity method when [TODO]
     */
    @Test
    public void testMyDequeExpandCapacity() {

    }

    /**
     * Test the addFirst method when [TODO]
     */
    @Test
    public void testAddFirst() {
        MyDeque<Integer> deque = new MyDeque<>(10);
        Integer[] orig = { 1, 2, 3, 4, 5, 6, null, null, null, null };
        initDeque(deque, orig, 6, 0, 5);

        deque.addFirst(6);

        assertEquals("Capacity should not change if deque not full", 10,
                deque.data.length);
        assertEquals("Should increment size", 7, deque.size);
        assertEquals("Front should move one index when inserting into " +
                "non-empty deque", 0, deque.front);
        assertEquals("Rear shouldn't change when calling addFirst", 6,
                deque.rear);
        assertEquals("6 should have been inserted into index 2",
                Integer.valueOf(2), deque.data[2]);
        assertEquals("Index 3 should not have changed", Integer.valueOf(3),
                deque.data[3]);
        assertEquals("Index 4 should not have changed",
                Integer.valueOf(4), deque.data[4]);
        assertEquals("Index 5 should not have changed", Integer.valueOf(5),
                deque.data[5]);
    }

    /**
     * Test the addLast method when [TODO]
     */
    @Test
    public void testAddLast() {

    }

    /**
     * Test the removeFirst method when [TODO]
     */
    @Test
    public void testRemoveFirst() {

    }

    /**
     * Test the removeLast method when [TODO]
     */
    @Test
    public void testRemoveLast() {

    }

    /**
     * Test the peekFirst method when [TODO]
     */
    @Test
    public void testPeekFirst(){

    }

    /**
     * Test the peekLast method when [TODO]
     */
    @Test
    public void testPeekLast(){

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
