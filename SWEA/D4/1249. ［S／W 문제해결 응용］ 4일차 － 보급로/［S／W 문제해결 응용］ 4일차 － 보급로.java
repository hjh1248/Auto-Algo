import java.util.*;
import java.lang.*;
import java.io.*;

class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        PriorityQueue<Node> pq = new PriorityQueue<>();

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++){
            int N = Integer.parseInt(br.readLine());
            int[][] map = new int[N][N];
            int[][] dist = new int[N][N];
            
            for(int i=0; i<N; i++){
                char[] chars = br.readLine().toCharArray();
                for(int j=0; j<N; j++){
                    map[i][j] = chars[j] - '0';
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
            dist[0][0] = 0;
            pq.offer(new Node(0, 0, 0));
            while(!pq.isEmpty()){
                Node cur = pq.poll();
                if(cur.d > dist[cur.r][cur.c]) continue;
                for(int i=0; i<4; i++){
                    int nr = cur.r + dr[i];
                    int nc = cur.c + dc[i];
                    if(nr<0 || N<=nr || nc<0 || N<=nc) continue;
                    int nd = cur.d + map[nr][nc];
                    if(dist[nr][nc] <= nd) continue;
                    dist[nr][nc] = nd;
                    pq.offer(new Node(nr, nc, nd));
                }
            }
            int answer = dist[N-1][N-1];
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
    static class Node implements Comparable<Node>{
        int r, c, d;
        Node(int r, int c, int d){
            this.r = r;
            this.c = c;
            this.d = d;
        }
        public int compareTo(Node o){
            return this.d - o.d;
        }
    }
}