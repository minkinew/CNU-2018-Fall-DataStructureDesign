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
			BufferedReader in = new BufferedReader(new FileReader(gf)); // ���� �о����
			String line = in.readLine(); /// line�� ù���� �Ҵ�
			String lineSplit[] = line.split(" "); // ��������� �迭�� �ε�����ȣ �ο�
			size = Integer.parseInt(lineSplit[1]);
			a = new Edge[Integer.parseInt(lineSplit[1])]; // a�迭 �ʱ�ȭ
			p = new int[Integer.parseInt(lineSplit[0])]; // p�迭 �ʱ�ȭ
			
			for (int i = 0; i < p.length; i++)
				p[i] = -1; // p�迭�� ���Ҹ� -1�� �ʱ�ȭ

			line = in.readLine(); // ���� ���� ����

			while (line != null) { // ���� ���� ���� ������ ��� �ݺ�
				String lineSplit1[] = line.split(" ");
				a[cnt] = new Edge(Integer.parseInt(lineSplit1[0]), Integer.parseInt(lineSplit1[1]),
						Integer.parseInt(lineSplit1[2]));
				line = in.readLine();
				cnt++;
			}
		} catch (IOException e) { // ����ó��
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private class Edge {
		int v, w, weight; // �� ������ ����ġ �ʵ�
		boolean selected; // �ּ� ���� Ʈ���� ���� �Ǵ�

		public Edge(int v, int w, int weight) {
			this.v = v; // ����v
			this.w = w; // ����w
			this.weight = weight; // ����ġ
		}
	}

	public void union(int set1, int set2) { // �� ��带 ��ġ�� �޼ҵ�
		p[set1] = set2; // set1�� �θ� set2��
	}

	public int find(int set) { // ��Ʈ ��带 ã�� �޼ҵ�
		while (p[set] >= 0) { // ������ ���� ������ �ݺ�
			set = p[set];
		}
		return set;
	}

	public void weightedunion(int set1, int set2) { // ��� ���� ���� Ʈ���� �θ�(��Ʈ)�� �ǰ� �޼ҵ�
		int temp = p[set1] + p[set2];
		if (p[set1] > p[set2]) { // set1�� ���� ��带 ������ �ִ� ���
			p[set1] = set2;
			p[set2] = temp;
		} else { // set2�� ���� ��带 �����ų� ���� ���
			p[set2] = set1;
			p[set1] = temp;
		}
	}

	public void collapsingfind(int set) { // ��� �� �ִ� ��带 ��Ʈ�� �ڽ����� ����� �޼ҵ�
		int i = set;
		while(p[i]>= 0) { //��Ʈ��带 ã��
			i = p[i];
		}
		
		int k;
		int j = i;
		while(j != i) { //��λ��� ��� ��带, ã�� ��Ʈ����� �ڽĵ�� ����
			k = p[j];
			p[j] = i;
			j = k;
		}
	}

	public void kruskal() { // �ּ� ��� ���� Ʈ���� ���Ե� edge��� �ּ� ��� ����ϴ� �޼ҵ�
		int root1, root2;
		int eCount = 0;
		int eCost = 0;

		for (int i = 0; i < size; i++) { // ����ġ�� ���������� ����
			for (int j = 0; j < size; j++) {
				if (a[i].weight < a[j].weight) {
					Edge a2 = a[i];
					a[i] = a[j];
					a[j] = a2;
				}
			}
		}

		System.out.println("- �ּ� ���� Ʈ���� ���Ե� ����");
		
		for (int k = 0; k < a.length; k++) {
			if (eCount == p.length - 1) // ������ ������ ������ ũ�⺸�� 1 ������
				break;
			root1 = find(a[k].v); // root1�� element1�� �θ�
			root2 = find(a[k].w); // root2�� element2�� �θ�

			if (root1 == root2) // ����Ŭ����
				continue;

			weightedunion(root1, root2); //weightedunion �Լ� ȣ��

			if (root1 < root2) // �ڽ��� ���� root1�� �� ���� ��
				collapsingfind(a[k].v); // collapsingfind �Լ� ȣ��
			else // �ڽ��� ���� roo2�� �� ���� ��
				collapsingfind(a[k].w); // collapsingfind �Լ� ȣ��

			
			System.out.print("(" + a[k].v + ", " + a[k].w + ")  "); //���� ���

			eCost += a[k].weight; // ����ġ�� �� ���
			eCount++; // ������ ���� ����
		}
		System.out.println();
		System.out.print("Min cost : " + eCost); //�ּ� ��� ���
	}
}
