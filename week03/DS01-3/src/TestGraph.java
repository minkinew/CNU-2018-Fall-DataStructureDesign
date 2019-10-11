import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestGraph {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph graph;
		try {
			BufferedReader in = new BufferedReader(new FileReader("graph.txt")); // 'graph.txt'������ �ҷ��� ��ü�� �ο�
			String line = in.readLine(); // line�� ù���� �Ҵ�
			String lineSplit[] = line.split(" "); // ��������� �迭�� �ε�����ȣ �ο�

			graph = new Graph(lineSplit);
			line = in.readLine();

			while (line != null) { // ������ �������� ��������� �ݺ�
				String lineSplit_1[] = line.split(" ");
				graph.add(lineSplit_1[0], lineSplit_1[1]);
				line = in.readLine();
			}

			System.out.println(graph); // �������� ������� ���
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

		catch (IOException e) { // ����ó��
			System.out.println(e);

		}
	}
}
