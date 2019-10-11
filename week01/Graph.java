
public class Graph {
	int size; // ������ ���� ����
	String[] vertices; // �������� �����ϴ� �迭
	boolean[][] a; // ���� ��İ� ������ ������ �����ϴ� �迭

	public Graph(String[] args) { //������
		size = args.length;
		vertices = new String[size]; // vertices�迭�� ���� ����
		System.arraycopy(args, 0, vertices, 0, size); // �Ű����� �迭���Ҹ� vertices�迭�� ����
		a = new boolean[size][size]; // �迭 a�� ũ��
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

	public int degree(String v) { // ���� ���� �޼ҵ�
		int deg = 0;
		int row = index(v);
		for (int i = 0; i < size; i++) {
			if (a[row][i])
				deg++;
		}
		return deg;
	}

	public void findPath(String v, String w) { // ��ΰ� 2�� ã�� �޼ҵ�
		String X;
		int V = 0; 
		int W = 0; 
		for (int i = 0; i < size; i++) {
			if (vertices[i].equals(v))
				V = index(v);
		}
		for (int j = 0; j < size; j++) {
			if (vertices[j].equals(w))
				W = index(w);
		}

		for (int k = 0; k < size; k++) {
			if (a[V][k] && a[k][W]) {
				X = vertices[k];
				System.out.println(v + " - " + X + " - " + w);
			}
		}
	}
}
