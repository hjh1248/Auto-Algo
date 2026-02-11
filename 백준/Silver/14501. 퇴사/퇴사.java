import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());

        int[] days = new int[N];
        int[] profits = new int[N];

        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            days[i] = Integer.parseInt(st.nextToken());
            profits[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N+1];
        for(int i=0; i<N; i++){
            dp[i+1] = Math.max(dp[i], dp[i+1]);
            if(i + days[i] <= N){
                dp[i+days[i]] = Math.max(dp[i+days[i]], dp[i] + profits[i]);
            }
        }
        System.out.println(dp[N]);
    }
}