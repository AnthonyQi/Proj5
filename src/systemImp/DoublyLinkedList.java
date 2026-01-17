package systemImp;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A doubly linked list implementation for storing elements.
 * This list supports basic operations like adding and removing elements at both ends.
 * @class Inner class description.
 * @param <E> The type of elements in this list, which must extend Comparable.
 */
public class DoublyLinkedList<E extends Comparable<E>> implements Iterable<E> {
    private Node head; //points to the first node
    private Node tail; //points to the last node
    private int size;  //number of elements in the list

    /**
     * Node represents a single element in the doubly linked list.
     * It contains references to the data and the next and previous nodes in the list.
     */
    private class Node {
        E data;
        Node next, prev;

        /**
         * Constructor for creating a new node.
         * 
         * @param data The data to be stored in the node.
         */
        Node(E data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }
    
    public DoublyLinkedList() {
    	this.head = null;
    	this.tail = null;
    	this.size = 0;
    }
    
    public int getSize() {
    	return this.size;
    }
 
    public E getFirst() {
    	if(this.head == null) {
    		throw new NoSuchElementException("List is empty!");
    	}
    	return head.data;
    }
    
    public E getLast() {
    	if(this.tail == null) {
    		throw new NoSuchElementException("List is empty!");
    	}
    	return tail.data;
    }
    
    public void addFirst(E e) {
    	if(e == null) {
    		throw new NullPointerException("Elements cannot be null");
    	}
    	Node temp = new Node(e);
    	if(head == null) {
    		head = temp;
    		tail = head;
    	}
    	else {
    		temp.next = head;
    		head.prev = temp;
    		head = temp;
    	}
    	size+=1;
    }
   
    public void addLast(E e) {
    	if(e == null) {
    		throw new NullPointerException("Elements cannot be null");
    	}
    	Node temp = new Node(e);
    	if(tail == null) {
    		head = temp;
    		tail = head;
    	}
    	else {
    		temp.prev = tail;
    		tail.next = temp;
    		tail = temp;
    	}
    	size+=1;
    }
    
    public void removeFirst() {
    	if(this.head == null) {
    		throw new NoSuchElementException("List is empty!");
    	}
    	if(head == tail) {
    		head = null;
    		tail = null;
    		size = 0;
    		return;
    	}
    	if (head == null) {
            tail = null;
        }
    	else {
        	head = head.next;
        	head.prev = null;
    	}
    	size-=1;
    }
    
    public void removeLast() {
    	if(this.head == null) {
    		throw new NoSuchElementException("List is empty!");
    	}
    	if(head == tail) {
    		head = null;
    		tail = null;
    		size = 0;
    		return;
    	}
    	if (tail == null) {
            tail = null;
        }
    	else {
    		tail = tail.prev; 
    	    tail.next = null;
    	}
    	size-=1;
    }
    
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	Node temp = head;
    	sb.append("[");
    	while(temp != null) {
    		if(temp != tail) {
    			sb.append(temp.data).append(", ");
    		}
    		else {
    			sb.append(temp.data);
    		}
    		temp = temp.next;
    	}
    	sb.append("]");
    	return sb.toString();
    }

    public void removeAllInRange(E start, E end) {
    	if(start == null || end == null) {
    		throw new NullPointerException("Elements cannot be null");
    	}
    	Node curr = head;
    	boolean removed = false;
    	while(curr != null) {
    		if(curr.data.compareTo(start) >= 0 
    				&& curr.data.compareTo(end) <= 0)  {
    			Node remove = curr;
    			curr = curr.next;
    			if (remove == head) {
    				removeFirst();
                }
                else if (remove == tail) {
                	removeLast();
                } 
                else {
                    remove.prev.next = remove.next;
                    remove.next.prev = remove.prev;
        			size-=1;
                }
    			removed = true;
            } 
            else {
                curr = curr.next;
            }
    	}
    	if(!removed) {
    		throw new NoSuchElementException("No such element exists");
    	}
    	if (head == null) {
            tail = null;
        }
    }
    
	@Override
	public Iterator<E> iterator() {
		Iterator<E> iter = new Iterator<E>() {
			private Node current = head;
			private Node lastReturned = null;
			@Override
			public boolean hasNext() {
				return current != null;
			}
			
			@Override
			public E next() {
				if(!hasNext()) {
		            throw new NoSuchElementException("No such element exists");
				}
				lastReturned = current;
				current = current.next;
				return lastReturned.data;
			}
			
			@Override
			public void remove() {
				if(lastReturned == null) {
					throw new IllegalStateException
						("Next must be called again");
				}
				//this checks if the previous node is null
				if (lastReturned.prev != null) {
					//if so set the next's address for the previous node to the
					//current node's next address
	                lastReturned.prev.next = lastReturned.next;
	            } else {
	            	//when the if fails, that means the current object is the
	            	//head. set head to the next object.
	                head = lastReturned.next;
	            }
				//this does the same but checking for tail
	            if (lastReturned.next != null) {
	                lastReturned.next.prev = lastReturned.prev;
	            } else {
	                tail = lastReturned.prev;
	            }
	            //resets lastReturned
	            lastReturned = null;
	            size-=1;
			}
		};
		return iter;
	}
	
	public String insertionSort() {
		StringBuilder sb = new StringBuilder();
		//checks size and if linked list is one element
		if(getSize() == 0 || head == tail) {
			return toString();
		}
		//this is a initial unsorted case;
		Node init = head.next;
		sb.append(head.data + " | ");
		while(init != null){
			sb.append(init.data + " ");
			init = init.next;
		}
		sb.append("\n");
		//the current node is the one being cycled through
		Node current = head.next;
		//while loop to traverse through and sort
		while(current != null) {
			Node iter = head;
			Node temp = current.next;
			//checks if the current node's data is smaller 
			//than the previous node's data
			//if not, it moves on, saving time
			if (current.data.compareTo(current.prev.data) < 0) {
	            //unlink current node from its original position
				//this initial if statement checks if the preceding node is not
				//null
	            if(current.prev != null) {
	            	current.prev.next = current.next;
	            }
	            //same thing, instead if the next node is null
	            if(current.next != null) {
	            	current.next.prev = current.prev;
	            }
	            //update head if needed
	            if(current == head) {
	            	head = current.next;
	            }
	            //update tail if needed
	            if(current == tail) {
	            	tail = current.prev;
	            }
	            //find the correct insertion position
	            //loops through to find the correct index
	            while (iter != current && current.data.compareTo(iter.data) > 0) {
	                iter = iter.next;
	            }
	            //insert before iter
	            //inserting at the head
	            if (iter.prev == null) { 
	                current.next = head;
	                head.prev = current;
	                head = current;
	                current.prev = null;
	            } 
	            //general case
	            else {
	                current.prev = iter.prev;
	                current.next = iter;
	                iter.prev.next = current;
	                iter.prev = current;
	            }
	        }
			//traversing through the linked list to update log
			current = temp;
			//log sorting
			iter = head;
			while(iter != null) {
				if(iter.next == current) {
	                sb.append(iter.data + " | ");
				}
				else {
					sb.append(iter.data + " ");
				}
				iter = iter.next;
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
