import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static ArrayList<int[]> edges;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            edges = new ArrayList<>();

            for(int i=0; i<M; i++){
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                edges.add(new int[] {u, v, d});
                edges.add(new int[] {v, u, d});
            }
            for(int i=0; i<W; i++){
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                edges.add(new int[] {u, v, -d});
            }
            if(hasNegativeCycle()) sb.append("YES\n");
            else sb.append("NO\n");
        }
        System.out.println(sb);
    }
    static boolean hasNegativeCycle(){
        int[] dists = new int[N+1];
        for(int i=1; i<=N; i++){
            for(int[] edge: edges){
                int u = edge[0];
                int v = edge[1];
                int d = edge[2];
                if(dists[u] > dists[v] + d){
                    dists[u] = dists[v] + d;
                    if(i==N) return true;
                }
            }
        }
        return false;
        
    }
}

