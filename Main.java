package onlyPlusMinus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 현재 한 자리만 입력 가능.
// Scanner에 비해 효율 좋은 bufferedreader 이용.
class Main {

	public static void main(String[] args) throws IOException{
		String input="";
		
		BufferedReader buf=new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("수식 입력: ");
		input=buf.readLine();
		
		
		noBracketInfix calc=new noBracketInfix(input);
		//System.out.println(calc.changeInfix());
		System.out.println("answer: "+calc.calcInfix());
	}

}
