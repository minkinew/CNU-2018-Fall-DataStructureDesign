
public class TestAVLTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AVLTree avlt = new AVLTree(14);
		avlt.add(17);
		avlt.add(11);
		avlt.add(7);
		avlt.add(53);
		avlt.add(4);
		avlt.add(13);
		avlt.add(12);
		avlt.add(8);

		System.out.println("���� : 14, 17, 11, 7, 53, 4, 13, 12, 8");
		System.out.println("���� : 53, 11, 7, 12, 14, 13");
		System.out.println();
		System.out.println();

		System.out.println("* ���� ��� : " + avlt);
		avlt.Delete(53);
		System.out.println("*  53 ���� ��� : " + avlt);
		avlt.Delete(11);
		System.out.println("*  11 ���� ��� : " + avlt);
		avlt.Delete(7);
		System.out.println("*  7  ���� ��� : " + avlt);
		avlt.Delete(12);
		System.out.println("*  12 ���� ��� : " + avlt);
		avlt.Delete(14);
		System.out.println("*  14 ���� ��� : " + avlt);
		avlt.Delete(13);
		System.out.println("*  13 ���� ��� : " + avlt);
	} 

}
