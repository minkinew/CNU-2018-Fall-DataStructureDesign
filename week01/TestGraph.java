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
			
			System.out.println(graph); //정점들의 연결상태 출력
			System.out.println();
			
			System.out.println("--------연결상태---------");
			System.out.println("A : " + graph.degree("A") + "개");
			System.out.println("B : " + graph.degree("B") + "개");
			System.out.println("C : " + graph.degree("C") + "개");
			System.out.println("D : " + graph.degree("D") + "개");
			System.out.println("E : " + graph.degree("E") + "개");
			System.out.println("F : " + graph.degree("F") + "개");
			System.out.println();
			
			System.out.println("-------차수가 2인 경우-------");
			for(int i = 0; i < lineSplit.length; i++) {
	            for(int j = 0 ; j < lineSplit.length; j++) {
	            	if(i==j)
	            		continue;
	            	graph.findPath(graph.vertices[i], graph.vertices[j]);
	            }	
			}
		}

		catch (IOException e) { //예외처리
			System.out.println(e);
		}
	}
}
