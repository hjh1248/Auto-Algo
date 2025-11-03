
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;
    static int[] rank;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int answer = 0;
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        parent = new int[V+1];
        rank = new int[V+1];

        int[][] edges = new int[E][3];
        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
            edges[i][2] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(edges, (a, b) -> a[2] - b[2]);

        for(int[] edge: edges){
            if(union(edge[0], edge[1])){
                answer += edge[2];
            }
        }
        System.out.println(answer);

    }
    static int find(int n){
        if(parent[n] == 0) return n;
        return parent[n] = find(parent[n]);
    }
    static boolean union(int a, int b){
        int pa = find(a);
        int pb = find(b);

        if(pa==pb)return false;

        if(rank[pa] >= rank[pb]){
            parent[pb] = pa;
            if(rank[pa]==rank[pb]) rank[pa]++;
        }
        else if(rank[pa] < rank[pb]){
            parent[pa] = pb;
        }
        return true;
    }
}
