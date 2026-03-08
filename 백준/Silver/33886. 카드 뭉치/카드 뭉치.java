import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        
        int[] cards = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }
        
        int[] dp = new int[N + 1];
        int INF = 1_000_000_000;
        Arrays.fill(dp, INF);
        dp[0] = 0;
        
        for (int i = 0; i < N; i++) {
            if (dp[i] == INF) continue;
            int limit = Math.min(N, i + cards[i + 1]);
            
            for (int j = i + 1; j <= limit; j++) {
                dp[j] = Math.min(dp[j], dp[i] + 1);
            }
        }
        
        System.out.println(dp[N]);
    }
}