import java.io.*;

public class TestHashTable {
  
	 public static void main(String[] args) {
		LinearProbingHashTable HT1 = new LinearProbingHashTable();
		QudraticProbingHashTable HT2 = new QudraticProbingHashTable();
		DoubleHashingHashTable HT3 = new DoubleHashingHashTable();
		SeparateChainingHashTable HT4 = new SeparateChainingHashTable(); 
    
		try {
			BufferedReader in = new BufferedReader(new FileReader("Caesar.txt")); // 'Caesar.txt'파일을 불러와 객체에 부여
			String line = in.readLine(); // line에 첫줄을 할당
			
			while (line != null) { // 파일의 다음줄이 계속있으면 반복
			HT1.put(line.toUpperCase(), 1);
			HT2.put(line.toUpperCase(), 1);
			HT3.put(line.toUpperCase(), 1);
			HT4.put(line.toUpperCase(), 1);
			line = in.readLine();
			}
		} 
		catch (IOException e) { // 예외처리
			System.out.println(e);
		}
		
		System.out.println("LinearProbingHashing : (" + HT1.getCount() + ")");
		System.out.println("QudraticProbingHashing : (" + HT2.getCount() + ")");
		System.out.println("DoubleProbingHashing : (" + HT3.getCount() + ")");
		System.out.println();
		System.out.println("***** 각 테이블에서 다음 단어들의 값 (value) : < I  You  he  Brutus  evil  the  and >" );
		System.out.println("I : 선형조사(" + HT1.get("I") + "), 제곱조사(" + HT2.get("I") + "), 이중 해싱(" + HT3.get("I") + "), 폐쇄 주소법 (" + HT4.get("I") + ")");
		System.out.println("You : 선형조사(" + HT1.get("YOU") + "), 제곱조사(" + HT2.get("YOU") + "), 이중 해싱(" + HT3.get("YOU") + "), 폐쇄 주소법 (" + HT4.get("YOU") + ")");
		System.out.println("he : 선형조사(" + HT1.get("HE") + "), 제곱조사(" + HT2.get((Object)"HE") + "), 이중 해싱(" + HT3.get("HE") + "), 폐쇄 주소법 (" + HT4.get("HE") + ")");
		System.out.println("Brutus : 선형조사(" + HT1.get("BRUTUS") + "), 제곱조사(" + HT2.get("BRUTUS") + "), 이중 해싱(" + HT3.get("BRUTUS") + "), 폐쇄 주소법 (" + HT4.get("BRUTUS") + ")");
		System.out.println("evil : 선형조사(" + HT1.get("EVIL") + "), 제곱조사(" + HT2.get("EVIL") + "), 이중 해싱(" + HT3.get("EVIL") + "), 폐쇄 주소법 (" + HT4.get("EVIL") + ")");
		System.out.println("the : 선형조사(" + HT1.get("THE") + "), 제곱조사(" + HT2.get("THE") + "), 이중 해싱(" + HT3.get("THE") + "), 폐쇄 주소법 (" + HT4.get("THE") + ")");
		System.out.println("and : 선형조사(" + HT1.get("AND") + "), 제곱조사(" + HT2.get("AND") + "), 이중 해싱(" + HT3.get("AND") + "), 폐쇄 주소법 (" + HT4.get("AND") + ")");
		System.out.println();
	}
}
