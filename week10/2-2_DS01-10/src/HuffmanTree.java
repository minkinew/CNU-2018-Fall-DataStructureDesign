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

	public void countAlphabet() { // ���ĺ� �󵵼��� ã�� �޼ҵ�
		try {
			BufferedReader in = new BufferedReader(new FileReader("text.txt")); // ���Ϸ� ���� �����͸� �о��
			String line = in.readLine(); // �� ���� �о�帲
			line = line.toLowerCase(); // ��ҹ��� ����X(�ҹ���)

			for (int i = 0; i < line.length(); i++) { // ���ĺ��� ������ �ϳ��� ��
				char c = line.charAt(i); // �ش��ϴ� ���ڸ� ��ȯ
				if (!freq.containsKey(c))
					freq.put(c, 1);
				else
					freq.put(c, freq.get(c) + 1);
			}
			System.out.println("Encoding this String : " + line);
			for (char key : freq.keySet()) { // ���� ������ �󵵼��� ���ĺ� ����
				mh.add(new Node(freq.get(key), key));
			}
			in.close();
		} catch (IOException e) {
			System.exit(0);
		}
	}

	public void makeHuffmanTree() {
		if (freq.isEmpty()) // �� ���� 0�̸� return null
			return;

		while (true) {
			// �ְ� �켱������ ������ ��� 2�� ����
			Node child_Left = mh.get();
			Node child_Right = mh.get();

			// ���ο� �θ��� ����(�ڽĵ��� ��)
			huffmanTree = new Node(child_Left.count + child_Right.count, '.');
			huffmanTree.leftNode = child_Left;
			huffmanTree.rightNode = child_Right;

			// ���� ��������� ������Ʈ�� ����
			if (mh.isEmpty())
				return;

			mh.add(huffmanTree);
		}
	}

	public int size() {
		return freq.size();
	}

	// ������Ʈ���� root�� ������ ���� ������ �ڵ带 ����ϴ� �޼ҵ�
	public void print(Node htRoot, int[] bitStream, int cnt) {

		// �����ڽİ� �������ڽ��� ���� ��
		if (htRoot.leftNode == null && htRoot.rightNode == null) {
			System.out.print(htRoot.alpha + "(�� ��: " + htRoot.count + "): ");
			printHuffmanCode(bitStream, cnt);
		}

		// ���� �ڽ��� ��������̸� ����� �迭�� 0�߰�
		if (htRoot.leftNode != null) {
			bitStream[cnt] = 0;
			print(htRoot.leftNode, bitStream, cnt + 1);
		}

		// ������ �ڽ��� ��������̸� ����� �迭�� 1�߰�
		if (htRoot.rightNode != null) {
			bitStream[cnt] = 1;
			print(htRoot.rightNode, bitStream, cnt + 1);
		}
	}

	public void printHuffmanCode(int[] array, int cnt) { // �������ڵ带 ����ϴ� �޼ҵ�
		for (int i = 0; i < cnt; i++) {
			System.out.print(array[i]);
			huffmanCode.add(array[i]);
		}
		System.out.println("");
	}

	public void printBitStream() { // ��Ʈ��Ʈ���� ����ϴ� �޼ҵ�
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