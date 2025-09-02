import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, M, R, C, L;
    static int[][] map;
    static int[][] visited;
    // 각 dir에 대해 갈 수 있는 방향 설정
    static int[][] dr = {{}, {-1, 1, 0, 0}, {-1, 1}, {0, 0}, {-1, 0}, {1, 0}, {1, 0}, {-1, 0}};
    static int[][] dc = {{}, {0, 0, -1, 1}, {0, 0}, {-1, 1}, {0, 1}, {0, 1}, {0, -1}, {0, -1}};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            visited = new int[N][M];
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<M; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            visited[R][C] = 1;
            int answer = 0;
            dfs(R, C, 1);
            for(int i=0; i<N; i++) {
            	for(int j=0; j<M; j++) {
            		if(visited[i][j] != 0) answer++;
            	}
            }
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
    static void dfs(int r, int c, int cnt){
    	// L번 이동하면 1 반환
        if(cnt==L)return;
        // 터널 종류
        int dir = map[r][c];
        for(int i=0; i<(dir==1?4:2); i++){
        	// 다음 r, c 값 설정
            int nr = r+dr[dir][i];
            int nc = c+dc[dir][i];
            // 범위, 방문 체크
            if(nr<0 || N<=nr || nc<0 || M<=nc) continue;
            if(visited[nr][nc]!=0 &&visited[nr][nc]<=cnt+1) continue;
            // 다음 좌표에서 현재 좌표로 돌아올 수 있는지 체크
        	if(!check(r, c, nr, nc)) continue;
            visited[nr][nc] = cnt+1;
            // 재귀로 n값 증가
            dfs(nr, nc, cnt+1);
        }
    }
    static boolean check(int r, int c, int nr, int nc){
    	// 다음 좌표의 터널 종류
        int dir = map[nr][nc];
        // 터널이 없으면 false 반환
        if(dir==0) return false;
        for(int i=0; i<(dir==1?4:2); i++){
        	// 다음 좌표의 다음 좌표
        	int nnr = nr + dr[dir][i];
        	int nnc = nc + dc[dir][i];
        	// 현재 좌표와 같다면 true 반환
        	if(nnr==r && nnc==c) return true;
        }
        return false;
    }
}