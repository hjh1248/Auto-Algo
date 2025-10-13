import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        parent = new int[N+1];
        graph = new ArrayList[N+1];
        for(int i=1; i<=N; i++) graph[i] = new ArrayList<>();

        for(int i=0; i<N-1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u  = Integer.parseInt(st.nextToken());
            int v  = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        dfs(1, 0);

        for(int i=2; i<=N; i++) sb.append(parent[i]).append("\n");
        System.out.println(sb);
    }

    static void dfs(int node, int parentNode){
        for(int i: graph[node]){
            if(i==parentNode) continue;
            parent[i] = node;
            dfs(i, node);
        }
    }
}
