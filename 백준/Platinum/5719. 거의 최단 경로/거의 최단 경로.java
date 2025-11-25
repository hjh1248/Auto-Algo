
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int INF = Integer.MAX_VALUE;

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            if(N==0 || M==0) break;

            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            ArrayList<int[]>[] graph = new ArrayList[N];
            ArrayList<Integer>[] parent = new ArrayList[N];
            int[] dist = new int[N];
            for(int i=0; i<N; i++){
                graph[i] = new ArrayList<>();
                parent[i] = new ArrayList<>();
                dist[i] = INF;
            }
            for(int i=0; i<M; i++){
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                graph[u].add(new int[] {v, d});
            }
            
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
            dist[start] = 0;
            pq.offer(new int[] {start, 0});

            while(!pq.isEmpty()){
                int[] cur = pq.poll();
                int cNode = cur[0];
                int cDist = cur[1];

                if(cDist > dist[cNode]) continue;
                for(int[] next : graph[cNode]){
                    int nNode = next[0];
                    int nDist = cDist + next[1];

                    if(nDist > dist[nNode]) continue;
                    if(nDist == dist[nNode]){
                        parent[nNode].add(cNode);
                        continue;
                    }
                    parent[nNode].clear();
                    parent[nNode].add(cNode);
                    dist[nNode] = nDist;
                    pq.offer(new int[] {nNode, nDist});
                }
            }

            boolean[][] isShortest = new boolean[N][N];
            ArrayDeque<Integer> q = new ArrayDeque<>();
            q.add(end);

            while(!q.isEmpty()){
                int cur = q.poll();
                for(int n : parent[cur]){
                    if (isShortest[n][cur]) continue;
                    isShortest[n][cur] = true;
                    q.offer(n);
                }
            }

            for(int i=0; i<N; i++) dist[i] = INF;

            dist[start] = 0;
            pq.offer(new int[] {start, 0});

            while(!pq.isEmpty()){
                int[] cur = pq.poll();
                int cNode = cur[0];
                int cDist = cur[1];

                if(cDist > dist[cNode]) continue;
                for(int[] next : graph[cNode]){
                    int nNode = next[0];
                    if(isShortest[cNode][nNode]) continue;
                    int nDist = cDist + next[1];

                    if(nDist >= dist[nNode]) continue;
                    dist[nNode] = nDist;
                    pq.offer(new int[] {nNode, nDist});
                }
            }

            sb.append(dist[end] == INF ? -1 : dist[end]).append("\n");
        }
        System.out.println(sb);
    }
}