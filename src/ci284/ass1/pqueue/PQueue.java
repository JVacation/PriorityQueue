/**
	 PQueue - James Holliday
	 
	 Complexity of Insert Method
	 
	 When it comes to a method/algorithm it is important to take how complex it is
	 into mind. there are usually 6 things which effect the run time of an method
	 and these are: the hardware platform, representation of abstract data types,
	 efficiency of compiler, competence of implementer (the way the method is written),
	 complexity of underlying algorithms and the size of the input. The underlying
	 algorithm and the size of the input are usually the most significant. My insert
	 method works like a Binary Search and has a best case performance of O(1),
	 a worst case performance of O(log n) and an average case performance of O(log n).
	 
	 The difference between space and time complexity is that Time quantifies the 
	 amount of time taken by an algorithm to run as a function of the length of the
	 input. Likewise Space complexity quantifies the amount of space or memory taken
	 by an algorithm to run as a function of the length of the input.
	 
	 When it comes to arrays and linked lists a linked list is much more faster when 
	 it comes to adding or removing elements however Arrays are much faster are 
	 locating and returning an element in the middle. When it comes to space Array 
	 also may waste space as you may need to re-declare and copy memory if the array
	 gets too big where as with a linked list this is not needed. So overall the 
	 complexity would most likely increase if I used an array instead of a linked list.
	 
	 
	 */

package ci284.ass1.pqueue;

public class PQueue<T> {

	private PQueueItem<T> head;

	public static enum ORDER {
		ASC, DESC;
	}

	public static ORDER DEFAULT_ORDER;
	private ORDER order;

	/**
	 * The default constructor for a PQueue, with the default order for priorities
	 */
	public PQueue() {
		order = DEFAULT_ORDER;
		head = null;
	}

	/**
	 * The constructor to make a new PQueue with a given order
	 * 
	 * @param order
	 */
	public PQueue(ORDER order) {
		this.order = order;
		head = null;
	}

	/**
	 * Remove and return data from the item at the front of the queue
	 * 
	 * @return
	 */
	public T pop() {
		PQueueItem<T> e1 = head;
		if (head == null) {
			return null;
		} else {
			head = head.getNext();
			return (e1.getData());
		}
	}

	/**
	 * Return the data from the item at the front of the queue, without removing the
	 * item itself
	 * 
	 * @return
	 */
	public T peek() {
		if (head == null) {
			return null;
		} else {
			return (head.getData());
		}

	}

	/**
	 * Remove and return the item at the front of the queue
	 * 
	 * @return
	 */
	public PQueueItem<T> popItem() {
		PQueueItem<T> e1 = head;
		if (head == null) {
			return null;
		} else {
			head = head.getNext();
			return (e1);
		}
	}

	/**
	 * Return the item at the front of the queue without removing it
	 * 
	 * @return
	 */
	public PQueueItem<T> peekItem() {

		if (head == null) {
			return null;
		} else {

			return (head);
		}
	}

	/**
	 * Insert a new item into the queue, which should be put in the right place
	 * according to its priority. That is, is order == ASC, then the new item should
	 * appear in the queue before all items with a HIGHER priority. If order ==
	 * DESC, then the new item should appear in the queue before all items with a
	 * LOWER priority.
	 * 
	 * @param data
	 * @param priority
	 */

	public void insert(T data, int priority) {
		PQueueItem<T> pq = new PQueueItem<T>(data, priority);
		if (head == null) {
			head = pq;
			pq.setNext(null);

		} else if (order == ORDER.ASC) {
			PQueueItem<T> next = head;
			PQueueItem<T> prev = next;
			do {
				if (priority < next.getPriority()) {
					break;
				}
				prev = next;
				next = next.getNext();
			} while (next != null);
			pq.setNext(next);
			if (pq.getPriority() < head.getPriority()) {
				head = pq;
			} else
				prev.setNext(pq);

		} else if (order == ORDER.DESC || order == DEFAULT_ORDER) {
			PQueueItem<T> next = head;
			PQueueItem<T> prev = next;
			do {
				if (priority > next.getPriority()) {
					break;
				}
				prev = next;
				next = next.getNext();
			} while (next != null);
			pq.setNext(next);
			if (pq.getPriority() > head.getPriority()) {
				head = pq;
			} else
				prev.setNext(pq);

		}
	}

	/**
	 * Return the length of the queue
	 * 
	 * @return
	 */
	public int length() {
		PQueueItem<T> e1 = head;
		if (e1 == null) {
			return 0;
		}
		int size = 0;
		while (e1 != null) {
			size++;
			e1 = e1.getNext();
		}
		return size;
	}

	public String toString() {
		int i = length();
		PQueueItem<T> current = head;
		StringBuffer sb = new StringBuffer();
		while (i > 0) {
			sb.append(current.toString());
			if (i > 1)
				sb.append(": ");
			current = current.getNext();
			i--;
		}
		return sb.toString();
	}

}
