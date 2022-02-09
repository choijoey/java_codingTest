package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class baek_5397 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		while(T>0) {
			//커서 이전 단어들 
			Stack <Character> stack = new Stack<>();
			//커서 이후 단어들 
			Stack <Character> stack2 = new Stack<>();
			
			//입력 문장
			String s =br.readLine();
			
			//문장을 idx로 서치
			for(int i=0;i<s.length();i++){
				
				char tmp = s.charAt(i);
				
				switch(tmp) {
				case '<':
					//커서 이전 단어 스텍이 비어있지 않다면 pop하고 커서 이후 스텍으로 push
					if(!stack.empty())
						stack2.push(stack.pop());
					break;
				case '>':
					//커서 이후 단어 스텍이 비어있지 않다면 pop하고 커서 이전 스텍으로 push
					if(!stack2.empty())
						stack.push(stack2.pop());
					break;
				case '-':
					//커서 이전 단어 스텍이 비어있지 않다면 pop
					if(stack.empty())
						break;
					stack.pop();
					break;
					
				default:
					//일반 단어들 push
					stack.push(tmp);	
				}
			}
			
			//출력 
			StringBuilder sb = new StringBuilder();
			int size = stack.size();
			for(int i=0;i<size;i++) {
				stack2.push(stack.pop());
			}
			size=stack2.size();
			for(int i=0;i<size;i++) {
				sb.append(stack2.pop());
			}
			bw.write(sb.toString()+"\n");
			bw.flush();
			
			T--;
		}
		bw.close();
	}

}
