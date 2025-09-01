import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<int[]>[] list = new ArrayList[N+1];
        int[] dist = new int[N+1];
        for(int i=1; i<=N; i++){
            list[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[u].add(new int[] {v, w});
            list[v].add(new int[] {u, w});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[] {1, 0});
        dist[1] = 0;
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            for(int[] edge: list[cur[0]]){
                int v = edge[0];
                int w = edge[1];
                if(cur[1] + w < dist[v]){
                    dist[v] = cur[1] + w;
                    pq.offer(new int[] {v, dist[v]});
                }
                
            }
        }
        System.out.println(dist[N]);
    }
}