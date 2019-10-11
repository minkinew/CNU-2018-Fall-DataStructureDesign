public class Deap {
	int[] deap;
	int n = 0; // deap�� �ִ� ������ ����; ��Ʈ�� ��� �ִ�.

	public Deap(int maxSize) {
		deap = new int[maxSize];
	}

	// deap�� ũ�⸦ i�� ������Ű�� ������ ���Ҹ� �����Ѵ�.
	private void increaseheap(int i) {
		int[] b = new int[i];
		System.arraycopy(deap, 0, b, 0, deap.length);
		deap = b;
	}

	// �ε��� i�� min-heap�� ��ġ�� ������ true�� �����ϰ�, �׷��� ������ false�� �����Ѵ�
	private boolean inMinHeap(int i) { 
		while (i != 1 && i != 2) {
			i = (i - 1) / 2; // �θ���ġ ���
		}

		if (i == 1) // minheap�� ���
			return true;
		else // maxheap�� ���
			return false;
	}

	// �ε��� i�� min-heap�� ��ġ�� ������ max partner�� �ε����� �����Ѵ�
	private int maxPartner(int i) {
		double a = Math.log10(i + 1) / Math.log10(2); //�αװ��
		int b = (int) Math.floor(a) - 1; //�Ҽ��� ���� ����
		int result = b; // 2�� �����κа��
		int j = (int) (i + Math.pow(2, result)); // j�� ���

		if (j >= n)
			j = (j - 1) / 2;
		return j;
	}

	// �ε��� i�� max-heap�� ��ġ�� ������ min partner�� �ε����� �����Ѵ�
	private int minPartner(int i) {	
		int j = (int) (i - Math.pow(2, Math.floor((Math.log10(i + 1) / Math.log10(2)) - 1)));
		return  j;
	}

	// min-heap�� �ִ� �ε��� at ��ġ�� key�� ����
	private void minInsert(int at, int key) {
		int child = at; // �ڽ���ġ
		int parent; // �θ���ġ
		deap[at] = key; // key�� ����
		int temp;
		
		while (true) {
			parent = (child - 1) / 2; // �θ���ġ ���
			if (deap[parent] > deap[child]) { // �θ� �� ũ�� �� �籸��
				temp = deap[parent];
				deap[parent] = deap[child];
				deap[child] = temp;
				child = parent;
			} else
				break;
		}
	}

	// max-heap�� �ִ� �ε��� at ��ġ�� key�� ����
	private void maxInsert(int at, int key) {
		int child = at; // �ڽ���ġ
		int parent; // �θ���ġ
		deap[at] = key; // key�� ����
		int temp;
		
		while (true) {
			if (at == 2) // max-heap�� index = 2����ġ�� �ִ밪�� �����Ƿ� �� �籸��X
				break;
			parent = (child - 1) / 2;
			if (deap[parent] < deap[child]) { // �ڽ��� �� ũ�� �� �籸��
				temp = deap[parent];
				deap[parent] = deap[child];
				deap[child] = temp;
				child = parent;
			} else
				break;
		}
	}

	// max ���� �����Ͽ� �����Ѵ�
	public int deleteMax() {
		int temp = deap[n--]; //������ ��带 temp�� �����ϰ�, deap���� ����
		int key = deap[2]; //�ִ밪�� ���� ��带 deap���� ����
		int i = 2;
		int j = 0;
		
		while (true) {
			if (deap[(i * 2) + 1] > deap[(i * 2) + 2]) //���� �ڽ��� �� Ŭ ��
				j = (i * 2) + 1; 
			else //������ �ڽ��� �� Ŭ ��
				j = (i * 2) + 2; 
			
			deap[i] = deap[j];
			i = j;
			
			 if ((2 * j) + 1 >= n) //��������Ʈ�� ���� �Ҹ��� ��
				break;
		}
		j = minPartner(i); //�����Ǵ� �ε���partner
		
		int x = j;
		if (deap[(j * 2) + 1] > deap[(j * 2) + 2]) //minPartner�� ���� �ڽ��� Ŭ ��
			x = (j * 2) + 1; 
		else if (deap[(j * 2) + 1] < deap[(j * 2) + 2]) //minPartner�� ������ Ŭ ��
			x = (j * 2) + 2;
		
		if (temp < deap[x]) { //minheap�� insert����
			deap[i] = deap[x];
			minInsert(x, temp);
		} else //maxheap�� insert����
			maxInsert(i, temp);
		
		return key;

	}

	// min ���� �����Ͽ� �����Ѵ�
	public int deleteMin() {
		int temp = deap[n--]; //������ ��带 temp�� �����ϰ�, deap���� ����
		int key = deap[1]; //�ּҰ��� ���� ��带 deap���� ����
		int i = 1;
		int j = 0;
		
		while (true) { //���������� ���鼭 �� �籸��
			if (deap[(i * 2) + 1] < deap[(i * 2) + 2]) //���� �ڽ��� �� ���� ��
				j = (i * 2) + 1; 
			else //������ �ڽ��� �� ���� ��
				j = (i * 2) + 2;
			
			deap[i] = deap[j];
			i = j;
			
			if (2 * j >= n) //��������Ʈ�� ���� �Ҹ��� ��
				break;
		}
		j = maxPartner(i); //�����Ǵ� �ε���partner
		
		if (temp > deap[j]) { //maxheap�� insert����
			deap[i] = deap[j];
			maxInsert(j, temp);
		} else //minheap�� insert����
			minInsert(i, temp);
		
		return key;
	}

	// x�� �����Ѵ�
	public void insert(int x) {
		if (n == deap.length - 1) {
			System.out.println("The heap is full. The heap size is doubled");
			increaseheap(deap.length * 2);
		}
		n++;
		if (n == 1) {
			deap[1] = x;
			return;
		}

		if (inMinHeap(n)) {
			int i = maxPartner(n);
		
			if (x > deap[i]) {
				deap[n] = deap[i];
				maxInsert(i, x);
			} else 
				minInsert(n, x);
			
		} else {
			int i = minPartner(n);
		
			if (x < deap[i]) {
				System.out.println("deen[n] " + n + "  deap[i]" + deap[i]);
				deap[n] = deap[i]; 
				minInsert(i, x);
			} else 
				maxInsert(n, x);
		}
	}

	// deap�� ����Ʈ�Ѵ�
	public void print() {
		int levelNum = 2;
		int thisLevel = 0;
		int gap = 8;
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < gap - 1; j++) {
				System.out.print(" ");
			}
			if (thisLevel != 0) {
				for (int j = 0; j < gap - 1; j++) {
					System.out.print(" ");
				}
			}
			if (Integer.toString(deap[i]).length() == 1) {
				System.out.print(" ");
			}
			System.out.print(deap[i]);
			thisLevel++;
			if (thisLevel == levelNum) {
				System.out.println();
				thisLevel = 0;
				levelNum *= 2;
				gap /= 2;
			}
		}
		System.out.println();
		if (thisLevel != 0) {
			System.out.println();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Deap a = new Deap(10);
		int i = 0;
		int[] data = { 4, 65, 8, 9, 48, 55, 10, 19, 20, 30, 15, 25, 50 };
		for (i = 0; i < 9; i++) {
			a.insert(data[i]);
			// System.out.print("z");
		}

		System.out.println("initial Deap");
		a.print();

		for (; i < data.length; i++) {
			a.insert(data[i]);
		}

		System.out.println("enlarged Deap");
		a.print();

		System.out.println("delete Min");
		a.deleteMin();
		a.print();

		System.out.println("delete Min");
		a.deleteMin();
		a.print();

		System.out.println("delete Min");
		a.deleteMin();
		a.print();

		System.out.println("delete Max");
		a.deleteMax();
		a.print();

		System.out.println("delete Max");
		a.deleteMax();
		a.print();

		System.out.println("delete Max");
		a.deleteMax();
		a.print();

	}
}