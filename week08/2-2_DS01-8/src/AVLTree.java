public class AVLTree {
	private int key, height;
	private AVLTree left, right;
	public static final AVLTree NIL = new AVLTree();

	public AVLTree(int key) {
		this.key = key;
		left = right = NIL;
	}

	private AVLTree() {
		left = right = this;
		height = -1;
	}

	private AVLTree(int key, AVLTree left, AVLTree right) {
		this.key = key;
		this.left = left;
		this.right = right;
		height = 1 + Math.max(left.height, right.height);
	}

	public int size() {
		if (this == NIL)
			return 0;
		return 1 + left.size() + right.size();
	}

	public String toString() {
		int balanceFactor = left.height - right.height;
		if (this == NIL)
			return "";
		return left + " " + key + "(" + balanceFactor + ") " + right;
	}

	public boolean add(int key) {
		int oldSize = size();
		grow(key);
		return size() > oldSize;
	}

	public boolean Delete(int key) {
		int OldValue = size();
		remove(key);
		return OldValue > size();
	}

	public AVLTree grow(int key) {
		// ���� ���� �����ų� �� ��带 ���� ��带 �߰�
		if (this == NIL)
			return new AVLTree(key);
		if (key == this.key)
			return this;

		// �� ��带 ã�� ���� ��Ʈ ������ ���� ������ Ž��
		if (key < this.key)
			left = left.grow(key);
		else
			right = right.grow(key);

		// Ʈ���� ������ ���� ��ȭ�� ������Ʈ
		rebalance();
		height = 1 + Math.max(left.height, right.height);
		return this;
	}

	public AVLTree remove(int key) {
		if (this == NIL)
			return NIL;

		// �����ϰ��� �ϴ� ���� ã���� ��
		if (key == this.key) {
			// �ڽ� ��尡 ���� X
			if (this.left == NIL && this.right == NIL)
				return NIL;

			// �ڽ� ��尡 1�� (����)
			if (this.left != NIL && this.right == NIL)
				return this.left;
			// �ڽ� ��尡 1�� (������)
			if (this.left == NIL && this.right != NIL)
				return this.right;

			// �ڽ� ��尡 2��
			if (this.left != NIL && this.right != NIL) {
				AVLTree prevkey = new AVLTree(key, left, right);
				prevkey = prevkey.right;
				if (prevkey.left != NIL) {
					while (true) {
						prevkey = prevkey.left;
						if (prevkey.left == NIL)
							break;
					}
				}
				this.key = prevkey.key;
				key = prevkey.key;

				if (key == this.right.key && this.right.left == NIL) {
					if (this.right.right == NIL)
						this.right = NIL;
					else
						this.right = this.right.right;
				} else
					this.right.remove(key);

				// Ʈ���� ������ ���� ��ȭ�� ������Ʈ
				rebalance();
				height = 1 + Math.max(left.height, right.height);
				return this;

			}
		}

		// ���� ����� ���� Ž�� ���� �ٸ� �� ���� Ž�� ����� ��ġ ����
		if (key < this.key)
			left = left.remove(key);
		else
			right = right.remove(key);

		// Ʈ���� ������ ���� ��ȭ�� ������Ʈ
		rebalance();
		height = 1 + Math.max(left.height, right.height);
		return this;
	}

	private void rebalance() {
		if (right.height > left.height + 1) {
			if (right.left.height > right.right.height)
				right.rotateRight();
			rotateLeft();
		} else if (left.height > right.height + 1) {
			if (left.right.height > left.left.height)
				left.rotateLeft();
			rotateRight();
		}
	}

	private void rotateLeft() {
		left = new AVLTree(key, left, right.left);
		key = right.key;
		right = right.right;
	}

	private void rotateRight() {
		right = new AVLTree(key, left.right, right);
		key = left.key;
		left = left.left;
	}
}
