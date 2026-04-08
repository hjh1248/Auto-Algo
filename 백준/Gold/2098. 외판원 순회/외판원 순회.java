import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] dp, graph;
    static int INF = 16000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        graph = new int[N][N];
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) graph[i][j] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N][1<<N];
        System.out.println(dfs(0, 1));
    }

    static int dfs(int cur, int visited){
        if(visited == (1<<N) - 1) return graph[cur][0]==0 ? INF : graph[cur][0];

        if(dp[cur][visited] != 0) return dp[cur][visited];
        int min = INF;
        for(int i=0; i<N; i++){
            if(graph[cur][i] == 0 || (visited & (1 << i)) != 0) continue;
            min = Math.min(min, graph[cur][i] + dfs(i, visited | 1 << i));
        }
        return dp[cur][visited] = min;
    }
}
