
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int N, answer = 0;
    static int[][] map;
    // {r, c, size, cnt}
    static int[] shark = new int[4];
    static ArrayDeque<int[]> q = new ArrayDeque<>();
    static boolean[][] visited;
    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        map = new int[N][N];
        visited = new boolean[N][N];
        shark[2] = 2;
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                int n = Integer.parseInt(st.nextToken());
                if(n==9){
                    shark[0] = i;
                    shark[1] = j;
                    n = 0;
                }
                map[i][j] = n;
            }
        }

        bfs(shark[0], shark[1]);

        System.out.println(answer);
    }
    static void bfs(int r, int c){
        for(int i=0; i<N; i++) Arrays.fill(visited[i], false);
        q.clear();
        q.offer(new int[] {r, c});
        ArrayList<int[]> canEat = new ArrayList<>();
        visited[r][c] = true;
        int dist = 0;
        while(!q.isEmpty()){
            int size = q.size();
            dist++;
            for(int i=0; i<size; i++){
                int[] cur = q.poll();
                for(int j=0; j<4; j++){
                    int nr = cur[0] + dr[j];
                    int nc = cur[1] + dc[j];
                    if(nr<0 || nc<0 || N<=nr || N<=nc) continue;
                    if(visited[nr][nc]) continue;
                    int next = map[nr][nc];
                    if(shark[2]<next) continue;
                    visited[nr][nc] = true;
                    q.offer(new int[] {nr, nc});
                    if(next!=0 && next<shark[2]){
                        canEat.add(new int[] {nr, nc});
                    }
                }
            }
            if(!canEat.isEmpty()){
                Collections.sort(canEat, (a, b) -> a[0] - b[0] !=0 ? a[0] - b[0] : a[1] - b[1]);
                shark[3]++;
                if(shark[2] == shark[3]){
                    shark[2]++;
                    shark[3] = 0;
                }
                int[] next = canEat.get(0);
                answer += dist;
                int nr = next[0];
                int nc = next[1];
                map[nr][nc] = 0;
                bfs(nr, nc);
                return;
            }
        }
    }
}
