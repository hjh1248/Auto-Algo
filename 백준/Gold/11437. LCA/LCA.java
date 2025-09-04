import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
    static int[][] parent;
    static int[] depths;
    static ArrayList<Integer>[] child;
    static int maxDepthLog = 0;
    static int[] parent0;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        
        parent0 = new int[N+1];
        child = new ArrayList[N+1];
        for(int i=1; i<=N; i++) child[i] = new ArrayList<>();
        depths = new int[N+1];
        
        for(int i=0; i<N-1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            child[a].add(b);
            child[b].add(a);
        }
        dfs(1, 0, 0);

        maxDepthLog = (int) Math.ceil(Math.log(maxDepthLog) / Math.log(2));
        parent = new int[maxDepthLog][N+1];
        parent[0] = parent0;
        
        for(int i=1; i<maxDepthLog; i++){
            for(int j=1; j<=N; j++){
                parent[i][j] = parent[i-1][parent[i-1][j]];
            }
        }

        int M = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(lca(a, b)).append('\n');
        }
        System.out.println(sb);
    }
    static void dfs(int node, int depth, int p){
        depths[node] = depth;
        parent0[node] = p;
        maxDepthLog = Math.max(maxDepthLog, depth);
        for(int next: child[node]){
            if(next == p) continue;
            dfs(next, depth+1, node);
        }
    }
    static int lca(int a, int b){
        // b가 더 깊게 설정
        if(depths[a]>depths[b]){
            int tmp = a;
            a = b;
            b = tmp;
        }
        for(int i=maxDepthLog-1; i>=0; i--){
            if(depths[b] - depths[a] >= 1<<i){
                b = parent[i][b];
            }
        }

        if(a==b) return a;

        for(int i=maxDepthLog-1; i>=0; i--){
            if(parent[i][a] != parent[i][b]){
                a = parent[i][a];
                b = parent[i][b];
            }
        }

        return parent[0][a];
    }
}