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
		
		//========== 100000번 ==========
		ArrayList<Integer> l = new ArrayList(n);
		for (int i = 0; i < n; i++)
			l.add(i);

		Collections.shuffle(l);

		System.out.println("총 " + n + "개의 정수를 무작위 생성합니다...");
		System.out.println("정수 생성 완료.");
		System.out.println("각 자료구조에 " + n + "개의 정수를 추가합니다.");
		System.out.println();
		System.out.println("=======100000번 검색=======");

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

		System.out.println("avl 검색 시간 : " + ((float) (end1 - start1)) / 1000.0 + "초");
		System.out.println("avl 검색 성공 횟수 : " + AVLcount + "회");
		System.out.println("bst 검색 시간 : " + ((float) (end2 - start2)) / 1000.0 + "초");
		System.out.println("bst 검색 성공 횟수 : " + BSTcount + "회");
		System.out.println("hash 검색 시간 : " + ((float) (end3 - start3)) / 1000.0 + "초");
		System.out.println("hash 검색 성공 횟수 : " + QHTcount + "회");

		//========== 200000번 ==========
		System.out.println();
		System.out.println("=======200000번 검색=======");

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

		System.out.println("avl 검색 시간 : " + ((float) (end4 - start4)) / 1000.0 + "초");
		System.out.println("avl 검색 성공 횟수 : " + AVLcount2 + "회");
		System.out.println("bst 검색 시간 : " + ((float) (end5 - start5)) / 1000.0 + "초");
		System.out.println("bst 검색 성공 횟수 : " + BSTcount2 + "회");
		System.out.println("hash 검색 시간 : " + ((float) (end6 - start6)) / 1000.0 + "초");
		System.out.println("hash 검색 성공 횟수 : " + QHTcount2 + "회");
		
		//========== 500000번 ==========
		System.out.println();
		System.out.println("=======500000번 검색=======");
		
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

		System.out.println("avl 검색 시간 : " + ((float) (end7 - start7)) / 1000.0 + "초");
		System.out.println("avl 검색 성공 횟수 : " + AVLcount3 + "회");
		System.out.println("bst 검색 시간 : " + ((float) (end8 - start8)) / 1000.0 + "초");
		System.out.println("bst 검색 성공 횟수 : " + BSTcount3 + "회");
		System.out.println("hash 검색 시간 : " + ((float) (end9 - start9)) / 1000.0 + "초");
		System.out.println("hash 검색 성공 횟수 : " + QHTcount3 + "회");
	}
}
