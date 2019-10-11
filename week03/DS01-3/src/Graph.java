import java.awt.List;
import java.util.Stack;

public class Graph {
	int size; // 정점의 갯수 저장
	String[] vertices; // 정점들을 저장하는 배열
	boolean[][] a; // 인접 행렬과 정점의 간선을 저장하는 배열
	boolean[] V1, V2, V3; // 다음 정점으로 방문할 배열
	String[] L; // DFS순서로 모든 정점을 가지고 있는 리스트 배열

	public Graph(String[] args) { // 생성자
		size = args.length;
		vertices = new String[size]; // vertices배열의 길이 결정
		System.arraycopy(args, 0, vertices, 0, size); // 매개변수 배열원소를 vertices배열의 복사
		a = new boolean[size][size]; // 배열 a의 크기
		L = new String[size]; // L배열의 길이 결정
		V1 = new boolean[size]; // 배열 V1의 크기
		V2 = new boolean[size]; // 배열 V2의 크기
		V3 = new boolean[size]; // 배열 V3의 크기
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
				System.out.println("정점 " + vertices[k] + "는 정점" + x + "의 자식이다.");
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
					System.out.println("정점 " + L[x] + "는 정점" + Print[y][1] + "의 자식이다.");
			}
		}
	}

}
