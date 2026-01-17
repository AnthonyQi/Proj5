package systemImp;

/**
 * A class that implements a Deque (double-ended queue) using a custom Doubly Linked List.
 * All operations (insertion and removal from both ends) must be O(1).
 */
public class Deque<T extends Comparable<T>> {
    private DoublyLinkedList<T> list; // The doubly linked list backing the deque.
    
    public Deque() {
    	this.list = new DoublyLinkedList<T>();
    }
    
    //uses addfirst from doubly linked list to simulate adding to deque
    public void addFirst(T data) {
    	list.addFirst(data);
    }

    public void addLast(T data) {
    	list.addLast(data);
    }
  
    //sets temp generic to the first object
    //then removes
    public T removeFirst() {
    	if(isEmpty()) {
    		throw new IllegalStateException("Empty");
    	}
    	T temp = list.getFirst();
    	list.removeFirst();
    	return temp;
    }
    
    //sets temp generic to the last object
    //then removes
    public T removeLast() {
    	if(isEmpty()) {
    		throw new IllegalStateException("Empty");
    	}
    	T temp = list.getLast();
    	list.removeLast();
    	return temp;
    }
    
    //straight up returns first node
    public T peekFirst() {
    	if(isEmpty()) {
    		throw new IllegalStateException("Empty");
    	}
    	return list.getFirst();
    }
    
    //returns last node
    public T peekLast() {
    	if(isEmpty()) {
    		throw new IllegalStateException("Empty");
    	}
    	return list.getLast();
    }
    
    //checks size == zero
    public boolean isEmpty() {
    	return list.getSize() == 0;
    }
    
    //takes advantage of doublylinkedlists getsize method
    public int size() {
    	return list.getSize();
    }
}

