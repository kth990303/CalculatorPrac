package onlyPlusMinus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// ���� �� �ڸ��� �Է� ����.
// Scanner�� ���� ȿ�� ���� bufferedreader �̿�.
class Main {

	public static void main(String[] args) throws IOException{
		String input="";
		
		BufferedReader buf=new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("���� �Է�: ");
		input=buf.readLine();
		
		
		noBracketInfix calc=new noBracketInfix(input);
		//System.out.println(calc.changeInfix());
		System.out.println("answer: "+calc.calcInfix());
	}

}
