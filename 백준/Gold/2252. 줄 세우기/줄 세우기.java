import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] in = new int[N+1];
		ArrayList<Integer>[] out = new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			out[i] = new ArrayList<>();
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			out[a].add(b);
			in[b]++;
		}
		ArrayDeque<Integer> q = new ArrayDeque<>();
		for(int i=1; i<=N; i++) {
			if(in[i]==0) {
				q.offer(i);
			}
		}
		while(!q.isEmpty()) {
			int cur = q.poll();
			sb.append(cur).append(" ");
			for(int i: out[cur]) {
				if(--in[i]==0) {
					q.offer(i);
				}
				
			}
		}
		System.out.println(sb);
	}
}
