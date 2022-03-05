package baekjoon;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

//find set 시간복잡도는 계산하기 어려워서  이번문제의 시간복잡도는 잘 모르겠네요..

public class baek4803 {

	static int[] parent;
	static int[] node;
	static int N;
	
	static void makeSet() {
		parent = new int[N+1];
		
		//트리인 경우 루트만 1처리 
		//나머지는 0
		node = new int[N+1];

		for(int i=1;i<=N;i++) {
			parent[i]=i;
			node[i]=1;
		}
	}
	static int findSet(int a) {

		if(parent[a] ==a)
			return a;
		return parent[a] = findSet(parent[a]);
	}
	static void unionSet(int a,int b) {
		a= findSet(a);
		b = findSet(b);

		//루트 겹침
		if(a==b) {
			for(int i=1;i<=N;i++) {
				//그래프가 아니게 되니까 연결된 그래프는 전부 0처리
				if(findSet(i)==a)
					node[i]=0;
			}
		}
		else {
			//만약 루트가 다르지만 b가 연결된 그래프가 사이클이 있는경우
			if(node[b]==0)
				node[a]=0; //a도 사이클 처리하고 종료 
			else {
			node[b]=0;//a가 사이클이여도 b는 어차피 0 처리 //a가 사이클이 아니면 node[a]만 1 
			parent[b] = a;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;



		int T=1;
		
		while(true) {
			st= new StringTokenizer(br.readLine());
			N= Integer.parseInt(st.nextToken());
			int M= Integer.parseInt(st.nextToken());
			
			if(N==0&&M==0)
				break;
			makeSet();

			for(int i=0;i<M;i++) {
				st= new StringTokenizer(br.readLine());
				int a= Integer.parseInt(st.nextToken());
				int b= Integer.parseInt(st.nextToken());

				unionSet(a,b); 

			}
			int cnt=0;
			for(int i=1;i<=N;i++) {
				cnt+=node[i];
			}
			
			System.out.println(Arrays.toString(parent));
			System.out.println(Arrays.toString(node));

			StringBuilder sb =new StringBuilder();
			sb.append("Case ").append(T++).append(": ");

			if(cnt ==0) {
				sb.append("No trees.\n");
			}
			else if(cnt ==1) {
				sb.append("There is one tree.\n");
			}
			else {
				sb.append("A forest of ").append(cnt).append(" trees.\n");
			}
			bw.write(sb.toString());
		}
		bw.flush();
		bw.close();

	}

}


