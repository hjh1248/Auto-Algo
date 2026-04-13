import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int INF = 1000;
    static int N, P;
    static int[][] dists;
    static int[] dp;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        dists = new int[N][N];
        
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                dists[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        char[] chars = br.readLine().toCharArray();
        int visited = 0;
        int cnt = 0;
        for(int i=0; i<N; i++) {
            if(chars[i] == 'Y'){
                visited |= 1<<i;
                cnt++;
            }
        }
        P = Integer.parseInt(br.readLine());
        dp = new int[1<<N];
        Arrays.fill(dp, -1);
        
        flag = false;
        int answer = dfs(visited, cnt);
        System.out.println(flag ? answer:-1);
    }

    static int dfs(int visited, int cnt){
        if(cnt>=P){
            flag = true;
            return 0;
        }

        if(dp[visited] != -1) return dp[visited];

        int min = INF;
        for(int i=0; i<N; i++){
            if((visited & (1<<i)) == 0) continue;
            for(int j=0; j<N; j++){
                if(i==j || (visited & (1<<j)) != 0) continue;
                min = Math.min(min, dists[i][j] + dfs(visited | 1 << j, cnt+1));
            }
        }
        return dp[visited] = min;
    }
}
