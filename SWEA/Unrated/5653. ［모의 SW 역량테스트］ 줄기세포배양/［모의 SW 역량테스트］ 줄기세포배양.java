
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static boolean[][] visited;
    static int[] life;
    static PriorityQueue<int[]> pq;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int answer = 0;

            visited = new boolean[650][650];
            life = new int[K+21];
            PriorityQueue<int[]> nextPq = new PriorityQueue<>((a, b) -> b[3]-a[3]);
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<M; j++){
                    int l = Integer.parseInt(st.nextToken());
                    if(l==0) continue;
                    int r = i+300;
                    int c = j+300;
                    visited[r][c] = true;
                    nextPq.offer(new int[] {r, c, l, l});
                    life[2*l]++;
                }
            }
            int time = 0;
            while(time<K){
                pq = nextPq;
                nextPq = new PriorityQueue<>((a, b) -> b[3]-a[3]);
                while(!pq.isEmpty()){
                    int[] cur = pq.poll();
                    int r = cur[0];
                    int c = cur[1];
                    int l = cur[2];
                    int wl = cur[3];
                    if(l==0){
                        for(int i=0; i<4; i++){
                            int nr = r+dr[i];
                            int nc = c+dc[i];
                            if(visited[nr][nc]) continue;
                            visited[nr][nc] = true;
                            nextPq.offer(new int[] {nr, nc, wl, wl});
                            life[time + 2*wl]++;
                        }
                    }
                    else nextPq.offer(new int[] {r, c, l-1, wl});
                }
                time++;
            }
            for(int i=time; i<K+21; i++){
                answer += life[i];
            }
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
}
