package onlyPlusMinus;

import java.util.Stack;
import java.util.ArrayList;

// 괄호가 없는 덧셈뺄셈만 있는 계산기. 
// 괄호 없음 -> 스택에 있는 연산자는 다른 연산자 들어오면 무조건 쳐내기만 하면 됨.
class noBracketInfix {
	String inputToInfix="";
	String infix="";
	boolean onlyDigit=true;
	private Stack stack;		// 연산자 잠깐 담아놓는 스택
	private Stack infixStack;	// 후위표기법으로 담아놓는 스택
	
	// 생성자를 만들어 놓았음. input: 수식 입력.
	public noBracketInfix(String input) {
		super();
		inputToInfix=input;
		stack=new Stack<>();
		infixStack=new Stack<>();
	}
	
	// 후위표기법으로 변환한 것을 return하는 메소드.
	public String getInfix() {
		return infix;
	}


	// 중간에 연산자를 담아놓을 stack. 혹시 몰라 getter method 구현.
	private Stack getStack() {
		return stack;
	}
	
	// 이 method는 후위표기법으로 변환 까지만.
	// infix로 변환할 프로세스 격 메소드.
	public String changeInfix() {
		String toInfix="";
		int k=0, num=0;
		for(int i=0;i<inputToInfix.length();i++) {
			char token=inputToInfix.charAt(i);
			
			// 숫자이면 바로 후위표기 infix로 넣어주고
			
			if(isNumber(token)) {
				// toInfix+=token;	// 한자리면 그냥 이렇게 처리하면 됨.
				
				// ++i에서 index초과하는 에러 발생
				// catch문으로 해결
				// 두 자리 이상이면 infixStack 이용
				// 아직 제대로 계산안됨.
				try {
					while(isNumber(inputToInfix.charAt(++i))) {
						k++;
						onlyDigit=false;
					}
					for(int j=0;j<=k;j++)
						i--;
						
					num+=(token-'0')*Math.pow(10, k);
					if(k>0) 
						k--;
				} catch(IndexOutOfBoundsException e) {
					--i;
					num+=(token-'0')*Math.pow(10, k);
				}
				toInfix+=num;
			}
			// 연산자이면 스택 계산. 단, 괄호도 없고 +-뿐이니 바로 pop해주고 token 넣자.
			else {
				k=0;
				if(!stack.isEmpty()) {
					toInfix+=stack.pop();
				}
				stack.push(token);
				num=0;
			}
		}
		// 스택에 쌓인 연산자들 쫙 빼내자.
		while(!stack.isEmpty())
			toInfix+=stack.pop();
		stack.clear();
		infix=toInfix;
		return toInfix;
	}
	
	// 후위표기법으로 표시된 infix를 계산하는 최종 메소드
	public int calcInfix() {
		if(onlyDigit)
			changeInfix();
		else
			changeInfix();
		for(int i=0;i<infix.length();i++) {
			char token=infix.charAt(i);
			
			if(isNumber(token))
				stack.push(token-'0');	// 숫자로 변환하고 스택에
			else {
				int op2=(int)stack.pop();
				int op1=(int)stack.pop();
				
				switch(token) {
				case '+':
					stack.push(op1+op2);
					break;
				case'-':
					stack.push(op1-op2);
					break;
				}
			}
		}
		return (int)stack.pop();
	}
	
	// 숫자인지 문자인지 구별해볼까
	private boolean isNumber(char token) {
		if(token>='0'&&token<='9')
			return true;	// 숫자면 true
		else
			return false;	// 연산자면 false
	}
}
