package ADTDictionary;

/** Source code example for "A Practical Introduction to Data
Structures and Algorithm Analysis, 3rd Edition (Java)" 
by Clifford A. Shaffer
Copyright 2008-2011 by Clifford A. Shaffer
*/
import ADTDictionary.*;

/** Dictionary implemented by unsorted array-based list. */
public class ALDictionary<Key, E> implements ADTDictionary<Key, E> {

	private static final int defaultSize = 100; // Default size

	private AList<Key> klist;
	private AList<E> vlist;

	private int[] index = null;

	/** Constructors */
	ALDictionary() {
		this(defaultSize);
	}

	ALDictionary(int sz) {
		klist = new AList<Key>(sz);
		vlist = new AList<E>(sz);
	}

	/** Reinitialize */
	public void clear() {
		klist.clear();
		vlist.clear();
	}

	/**
	 * Find k using sequential search
	 * 
	 * @return Record with key value k
	 */
	public E find(Key k) {

		int pos = klist.find(k);
		if (pos < klist.length() && pos >= 0) {
			return vlist.getValue(pos);
		}
		return null;
	}

	/** Insert an element: append to list */
	public void insert(Key k, E e) {
		if (find(k) == null) {
			klist.append(k);
			vlist.append(e);
		}

	}

	/** Use sequential search to find the element to remove */
	public E remove(Key k) {
		E temp = find(k);
		int origin = klist.currPos();
		if (temp != null) {
			int pos = klist.find(k);
			klist.moveToPos(pos);
			vlist.moveToPos(pos);
			klist.remove();
			vlist.remove();
		}
		klist.moveToPos(origin);
		vlist.moveToPos(origin);
		return temp;
	}

	/** Remove the current element */
	public E removeAny() {

		if (size() != 0) {
			klist.remove();
			E temp = vlist.getValue();
			vlist.remove();
			return temp;
		} else
			return null;
	}

	/** @return List size */
	public int size() {
		return klist.length();
	}

	public E[] toArray() {
		return vlist.toArray();
	}

	public String toString() {
		int origin = klist.currPos();
		StringBuffer out = new StringBuffer();
		assert (vlist.length() == klist.length()) : "the dict is inconsistent";
		for (int i = 0; i < klist.length(); i++) {
			out.append(klist.getValue().toString());
			klist.next();
			out.append(":");
			out.append(vlist.getValue().toString());
			out.append(" , ");
			vlist.next();
		}

		klist.moveToPos(origin);
		vlist.moveToPos(origin);
		return out.toString().trim();

	}

	public int find(AList<Key> klist, Key k) {

		int orgCurr = klist.currPos();
		int pos = 0;
		for (; pos < klist.length(); pos++) {
			klist.moveToPos(pos);
			if (k == klist.getValue()) {
				break;
			}

		}
		klist.moveToPos(orgCurr);
		return pos;
		// k not found
	}

	/**
	 * public int[] createDesendingIndex() {
	 * index = new int [klist.length()];
	 * // revise sorting algorithm to solve the right position of each record
	 * // according to if it is a ascending or descending order.
	 * // the original record order must be reserved without any swap.
	 * 
	 * 
	 * return index;
	 * 
	 * 
	 * }
	 */

	@Override
	public E[] createIndex() { // giving each value their own index by sorting them. This is an example of
								// bubble sort
		index = new int[list.length()];
		fillIndex();// calling fill index to fill the elements into an array, which will be sorted
		for (int i = 1; i < index.length; i++)
			for (int j = i; (j > 0) && (elementAtIndex(index[j]).compareTo(elementAtIndex(index[j - 1])) < 0); j--) {
				DSutil.swap(index, j, j - 1);
			}
		return index;

	}

	private KVpair<Key, E> elementAtIndex(int index) { // taking value as a KVpair at each index and giving them a key
		int i = 0;
		KVpair<Key, E> key = list.getValue();
		for (list.moveToStart(); list.currPos() < list.length(); list.next()) {
			if (index == i) {
				key = list.getValue();
			}
			i++;
		}
		return key;
	}

	private void fillIndex() {
		for (int i = 0; i < index.length; i++) {
			index[i] = i;
		}
	}

	@Override
	public String returnList() { // returning sorted list in their respective indeces
		return list;
	}

	class BST<Key extends Comparable<? super Key>, E> implements Dictionary<Key, E> {
		private BSTNode<Key, E> root; // Root of the BST
		private int nodecount; // Number of nodes in the BST
		int key; //searching/adding keys in the BST

		initializeBST(){ //initialize binary search tree
			root = null; 
			nodecount = 0;
		}

		/** Reinitialize tree */
		public void clear() {
			root = null;
			nodecount = size();
		}

		public void insert(Key k, E e) {
			root = inserthelp(root, k, e);
			nodecount++;
		}

		private BSTNode<Key,E> inserthelp(BSTNode<Key,E> rt, Key k, E e) {
			if (rt == null){
				return new BSTNode<Key, E> (k, e);}
			if (rt.key().compareTo(k) > 0){
				rt.setLeft(inserthelp(rt.left(), k, e));
			else
			rt.setRight(inserthelp(rt.right(), k, e));
			return rt; }
			}

		private E query(BSTNode<Key,E> rt, Key k) {
			if (rt == null){ 
				return null;}
			if (rt.key().compareTo(k) > 0){
				return query(rt.left(), k);
				else if (rt.key().compareTo(k) == 0) return rt.element();
				else return query(rt.right(), k);
			}
		}

		public E remove(Key k) {
			E temp = query(root, k); // First find it

			if (temp != null) {
				root = removehelp(root, k); // Now remove it
				nodecount--;
			}
			return temp;
		}	

		// recursive delete function
		public removeHelp(BSTNode<Key,E> root,Key k) {
			// tree is empty
			if (root == null)
				return root;

			// traverse the tree
			if (key < root.key) // traverse left subtree
				root.left = removeHelp(root.left, key);
			else if (key > root.key) // traverse right subtree
				root.right = removeHelp(root.right, key);
			else {
				// node contains only one child
				if (root.left == null)
					return root.right;
				else if (root.right == null)
					return root.left;

				// node has two children;
				// get inorder successor (min value in the right subtree)
				root.key = minValue(root.right);

				// Delete the inorder successor
				root.right = deleteRecursive(root.right, root.key);
			}
			return root;
		}

		public E find(Key k) {
			return query(root, k);
		}		

		int minValue() {
			int smallestValue = root.key;
			// find minval
			while (root.left != null) {
				smallestValue = root.left.key;
				root = root.left;
			}
			return smallestValue;
		}

		// insert a node in BST
		

		// recursive insert function
		public insert_Recursive(root, int key) {
			// tree is empty
			if (root == null) {
				root = new BSTNode(key);
				return root;
			}
			// traverse the tree
			if (key < root.key) // insert in the left subtree
				root.left = insert_Recursive(root.left, key);
			else if (key > root.key) // insert in the right subtree
				root.right = insert_Recursive(root.right, key);
			// return pointer
			return root;
		}

		// method for inorder traversal of BST
		void inorder() {
			inorder_Recursive(root);
		}

		// recursively traverse the BST
		void inorder_Recursive(Node root) {
			if (root != null) {
				inorder_Recursive(root.left);
				System.out.print(root.key + " ");
				inorder_Recursive(root.right);
			}
		}

		boolean search(int key) {
			root = search_Recursive(root, key);
			if (root != null)
				return true;
			else
				return false;
		}

		// recursive insert function
		Node search_Recursive(Node root, int key) {
			// Base Cases: root is null or key is present at root
			if (root == null || root.key == key)
				return root;
			// val is greater than root's key
			if (root.key > key)
				return search_Recursive(root.left, key);
			// val is less than root's key
			return search_Recursive(root.right, key);
		}
	}
	// InOrder Traversal - Left:rootNode:Right (LnR)
	void inOrder(Node node) {
		if (node == null)
			return;
		// first traverse left subtree recursively
		inOrder(node.left);

		// then go for root node
		System.out.print(node.key + " ");

		// next traverse right subtree recursively
		inOrder(node.right);
	}
}
