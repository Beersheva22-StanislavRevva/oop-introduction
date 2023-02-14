package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;



public class LinkedHashSet<T> extends AbstractCollection<T> implements Set<T> {

	private Map<T, Node<T>> linkedHashSet;
	private T head;
	private T tail;
	
		private static class Node<T> {
			T prev;
			T next;

			Node(T next, T prev) {
				this.next = next;
				this.prev = prev;
			}
		}
	
	private class LinkedHashSetIterator implements Iterator<T> {
		private T current = head;
		private boolean flNext;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T res = current;
			current = linkedHashSet.get(current).next;
			flNext = true;
			return res;
		}
		
		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			T removed = current == null ? tail : linkedHashSet.get(current).prev;
			LinkedHashSet.this.remove(removed);
			flNext = false;
		}
			
	}
	
	public LinkedHashSet() {
		linkedHashSet = new HashMap<>();
	}
	
	@Override
	public boolean add(T element) {
		if (linkedHashSet.containsKey(element)) {
			return false;
		} if (isEmpty()) {
			head = tail = element;
			linkedHashSet.put(element, new Node<T>(null,null));
		}
		linkedHashSet.put(element, new Node<T>(null,tail));
		linkedHashSet.get(tail).next = element;
		tail = element;
		return true;
	}

	@Override
	public boolean remove(T pattern) {
		Node<T> removed = linkedHashSet.remove(pattern);
		if (removed != null) {
			T next = removed.next;
			T prev = removed.prev;
			if (head == pattern) {
				head = next;
			} else {linkedHashSet.get(prev).next = next;
					}
			if (tail == pattern) {
					tail = prev;
			} else {linkedHashSet.get(next).prev = prev;
					}
		}
		return removed != null;
	}

	@Override
	public boolean contains(T pattern) {
		return  linkedHashSet.containsKey(pattern);
		}

	@Override
	public Iterator<T> iterator() {
		
		return new LinkedHashSetIterator();
	}
	
	@Override
	public int size() {
		return linkedHashSet.size();
	}

	public boolean isEmpty() {
		return linkedHashSet.isEmpty();
	}

	@Override
	public T get(T pattern) {
				return null;
	}

}