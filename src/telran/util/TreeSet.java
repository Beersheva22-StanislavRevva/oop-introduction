package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeSet<T> extends AbstractCollection<T> implements Sorted<T> {
   static private class Node<T> {
	   T obj;
	   Node<T> parent;
	   Node<T> left;
	   Node<T> right;
	   Node(T obj) {
		   this.obj = obj;
	   }
   }
   private class TreeSetIterator implements Iterator<T> {
	   Node<T> current = root;
	   TreeSetIterator() {
		   if (current != null) {
			   current = getLeastNode(current);
		   }
	   }
	@Override
	public boolean hasNext() {
		
		return current != null;
	}

	@Override
	public T next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		T res = current.obj;
		current = getNextCurrent(current);
		return res;
	}
	   
   }
   private Node<T> root;
   private Comparator<T> comp;
   public TreeSet(Comparator<T> comp) {
	   this.comp = comp;
   }
   private Node<T> getNextCurrent(Node<T> current) {
	
	return current.right == null ? getGreaterParent(current) : getLeastNode(current.right);
}
private Node<T> getGreaterParent(Node<T> current) {
	while(current.parent != null && current.parent.left != current) {
		current = current.parent;
	}
	return current.parent;
}
private Node<T> getLeastNode(Node<T> current) {
	while(current.left != null) {
		current = current.left;
	}
	return current;
}
@SuppressWarnings("unchecked")
public TreeSet() {
	   this((Comparator<T>) Comparator.naturalOrder());
   }
	@Override
	public boolean add(T element) {
		boolean res = false;
		Node<T> parent = getNode(element);
		int compRes = 0;
		if(parent == null || (compRes = comp.compare(element, parent.obj)) != 0) {
			res  = true;
			size++;
			Node<T> node = new Node<>(element);
			node.parent = parent;
			if(parent == null) {
				root = node;
			} else {
				if (compRes < 0) {
					parent.left = node;
				} else {
					parent.right = node;
				}
			}
		}
		
		return res;
	}

	private Node<T> getNode(T element) {
		Node<T> current = root;
		Node<T> parent = null;
		int compRes;
		while(current != null && (compRes = comp.compare(element, current.obj)) != 0) {
			parent = current;
			current = compRes < 0 ? current.left : current.right;
		}
		return current == null ? parent : current;
	}
	@Override
	public boolean remove(T pattern) {
		// Not implemented yet
		return false;
	}

	@Override
	public boolean contains(T pattern) {
		Node<T> node = getNode(pattern);
		return node != null && comp.compare(pattern, node.obj) == 0;
	}

	@Override
	public Iterator<T> iterator() {
		
		return new TreeSetIterator();
	}
	@Override
	public T floor(T element) {
		Node<T> current = root;
		Node<T> parent = null;
		if (comp.compare(element, first()) < 0) {
			return null;
		}
		
		while (current != null) {
			int compRes = comp.compare(element, current.obj);
			if (compRes == 0) {
			return current.obj;
			}
			if (compRes > 0) {
				parent = current;
				current = current.right;
			} else {
				parent = current;
				current = current.left;
			}
		}
			
		return comp.compare(element, parent.obj) > 0 ? parent.obj : parent.parent.obj;
	}
		
	@Override
	public T ceiling(T element) {
		Node<T> current = root;
		Node<T> parent = null;
		if (comp.compare(element, last()) > 0) {
			return null;
		}
		
		while (current != null) {
			int compRes = comp.compare(element, current.obj);
			if (compRes == 0) {
			return current.obj;
			}
			if (compRes < 0) {
				parent = current;
				current = current.left;
			} else {
				parent = current;
				current = current.right;
			}
		}
			
		return comp.compare(element, parent.obj) < 0 ? parent.obj : parent.parent.obj;
	}
	@Override
	public T first() {
		return root != null ? getLeastNode(root).obj : null;
	}
	@Override
	public T last() {
		return root != null ? getGreatestNode(root).obj : null;
	}
	private Node<T> getGreatestNode(Node<T> current) {
		while(current.right != null) {
			current = current.right;
		}
		return current;
	}

}