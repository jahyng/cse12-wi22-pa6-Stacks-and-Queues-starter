import java.lang.invoke.WrongMethodTypeException;
import java.rmi.server.ObjID;

public class MyDeque<E> implements DequeInterface<E>{
    Object[] data;
    int size;
    int rear;
    int front;

    /**
     * constructor for deque
     * @param initialCapacity gives the capacity of the list that is initialized
     */
    public MyDeque(int initialCapacity) {
        // throw exception
        if (initialCapacity < 0) {
            throw new IllegalArgumentException();
        }
        data = new Object[initialCapacity];
    }

    /**
     * gets size of list
     * @return number of elements that exists in deque
     */
    public int size() {
        return this.size;
    }

    /**
     * Doubles current capacity. If current capacity is 0, set capactiy to 
     * 10.
     */
    public void expandCapacity() {
        if (this.size == 0) {
            Object[] temp =  new Object[10];
            for (int i = 0; i < this.size; i++) {
                temp[i] = this.data[i];
            }
            this.data = temp;
        }
        else {
            Object[] temp = new Object[this.data.length * 2];
            int count = 0;
            int secCount = 0;
            while (count < this.size) {
                if (this.front + count > this.data.length -1) {
                    temp[count] = this.data[secCount];
                    secCount++;
                } else temp[count] = this.data[this.front + count];
                count++;
            }
            this.data = temp;
            this.front = 0;
            this.rear = this.size-1;
        }
    }

    /**
     * First check if deque is at capacity. If it is, expand capacity. Add 
     * specified element to the front of deque and update front. Update size.
     */
    public void addFirst(E element) {
        // throw exception
        if (element == null) {
            throw new NullPointerException();
        }

        // check if deque is at capacity
        if (this.size >= this.data.length) this.expandCapacity();

        // add element to front of list
        if (this.front == 0) {
            if (this.size == 0) {
                this.data[0] = element;
            } else {
                this.data[this.data.length-1] = element;
                this.front = this.data.length - 1;
            }
        } else {
            this.data[this.front - 1] = element;
            this.front--;
        }

        // update front and size
        this.size++;


    }

    /**
     * First check if deque is at capacity. If it is, expand capacity. Add 
     * specified element to the end of deque. Update rear and size.
     */
    public void addLast(E element) {
        // throw exception
        if (element == null) {
            throw new NullPointerException();
        }

        // check if deque is at capacity
        if (this.size >= this.data.length) {
            this.expandCapacity();
        }

        // add element to end of deque
        this.data[this.rear + 1] = element;

        // update rear and size
        this.rear++;
        this.size++;
    }

    /**
     * removes and returns element at front of deque. If no element to remove, 
     * return null. Update front and size.
     */
    public E removeFirst() {
        if (this.size != 0) {
            // get element at the front
            Object temp = this.data[this.front];

            // remove element at front
            this.data[this.front] = null;
            
            // check if front is the end of the list
            if (this.front == this.data.length - 1) {
                this.front = 0;
            } else this.front++;

            // step down size
            this.size--;
            return (E) temp;
        } else return null;
    }

    /**
     * Removes and returns element at end of deque. If not element to remove, 
     * return null. Update rear and size.
     */
    public E removeLast() {
        if (this.size != 0) {
            // store last element
            Object temp = this.data[this.rear];

            // remove last element from list
            this.data[this.rear] = null;

            // check if the rear is at front of list
            if (this.rear == 0) this.rear = this.data.length - 1;
            else this.rear--;

            // step down size
            this.size--;
            return (E) temp;
        } else return null;
    }

    /**
     * Returns element at front of list. If no element, return null.
     */
    public E peekFirst() {
        if (this.size != 0) {
            return (E) this.data[this.front];
        }
        return null;
    }

    /**
     * Returns element at end of list. If no element, return null.
     */
    public E peekLast() {
        if (this.size != 0) {
            return (E) this.data[this.rear];
        }
        return null;
    }
}
