
public class Graph {
	int size; // 정점의 갯수 저장
	String[] vertices; // 정점들을 저장하는 배열
	boolean[][] a; // 인접 행렬과 정점의 간선을 저장하는 배열

	public Graph(String[] args) { //생성자
		size = args.length;
		vertices = new String[size]; // vertices배열의 길이 결정
		System.arraycopy(args, 0, vertices, 0, size); // 매개변수 배열원소를 vertices배열의 복사
		a = new boolean[size][size]; // 배열 a의 크기
	}

	public void add(String v, String w) { // 정점들 간 연결상태를 나타내는 메소드
		int i = index(v), j = index(w);
		a[i][j] = a[j][i] = true; // 대칭행렬
	}

	private int index(String v) { // 인덱스 반환 메소드
		for (int i = 0; i < size; i++)
			if (vertices[i].equals(v))
				return i;
		return a.length;
	}

	public String toString() { // 정점 출력 메소드
		if (size == 0)
			return "{}";
		StringBuffer buf = new StringBuffer("{" + vertex(0));
		for (int i = 1; i < size; i++)
			buf.append(", " + vertex(i));
		return buf + "}";
	}

	private String vertex(int i) { // 정점과 연결된 정점들을 출력하는 메소드
		StringBuffer buf = new StringBuffer(vertices[i] + ":");
		for (int j = 0; j < size; j++)
			if (a[i][j])
				buf.append(vertices[j]);
		return buf + "";
	}

	public int degree(String v) { // 차수 정의 메소드
		int deg = 0;
		int row = index(v);
		for (int i = 0; i < size; i++) {
			if (a[row][i])
				deg++;
		}
		return deg;
	}

	public void findPath(String v, String w) { // 경로가 2를 찾는 메소드
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
