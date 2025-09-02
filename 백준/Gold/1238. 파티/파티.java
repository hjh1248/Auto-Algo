import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    static PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
    static int[] dists;
    static ArrayList<int[]>[] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int answer = 0;

        edges = new ArrayList[N+1];
        dists = new int[N+1];
        for(int i=1; i<=N; i++) edges[i] = new ArrayList<>();

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            // u에서 출발해서 v로 가는 가중치가 w인 간선 추가
            edges[u].add(new int[] {v, w});
        }
        for(int i=1; i<=N; i++){
            int dist = dijkstra(i, X) + dijkstra(X, i);
            answer = Math.max(answer, dist);
        }
        System.out.println(answer);
    }
    static int dijkstra(int start, int end){
        Arrays.fill(dists, Integer.MAX_VALUE);
        dists[start] = 0;
        pq.offer(new int[] {start, 0});
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int u = cur[0];
            int dist = cur[1];
            if(dist>dists[u]) continue;
            for(int[] edge: edges[u]){
                int v = edge[0];
                int w = edge[1];
                if(dists[v]>dist+w){
                    dists[v] = dist + w;
                    pq.offer(new int[] {v, dists[v]});
                }
            }
        }
        return dists[end];
    }
}