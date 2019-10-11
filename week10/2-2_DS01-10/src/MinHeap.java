public class MinHeap {
	private static final int CAPACITY = 100;
	private Node[] tree;
	private int size;

	public MinHeap() {
		this(CAPACITY);
	}

	public MinHeap(int capcity) {
		tree = new Node[capcity];
	}

	public void add(Node n) {
		Node x = n;
		if (size == tree.length)
			resize();
		int i = size++;
		while (i > 0) {
			int j = i;
			i = (i - 1) / 2;
			if (tree[i].count <= x.count) {
				tree[j] = x;
				return;
			}
			tree[j] = tree[i];
		}
		tree[i] = x;
	}

	public Node get() {
		if (isEmpty())
			return null;
		else {
			Node min = tree[0];
			tree[0] = tree[--size];
			heapify(0, size);
			return min;
		}
	}

	private void heapify(int i, int n) {
		Node node = tree[i];
		while (i < n / 2) {
			int j = 2 * i + 1;
			if (j + 1 < n && tree[j + 1].count < tree[j].count)
				++j;
			if (tree[j].count >= node.count)
				break;
			tree[i] = tree[j];
			i = j;
		}
		tree[i] = node;
	}

	public boolean isEmpty() {
		return (size <= 0);
	}

	public int size() {
		return size;
	}

	private void resize() {
		Node[] node2 = new Node[2 * tree.length];
		System.arraycopy(tree, 0, node2, 0, tree.length);
		tree = node2;
	}
}
