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
	   Node<T> prev;
	   boolean flRemove = false;
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
		flRemove = true;
		T res = current.obj;
		prev = current;
		current = getNextCurrent(current);
		return res;
	}
	@Override
	public void remove() {
		if(!flRemove)  {
			throw new IllegalStateException();
		}
		flRemove = false;
		if(isJunction(prev)) {
			current = prev;
		}
		removeNode(prev);
	}
	   
   }
private static final String SYMBOL = " ";
private static final int NUMBER_SYMBOLS_PER_LEVEL = 3;
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
private Node<T> getMostNode(Node<T> current) {
	while(current.right != null) {
		current = current.right;
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
		boolean res = false;
		Node<T> removedNode = getNode(pattern);
		if (removedNode != null && comp.compare(pattern, removedNode.obj) == 0) {
			res = true;
			removeNode(removedNode);
		}
		return res;
	}

	private void removeNode(Node<T> node) {
		if(isJunction(node)) {
			removeNodeJunction(node);
		} else {
			removeNodeNonJunction(node);
		}
		size--;
		
	}
	private boolean isJunction(Node<T> node) {
		return node.left != null && node.right != null;
	}
	private void removeNodeNonJunction(Node<T> node) {
		Node<T> parent = node.parent;
		Node<T> child = node.left == null ? node.right : node.left;
		if (parent == null) {
			root = child;
		} else {
			if (parent.left == node) {
				parent.left = child;
			} else {
				parent.right = child;
			}
		}
		if (child != null) {
			child.parent = parent;
		}
		
	}
	private void removeNodeJunction(Node<T> node) {
		Node<T> substitution = getLeastNode(node.right);
		node.obj = substitution.obj;
		removeNodeNonJunction(substitution);
		
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
		
		return floorCeiling(element, true);
	}
	@Override
	public T ceiling(T element) {
		
		return floorCeiling(element, false);
	}
	@Override
	public T first() {
		T res = null;
		if (root != null) {
			res = getLeastNode(root).obj;
		}
		return res;
	}
	@Override
	public T last() {
		T res = null;
		if (root != null) {
			res = getMostNode(root).obj;
		}
		return res;
	}
	private T floorCeiling(T pattern, boolean isFloor) {
		T res = null;
		int compRes = 0;
		Node<T> current = root;
		while (current != null && (compRes = comp.compare(pattern, current.obj)) != 0) {
			if ((compRes < 0 && !isFloor) || (compRes > 0 && isFloor) ) {
				res = current.obj;
			} 
			current = compRes < 0 ? current.left : current.right;
		}
		return current == null ? res : current.obj;
		
	}
	public void displayTreeRotated() {
		displayTreeRotated(root, 0);
	}
	private void displayTreeRotated(Node<T> root, int level) {
		if (root != null) {
			displayTreeRotated(root.right, level + 1);
			displayRoot(root, level);
			displayTreeRotated(root.left, level + 1);
		}
		
	}
	private void displayRoot(Node<T> root, int level) {
		System.out.printf("%s%s\n", SYMBOL.repeat(NUMBER_SYMBOLS_PER_LEVEL * level), root.obj);
		
	}
	public int height() {
		
		return height(root);
	}
	private int height(Node<T> root) {
		int res = 0;
		if (root != null) {
			int heightLeft = height(root.left);
			int heightRight = height(root.right);
			res = Math.max(heightLeft, heightRight) + 1;
		}
		return res;
		
	}
	public int width() {
		
		return width(root);
	}
	private int width(Node<T> root) {
		int res = 0;
		if (root != null) {
			if (root.left == null && root.right == null) {
				res = 1;
			} else {
				res = width(root.left) + width(root.right);
			}
				
		}
		
		return res;
	}
	public void inversion() {
		inversion(root);
		comp = comp.reversed();
		
	}
	private void inversion(Node<T> root) {
		if (root != null) {
			inversion(root.left);
			inversion(root.right);
			swap(root);
		}
		
	}
	private void swap(Node<T> root) {
		Node<T> tmp = root.left;
		root.left = root.right;
		root.right = tmp;
		
	}
	public void balance() {
		Node<T>[] array = getNodesArray();
		root = balance(array, 0, array.length - 1, null);
		
	}
	private Node<T> balance(Node<T>[] array, int left, int right, Node<T>parent) {
		Node<T> root = null;
		if (left <= right) {
			final int rootIndex = (left + right) / 2;
			root = array[rootIndex];
			root.parent = parent;
			root.left = balance(array, left, rootIndex - 1, root);
			root.right = balance(array, rootIndex + 1, right, root);
		}
		return root;
	}
	@SuppressWarnings("unchecked")
	private Node<T>[] getNodesArray() {
		Node<T> res[] = new Node[size];
		int index = 0;
		if (root != null) {
			Node<T> current = getLeastNode(root);
			while (current != null) {
				res[index++] = current;
				current = getNextCurrent(current);
			} 
		}
		return res;
	}
	@Override
	public T get(T pattern) {
		T res = floor(pattern);
		if (!isEqual(res, pattern)) {
			res = null;
		}
		return res;
	}
	
	

}