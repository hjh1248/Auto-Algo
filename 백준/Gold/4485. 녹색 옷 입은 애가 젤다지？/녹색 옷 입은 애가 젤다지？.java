import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node implements Comparable<Node>{
		int row;
		int col;
		int dist;
		
		Node(int row, int col, int dist){
			this.row = row;
			this.col = col;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node other) {
			return this.dist - other.dist;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		int[] dr = {1, -1, 0, 0};
		int[] dc = {0, 0, 1, -1};
		
		int pN = 0;
		
		while(true) {
			int N = Integer.parseInt(br.readLine());
			if(N==0) break;
			pN++;
			pq.clear();
			
			int[][] costs = new int[N][N];
			int[][] dists = new int[N][N];
			boolean [][] visited = new boolean[N][N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					costs[i][j] = Integer.parseInt(st.nextToken());
					dists[i][j] = Integer.MAX_VALUE;
				}
			}
			dists[0][0] = costs[0][0];
			pq.offer(new Node(0, 0, dists[0][0]));
			while(!pq.isEmpty()) {
				Node cur = pq.poll();
				int cr = cur.row;
				int cc = cur.col;
				if(visited[cr][cc]) continue;
				visited[cr][cc] = true;
				
				for(int i=0; i<4; i++) {
					int nr = cr + dr[i];
					int nc = cc + dc[i];
					if(0<=nr && nr < N && 0<= nc && nc < N && !visited[nr][nc] && dists[nr][nc] > dists[cr][cc] + costs[nr][nc]) {
						dists[nr][nc] = dists[cr][cc] + costs[nr][nc];
						pq.offer(new Node(nr, nc, dists[nr][nc]));
					}
				}
				
			}
			sb.append("Problem ").append(pN).append(": ").append(dists[N-1][N-1]).append("\n");
		}
		System.out.println(sb);
	}
}
