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
			
			System.out.println(graph); //�������� ������� ���
			System.out.println();
			
			System.out.println("--------�������---------");
			System.out.println("A : " + graph.degree("A") + "��");
			System.out.println("B : " + graph.degree("B") + "��");
			System.out.println("C : " + graph.degree("C") + "��");
			System.out.println("D : " + graph.degree("D") + "��");
			System.out.println("E : " + graph.degree("E") + "��");
			System.out.println("F : " + graph.degree("F") + "��");
			System.out.println();
			
			System.out.println("-------������ 2�� ���-------");
			for(int i = 0; i < lineSplit.length; i++) {
	            for(int j = 0 ; j < lineSplit.length; j++) {
	            	if(i==j)
	            		continue;
	            	graph.findPath(graph.vertices[i], graph.vertices[j]);
	            }	
			}
		}

		catch (IOException e) { //����ó��
			System.out.println(e);
		}
	}
}
