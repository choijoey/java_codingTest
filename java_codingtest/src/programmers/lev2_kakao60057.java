package programmers;

import java.util.*;

class lev2_kakao60057 {
	public int solution(String s) {

		int size = s.length();
		int S = 1;
		Stack<String> stack = new Stack<>();

		int min=size;
		
		while(S<=size/2){

			int idx =0;
			String res="";
			int new_idx=idx+S;

			for(int i=idx; i<(size/S)*S;i+=S){
				
				idx =i;
				String tmp = s.substring(i,i+S);
				stack.push(tmp);

				new_idx=i+S;

				boolean flag = true;

				if(new_idx+S>size) {
					break;
				}
				
					String nextStr=s.substring(new_idx,new_idx+S);

					
					while(stack.peek().equals(nextStr)){

						
						i = new_idx;

						new_idx = new_idx+S;
						stack.push(nextStr);
						if(new_idx+S>size) {
							idx =new_idx;
							break;
						}
							
						nextStr= s.substring(new_idx,new_idx+S);

					}
					
					//같은게 끝났다면 
					if(stack.size() != 1)
						res=res+stack.size()+stack.peek()+"";
					else {
						res= res+stack.peek();
					}
					
					if(!stack.empty())
						stack.clear();



					if(res.length()>min)
						break;
				}
				
			
			for(int j=idx;j<size;j++){
				res+=s.charAt(j)+"";
			}
			


			stack.clear();
			if(res.length() <min){
				min=res.length();
			}
			S++;
		}
		return min;
	}}