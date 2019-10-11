import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Graph {
	Edge a[];
	int p[];
	int size;
	int cnt = 0;

	public Graph(String gf) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(gf)); // 파일 읽어오기
			String line = in.readLine(); /// line에 첫줄을 할당
			String lineSplit[] = line.split(" "); // 띄어쓰기단위로 배열에 인덱스번호 부여
			size = Integer.parseInt(lineSplit[1]);
			a = new Edge[Integer.parseInt(lineSplit[1])]; // a배열 초기화
			p = new int[Integer.parseInt(lineSplit[0])]; // p배열 초기화
			
			for (int i = 0; i < p.length; i++)
				p[i] = -1; // p배열의 원소를 -1로 초기화

			line = in.readLine(); // 다음 줄을 읽음

			while (line != null) { // 다음 줄이 없을 때까지 계속 반복
				String lineSplit1[] = line.split(" ");
				a[cnt] = new Edge(Integer.parseInt(lineSplit1[0]), Integer.parseInt(lineSplit1[1]),
						Integer.parseInt(lineSplit1[2]));
				line = in.readLine();
				cnt++;
			}
		} catch (IOException e) { // 예외처리
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private class Edge {
		int v, w, weight; // 두 정점과 가중치 필드
		boolean selected; // 최소 신장 트리에 적용 판단

		public Edge(int v, int w, int weight) {
			this.v = v; // 정정v
			this.w = w; // 정점w
			this.weight = weight; // 가중치
		}
	}

	public void union(int set1, int set2) { // 두 노드를 합치는 메소드
		p[set1] = set2; // set1의 부모를 set2로
	}

	public int find(int set) { // 루트 노드를 찾는 메소드
		while (p[set] >= 0) { // 음수를 만날 때까지 반복
			set = p[set];
		}
		return set;
	}

	public void weightedunion(int set1, int set2) { // 노드 수가 많은 트리가 부모(루트)가 되게 메소드
		int temp = p[set1] + p[set2];
		if (p[set1] > p[set2]) { // set1이 적은 노드를 가지고 있는 경우
			p[set1] = set2;
			p[set2] = temp;
		} else { // set2가 적은 노드를 가지거나 같은 경우
			p[set2] = set1;
			p[set1] = temp;
		}
	}

	public void collapsingfind(int set) { // 경로 상에 있는 노드를 루트의 자식으로 만드는 메소드
		int i = set;
		while(p[i]>= 0) { //루트노드를 찾음
			i = p[i];
		}
		
		int k;
		int j = i;
		while(j != i) { //경로상의 모든 노드를, 찾은 루트노드의 자식들로 변경
			k = p[j];
			p[j] = i;
			j = k;
		}
	}

	public void kruskal() { // 최소 비양 신장 트리에 포함된 edge들과 최소 비용 출력하는 메소드
		int root1, root2;
		int eCount = 0;
		int eCost = 0;

		for (int i = 0; i < size; i++) { // 가중치가 낮은순으로 정렬
			for (int j = 0; j < size; j++) {
				if (a[i].weight < a[j].weight) {
					Edge a2 = a[i];
					a[i] = a[j];
					a[j] = a2;
				}
			}
		}

		System.out.println("- 최소 신장 트리에 포함된 간선");
		
		for (int k = 0; k < a.length; k++) {
			if (eCount == p.length - 1) // 간선의 갯수가 정점의 크기보다 1 작으면
				break;
			root1 = find(a[k].v); // root1은 element1의 부모
			root2 = find(a[k].w); // root2는 element2의 부모

			if (root1 == root2) // 사이클형성
				continue;

			weightedunion(root1, root2); //weightedunion 함수 호출

			if (root1 < root2) // 자식의 수가 root1이 더 많을 떄
				collapsingfind(a[k].v); // collapsingfind 함수 호출
			else // 자식의 수가 roo2가 더 많을 때
				collapsingfind(a[k].w); // collapsingfind 함수 호출

			
			System.out.print("(" + a[k].v + ", " + a[k].w + ")  "); //간선 출력

			eCost += a[k].weight; // 가중치의 합 계산
			eCount++; // 간선의 갯수 증가
		}
		System.out.println();
		System.out.print("Min cost : " + eCost); //최소 비용 출력
	}
}
