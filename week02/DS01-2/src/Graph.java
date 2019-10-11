
public class Graph {
	int size; // ������ ���� ����
	String[] vertices; // �������� �����ϴ� �迭
	Node[] a; // ��� Ÿ���� �迭

	private class Node { // ��� Ŭ����
		int to;
		Node next;

		public Node(int io, Node next) {
			this.to = io;
			this.next = next;
		}
	}

	public Graph(String[] args) { // �׷��� ����
		size = args.length;
		vertices = new String[size];
		System.arraycopy(args, 0, vertices, 0, size);
		a = new Node[size];
		for (int i = 0; i < size; i++)
			a[i] = new Node(index(vertices[i]), null);
	}

	public void add(String v, String w) { // ���� ��θ� ���� �����ϴ� �޼ҵ�
		a[index(v)].next = new Node(index(w), a[index(v)].next);
		a[index(w)].next = new Node(index(v), a[index(w)].next);
	}

	private int index(String v) { // �ε��� ��ȯ �޼ҵ�
		for (int i = 0; i < size; i++)
			if (vertices[i].equals(v))
				return i;
		return a.length;
	}

	public String toString() { // ���� ��� �޼ҵ�
		if (size == 0)
			return "{ }";
		StringBuffer buf = new StringBuffer("{" + vertex(0));
		for (int i = 1; i < size; i++)
			buf.append(", " + vertex(i));
		return buf + "}";
	}

	public String vertex(int i) { // ������ ����� �������� ����ϴ� �޼ҵ�
		StringBuffer buf = new StringBuffer(vertices[i] + ": ");
		Node p;
		for (p = a[i].next; p != null; p = p.next)
			buf.append(vertices[p.to]);
		return buf + "";
	}

	public void findPath(String v, String w) { // ��ΰ� 2�� ��츦 ã�� �޼ҵ�
		Node p, q;
		for (p = a[index(v)]; p.next != null; p = p.next) {
			for (q = a[p.next.to]; q.next != null; q = q.next) {
				if (p.next.to == index(w))
					continue;

				if (q.to == index(w))
					System.out.println(v + " : " + vertices[index(v)] + " -> " + vertices[p.next.to] + " -> "
							+ vertices[index(w)]);
			}
		}
	}
}
