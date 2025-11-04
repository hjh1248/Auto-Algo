
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int INF = Integer.MAX_VALUE;

        int[][] arr = new int[N][2];
        int[][] dp = new int[N][N];

        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        for(int i=1; i<N; i++){
            for(int j=i-1; j>=0; j--){
                dp[j][i] = INF;
                for(int k=j; k<i; k++){
                    int tmp = dp[j][k] + dp[k+1][i] + arr[j][0] * arr[k][1] * arr[i][1];
                    dp[j][i] = Math.min(dp[j][i], tmp);
                }
            }
        }
        System.out.println(dp[0][N-1]);
    }
}
