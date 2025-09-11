
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++){
            int N = Integer.parseInt(br.readLine());
            int[][] dp = new int[3][N+1];
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            StringTokenizer st2 = new StringTokenizer(br.readLine());

            for(int i=1; i<=N; i++){
                dp[0][i] = Math.max(dp[1][i-1], dp[2][i-1]) + Integer.parseInt(st1.nextToken());
                dp[1][i] = Math.max(dp[0][i-1], dp[2][i-1]) + Integer.parseInt(st2.nextToken());
                dp[2][i] = Math.max(dp[0][i-1], dp[1][i-1]);
            }

            int max = Math.max(dp[0][N], dp[1][N]);
            sb.append(Math.max(max, dp[2][N])).append("\n");
        }
        System.out.println(sb);
    }
}
