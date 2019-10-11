public class Deap {
	int[] deap;
	int n = 0; // deap에 있는 원소의 개수; 루트는 비어 있다.

	public Deap(int maxSize) {
		deap = new int[maxSize];
	}

	// deap의 크기를 i로 증가시키고 기존의 원소를 복사한다.
	private void increaseheap(int i) {
		int[] b = new int[i];
		System.arraycopy(deap, 0, b, 0, deap.length);
		deap = b;
	}

	// 인덱스 i가 min-heap에 위치해 있으면 true를 리턴하고, 그렇지 않으면 false를 리턴한다
	private boolean inMinHeap(int i) { 
		while (i != 1 && i != 2) {
			i = (i - 1) / 2; // 부모위치 계산
		}

		if (i == 1) // minheap인 경우
			return true;
		else // maxheap인 경우
			return false;
	}

	// 인덱스 i가 min-heap에 위치해 있을때 max partner의 인덱스를 리턴한다
	private int maxPartner(int i) {
		double a = Math.log10(i + 1) / Math.log10(2); //로그계산
		int b = (int) Math.floor(a) - 1; //소수점 이하 제거
		int result = b; // 2의 지수부분계산
		int j = (int) (i + Math.pow(2, result)); // j값 계산

		if (j >= n)
			j = (j - 1) / 2;
		return j;
	}

	// 인덱스 i가 max-heap에 위치해 있을때 min partner의 인덱스를 리턴한다
	private int minPartner(int i) {	
		int j = (int) (i - Math.pow(2, Math.floor((Math.log10(i + 1) / Math.log10(2)) - 1)));
		return  j;
	}

	// min-heap에 있는 인덱스 at 위치에 key를 삽입
	private void minInsert(int at, int key) {
		int child = at; // 자식위치
		int parent; // 부모위치
		deap[at] = key; // key를 삽입
		int temp;
		
		while (true) {
			parent = (child - 1) / 2; // 부모위치 계산
			if (deap[parent] > deap[child]) { // 부모가 더 크면 힙 재구성
				temp = deap[parent];
				deap[parent] = deap[child];
				deap[child] = temp;
				child = parent;
			} else
				break;
		}
	}

	// max-heap에 있는 인덱스 at 위치에 key를 삽입
	private void maxInsert(int at, int key) {
		int child = at; // 자식위치
		int parent; // 부모위치
		deap[at] = key; // key를 삽입
		int temp;
		
		while (true) {
			if (at == 2) // max-heap은 index = 2인위치가 최대값을 가지므로 힙 재구성X
				break;
			parent = (child - 1) / 2;
			if (deap[parent] < deap[child]) { // 자식이 더 크면 힙 재구성
				temp = deap[parent];
				deap[parent] = deap[child];
				deap[child] = temp;
				child = parent;
			} else
				break;
		}
	}

	// max 값을 삭제하여 리턴한다
	public int deleteMax() {
		int temp = deap[n--]; //마지막 노드를 temp로 저장하고, deap에서 삭제
		int key = deap[2]; //최대값을 갖는 노드를 deap에서 삭제
		int i = 2;
		int j = 0;
		
		while (true) {
			if (deap[(i * 2) + 1] > deap[(i * 2) + 2]) //왼쪽 자식이 더 클 때
				j = (i * 2) + 1; 
			else //오른쪽 자식이 더 클 때
				j = (i * 2) + 2; 
			
			deap[i] = deap[j];
			i = j;
			
			 if ((2 * j) + 1 >= n) //완전이진트리 조건 불만족 시
				break;
		}
		j = minPartner(i); //대응되는 인덱스partner
		
		int x = j;
		if (deap[(j * 2) + 1] > deap[(j * 2) + 2]) //minPartner의 왼쪽 자식이 클 때
			x = (j * 2) + 1; 
		else if (deap[(j * 2) + 1] < deap[(j * 2) + 2]) //minPartner의 오른쪽 클 떄
			x = (j * 2) + 2;
		
		if (temp < deap[x]) { //minheap의 insert과정
			deap[i] = deap[x];
			minInsert(x, temp);
		} else //maxheap의 insert과정
			maxInsert(i, temp);
		
		return key;

	}

	// min 값을 삭제하여 리턴한다
	public int deleteMin() {
		int temp = deap[n--]; //마지막 노드를 temp로 저장하고, deap에서 삭제
		int key = deap[1]; //최소값을 갖는 노드를 deap에서 삭제
		int i = 1;
		int j = 0;
		
		while (true) { //리프노드까지 가면서 힙 재구성
			if (deap[(i * 2) + 1] < deap[(i * 2) + 2]) //왼쪽 자식이 더 작을 때
				j = (i * 2) + 1; 
			else //오른쪽 자식이 더 작을 때
				j = (i * 2) + 2;
			
			deap[i] = deap[j];
			i = j;
			
			if (2 * j >= n) //완전이진트리 조건 불만족 시
				break;
		}
		j = maxPartner(i); //대응되는 인덱스partner
		
		if (temp > deap[j]) { //maxheap의 insert과정
			deap[i] = deap[j];
			maxInsert(j, temp);
		} else //minheap의 insert과정
			minInsert(i, temp);
		
		return key;
	}

	// x를 삽입한다
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

	// deap을 프린트한다
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