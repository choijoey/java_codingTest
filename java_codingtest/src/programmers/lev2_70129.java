package programmers;


public	class lev2_70129 {
		static int b;
	    public static int[] solution(String s) {
	    	
	    	
	    	int a = 0 ; //변환된 횟수
	    	b = 0 ; //제거된 0의 수
	    	
	    	while(s.compareTo("1") != 0) { //1이 될때까지 계속 돌림
	    		s =cal(s); // 1의 수
	    		
	    		//2진법 변환 함수
	    		
	    		a++;
	    		
	    	}
	    	

	        int[] answer = {a,b};
	        return answer;
	    }

	    static String cal(String s) {
	    	char[] li = s.toCharArray();
	    	int tmp = 0; //1의 수
	    	
	    	for(int i=0;i<s.length();i++) {
	    		if(li[i] == '1')
	    			tmp++;
	    		else
	    			b++;
	    	}
	    	
	    	String res ="";
	    	while(tmp >0) {
	    		
	    		int a = tmp%2;
	    		tmp/=2;
	    		
	    		if(a == 1) {
	    			res+="1";
	    		}
	    		else
	    			res+="0";
	    		
	    	}
	    	
	    	return res;
	    }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		solution("110010101001");
	}

}
