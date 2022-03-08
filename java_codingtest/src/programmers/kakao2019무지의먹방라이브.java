package programmers;

import java.util.*;

public class kakao2019무지의먹방라이브 {

	//책은 난이도 중이라 했는데.......
	//난이도 하가 백준 브론즈던데....
	//하도안되서 다른코드 보고했습니다 ㅠ
	
	    public static int solution(int[] food_times, long k) {
	        
	    	int size = food_times.length;
	    	
	    	//우선순위 큐를 이용하여 사이즈가 작은 음식을 기준으로 정렬
	    	PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)-> o1[1]-o2[1]);
	    	
	    	//k가 전채 음식 양보다 많을경우 -1을 리턴하기 위해 계산
	    	long sum =0;

	    	
	    	for(int i=0;i<size;i++) {
	    		pq.offer(new int[] {i+1,food_times[i]});
	    		sum+=food_times[i];
	    	}
	    	
	    	//k가 전채 음식보다 많을경우 -1리턴
	    	if(sum<=k)
	    		return -1;
	    	
	    	//curTime 현재 음식을 빼낼 때 걸리는 시간
	    	//curTime을 계산하기 위한 lastVal (PQ에서 cur 전에 poll한 값)
	    	long lastVal=0;
	    	
	    	while(true) {
	    		int[] cur = pq.peek();
	    		
	    		//이번에 뽑은 값이 0이 되는데 걸리는 시간 = 현재 사이즈의 길이 * (뽑은 값 -전에 뽑은 값)
	    		long curTime = (long)size * ((long)cur[1]-lastVal);
	    		
	    		//K-뽑는데 걸리는 시간이 0이 되거나 더 작은경우 종료
	    		if(0>= k-curTime) {
	    			break;
	    		}
	    		pq.poll();
	    		k= k-curTime;
	    		size--;
	    		lastVal=cur[1];
	    	}
	    	
	    	//남아있는 음식들로 새로운 배열을 만들어 idx기준으로 정렬
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
