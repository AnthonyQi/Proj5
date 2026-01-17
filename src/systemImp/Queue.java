package systemImp;

/**
 * A class that implements a Queue using your custom Doubly Linked List.
 * All operations must be O(1).
 */
public class Queue<T extends Comparable<T>> {
    private DoublyLinkedList<T> list; // The doubly linked list that backs the queue.

    public Queue() {
    	this.list = new DoublyLinkedList<T>();
    }
    
    //adds to rear
    public void enqueue(T data) {
    	list.addLast(data);
    }
   
    //removes front
    public T dequeue() {
    	if(isEmpty()) {
    		throw new IllegalStateException("Empty");
    	}
    	T temp = list.getFirst();
    	list.removeFirst();
    	return temp;
    }
    
    //returns front element
    public T peek() {
    	if(isEmpty()) {
    		throw new IllegalStateException("Empty");
    	}
    	T temp = list.getFirst();
    	return temp;
    }
    
    //checks size == zero
    public boolean isEmpty() {
    	return list.getSize() == 0;
    }
    
    //takes advantage of doubly linked lists getSize method
    public int size() {
    	return list.getSize();
    }
}

