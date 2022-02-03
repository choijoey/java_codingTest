package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class baek_1929 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int M=Integer.parseInt(st.nextToken());
		int N=Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N+1];
		
		List<Integer> ans = new ArrayList<>(); 
		//�Ҽ� �Է¹޴� �迭
		
		
		//arr 0�̸� �Ҽ�
		
		
		for(int i=2;i<=N;i++) {
			if(arr[i] == 0) {//i�� �Ҽ����
				
				if(i>=M) // �Ҽ��� M ���� ũ�ٸ� �߰�
					ans.add(i);
				
				for(int j=i;j<=N;j++) {
					
					//�����÷ο� ���� 
					long temp = (long)i*j;
					
					//N�������� �� ũ�� ������
					if(temp >=N+1)
						break;
					//�Ҽ��� ������� ����
					if(arr[(int) temp] == 0)
						arr[(int) temp]=1;
					
				}
			}
			
		}
		
		for(int tmp:ans) {
				sb.append(tmp+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		
	}

}
