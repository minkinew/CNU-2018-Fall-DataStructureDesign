import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class HuffmanTree {
	public HashMap<Character, Integer> freq = new HashMap<Character, Integer>(100);
	public Node huffmanTree = null;
	public ArrayList<Integer> huffmanCode = new ArrayList<Integer>();
	MinHeap mh = new MinHeap();
	
	public Node huffman() {
		return huffmanTree;
	}

	public void countAlphabet() { // 알파벳 빈도수를 찾는 메소드
		try {
			BufferedReader in = new BufferedReader(new FileReader("text.txt")); // 파일로 부터 데이터를 읽어옴
			String line = in.readLine(); // 한 줄을 읽어드림
			line = line.toLowerCase(); // 대소문자 구분X(소문자)

			for (int i = 0; i < line.length(); i++) { // 알파벳의 갯수를 하나씩 셈
				char c = line.charAt(i); // 해당하는 문자를 반환
				if (!freq.containsKey(c))
					freq.put(c, 1);
				else
					freq.put(c, freq.get(c) + 1);
			}
			System.out.println("Encoding this String : " + line);
			for (char key : freq.keySet()) { // 힙에 각각의 빈도수와 알파벳 저장
				mh.add(new Node(freq.get(key), key));
			}
			in.close();
		} catch (IOException e) {
			System.exit(0);
		}
	}

	public void makeHuffmanTree() {
		if (freq.isEmpty()) // 빈도 수가 0이면 return null
			return;

		while (true) {
			// 최고 우선순위를 가지는 노드 2개 추출
			Node child_Left = mh.get();
			Node child_Right = mh.get();

			// 새로운 부모노드 생성(자식들의 합)
			huffmanTree = new Node(child_Left.count + child_Right.count, '.');
			huffmanTree.leftNode = child_Left;
			huffmanTree.rightNode = child_Right;

			// 힙이 비어있으면 허프만트리 생성
			if (mh.isEmpty())
				return;

			mh.add(huffmanTree);
		}
	}

	public int size() {
		return freq.size();
	}

	// 허프만트리의 root를 받으면 각각 문자의 코드를 출력하는 메소드
	public void print(Node htRoot, int[] bitStream, int cnt) {

		// 왼쪽자식과 오른쪽자식이 없을 때
		if (htRoot.leftNode == null && htRoot.rightNode == null) {
			System.out.print(htRoot.alpha + "(빈도 수: " + htRoot.count + "): ");
			printHuffmanCode(bitStream, cnt);
		}

		// 왼쪽 자식이 리프노드이면 출력할 배열에 0추가
		if (htRoot.leftNode != null) {
			bitStream[cnt] = 0;
			print(htRoot.leftNode, bitStream, cnt + 1);
		}

		// 오른쪽 자식이 리프노드이면 출력할 배열에 1추가
		if (htRoot.rightNode != null) {
			bitStream[cnt] = 1;
			print(htRoot.rightNode, bitStream, cnt + 1);
		}
	}

	public void printHuffmanCode(int[] array, int cnt) { // 허프만코드를 출력하는 메소드
		for (int i = 0; i < cnt; i++) {
			System.out.print(array[i]);
			huffmanCode.add(array[i]);
		}
		System.out.println("");
	}

	public void printBitStream() { // 비트스트림을 출력하는 메소드
		for (int i = 0; i < huffmanCode.size(); i++)
			System.out.print(huffmanCode.get(i));
	}
}

class Node {
	public Node leftNode;
	public Node rightNode;
	public int count;
	public char alpha;

	public Node(Node left, Node right, int count) {
		this.leftNode = left;
		this.rightNode = right;
		this.count = count;
	}

	public Node(int count, char alpha) {
		this.count = count;
		this.alpha = alpha;
	}
}