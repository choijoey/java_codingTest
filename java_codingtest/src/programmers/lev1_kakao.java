package programmers;

import java.util.*;

//https://programmers.co.kr/learn/courses/30/lessons/92334
//신고 결과 받기 
//다시 풀기 

class lev1_kakao {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        
        Map<String , Integer> index =new HashMap<>();
        Map<String, List<Integer>> map= new HashMap<>();
        
        for(int i =0; i <id_list.length;i++){
            index.put(id_list[i],i);
        }

        for(int i =0; i <report.length;i++){
            String[] ids =report[i].split(" ");
            String from_id =ids[0];
            String to_id = ids[1];
            
            if(!map.containsKey(to_id)){
                map.put(to_id,new ArrayList<>());
            }
            List<Integer> tmp = map.get(to_id);
            if(!tmp.contains(index.get(from_id))){
             tmp.add(index.get(from_id));
            }
        }

        for(int i =0; i <id_list.length ;i++){
            String s = id_list[i];
                        
            if(map.containsKey(s) && map.get(s).size()>=k){
                for(int idx :map.get(s) )
                answer[idx]++;
            }
        }
        
        
        return answer;
    }
}