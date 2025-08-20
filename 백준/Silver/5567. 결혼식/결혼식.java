import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		ArrayList<Integer>[] list = new ArrayList[N+1];
		boolean[] visited = new boolean[N+1];
		for(int i=0; i<N+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		
		queue.offer(new int[] {1, 0});
		visited[1] = true;
		int answer = 0;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			if(cur[1]==2) continue;
			for(int next: list[cur[0]]) {
				if(!visited[next]) {
					visited[next] = true;
					queue.offer(new int[]{next, cur[1]+1});
					answer += 1;
				}
			}
		}
		System.out.println(answer);
	}
}
