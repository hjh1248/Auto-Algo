import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	static int N;
	static int[][] numbers;
	static int[][] visited;
	static int max;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
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
					if(dfs(i, j) >= max) {
						if(visited[i][j] == max) start = Math.min(start, numbers[i][j]);
						else {
							max = visited[i][j];
							start = numbers[i][j];
						}
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(start).append(" ").append(max).append("\n");
		}
		System.out.println(sb);
	}
	static int dfs(int i, int j) {
		if(visited[i][j]!=0) return visited[i][j];
		visited[i][j] = 1;
		for(int dir=0; dir<4; dir++) {
			int nr = i + dr[dir];
			int nc = j + dc[dir];
			if(0<=nr && nr<N && 0<=nc && nc<N) {
				if(numbers[i][j] + 1 == numbers[nr][nc]) {
					visited[i][j] = dfs(nr, nc) + 1;
				}
			}
		}
		return visited[i][j];
	}
}