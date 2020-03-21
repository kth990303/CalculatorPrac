package onlyPlusMinus;

import java.util.Stack;
import java.util.ArrayList;

// ��ȣ�� ���� ���������� �ִ� ����. 
// ��ȣ ���� -> ���ÿ� �ִ� �����ڴ� �ٸ� ������ ������ ������ �ĳ��⸸ �ϸ� ��.
class noBracketInfix {
	String inputToInfix="";
	String infix="";
	boolean onlyDigit=true;
	private Stack stack;		// ������ ��� ��Ƴ��� ����
	private Stack infixStack;	// ����ǥ������� ��Ƴ��� ����
	
	// �����ڸ� ����� ������. input: ���� �Է�.
	public noBracketInfix(String input) {
		super();
		inputToInfix=input;
		stack=new Stack<>();
		infixStack=new Stack<>();
	}
	
	// ����ǥ������� ��ȯ�� ���� return�ϴ� �޼ҵ�.
	public String getInfix() {
		return infix;
	}


	// �߰��� �����ڸ� ��Ƴ��� stack. Ȥ�� ���� getter method ����.
	private Stack getStack() {
		return stack;
	}
	
	// �� method�� ����ǥ������� ��ȯ ������.
	// infix�� ��ȯ�� ���μ��� �� �޼ҵ�.
	public String changeInfix() {
		String toInfix="";
		int k=0, num=0;
		for(int i=0;i<inputToInfix.length();i++) {
			char token=inputToInfix.charAt(i);
			
			// �����̸� �ٷ� ����ǥ�� infix�� �־��ְ�
			
			if(isNumber(token)) {
				// toInfix+=token;	// ���ڸ��� �׳� �̷��� ó���ϸ� ��.
				
				// ++i���� index�ʰ��ϴ� ���� �߻�
				// catch������ �ذ�
				// �� �ڸ� �̻��̸� infixStack �̿�
				// ���� ����� ���ȵ�.
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
			// �������̸� ���� ���. ��, ��ȣ�� ���� +-���̴� �ٷ� pop���ְ� token ����.
			else {
				k=0;
				if(!stack.isEmpty()) {
					toInfix+=stack.pop();
				}
				stack.push(token);
				num=0;
			}
		}
		// ���ÿ� ���� �����ڵ� �� ������.
		while(!stack.isEmpty())
			toInfix+=stack.pop();
		stack.clear();
		infix=toInfix;
		return toInfix;
	}
	
	// ����ǥ������� ǥ�õ� infix�� ����ϴ� ���� �޼ҵ�
	public int calcInfix() {
		if(onlyDigit)
			changeInfix();
		else
			changeInfix();
		for(int i=0;i<infix.length();i++) {
			char token=infix.charAt(i);
			
			if(isNumber(token))
				stack.push(token-'0');	// ���ڷ� ��ȯ�ϰ� ���ÿ�
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
	
	// �������� �������� �����غ���
	private boolean isNumber(char token) {
		if(token>='0'&&token<='9')
			return true;	// ���ڸ� true
		else
			return false;	// �����ڸ� false
	}
}
