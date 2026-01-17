package tests;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import systemImp.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class StudentTests {
	
	//Testing DoublyLinkedList Class
	
	@Test
	public void testingGetSize() {
		DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
		//testing when empty
		assertEquals(0, dll.getSize());
		//adding some elements
		dll.addFirst(1);
		dll.addFirst(2);
		dll.addFirst(3);
		dll.addFirst(4);
		assertEquals(4, dll.getSize());
		//testing when adding to last
		dll.addLast(0);
		dll.addLast(-1);
		dll.addLast(-2);
		dll.addLast(-3);
		assertEquals(8, dll.getSize());
		dll.removeFirst();
		assertEquals(7, dll.getSize());
		dll.removeLast();
		assertEquals(6, dll.getSize());
		//new object to test when removing some elements
		DoublyLinkedList<Integer> test = new DoublyLinkedList<>();
		test.addFirst(3);
		test.addFirst(1);
		test.addFirst(5);
		test.addLast(4);
		test.addLast(10);
		test.addLast(0);
		test.removeAllInRange(3, 5);
		assertEquals(3, test.getSize());
	}
	
	@Test
	public void testingAddAndGet() {
		Integer one = 1;
		Integer two = 2;
		Integer three = 3;
		DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
		//first checking when empty, for head
		try {
			dll.getFirst();
		} catch(Exception e) {
			String answer = "List is empty!";
			assertEquals(answer, e.getMessage());
		}
		//checking for tail
		try {
			dll.getLast();
		} catch(Exception e) {
			String answer = "List is empty!";
			assertEquals(answer, e.getMessage());
		}
		//testing when adding to tail when list is empty. head == tail
		dll.addLast(three);
		assertEquals(dll.getFirst(), dll.getLast());
		//testing when addingFirst
		dll.addFirst(one);
		dll.addFirst(two);
		assertEquals(two, dll.getFirst());
		
		//checking when elements are null
		try {
			dll.addFirst(null);
		} catch(Exception e) {
			String answer = "Elements cannot be null";
			assertEquals(answer, e.getMessage());
		}
		try {
			dll.addLast(null);
		} catch(Exception e) {
			String answer = "Elements cannot be null";
			assertEquals(answer, e.getMessage());
		}
	}
	
	@Test
	public void testingRemove() {
		Integer one = 1;
		Integer two = 2;
		Integer three = 3;
		DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
		try {
			dll.removeFirst();
		} catch(Exception e) {
			String answer = "List is empty!";
			assertEquals(answer, e.getMessage());
		}
		//checking for tail
		try {
			dll.removeLast();
		} catch(Exception e) {
			String answer = "List is empty!";
			assertEquals(answer, e.getMessage());
		}
		dll.addFirst(three);
		dll.addFirst(two);
		dll.addFirst(one);
		//the order should be 1 -> 2 -> 3
		//removing first test
		String ans1 = "[2, 3]";
		dll.removeFirst();
		assertEquals(ans1, dll.toString());
		//removing last test
		dll.removeLast();
		String ans2 = "[2]";
		assertEquals(ans2, dll.toString());
		//does removing last and removing first have any change different
		//if the element is addFirst or addLast
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		list.addFirst(one);
		list.removeLast();
		assertEquals("[]", list.toString());
		list.addLast(three);
		list.removeFirst();
		assertEquals("[]", list.toString());
		//now testing allInRange
		//first testing when null data points are passed through
		String error = "Elements cannot be null";
		String err = "No such element exists";
		try {
			list.removeAllInRange(null, null);
		} catch(Exception e) {
			assertEquals(error, e.getMessage());
		}
		//testing when empty
		try {
			list.removeAllInRange(0, 2);
		} catch(Exception e) {
			assertEquals(err, e.getMessage());
		}
		//adding elements
		list.addFirst(1);
		list.addLast(5);
		list.addFirst(-2);
		list.addLast(4);
		list.addFirst(9);
		list.addLast(11);
		list.addFirst(5);
		list.addLast(7);
		list.addFirst(2);
		list.addLast(-11);
		list.addFirst(-1);
		list.addLast(0);
		//removing between -1 and 7 inclusive
		list.removeAllInRange(-1, 7);
		//should remove 1 5 4 5 7 2 -1 0
		String answer = "[9, -2, 11, -11]";
		assertEquals(answer, list.toString());
	}
	
	@Test
	public void testingToString() {
		DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
		dll.addFirst(1);
		dll.addFirst(2);
		dll.addFirst(3);
		dll.addFirst(4);
		dll.addLast(0);
		dll.addLast(-1);
		dll.addLast(-2);
		dll.addLast(-3);
		dll.removeFirst();
		
	}
	
	@Test
	public void testingIterator() {
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		Iterator<String> iter = list.iterator();
		//checking has next, when empty. getting next when empty
		assertFalse(iter.hasNext());
		String err1 = "No such element exists";
		try {
			iter.next();
		} catch(Exception e) {
			assertEquals(err1, e.getMessage());
		}
		list.addLast("He");
		list.addLast("He");
		list.addFirst("He");
		list.addLast("Ha");
		list.addFirst("Tralalero Tralala");
		Iterator<String> iterator = list.iterator();
		//checking has next and getting next
		//making sure when calling next it returns the current and moves to the
		//next one.
		String str = "Tralalero Tralala";
		assertEquals(str, list.getFirst());
		assertTrue(iterator.hasNext());
		assertEquals(str, iterator.next());
		//testing remove, to see if it removes the current one
		iterator.remove();
		assertEquals("He", list.getFirst());
		//testing when next is not called
		try {
			iterator.remove();
		} catch(Exception e) {
			String err2 = "Next must be called again";
			assertEquals(err2, e.getMessage());
		}
	}
	
	@Test
	public void testingInsertionSort() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		//if already sorted
		list.addLast(1);
		list.addLast(2);
		list.addLast(3);
		list.addLast(4);
		list.addLast(5);
		String ans1 = "1 | 2 3 4 5 \n"
				+ "1 2 | 3 4 5 \n"
				+ "1 2 3 | 4 5 \n"
				+ "1 2 3 4 | 5 \n"
				+ "1 2 3 4 5 | \n";
		assertEquals(ans1, list.insertionSort());
		assertEquals("[1, 2, 3, 4, 5]", list.toString());
		//testing in worst case scenario
		list.removeAllInRange(1, 5);
		assertTrue(list.getSize() == 0);
		assertEquals("[]", list.insertionSort());
		list.addFirst(1);
		list.addFirst(2);
		list.addFirst(3);
		list.addFirst(4);
		list.addFirst(5);
		String ans2 = "5 | 4 3 2 1 \n"
				+ "4 5 | 3 2 1 \n"
				+ "3 4 5 | 2 1 \n"
				+ "2 3 4 5 | 1 \n"
				+ "1 2 3 4 5 | \n";
		assertEquals(ans2, list.insertionSort());
		assertEquals("[1, 2, 3, 4, 5]", list.toString());
	}
	
	//Testing Deque Class
	
	@Test
    public void testingDeque() {
        Deque<Integer> deque = new Deque<>();

        //testing empty
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());

        //testing adding
        Integer ten = 10;
        deque.addFirst(ten);
        assertFalse(deque.isEmpty());
        assertEquals(1, deque.size());
        assertEquals(ten, deque.peekFirst());
        assertEquals(ten, deque.peekLast());

        Integer twenty = 20;
        deque.addLast(twenty);
        assertEquals(2, deque.size());
        assertEquals(ten, deque.peekFirst());
        assertEquals(twenty, deque.peekLast());

        Integer five = 5;
        deque.addFirst(five);
        assertEquals(3, deque.size());
        assertEquals(five, deque.peekFirst());
        assertEquals(twenty, deque.peekLast());

        Integer thirty = 30;
        deque.addLast(thirty);
        assertEquals(4, deque.size());
        assertEquals(five, deque.peekFirst());
        assertEquals(thirty, deque.peekLast());

        //testing removing
        assertEquals(five, deque.removeFirst());
        assertEquals(3, deque.size());
        assertEquals(ten, deque.peekFirst());

        assertEquals(thirty, deque.removeLast());
        assertEquals(2, deque.size());
        assertEquals(twenty, deque.peekLast());

        assertEquals(ten, deque.removeFirst());
        assertEquals(1, deque.size());
        assertEquals(twenty, deque.peekFirst());

        assertEquals(twenty, deque.removeFirst());
        assertTrue(deque.isEmpty());
        //testing exceptions
        try {
            deque.removeFirst();
        } catch (IllegalStateException e) {
            assertEquals("Empty", e.getMessage());
        }

        try {
            deque.removeLast();
        } catch (IllegalStateException e) {
            assertEquals("Empty", e.getMessage());
        }

        try {
            deque.peekFirst();
        } catch (IllegalStateException e) {
            assertEquals("Empty", e.getMessage());
        }

        try {
            deque.peekLast();
        } catch (IllegalStateException e) {
            assertEquals("Empty", e.getMessage());
        }
    }
	//Testing Queue Class
	
	@Test
	public void testingQueue() {
		Queue<Integer> queue = new Queue<>();

        //checking isEmpty() on an empty queue
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());

        //adding elements to rear
        Integer ten = 10;
        queue.enqueue(ten);
        assertFalse(queue.isEmpty());
        assertEquals(1, queue.size());
        assertEquals(ten, queue.peek());

        Integer twenty = 20;
        queue.enqueue(twenty);
        assertEquals(2, queue.size());
        assertEquals(ten, queue.peek());

        Integer five = 5;
        queue.enqueue(five);
        assertEquals(3, queue.size());
        assertEquals(ten, queue.peek());

        //removing elements from front
        assertEquals(ten, queue.dequeue());
        assertEquals(2, queue.size());
        assertEquals(twenty, queue.peek());

        assertEquals(twenty, queue.dequeue());
        assertEquals(1, queue.size());
        assertEquals(five, queue.peek());

        assertEquals(five, queue.dequeue());
        assertTrue(queue.isEmpty());

        //testing exceptions
        try {
            queue.dequeue();
        } catch (IllegalStateException e) {
            assertEquals("Empty", e.getMessage());
        }

        try {
            queue.peek();
        } catch (IllegalStateException e) {
            assertEquals("Empty", e.getMessage());
        }
	}
	
	//Testing Stack Class
	
	@Test
	public void testingStack() {
		Stack<Integer> stack = new Stack<>();

        //testing empty
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());

        //adding elements to top
        Integer ten = 10;
        stack.push(ten);
        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());
        assertEquals(ten, stack.peek());

        Integer twenty = 20;
        stack.push(twenty);
        assertEquals(2, stack.size());
        assertEquals(twenty, stack.peek());

        Integer five = 5;
        stack.push(five);
        assertEquals(3, stack.size());
        assertEquals(five, stack.peek());

        //removing elements from top
        assertEquals(five, stack.pop());
        assertEquals(2, stack.size());
        assertEquals(twenty, stack.peek());

        assertEquals(twenty, stack.pop());
        assertEquals(1, stack.size());
        assertEquals(ten, stack.peek());

        assertEquals(ten, stack.pop());
        System.out.print(stack.size());
        assertTrue(stack.isEmpty());

        //testing exceptions
        try {
            stack.pop();
        } catch (Exception e) {
            assertEquals("Empty", e.getMessage());
        }

        try {
            stack.peek();
        } catch (Exception e) {
            assertEquals("Empty", e.getMessage());
        }
	}
}