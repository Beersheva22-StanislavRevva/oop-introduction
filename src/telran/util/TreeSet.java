package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import telran.util.LinkedList.Node;

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
	
	@Override
	public void remove() {
//		if(!hasNext()) {
//			throw new IllegalStateException();
//		}
		if (current != null) {
			T res = current.obj;
			Node<T> prev = getPrevCurrent(current);
			TreeSet.this.remove(prev.obj);
			current = getNode(current.obj);
		} else TreeSet.this.remove(last());
				
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

private Node<T> getPrevCurrent(Node<T> current) {
	return current.left == null ? getLowerParent(current) : getGreatestNode(current.left);
	}

private Node<T> getLowerParent(Node<T> current) {
	while(current.parent != null && current.parent.right != current) {
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
		boolean res = true;
		if (!contains(pattern)) {
			return res = false;
		}
		Node<T> node = getNode(pattern);
		Node<T> left = node.left;
		Node<T> right = node.right;
		Node<T> parent = node.parent;
		size--;
		if (left == null && right == null) {
		removeNoChildren(node, parent);	
		return res;
		}
		if (left == null && right != null) {
		removeNoLeft(node, parent, right);
		return res;
		}
		if (left != null && right == null) {
		removeNoRight(node, parent, left);
		return res;
		} else {
			removeBothChildren(node, parent, left, right);
		return res;
		}
	}

	
	private void removeNoChildren(Node<T> node, Node<T> parent) {
		if (parent != null) {
			if (comp.compare(node.obj, parent.obj) < 0) {
				parent.left = null;
			} else {parent.right = null;
				}
			node.parent = null;
		} else {
			node.parent = null;
			node = root = null;
		}
	}
	
	private void removeNoLeft(Node<T> node, Node<T> parent, Node<T> right) {
		if (parent != null) {
			if (comp.compare(node.obj, parent.obj) < 0) {
				parent.left = right;
			} else { 
				parent.right = right;
				}
		} 
		right.parent = parent;
	}
	
	private void removeNoRight(Node<T> node, Node<T> parent, Node<T> left) {
		if (parent != null) {
			if (comp.compare(node.obj, parent.obj) < 0) {
				parent.left = left;
			} else {parent.right = left;
				}
		}
		left.parent = parent;
		
	}
	
	private void removeBothChildren(Node<T> node, Node<T> parent, Node<T> left, Node<T> right) {
		Node<T> newNode = getNextCurrent(node);
		remove(newNode.obj);
		if (parent != null) {
			if (comp.compare(newNode.obj, parent.obj) < 0) {
				parent.left = newNode;
			} else {parent.right = newNode;
				}
		} else {root = newNode;
				newNode.parent = null;
			}
		if (left != null && comp.compare(newNode.obj, left.obj) != 0) {
			left.parent = newNode;
			newNode.left = left;
		} else newNode.left = null;
		if (right != null && comp.compare(newNode.obj, right.obj) != 0) {
			right.parent = newNode;
			newNode.right = right;
		} else newNode.right = null;
		size++;
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
				if (compRes < 0)
					if (comp.compare(element, current.left.obj) < 0) {
						return parent.obj;
					}
				
				parent = current;
				current = current.left;
			}
		}
			
		return comp.compare(element, parent.obj) > 0 ? parent.obj : findLowerParent(parent,element);
	}
		
	private T findLowerParent(Node<T> parent, T element) {
		while (comp.compare(element, parent.obj) < 0) {
			parent = parent.parent;
		}
		return parent.obj;
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
			
		return comp.compare(element, parent.obj) < 0 ? parent.obj : findHigherParent(parent,element);
	}
	
	private T findHigherParent(Node<T> parent, T element) {
		while (comp.compare(element, parent.obj) > 0) {
			parent = parent.parent;
		}
		return parent.obj;
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