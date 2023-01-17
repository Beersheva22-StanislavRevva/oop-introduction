package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import telran.util.LinkedList.Node;

public class TreeSet<T> extends AbstractCollection<T> implements Set<T> {
   public static class Node<T> {
	   public T obj;
	   Node<T> parent;
	   Node<T> left;
	   Node<T> right;
	   Node(T obj) {
		   this.obj = obj;
	   }
   }
   private class TreeSetIterator implements Iterator<T> {
	public Node<T> current = getMinElement();
	
	@Override
	public boolean hasNext() {
		return current != null;
	}

	private Node<T> getMinElement() {
		Node<T> res = root;
		while (res.left != null) {
		res = res.left;
		}
		return res;
	}

	@Override
	public T next() {
	T res = current.obj;	
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		
		if (current.right == null) {
			current = current.parent;
			if (comp.compare(res, current.obj) > 0) {
				current = null;
			}
		} else { current = current.right;
				while(current.right != null && current.left != null) {
					current = current.left;
				}
			}
		return res;
	}
	   
   }
   public Node<T> root;
   private Comparator<T> comp;
   public TreeSet(Comparator<T> comp) {
	   this.comp = comp;
   }
   @SuppressWarnings("unchecked")
public TreeSet() {
	   this((Comparator<T>) Comparator.naturalOrder());
   }
	@Override
	public boolean add(T element) {
		boolean isLeft = false;
		if (root == null) {
			root = new Node<>(element);
			return true;
		}
		Node<T> current = root;
		Node<T> prev = null;
		while (current != null) {
			if (comp.compare(element, current.obj) == 0) {
				return false;
			}
			prev = current;
			if (comp.compare(element, current.obj) < 0) {
				current = current.left;	
				isLeft = true;
				
			} else {current = current.right;
					isLeft = false;
			}
			
		}
		Node<T> newNode = new Node<>(element);
		if (isLeft) {
			current = prev;
			current.left = newNode;
		} else {current = prev;
				current.right = newNode;
			}
		newNode.parent = current;
		
		return true;
	}

	@Override
	public boolean remove(T pattern) {
		// Not implemented yet
		return false;
	}

	@Override
	public boolean contains(T pattern) {
		if (add (pattern) == false) {
		return true;	
		} else return false;
	}

	@Override
	public Iterator<T> iterator() {
		
		return new TreeSetIterator();
	}

}