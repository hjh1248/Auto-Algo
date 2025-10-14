import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        int[][] dp = new int[N][];

        dp[0] = new int[1];
        dp[0][0] = Integer.parseInt(br.readLine());
        answer = dp[0][0];
        
        for(int i=1; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] row = new int[i+1];

            
            for(int j=0; j<=i; j++){
                int preMax = 0;
                if(0<j) preMax = Math.max(preMax, dp[i-1][j-1]);
                if(j<i) preMax = Math.max(preMax, dp[i-1][j]);
                int sum = preMax + Integer.parseInt(st.nextToken());
                if(i==N-1) answer = Math.max(answer,sum);
                row[j] = sum;
            }
            dp[i] = row;
        }
        System.out.println(answer);
    }
}
