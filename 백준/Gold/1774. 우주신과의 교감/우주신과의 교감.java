import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] parent, rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        double answer = 0;
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N+1];
        rank = new int[N+1];
        for(int i=1; i<=N; i++) parent[i] = i;

        int[][] node = new int[N][2];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            node[i][0] = Integer.parseInt(st.nextToken());
            node[i][1] = Integer.parseInt(st.nextToken());
        }
        int maxIdx = N*(N-1)/2;
        // u, v, cost
        double[][] edges = new double[maxIdx][3];
        int idx = 0;
        for(int i=0; i<N-1; i++){
            for(int j=i+1; j<N; j++){
                double[] edge = new double[3];
                edge[0] = i;
                edge[1] = j;
                edge[2] = Math.sqrt(Math.pow(node[j][0] - node[i][0], 2) + Math.pow(node[j][1] - node[i][1], 2));
                edges[idx] = edge;
                idx++;
            }
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            union(a, b);
        }

        Arrays.sort(edges, (a, b) -> Double.compare(a[2], b[2]));

        for(double[] edge: edges){
            int u = (int) edge[0];
            int v = (int) edge[1];
            double cost = edge[2];

            if(union(u, v)) answer += cost;
        }

        System.out.printf("%.2f", answer);
    }

    static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static boolean union(int a, int b){
        int pa = find(a);
        int pb = find(b);

        if(pa == pb) return false;

        if(rank[pa] > rank[pb]) parent[pb] = pa;
        else if(rank[pa] < rank[pb]) parent[pa] = pb;
        else{
            parent[pb] = pa;
            rank[pa]++;
        }

        return true;
    }
}