
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int start = Integer.parseInt(br.readLine());

        ArrayList<int[]>[] edges = new ArrayList[V+1];
        for(int i=1; i<=V; i++) edges[i] = new ArrayList<>();

        int[] dists = new int[V+1];
        for(int i=1; i<=V; i++){
            dists[i] = Integer.MAX_VALUE;
        }


        for(int i=1; i<=E; i++){
            st = new StringTokenizer(br.readLine());
            int u =Integer.parseInt(st.nextToken());
            int v =Integer.parseInt(st.nextToken());
            int w =Integer.parseInt(st.nextToken());

            edges[u].add(new int[] {v, w});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[] {start, 0});
        dists[start] = 0;

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int curN = cur[0];
            int dist = cur[1];
            if(dist > dists[curN]) continue;
            for(int[] next: edges[curN]){
                int nextN = next[0];
                int nDist = dist + next[1];
                if(nDist >= dists[nextN]) continue;
                dists[nextN] = nDist;
                pq.add(new int[] {nextN, nDist});
            }
        }

        for(int i=1; i<=V; i++){
            int n = dists[i];
            if(n==Integer.MAX_VALUE) sb.append("INF");
            else sb.append(n);
            sb.append("\n");
        }
        System.out.println(sb);
    }    
}
