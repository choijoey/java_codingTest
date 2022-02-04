package programmers;
//https:programmers.co.kr/learn/courses/30/lessons/77484
	
class lev1_dev_matching {
    public static int cal(int num){
        if(num ==6){
            return 1;
        }else if(num ==5){
            return 2;
        }else if(num ==4){
            return 3;
        }else if(num ==3){
            return 4;
        }else if(num ==2){
            return 5;
        }else
            return 6;
    }
    public int[] solution(int[] lottos, int[] win_nums) {
        

        int win_cnt =0;
        int zero_cnt = 0;

        for(int j=0;j<lottos.length;j++){
            for(int i=0;i<win_nums.length;i++){
                if(win_nums[i] == lottos[j])
                    win_cnt++;
            }
            if(lottos[j]==0)
                zero_cnt++;
        }
        //최저순위,최고순위
        int[] answer = {cal(win_cnt+zero_cnt),cal(win_cnt)};
        
        return answer;
    }
}
