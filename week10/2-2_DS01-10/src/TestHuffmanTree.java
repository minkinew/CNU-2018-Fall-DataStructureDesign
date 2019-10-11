public class TestHuffmanTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HuffmanTree HFT = new HuffmanTree();

		HFT.countAlphabet();
		HFT.makeHuffmanTree();

		int[] arr = new int[HFT.size() - 1];
		HFT.print(HFT.huffman(), arr, 0);

		System.out.print("Huffman code Stream: ");
		HFT.printBitStream();
	}

}