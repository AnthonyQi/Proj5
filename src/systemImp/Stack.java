package systemImp;

/**
 * A class that implements a Stack using your custom Doubly Linked List.  All operations must be O(1).
 */
public class Stack<T extends Comparable<T>> {
    private DoublyLinkedList<T> list; // The doubly linked list that will back the stack.

    public Stack() {
    	this.list = new DoublyLinkedList<T>();
    }
 
    //adds to last
    public void push(T data) {
    	list.addLast(data);
    }

    //removes last
    public T pop() {
    	if(isEmpty()) {
    		throw new IllegalStateException("Empty");
    	}
    	T temp = list.getLast();
    	list.removeLast();
    	return temp;
    }
    
    //returns front element
    public T peek() {
    	if(isEmpty()) {
    		throw new IllegalStateException("Empty");
    	}
    	T temp = list.getLast();
    	return temp;
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

