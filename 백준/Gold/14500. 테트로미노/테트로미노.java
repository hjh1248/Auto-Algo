
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int answer = 0;
    static int N, M;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static boolean[][] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                dfs(i, j, 0, 0);
                dfs2(i, j);
            }
        }
        System.out.println(answer);
    }
    static void dfs(int r, int c, int cnt, int sum){
        visited[r][c] = true;
        cnt++;
        sum += map[r][c];

        if(cnt==4){
            answer = Math.max(answer, sum);
            visited[r][c] = false;
            return;
        }

        for(int i=0; i<4; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr<0 || N<=nr || nc<0 || M<=nc) continue;
            if(visited[nr][nc]) continue;
            dfs(nr, nc, cnt, sum);
        }
        visited[r][c] = false;
    }
    static void dfs2(int r, int c){
        A:for(int i=0; i<4; i++){
            int sum = map[r][c];
            int nr = r;
            int nc = c;

            for(int j=0; j<2; j++){
                nr += dr[i];
                nc += dc[i];
                if(nr<0 || N<=nr || nc<0 || M<=nc) continue A;
                sum += map[nr][nc];
            }
            nr = r + dr[i];
            nc = c + dc[i];
            if(i==0 || i==1){
                if(0 <= nc - 1) answer = Math.max(answer, sum + map[nr][nc-1]);
                if(nc + 1 < M) answer = Math.max(answer, sum + map[nr][nc+1]);
            }
            else{
                if(0 <= nr - 1) answer = Math.max(answer, sum + map[nr-1][nc]);
                if(nr + 1 < N) answer = Math.max(answer, sum + map[nr+1][nc]);
            }
        }
    }
}
