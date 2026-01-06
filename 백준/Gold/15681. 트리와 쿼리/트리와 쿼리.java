import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int[] subtree;
    static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for(int i=1; i<=N; i++) graph[i] = new ArrayList<>();

        subtree = new int[N+1];
        for(int i=1; i<=N; i++) subtree[i] = 1;

        for(int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        dfs(R, 0);

        for(int i=0; i<Q; i++){
            int n = Integer.parseInt(br.readLine());
            sb.append(subtree[n]).append("\n");
        }
        
        System.out.println(sb);
    }

    static int dfs(int node, int parent){
        for(int next: graph[node]){
            if(next==parent) continue;
            subtree[node] += dfs(next, node);
        }
        return subtree[node];
    }
}
