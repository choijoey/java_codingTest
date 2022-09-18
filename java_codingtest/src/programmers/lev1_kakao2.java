package programmers;

import java.util.*;

//https://programmers.co.kr/learn/courses/30/lessons/72410
class lev_1kakao2 {
    public String solution(String new_id) {
        String answer = "";
        
        

        //1�ܰ�
        new_id =new_id.toLowerCase();
        

        //2�ܰ�
        String s ="";
        
        for(int i=0;i<new_id.length();i++ ){
            if((new_id.charAt(i) >= 'a'&& new_id.charAt(i) <='z') ||
               (new_id.charAt(i) >='0' &&  new_id.charAt(i)<='9') ||  new_id.charAt(i) =='-'||
               new_id.charAt(i) == '_' ||  new_id.charAt(i)=='.'){
                s +=new_id.charAt(i);
            }
        }
        
        new_id = s; 

        
        //3�ܰ�
        while(new_id.contains("..")){
            new_id = new_id.replace("..","."); 
        }

        //4�ܰ�
        s="";

        for(int i=0; i<new_id.length();i++){
            if(i==0 &&new_id.charAt(i) == '.')
                continue;
            if(i==new_id.length()-1&&new_id.charAt(i) == '.')
                continue; 
            s+= new_id.charAt(i);
        }
        
        new_id = s;
        

        //5 �ܰ�
        if(new_id.length() == 0)
            new_id+='a';

        //6 �ܰ�
        
        s="";
        if(new_id.length() >=16){
            for(int i =0;i<15;i++){
                if(i==14&& new_id.charAt(i) =='.')
                    continue;
                s+=new_id.charAt(i);
            }
            new_id=s;
        }

        //7 �ܰ�
        answer = new_id;
        
        if(new_id.length() <=2){
              s=new_id;
            while(s.length()!=3){
                s+=new_id.charAt(new_id.length()-1);
            }
            answer = s;
        }
        return answer;
    }
}