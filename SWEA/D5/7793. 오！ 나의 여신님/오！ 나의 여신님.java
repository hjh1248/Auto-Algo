import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        ArrayDeque<int[]> q = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            char[][] map = new char[R][C];
            boolean[][] visited = new boolean[R][C];
            q.clear();
            int answer = 0;
            for(int i=0; i<R; i++){
                String str = br.readLine();
                for(int j=0; j<C; j++){
                    char c = str.charAt(j);
                    if(c=='S'){
                        visited[i][j] = true;
                        q.offer(new int[] {i, j, 0});
                    }
                    else if(c=='*'){
                        visited[i][j] = true;
                        q.push(new int[] {i, j, -1});
                    }
                    else if(c=='X') visited[i][j] = true;
                    map[i][j] = c;
                }
            }
            
            A:while(!q.isEmpty()){
                int[] cur = q.poll();
                for(int i=0; i<4; i++){
                    int nr = cur[0] + dr[i];
                    int nc = cur[1] + dc[i];
                    int nd = cur[2]==-1 ? -1:cur[2]+1;
                    if(0<=nr && nr<R && 0<=nc && nc<C && !visited[nr][nc]){
                        if(map[nr][nc]=='D'){
                            if(cur[2]==-1){
                                continue;
                            }
                            else answer = nd; break A;
                        }
                        visited[nr][nc] = true;
                        q.offer(new int[] {nr, nc, nd});
                    }
                }
            }
            sb.append("#").append(tc).append(" ").append(answer == 0 ? "GAME OVER":answer).append("\n");
        }
        System.out.println(sb);
    }
}
