import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Solution {
	static int N;
	static int[][] numbers;
	static int[][] visited;
	static int max;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static ArrayDeque<Point> q = new ArrayDeque<>();
	static int start;
	
	public static void main (String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			numbers = new int[N][N];
			visited = new int[N][N];
			start = Integer.MAX_VALUE;
			max = 0;
			
			for(int i=0; i<N; i++){
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					numbers[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(visited[i][j] == 0) {
						bfs(i, j);
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(start).append(" ").append(max).append("\n");
		}
		System.out.println(sb);
	}
	static void bfs(int i, int j) {
		q.clear();
		q.offer(new Point(i, j, 1));
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			for(int dir=0; dir<4; dir++) {
				int nr = cur.r + dr[dir];
				int nc = cur.c + dc[dir];
				int ndist = cur.dist + 1;
				if(0<=nr && nr<N && 0<=nc && nc<N) {
					if(numbers[cur.r][cur.c] -1 == numbers[nr][nc]) {
						q.offer(new Point(nr, nc, ndist));
						visited[nr][nc] = ndist;
						if(ndist>=max) {
							if(ndist==max) start = Math.min(start, numbers[nr][nc]);
							else{
								max = ndist;
								start = numbers[nr][nc];
							}
						}
					}
				}
			}
		}
	}
	
	static class Point {
		int r;
		int c;
		int dist;
		Point(int r, int c, int dist){
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
	}
}

