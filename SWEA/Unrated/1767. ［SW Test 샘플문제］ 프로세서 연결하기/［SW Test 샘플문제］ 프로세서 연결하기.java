
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
    static int N, core_num, answer, answerCnt;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static ArrayList<int[]> cores;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++){
            N = Integer.parseInt(br.readLine());
            answer = 0;
            answerCnt = 0;

            cores = new ArrayList<>();
            core_num = 0;
            visited = new boolean[N][N];

            for(int i=0; i<N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++){
                    if(st.nextToken().equals("1")){
                        cores.add(new int[] {i, j});
                        visited[i][j] = true;
                        core_num++;
                    }
                }
            }
            dfs(0, 0, 0);
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
    static void dfs(int idx, int cnt, int length){
        if(cnt + core_num - idx < answerCnt) return;

        if(idx==core_num){
            if(cnt>answerCnt){
                answerCnt = cnt;
                answer = length;
            }
            else answer = Math.min(answer, length);
            return;
        }

        int[] core = cores.get(idx);
        int r = core[0];
        int c = core[1];
        if(r==0 || r==N-1 || c==0 || c==N-1){
            dfs(idx+1, cnt+1, length);
            return;
        }
        for(int i=0; i<4; i++){
            if(!check(r, c, i)) continue;
            int tmp_length = 0;
            int nr = r;
            int nc = c;
            while(0<nr && nr<N-1 && 0<nc && nc<N-1){
                nr += dr[i];
                nc += dc[i];
                visited[nr][nc] = true;
                tmp_length++;
            }
            dfs(idx+1, cnt+1, length+tmp_length);
            nr = r;
            nc = c;
            while(0<nr && nr<N-1 && 0<nc && nc<N-1){
                nr += dr[i];
                nc += dc[i];
                visited[nr][nc] = false;
            }
        }
        dfs(idx+1, cnt, length);
    }
    static boolean check(int r, int c, int dir){
        while(0<r && r<N-1 && 0<c && c<N-1){
            r += dr[dir];
            c += dc[dir];
            if(visited[r][c]) return false;
        }
        return true;
    }
}
