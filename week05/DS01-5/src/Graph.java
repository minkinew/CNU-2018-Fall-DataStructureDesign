import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Graph {
	boolean[] included;
	int[] near;
	float[][] adjacency;
	int size;
	int[][] list;

	public Graph(String gf) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(gf)); // 파일 읽어오기
			String line = in.readLine(); /// line에 첫줄을 할당
			String lineSplit[] = line.split(" "); // 띄어쓰기단위로 배열에 인덱스번호 부여
			
			included = new boolean[Integer.parseInt(lineSplit[0])]; // included배열 초기화
			near = new int[Integer.parseInt(lineSplit[0])]; // near배열 초기화
			adjacency = new float[Integer.parseInt(lineSplit[0])][Integer.parseInt(lineSplit[0])]; // adgacency행렬 초기화
			size = near.length;
			list = new int[Integer.parseInt(lineSplit[1]) - 1][2]; //list배열 초기화
			
			line = in.readLine(); // 다음 줄을 읽음

			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (i == j) // 자기 자신은 0으로 초기화
						adjacency[i][j] = 0;
					adjacency[i][j] = Float.MAX_VALUE; // 아무것도 연결되있지 않은 adjacency행렬 설정
				}
			}

			while (line != null) { // 다음 줄이 없을 때까지 계속 반복
				String lineSplit1[] = line.split(" ");
				add(Integer.parseInt(lineSplit1[0]), Integer.parseInt(lineSplit1[1]), Integer.parseInt(lineSplit1[2]));
				line = in.readLine();
			}
		} catch (IOException e) { // 예외처리
			e.printStackTrace();
		}
	}

	public void add(int u, int v, float weight) {
		adjacency[u][v] = adjacency[v][u] = weight;
	}

	public void prim() {
		int sp = 0; // 시작정점
		int np = 0; // 인접정점
		float sum = 0; // 가중치 합

		for (int i = 0; i < size; i++) { // 아무것도 연결되있지 않은
			included[i] = false; // included배열 설정
			near[i] = -1; // 초기 near배열을 -1로 설정
		}

		System.out.println("선택된 간선들");

		for (int j = 0; j < size - 1; j++) { // T에 추가된 간선의 수가 정점-1 이 될 때까지
			int n = Integer.MIN_VALUE; // N값 설정
			float min = Float.MAX_VALUE; // 최솟값을 찾기위해 설정

			included[sp] = true; // TV에 방문했다고 체크
			near[sp] = n; // near배열 업데이트

			for (int k = 1; k < size; k++) { // 0번째는 탐색할 필요가 없으므로 k=1부터
				if (near[k] != n) { // near배열 구성
					if ( (near[k] == -1 && adjacency[sp][k] != min && adjacency[sp][k] != 0) ||
							( near[k] >= 0 && (adjacency[sp][k] < adjacency[near[k]][k]) ) )
						near[k] = sp;
				}
			}
			
			int a = 0;
			int b = 0;
			for (int x = 0; x < size; x++) {
				a = near[x];
				b = x;
				if (included[b] == false && near[x] >= 0 && adjacency[a][b] < min) {
					min = adjacency[a][b]; // 최소비용을 가지는 간선 설정
					a = near[x];
					b = x;
					np = x;
					list[0][0] = a;
					list[0][1] = b;
				}
			}
			System.out.print("(" + list[0][0] + ", " + list[0][1] + "): " + (int) min + "  ");
			sp = np;
			sum += min;
		}
		System.out.println();
		System.out.println("총 비용 : " + (int) sum);
	}
}

