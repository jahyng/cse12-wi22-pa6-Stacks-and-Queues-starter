/**
 * Name: Josh Yang
 * ID: A16667394   
 * Email: jwyang@ucsd.edu
 * Sources used: None
 * Custom tester file for Deques, Queues, and Stacks. Tests edge cases and other
 * cases that public tester did not test. 
 */

import org.junit.*;
import static org.junit.Assert.*;

/**
 * 
 * IMPORTANT: Do not change the method names and points are awarded
 * only if your test cases cover cases that the public tester file
 * does not take into account.
 */
public class CustomTester {
    // ----------------MyDeque class----------------
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
    /**
     * Test the constructor when innital capacity is less than 0
     */

    @Test
    public void testMyDequeConstructor() {
            boolean exceptionThrown = false;
            try {
                MyDeque<Integer> test = new MyDeque<>(-1);
            }
            catch (IllegalArgumentException e) {
                exceptionThrown = true;
            }
            assertTrue(exceptionThrown);
            MyDeque<Integer> test2 = new MyDeque<>(0);
            assertEquals(0, test2.size());
            assertEquals(0, test2.data.length);
            assertEquals(0, test2.front);
            assertEquals(test2.front, test2.rear);
    }

/**
 * Test the expandCapacity method when front and rear are in the middle of the 
 * list.
 */
@Test
public void testMyDequeExpandCapacity() {
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
     * Test the addLast method when list is full and last is in the middle of 
     * list
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

        // test removing first element
        Integer[] orig3 = {2,3,4,5,6,7,8,9,10,1};
        initDeque(deque, orig3, 10, 9, 8);
        assertEquals(1, deque.removeFirst().intValue());
        assertEquals(0, deque.front);
        assertEquals(9, deque.size);
        assertEquals(10, deque.data.length);
        for (int i = 0; i < 9; i++) {
                assertEquals(i + 2, deque.data[i]);
        }
        assertEquals(null, deque.data[9]);

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

        Integer[] orig3 = {10,1,2,3,4,5,6,7,8,9};
        initDeque(deque, orig3, 10, 1, 0);
        assertEquals(10, deque.removeLast().intValue());
        assertEquals(9, deque.rear);
        assertEquals(null, deque.data[0]);
        assertEquals(9, deque.size);
        assertEquals(10, deque.data.length);
        assertEquals(9, deque.data[deque.rear]);
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
     * Test MyStack when pushing when the list is at capacity, popping when the 
     * front is at the list end
     */
    @Test
    public void testMyStack(){
        // You can test any method from MyStack or a combination of methods
        MyStack<Integer> stack = new MyStack<>(10);
        Integer[] orig = { 1, 2, 3, 4, 5, 6, 7, 8,
                9, 10 };
        initDeque(stack.theStack, orig, 10, 0, 9);

        // test push when list is at capacity
        stack.push(11);
        assertEquals(11, stack.theStack.size());
        assertEquals(20, stack.theStack.data.length);
        assertEquals(19, stack.theStack.front);
        assertEquals(9, stack.theStack.rear);
        assertEquals(11, stack.theStack.data[19]);
        for (int i = 0; i < 9; i++) {
                assertEquals(i + 1, stack.theStack.data[i]);
        }

        // test pop method when front is last element in list
        assertEquals(11, stack.pop().intValue());
        assertEquals(0, stack.theStack.front);
        assertEquals(9, stack.theStack.rear);
        assertEquals(10, stack.size());
        assertEquals(20, stack.theStack.data.length);

    }

    // ----------------MyQueue class----------------
    /**
     * Test MyQueue when enqueueing when list is at capacity
     */
    @Test
    public void testMyQueue(){
        MyQueue<Integer> queue = new MyQueue<>(10);
        Integer[] orig = { 1, 2, 3, 4, 5, 6, 7, 8,
                9, 10 };
        initDeque(queue.theQueue, orig, 10, 0, 9);

        queue.enqueue(11);
        assertEquals(11, queue.size());
        assertEquals(20, queue.theQueue.data.length);
        assertEquals(19, queue.theQueue.front);
        assertEquals(9, queue.theQueue.rear);
        assertEquals(11, queue.theQueue.data[19]);
        for (int i = 0; i < 9; i++) {
                assertEquals(i + 1, queue.theQueue.data[i]);
        }

        assertEquals(11, queue.dequeue().intValue());
        assertEquals(10, queue.size());
        assertEquals(0, queue.theQueue.front);
        assertEquals(9, queue.theQueue.rear);
        assertEquals(null, queue.theQueue.data[19]);
                
    }
}
