package programmers;

import java.util.*;

public class kakao2019무지의먹방라이브 {

	    public static int solution(int[] food_times, long k) {
	        
	    	int size = food_times.length;
	    	PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					if(o1[1]==o2[1])
						return o1[0]-o2[0];
					else
						return o1[1]-o2[1];
				}});
	    	
	    	long sum =0;
	    	for(int i=0;i<size;i++) {
	    		pq.offer(new int[] {i+1,food_times[i]});
	    		sum+=food_times[i];
	    	}
	    	
	    	if(sum<=k)
	    		return -1;
	    	
	    	
	    	long lastVal=0;
	    	
	    	while(true) {
	    		int[] cur = pq.peek();
	    		
	    		long curTime = (long)size * ((long)cur[1]-lastVal);
	    		
	    		if(0>= k-curTime) {
	    			break;
	    		}
	    		pq.poll();
	    		k= k-curTime;
	    		size--;
	    		lastVal=cur[1];
	    	}
	    	ArrayList<Integer> list = new ArrayList<>();
	    	while(!pq.isEmpty()) {
	    		list.add(pq.poll()[0]);
	    	}
	    	list.sort((o1, o2)-> o1-o2);
	    	
	    	int ans = list.get((int) (k%size));
	    	
	    	return ans;

	    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int ans =solution(new int[] {8,6,4}, (long)15);
		System.out.println(ans);
	}

}
