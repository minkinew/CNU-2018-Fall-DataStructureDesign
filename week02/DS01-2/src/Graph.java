
public class Graph {
	int size; // 정점의 갯수 저장
	String[] vertices; // 정점들을 저장하는 배열
	Node[] a; // 노드 타입의 배열

	private class Node { // 노드 클래스
		int to;
		Node next;

		public Node(int io, Node next) {
			this.to = io;
			this.next = next;
		}
	}

	public Graph(String[] args) { // 그래프 생성
		size = args.length;
		vertices = new String[size];
		System.arraycopy(args, 0, vertices, 0, size);
		a = new Node[size];
		for (int i = 0; i < size; i++)
			a[i] = new Node(index(vertices[i]), null);
	}

	public void add(String v, String w) { // 다음 경로를 노드로 연결하는 메소드
		a[index(v)].next = new Node(index(w), a[index(v)].next);
		a[index(w)].next = new Node(index(v), a[index(w)].next);
	}

	private int index(String v) { // 인덱스 반환 메소드
		for (int i = 0; i < size; i++)
			if (vertices[i].equals(v))
				return i;
		return a.length;
	}

	public String toString() { // 정점 출력 메소드
		if (size == 0)
			return "{ }";
		StringBuffer buf = new StringBuffer("{" + vertex(0));
		for (int i = 1; i < size; i++)
			buf.append(", " + vertex(i));
		return buf + "}";
	}

	public String vertex(int i) { // 정점과 연결된 정점들을 출력하는 메소드
		StringBuffer buf = new StringBuffer(vertices[i] + ": ");
		Node p;
		for (p = a[i].next; p != null; p = p.next)
			buf.append(vertices[p.to]);
		return buf + "";
	}

	public void findPath(String v, String w) { // 경로가 2인 경우를 찾는 메소드
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
