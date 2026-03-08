import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] parent, rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        rank = new int[n+1];
        for(int i=0; i<=n; i++) parent[i] = i;
        
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(cmd==0) union(a, b);
            else{
                if(find(a) == find(b)) sb.append("YES");
                else sb.append("NO");
                sb.append("\n");
            }
        }

        System.out.println(sb);
    }

    static int find(int n){
        if(n==parent[n]) return n;
        return parent[n] = find(parent[n]);
    }

    static boolean union(int a, int b){
        int pa = find(a);
        int pb = find(b);

        if(pa==pb) return false;

        if(rank[pa] > rank[pb]){
            parent[pb] = pa;
        }
        else if(rank[pb] > rank[pa]){
            parent[pa] = pb;
        }
        else{
            parent[pb] = pa;
            rank[pa]++;
        }

        return true;
    }
}
