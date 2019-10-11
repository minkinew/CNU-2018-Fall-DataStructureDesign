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
			BufferedReader in = new BufferedReader(new FileReader(gf)); // ���� �о����
			String line = in.readLine(); /// line�� ù���� �Ҵ�
			String lineSplit[] = line.split(" "); // ��������� �迭�� �ε�����ȣ �ο�
			
			included = new boolean[Integer.parseInt(lineSplit[0])]; // included�迭 �ʱ�ȭ
			near = new int[Integer.parseInt(lineSplit[0])]; // near�迭 �ʱ�ȭ
			adjacency = new float[Integer.parseInt(lineSplit[0])][Integer.parseInt(lineSplit[0])]; // adgacency��� �ʱ�ȭ
			size = near.length;
			list = new int[Integer.parseInt(lineSplit[1]) - 1][2]; //list�迭 �ʱ�ȭ
			
			line = in.readLine(); // ���� ���� ����

			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (i == j) // �ڱ� �ڽ��� 0���� �ʱ�ȭ
						adjacency[i][j] = 0;
					adjacency[i][j] = Float.MAX_VALUE; // �ƹ��͵� ��������� ���� adjacency��� ����
				}
			}

			while (line != null) { // ���� ���� ���� ������ ��� �ݺ�
				String lineSplit1[] = line.split(" ");
				add(Integer.parseInt(lineSplit1[0]), Integer.parseInt(lineSplit1[1]), Integer.parseInt(lineSplit1[2]));
				line = in.readLine();
			}
		} catch (IOException e) { // ����ó��
			e.printStackTrace();
		}
	}

	public void add(int u, int v, float weight) {
		adjacency[u][v] = adjacency[v][u] = weight;
	}

	public void prim() {
		int sp = 0; // ��������
		int np = 0; // ��������
		float sum = 0; // ����ġ ��

		for (int i = 0; i < size; i++) { // �ƹ��͵� ��������� ����
			included[i] = false; // included�迭 ����
			near[i] = -1; // �ʱ� near�迭�� -1�� ����
		}

		System.out.println("���õ� ������");

		for (int j = 0; j < size - 1; j++) { // T�� �߰��� ������ ���� ����-1 �� �� ������
			int n = Integer.MIN_VALUE; // N�� ����
			float min = Float.MAX_VALUE; // �ּڰ��� ã������ ����

			included[sp] = true; // TV�� �湮�ߴٰ� üũ
			near[sp] = n; // near�迭 ������Ʈ

			for (int k = 1; k < size; k++) { // 0��°�� Ž���� �ʿ䰡 �����Ƿ� k=1����
				if (near[k] != n) { // near�迭 ����
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
					min = adjacency[a][b]; // �ּҺ���� ������ ���� ����
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
		System.out.println("�� ��� : " + (int) sum);
	}
}

