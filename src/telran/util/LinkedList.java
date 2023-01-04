package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class LinkedList<T> implements List<T> {
	private static class Node<T> {
		T obj;
		Node<T> prev;
		Node<T> next;

		Node(T obj) {
			this.obj = obj;
		}
	}

	private Node<T> head;
	private Node<T> tail;
	private int size;
	private class LinkedListIterator implements Iterator<T> {
	Node<T> current = head;
		@Override
		public boolean hasNext() {
			return current != null ? true : false;
			}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			T res = current.obj;
			current = current.next;
			return res;
		}

	}

	@Override
	public boolean add(T element) {
		Node<T> node = new Node<>(element);
		if (head == null) {
			head = tail = node;
		} else {
			tail.next = node;
			node.prev = tail;
			tail = node;
		}

		size++;
		return true;
	}

	@Override
	public boolean remove(T pattern) {
		boolean res = false;
		int index = indexOf(pattern);
		if (index > -1) {
			res = true;
			remove(index);
		}
		
		return res;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		int oldSize = size;
		Node<T> current = tail;
		for (int i = size - 1; i >= 0; i--) {
			if (predicate.test(current.obj)) {
				remove(i);
			}
			current = current.prev;
		}
		return oldSize > size;
		
	}

	@Override
	public boolean isEmpty() {
		
		return size == 0;
	}

	@Override
	public int size() {
		
		return size;
	}

	@Override
	public T[] toArray(T[] ar) {
		if(ar.length < size) {
			ar = Arrays.copyOf(ar, size);
		}
		Node<T> current = head;
		for(int i = 0; i < size; i++) {
			ar[i] = current.obj;
			current = current.next;
		}
		Arrays.fill(ar, size, ar.length, null);
		return ar;
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}

	@Override
	public void add(int index, T element) {
		checkIndex(index, true);
		if (index == size) {
			add(element);
		} else if(index == 0) {
			addHead(element);
		} else {
			addMiddle(index, element);
		}

	}

	private void addMiddle(int index, T element) {
		Node<T> node = new Node<>(element);
		Node<T> nodeIndex = getNode(index);
		Node<T> nodePrev = nodeIndex.prev;
		nodePrev.next = node;
		node.prev = nodePrev;
		nodeIndex.prev = node;
		node.next = nodeIndex;
		size++;
		
	}

	private Node<T> getNode(int index) {
		
		return index < size / 2 ? getNodeFromLeft(index) : getNodeFromRight(index);
	}

	private Node<T> getNodeFromRight(int index) {
		Node<T> current = tail;
		for(int i = size - 1; i > index; i--) {
			current = current.prev;
		}
		return current;
	}

	private Node<T> getNodeFromLeft(int index) {
		Node<T> current = head;
		for(int i = 0; i < index; i++) {
			current = current.next;
		}
		return current;
	}

	private void addHead(T element) {
		Node<T> node = new Node<>(element);
		node.next = head;
		head.prev = node;
		head = node;
		size++;
		
	}

	@Override
	public T remove(int index) {
		checkIndex(index, true);
		T res;
		if (index == size - 1) {
			res = removeTail();
		} else if(index == 0) {
			res = removeHead();
		} else {
			res = removeMiddle(index);
		}
		return res;
	}

	private T removeMiddle(int index) {
		Node<T> nodeIndex = getNode(index);
		T res = nodeIndex.obj;
		Node<T> nodePrev = nodeIndex.prev;
		Node<T> nodeNext = nodeIndex.next;
		nodePrev.next = nodeNext;
		nodeNext.prev = nodePrev;
		size--;
		return res;
	}

	private T removeHead() {
		T res = head.obj;
		head = head.next;
		size--;
		return res;
	}

	private T removeTail() {
		T res = tail.obj;
		tail = tail.prev;
		size--;
		return res;
	}

	@Override
	public int indexOf(T pattern) {
		int index = 0;
		Node<T> current = head;
		while(index < size && !isEqual(current.obj, pattern)) {
			index++;
			current = current.next;
		}
		return index < size ? index : -1;
	}
	
	private boolean isEqual(T element, T pattern) {
		
		return element == null  ? element == pattern : element.equals(pattern);
	}
	

	@Override
	public int lastIndexOf(T pattern) {
		int index = size - 1;
		Node<T> current = tail;
		while(index >= 0 && !isEqual(current.obj, pattern)) {
			index--;
			current = current.prev;
		}
		return index;
	}

	@Override
	public T get(int index) {
		checkIndex(index, false);
		return getNode(index).obj;
	}

	@Override
	public void set(int index, T element) {
		checkIndex(index, false);
		Node<T> node = getNode(index);
		node.obj = element;
		

	}

}