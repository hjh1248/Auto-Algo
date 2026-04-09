import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int w, h, N;
    static int INF = 50000;
    static int[][] waste, dists, dp;
    static char[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());

            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if(w==0) break;

            waste = new int[11][2];
            map = new char[h][w];
            dists = new int[11][11];

            N = 1;
            for(int i=0; i<h; i++){
                char[] chars = br.readLine().toCharArray();
                map[i] = chars;
                for(int j=0; j<w; j++){
                    if(chars[j] == 'o'){
                        waste[0][0] = i;
                        waste[0][1] = j;
                    }
                    else if(chars[j] == '*'){
                        waste[N][0] = i;
                        waste[N][1] = j;
                        N++;
                    }
                }
            }

            for(int i=0; i<N; i++){
                bfs(waste[i][0], waste[i][1], i);
            }

            boolean flag = false;
            for(int i=1; i<N; i++){
                if(dists[0][i]==0) flag = true;
            }
            if(flag) {
                sb.append(-1).append("\n");
                continue;
            }

            dp = new int[N][1<<N];
            sb.append(dfs(0, 1)).append("\n");
        }
        System.out.println(sb);
    }

    static void bfs(int r, int c, int idx){
        boolean[][] visited = new boolean[h][w];

        ArrayDeque<int[]> q = new ArrayDeque<>();
        
        q.offer(new int[] {r, c});
        visited[r][c] = true;

        int dist = 0;
        while(!q.isEmpty()){
            int qSize = q.size();
            dist++;
            for(int i=0; i<qSize; i++){
                int[] cur = q.poll();
                
                int cr = cur[0];
                int cc = cur[1];

                for(int j=0; j<4; j++){
                    int nr = cr + dr[j];
                    int nc = cc + dc[j];
                    if(nr < 0 || nr >= h || nc < 0 || nc >= w || visited[nr][nc] || map[nr][nc] == 'x') continue;

                    if(map[nr][nc] == '*'){
                        for(int k=0; k<N; k++){
                            if(waste[k][0] == nr && waste[k][1] == nc){
                                dists[idx][k] = dist;
                            }
                        }
                    }
                    q.offer(new int[] {nr, nc});
                    visited[nr][nc] = true;
                }
            }
            
        }
    }

    static int dfs(int node, int visited){
        if(visited==(1<<N)-1) return 0;

        if(dp[node][visited] != 0) return dp[node][visited];

        int min = INF;

        for(int i=0; i<N; i++){
            if(dists[node][i] == 0 || (visited & (1<<i)) != 0) continue;

            min = Math.min(min, dists[node][i] + dfs(i, visited | 1<<i));
        }
        return dp[node][visited] = min;
    }
}