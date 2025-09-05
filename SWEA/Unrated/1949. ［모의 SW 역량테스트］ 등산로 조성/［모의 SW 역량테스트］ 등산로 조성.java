import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, K, answer;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            answer = 0;
            int max = 0;
            map = new int[N][N];
            visited = new boolean[N][N];

            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++){
                    int n = Integer.parseInt(st.nextToken());
                    map[i][j] = n;
                    max = Math.max(max, n);
                }
            }
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(map[i][j]==max){
                        visited[i][j] = true;
                        dfs(i, j, 0, false, max);
                        visited[i][j] = false;
                    }
                }
            }
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
    static void dfs(int r, int c, int cnt, boolean check, int cur){
        cnt++;
        answer = Math.max(answer, cnt);
        boolean checkcheck = false;
        for(int i=0; i<4; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];
            
            if(nr<0||N<=nr||nc<0||N<=nc) continue;
            if(visited[nr][nc]) continue;
            int next = map[nr][nc];
            if(cur<=next){
                if(check || cur<=next-K) continue;
                next = cur-1;
                check = true;
                checkcheck = true;
            }
            visited[nr][nc] = true;
            dfs(nr,nc, cnt, check, next);
            visited[nr][nc] = false;
            if(checkcheck) check = false;
        }
    }
}