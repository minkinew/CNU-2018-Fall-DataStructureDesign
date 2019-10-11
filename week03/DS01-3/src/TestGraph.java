import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestGraph {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph graph;
		try {
			BufferedReader in = new BufferedReader(new FileReader("graph.txt")); // 'graph.txt'파일을 불러와 객체에 부여
			String line = in.readLine(); // line에 첫줄을 할당
			String lineSplit[] = line.split(" "); // 띄어쓰기단위로 배열에 인덱스번호 부여

			graph = new Graph(lineSplit);
			line = in.readLine();

			while (line != null) { // 파일의 다음줄이 계속있으면 반복
				String lineSplit_1[] = line.split(" ");
				graph.add(lineSplit_1[0], lineSplit_1[1]);
				line = in.readLine();
			}

			System.out.println(graph); // 정점들의 연결상태 출력
			System.out.println();
			System.out.println("========== recu_dfs ==========");
			System.out.print("DFS : ");
			graph.recu_dfs("A");
			System.out.println();
			System.out.println();
			
			System.out.println("========== recu_dfs_tree ==========");
			graph.recu_dfs_tree("A");
			System.out.println();
			
			System.out.println("========== dfs_tree ==========");
			graph.dfs_tree();
			System.out.println();

		
		}

		catch (IOException e) { // 예외처리
			System.out.println(e);

		}
	}
}
