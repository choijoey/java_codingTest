package programmers;

//https://programmers.co.kr/learn/courses/30/lessons/81301

public class lev_1_81301 {
	    public int solution(String s) {
	        
	        String tmp = "";
	        
	        int cnt =1;
	        int answer = 0;        

	        for(int i=0;i<s.length();i++){
	            
	            if((s.charAt(i)-'0')>9){
	                tmp+=s.charAt(i);
	            }
	            else{
	                answer*=10;
	                answer+=(s.charAt(i)-'0');    
	                cnt++;   
	            }
	            switch(tmp){
	                case "zero":
	                    answer*=10;  
	                    cnt++;
	                    tmp = "";
	                    break;
	                case "one":
	                    answer*=10;
	                    answer+=1; 
	                    cnt++;tmp = "";
	                    break;
	                case "two":
	                    answer*=10;
	                    answer+=2; 
	                    cnt++;tmp = "";
	                    break;
	                case "three":
	                    answer*=10;
	                    answer+=3; 
	                    cnt++;tmp = "";
	                    break;
	                case "four":
	                    answer*=10;
	                    answer+=4; 
	                    cnt++;tmp = "";
	                    break;
	                case "five":
	                    answer*=10;
	                    answer+=5; 
	                    cnt++;tmp = "";
	                    break;
	                case "six":
	                    answer*=10;
	                    answer+=6; 
	                    cnt++;tmp = "";
	                    break;
	                case "seven":
	                   answer*=10;
	                    answer+=7; 
	                    cnt++;tmp = "";
	                    break;
	                case "eight":
	                    answer*=10;
	                    answer+=8; 
	                    cnt++;tmp = "";
	                    break;
	                case "nine":
	                    answer*=10;
	                    answer+=9; 
	                    cnt++;tmp = "";
	                    break;
	            }
	        }

	       
	        
	        return answer;
	    }
}
