import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SearchTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 200000;
		int a = 100000;
		int b = 200000;
		int c = 500000;
		
		//========== 100000�� ==========
		ArrayList<Integer> l = new ArrayList(n);
		for (int i = 0; i < n; i++)
			l.add(i);

		Collections.shuffle(l);

		System.out.println("�� " + n + "���� ������ ������ �����մϴ�...");
		System.out.println("���� ���� �Ϸ�.");
		System.out.println("�� �ڷᱸ���� " + n + "���� ������ �߰��մϴ�.");
		System.out.println();
		System.out.println("=======100000�� �˻�=======");

		// AVLTree
		AVLTree AVL = new AVLTree(l.get(0));
		int AVLcount = 0;
		for (int j = 1; j < n; j++)
			AVL.add(l.get(j));
		long start1 = System.currentTimeMillis();
		for (int k = 0; k < a; k++) {
			if (AVL.search(k) == true)
				AVLcount++;
		}
		long end1 = System.currentTimeMillis();

		// BinarySearchTree
		BinarySearchTree BST = new BinarySearchTree(l.get(0));
		int BSTcount = 0;
		for (int j = 1; j < n; j++)
			BST.recu_insert(l.get(j));
		long start2 = System.currentTimeMillis();
		for (int k = 0; k < a; k++) {
			if (BST.search(k) == true)
				BSTcount++;
		}
		long end2 = System.currentTimeMillis();

		// QuadraticHashTable
		QuadraticHashTable QHT = new QuadraticHashTable();
		int QHTcount = 0;
		for (int j = 0; j < n; j++)
			QHT.put(l.get(j), 1);
		long start3 = System.currentTimeMillis();
		for (int k = 0; k < a; k++) {
			if (QHT.get(k) != null)
				QHTcount++;
		}
		long end3 = System.currentTimeMillis();

		System.out.println("avl �˻� �ð� : " + ((float) (end1 - start1)) / 1000.0 + "��");
		System.out.println("avl �˻� ���� Ƚ�� : " + AVLcount + "ȸ");
		System.out.println("bst �˻� �ð� : " + ((float) (end2 - start2)) / 1000.0 + "��");
		System.out.println("bst �˻� ���� Ƚ�� : " + BSTcount + "ȸ");
		System.out.println("hash �˻� �ð� : " + ((float) (end3 - start3)) / 1000.0 + "��");
		System.out.println("hash �˻� ���� Ƚ�� : " + QHTcount + "ȸ");

		//========== 200000�� ==========
		System.out.println();
		System.out.println("=======200000�� �˻�=======");

		// AVLTree
		int AVLcount2 = 0;
		long start4 = System.currentTimeMillis();
		for (int k = 0; k < b; k++) {
			if (AVL.search(k) == true)
				AVLcount2++;
		}
		long end4 = System.currentTimeMillis();

		// BinarySearchTree
		int BSTcount2 = 0;
		long start5 = System.currentTimeMillis();
		for (int k = 0; k < b; k++) {
			if (BST.search(k) == true)
				BSTcount2++;
		}
		long end5 = System.currentTimeMillis();

		// QuadraticHashTable
		int QHTcount2 = 0;
		long start6 = System.currentTimeMillis();
		for (int k = 0; k < b; k++) {
			if (QHT.get(k) != null)
				QHTcount2++;
		}
		long end6 = System.currentTimeMillis();

		System.out.println("avl �˻� �ð� : " + ((float) (end4 - start4)) / 1000.0 + "��");
		System.out.println("avl �˻� ���� Ƚ�� : " + AVLcount2 + "ȸ");
		System.out.println("bst �˻� �ð� : " + ((float) (end5 - start5)) / 1000.0 + "��");
		System.out.println("bst �˻� ���� Ƚ�� : " + BSTcount2 + "ȸ");
		System.out.println("hash �˻� �ð� : " + ((float) (end6 - start6)) / 1000.0 + "��");
		System.out.println("hash �˻� ���� Ƚ�� : " + QHTcount2 + "ȸ");
		
		//========== 500000�� ==========
		System.out.println();
		System.out.println("=======500000�� �˻�=======");
		
		// AVLTree
		int AVLcount3 = 0;
		long start7 = System.currentTimeMillis();
		for (int k = 0; k < c; k++) {
			if (AVL.search(k) == true)
				AVLcount3++;
		}
		long end7 = System.currentTimeMillis();

		// BinarySearchTree
		int BSTcount3 = 0;
		long start8 = System.currentTimeMillis();
		for (int k = 0; k < c; k++) {
			if (BST.search(k) == true)
				BSTcount3++;
		}
		long end8 = System.currentTimeMillis();

		// QuadraticHashTable
		int QHTcount3 = 0;
		long start9 = System.currentTimeMillis();
		for (int k = 0; k < c; k++) {
			if (QHT.get(k) != null)
				QHTcount3++;
		}
		long end9 = System.currentTimeMillis();

		System.out.println("avl �˻� �ð� : " + ((float) (end7 - start7)) / 1000.0 + "��");
		System.out.println("avl �˻� ���� Ƚ�� : " + AVLcount3 + "ȸ");
		System.out.println("bst �˻� �ð� : " + ((float) (end8 - start8)) / 1000.0 + "��");
		System.out.println("bst �˻� ���� Ƚ�� : " + BSTcount3 + "ȸ");
		System.out.println("hash �˻� �ð� : " + ((float) (end9 - start9)) / 1000.0 + "��");
		System.out.println("hash �˻� ���� Ƚ�� : " + QHTcount3 + "ȸ");
	}
}
