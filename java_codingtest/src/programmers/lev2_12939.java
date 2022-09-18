package programmers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Solution {
    public String solution(String s) {
        
    	//공백으로 쪼갬
    	String[] li = s.split(" ");
    	
    	//sort쓰려고 ArrayList 선언
    	List<Integer> num = new ArrayList<>();
    	
    	//쪼갠 String Int로 바꿔서 num에 추가
    	for(String tmp : li) {
    		num.add(Integer.parseInt(tmp));
    	}
    	
    	//정렬
    	num.sort((o1,o2)-> (o1-o2));
    	
    	//최솟값
    	String s1 = Integer.toString(num.get(0));
    	
    	//최댓값
    	String s2 = Integer.toString(num.get(num.size()-1));
        return s1+" "+s2;
    }
}

public class lev2_12939 {

}
