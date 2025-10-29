
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
    static int N;
    static int[][] map, dists;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++){
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            dists = new int[N][N];

            for(int i=0; i<N; i++){
                String line = br.readLine();
                for(int j=0; j<N; j++){
                    map[i][j] = line.charAt(j)-'0';
                }                
            }
            
            for(int i=0; i<N; i++){
                Arrays.fill(dists[i], Integer.MAX_VALUE);
            }
            
            dijkstra(0, 0);
            sb.append("#").append(tc).append(" ").append(dists[N-1][N-1]).append("\n");
        }
        System.out.println(sb);
    }
    static void dijkstra(int r, int c){
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        dists[r][c] = 0;
        pq.offer(new int[] {r, c, 0});

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int cr = cur[0];
            int cc = cur[1];
            int dist = cur[2];

            if(dists[cr][cc]<dist) continue;

            for(int i=0; i<4; i++){
                int nr = cr + dr[i];
                int nc = cc + dc[i];
                if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
                int nDist = dist + map[nr][nc];
                if(nDist >= dists[nr][nc]) continue;
                dists[nr][nc] = nDist;
                pq.offer(new int[] {nr, nc, nDist});
            }
        }
    }
}
