import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        

        ArrayList<int[]>[] graph = new ArrayList[N+1];
        int[] dist = new int[N+1];
        int[] parent = new int[N+1];

        for(int i=1; i<=N; i++){
            graph[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new int[] {b, c});
            graph[b].add(new int[] {a, c});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[] {1, 0});
        dist[1] = 0;

        int cnt = 0;
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int cNode = cur[0];
            int cDist = cur[1];
            if(cDist > dist[cNode]) continue;

            for(int[] next : graph[cNode]){
                int nNode = next[0];
                int nDist = cDist + next[1];
                if(nDist >= dist[nNode]) continue;

                if(parent[nNode] == 0) cnt++;
                parent[nNode] = cNode;
                dist[nNode] = nDist;
                pq.offer(new int[] {nNode, nDist});
            }
        }
        for(int i=0; i<=N; i++){
            if(parent[i]==0) continue;
            sb.append(parent[i]).append(" ").append(i).append("\n");
        }

        System.out.println(cnt);
        System.out.println(sb);
    }
}
