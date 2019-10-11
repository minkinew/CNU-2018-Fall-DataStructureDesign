import java.awt.List;
import java.util.Stack;

public class Graph {
	int size; // ������ ���� ����
	String[] vertices; // �������� �����ϴ� �迭
	boolean[][] a; // ���� ��İ� ������ ������ �����ϴ� �迭
	boolean[] V1, V2, V3; // ���� �������� �湮�� �迭
	String[] L; // DFS������ ��� ������ ������ �ִ� ����Ʈ �迭

	public Graph(String[] args) { // ������
		size = args.length;
		vertices = new String[size]; // vertices�迭�� ���� ����
		System.arraycopy(args, 0, vertices, 0, size); // �Ű����� �迭���Ҹ� vertices�迭�� ����
		a = new boolean[size][size]; // �迭 a�� ũ��
		L = new String[size]; // L�迭�� ���� ����
		V1 = new boolean[size]; // �迭 V1�� ũ��
		V2 = new boolean[size]; // �迭 V2�� ũ��
		V3 = new boolean[size]; // �迭 V3�� ũ��
	}

	public void add(String v, String w) { // ������ �� ������¸� ��Ÿ���� �޼ҵ�
		int i = index(v), j = index(w);
		a[i][j] = a[j][i] = true; // ��Ī���
	}

	private int index(String v) { // �ε��� ��ȯ �޼ҵ�
		for (int i = 0; i < size; i++)
			if (vertices[i].equals(v))
				return i;
		return a.length;
	}

	public String toString() { // ���� ��� �޼ҵ�
		if (size == 0)
			return "{}";
		StringBuffer buf = new StringBuffer("{" + vertex(0));
		for (int i = 1; i < size; i++)
			buf.append(", " + vertex(i));
		return buf + "}";
	}

	private String vertex(int i) { // ������ ����� �������� ����ϴ� �޼ҵ�
		StringBuffer buf = new StringBuffer(vertices[i] + ":");
		for (int j = 0; j < size; j++)
			if (a[i][j])
				buf.append(vertices[j]);
		return buf + "";
	}

	public void recu_dfs(String x) {
		int i = index(x);

		if (V1[i])
			for (int j = 0; j < size; j++)
				V1[j] = false;

		if (V1[i])
			return;
		V1[i] = true;
		System.out.print(x + " ");

		for (int k = 0; k < size; k++) {
			if (V1[k] == false && a[i][k] == true)
				recu_dfs(vertices[k]);
		}
	}

	public void recu_dfs_tree(String x) {
		int i = index(x);

		if (V2[i])
			for (int j = 0; j < size; j++)
				V2[j] = false;

		if (V2[i])
			return;
		V2[i] = true;

		for (int k = 0; k < size; k++) {
			if (V2[k] == false && a[i][k] == true) {
				System.out.println("���� " + vertices[k] + "�� ����" + x + "�� �ڽ��̴�.");
				recu_dfs_tree(vertices[k]);
			}
		}

	}

	public void dfs_tree() {
		Stack stack = new Stack();
		String Print[][] = new String[size][2];
		int index = 0;
		int ListIndex = 0;
		int PrintIndex = 0;

		if (V3[index])
			for (int j = 0; j < size; j++)
				V3[j] = false;

		if (V3[index])
			return;
		V3[index] = true;
		stack.push(vertices[index]);

		while (stack.size() != 0) {
			String s = new String();
			s = (String) stack.pop();
			L[ListIndex] = s;
			ListIndex++;

			for (int j = 0; j < size; j++)
				if (s == vertices[j])
					index = j;
			for (int k = 0; k < size; k++) {
				if (V3[k] == false && a[index][k] == true) {
					V3[k] = true;
					stack.push(vertices[k]);
					Print[PrintIndex][0] = vertices[k];
					Print[PrintIndex++][1] = vertices[index];
				}
			}
		}

		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (L[x] == Print[y][0])
					System.out.println("���� " + L[x] + "�� ����" + Print[y][1] + "�� �ڽ��̴�.");
			}
		}
	}

}
