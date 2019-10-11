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
		// 같은 값을 가지거나 빈 노드를 만나 노드를 추가
		if (this == NIL)
			return new AVLTree(key);
		if (key == this.key)
			return this;

		// 빈 노드를 찾기 위해 루트 노드부터 리프 노드까지 탐색
		if (key < this.key)
			left = left.grow(key);
		else
			right = right.grow(key);

		// 트리의 구조와 높이 변화를 업데이트
		rebalance();
		height = 1 + Math.max(left.height, right.height);
		return this;
	}

	public AVLTree remove(int key) {
		if (this == NIL)
			return NIL;

		// 삭제하고자 하는 값을 찾았을 때
		if (key == this.key) {
			// 자식 노드가 존재 X
			if (this.left == NIL && this.right == NIL)
				return NIL;

			// 자식 노드가 1개 (왼쪽)
			if (this.left != NIL && this.right == NIL)
				return this.left;
			// 자식 노드가 1개 (오른쪽)
			if (this.left == NIL && this.right != NIL)
				return this.right;

			// 자식 노드가 2개
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

				// 트리의 구조와 높이 변화를 업데이트
				rebalance();
				height = 1 + Math.max(left.height, right.height);
				return this;

			}
		}

		// 삭제 노드의 값이 탐색 노드와 다를 때 다음 탐색 노드의 위치 설정
		if (key < this.key)
			left = left.remove(key);
		else
			right = right.remove(key);

		// 트리의 구조와 높이 변화를 업데이트
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
