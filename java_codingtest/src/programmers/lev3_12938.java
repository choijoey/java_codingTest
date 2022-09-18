package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class lev3_12938 {
	
	static int max;
	static int[] ans;
	public static int[] solution(int n, int s) {
    	ans = new int[n];
    	max = -1;
    	
    	//시작 값  s-(n-1) 나머지 1
    	
    	int[] li = new int[n];
    	
    	li[0] = s-(n-1);
    	
    	for(int i=1;i<n;i++) {
    		li[i] = 1;
    	}
    	
    	
    	cal(0,li,n);
    	Arrays.sort(ans);
    	
    	if(max <=0) {
    		ans= new int[] {-1};
    	}

        return ans;
    }
    
    static void cal(int idx, int[] li, int n) {
    	
    	if(idx+1 == n)
    		return;
    	
    	if(li[idx] <=-1)
    		return;
    	
    	int tmp = 1;
    	
    	for(int i=0;i<n;i++)
    		tmp*=li[i];
    	
    	if(tmp > max) {
    		max =tmp;
    		for(int i=0;i<n;i++)
    			ans[i] = li[i];
    	}
    	
    	li[idx] --;
    	li[idx+1] ++;
    	
    	cal(idx,li,n); 	
    	li[idx] ++;
    	li[idx+1] --;
    	cal(idx+1,li,n);
    	
    }
    
    public static void main(String[] args) {
		solution(71,711);
		System.out.println(Arrays.toString(ans));
		
	}
    
}


