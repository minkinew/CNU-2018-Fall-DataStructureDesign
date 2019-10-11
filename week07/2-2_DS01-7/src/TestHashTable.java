import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestHashTable {
	public static void main(String[] args) {
		java.util.Map<String, Integer> map = new java.util.HashMap<String, Integer>();

		try {
			BufferedReader in = new BufferedReader(new FileReader("Caesar.txt")); // 'Caesar.txt'������ �ҷ��� ��ü�� �ο�
			String line = in.readLine(); // line�� ù���� �Ҵ�

			while (line != null) { // ������ �������� ��������� �ݺ�
				if (map.containsKey(line.toUpperCase())) {
					int value = map.get(line.toUpperCase());
					map.put(line.toUpperCase(), ++value);
				} 
				else
					map.put(line.toUpperCase(), 1);
				line = in.readLine();
			}
		} catch (IOException e) { // ����ó��
			System.out.println(e);
		}

		System.out.println("***** HashMap���� ���� �ܾ���� �� (value) : < I  You  he  Brutus  evil  the  and >");
		System.out.println("I : " + map.get("I"));
		System.out.println("You : " + map.get("YOU"));
		System.out.println("he : " + map.get("HE"));
		System.out.println("Brutus : " + map.get("BRUTUS"));
		System.out.println("evil : " + map.get("EVIL"));
		System.out.println("the : " + map.get("THE"));
		System.out.println("and : " + map.get("AND"));

	}
}
