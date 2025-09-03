import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

// 메모리: 25,600kb 실행시간: 125ms

public class Solution {
    static int N, M, R, C, L, answer;
    static int[][] map;
    static boolean [][] visited;
    // 각 dir에 대해 갈 수 있는 방향 설정
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] dirs = {{}, {0, 1, 2, 3}, {0, 1}, {2, 3}, {0, 3}, {1, 3}, {1, 2}, {0, 2}};
    static ArrayDeque<int[]> q = new ArrayDeque<>();

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
            visited = new boolean[N][M];
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<M; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            answer = 0;
            bfs(R, C);
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
    static void bfs(int r, int c){
        visited[r][c] = true;
        q.offer(new int[] {r, c, 1});
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cr = cur[0];
            int cc = cur[1];
            int cd = cur[2];
            answer++;
            if(cd==L) continue;
            int[] dir = dirs[map[cr][cc]];
            for(int i: dir){
                int nr = cr + dr[i];
                int nc = cc + dc[i];
                if(nr<0 || N<=nr || nc<0 || M<=nc) continue;
                if(visited[nr][nc]) continue;
                if(!check(i, nr, nc)) continue;
                visited[nr][nc] = true;
                q.offer(new int[] {nr, nc, cd+1});
            }
        }
    }
    static boolean check(int d, int nr, int nc){
        int[] dir = dirs[map[nr][nc]];
        for(int nd: dir){
            if(d+nd==1 || d+nd==5) return true;
        }
        return false;
    }
}