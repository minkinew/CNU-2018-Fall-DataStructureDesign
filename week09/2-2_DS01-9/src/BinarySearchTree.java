public class BinarySearchTree {
	private Comparable<Integer> key;
	private BinarySearchTree left, right;
	private int size = 1;
	BinarySearchTree root;

	private class Node implements Comparable<Object> {
		private int root;

		public int compareTo(Object object) {
			if (this.root < (int) object) {
				return -1; // c<0
			} else if (this.root == (int) object) {
				return 0; // c=0
			} else {
				return 1; // c>0
			}
		}

	}

	public BinarySearchTree() {
		this.root = this;
	}

	public BinarySearchTree(Comparable<Integer> key) {
		this.root = this;
		this.key = key;
	}

	public int treesize() {
		return size;
	}

	public boolean recu_insert(int key) { // Àç±ÍÀû
		if (root.key == null) {
			root = new BinarySearchTree((Comparable<Integer>) key);
		} else if (root.key != null) {
			if (root.key.compareTo(key) > 0) {
				if (root.left == null) {
					root.left = new BinarySearchTree((Comparable<Integer>) key);
				} else {
					root.left.recu_insert(key);
				}
			} else if (root.key.compareTo(key) < 0) {
				if (root.right == null) {
					root.right = new BinarySearchTree((Comparable<Integer>) key);
				} else {
					root.right.recu_insert(key);
				}
			} else {
				return false;
			}
			size++;
		}
		return true;
	}

	public boolean search(Comparable key) {
		if (key.compareTo(this.key) == 0)
			return true;
		else if (key.compareTo(this.key) < 0) {
			if (left != null)
				return left.search(key);
			else
				return false;
		} else if (key.compareTo(this.key) > 0) {
			if (right != null)
				return right.search(key);
			else
				return false;
		}
		return false;
	}
}