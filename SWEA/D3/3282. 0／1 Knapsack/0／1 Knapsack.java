
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[][] dp = new int[N+1][K+1];

            for(int i=1; i<=N; i++){
                st = new StringTokenizer(br.readLine());
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                for(int j=0; j<=K; j++){
                    if(v>j) dp[i][j] = dp[i-1][j];
                    else if(dp[i-1][j-v] + c <= dp[i-1][j]) dp[i][j] = dp[i-1][j];
                    else dp[i][j] = dp[i-1][j-v] + c;
                }
            }
            sb.append("#").append(tc).append(" ").append(dp[N][K]).append("\n");
        }
        System.out.println(sb);
    }    
}
