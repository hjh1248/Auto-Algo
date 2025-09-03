import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;
 
class Solution {
    static int[] depth;
    static int[][] parent;
    static int MAX_DEPTH_LOG;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
 
        int T = Integer.parseInt(br.readLine());
        ArrayDeque<Integer> q = new ArrayDeque<>();
 
        for(int tc=1; tc<=T; tc++){
            int N = Integer.parseInt(br.readLine());
            MAX_DEPTH_LOG = (int) Math.ceil(Math.log(N) / Math.log(2));
            parent = new int[MAX_DEPTH_LOG+1][N+1];
            @SuppressWarnings("unchecked")
            ArrayList<Integer>[] child = new ArrayList[N+1];
            ArrayList<Integer> visit = new ArrayList<>();
            depth = new int[N+1];
 
            for(int i=1; i<=N; i++){
                child[i] = new ArrayList<>();
            }
            st = new StringTokenizer(br.readLine());
            for(int i=2; i<=N; i++){
                int a = Integer.parseInt(st.nextToken());
                child[a].add(i);
                parent[0][i] = a;
            }
            q.offer(1);
            visit.add(1);
            int d=0;
            while(!q.isEmpty()){
                int size = q.size();
                for(int i=0; i<size; i++){
                    int cur = q.poll();
                    depth[cur] = d;
                    for(int next : child[cur]){
                        q.offer(next);
                        visit.add(next);
                    }
                }
                d++;
            }
            for(int i=1; i<=MAX_DEPTH_LOG; i++){
                for(int j=1; j<=N; j++){
                    if(parent[i-1][j] == 0) continue;
                    parent[i][j] = parent[i-1][parent[i-1][j]];
                }
            }
            long dist = 0;
            for(int i=1; i<N; i++){
                int a = visit.get(i);
                int b = visit.get(i-1);
                dist += depth[a] + depth[b] - 2*depth[lca(a, b)];
            }
            sb.append("#").append(tc).append(" ").append(dist).append("\n");
        }
        System.out.println(sb);
    }
    static int lca(int a, int b){
        if(depth[a]>depth[b]) a = parent[0][a];
        if(a==b) return a;
        for(int i=MAX_DEPTH_LOG-1; i>=0; i--){
            if(parent[i][a] != parent[i][b]) {
                a = parent[i][a];
                b = parent[i][b];
            }
        }
        return parent[0][a];
    }
}