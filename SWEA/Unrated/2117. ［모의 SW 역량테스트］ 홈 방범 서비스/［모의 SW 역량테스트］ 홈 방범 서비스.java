
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int N, M;
    static int[][] map;
    static ArrayDeque<int[]> q = new ArrayDeque<>();
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[] maxcnt;
    
    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            int max = 0;
            map = new int[N][N];
            maxcnt = new int[N+1];
            maxcnt[0] = 1;
            for(int i=1; i<=N; i++){
                maxcnt[i] = maxcnt[i-1] + 4*i;
            }
            visited = new boolean[N][N];

            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    max = Math.max(bfs(i, j), max);
                }
            }
            sb.append("#").append(tc).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }
    static int bfs(int r, int c) {
        q.clear();
        for(int i=0; i<N; i++){
            Arrays.fill(visited[i], false);
        }
        visited[r][c] = true;
        q.offer(new int[] {r, c});
        int cnt = map[r][c]==1 ? 1:0;
        int max = cnt;
        int dist = 0;
        int point = map[r][c]==1 ? M:0;
        while(!q.isEmpty()){
            if(dist==N) break;
            int size = q.size();
            for(int i=0; i<size; i++){
                int[] cur = q.poll();
                for(int j=0; j<4; j++){
                    int nr = cur[0] + dr[j];
                    int nc = cur[1] + dc[j];
                    if(nr<0 || N<=nr || nc<0 || N<=nc) continue;
                    if(visited[nr][nc]) continue;
                    visited[nr][nc] = true;
                    q.offer(new int[] {nr, nc});
                    if(map[nr][nc]==1){
                        point += M;
                        cnt++;
                    }
                }
            }
            dist++;
            if(point>=maxcnt[dist]) max = cnt;
        }
        return max;
    }
}
