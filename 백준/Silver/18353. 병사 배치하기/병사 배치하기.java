
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int answer = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        int[][] dp = new int[N][2];

        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1; i<N; i++){
            for(int j=0; j<i; j++){
                if(arr[j] <= arr[i]) continue;
                dp[i][0] = Math.max(dp[i][0], dp[j][0]+1);
            }
        }
        for(int i=N-2; i>=0; i--){
            for(int j=N-1; j>i; j--){
                if(arr[j] >= arr[i]) continue;
                dp[i][1] = Math.max(dp[i][1], dp[j][1]+1);
            }
        }
        for(int i=0; i<N; i++){
            answer = Math.max(answer, dp[i][0] + dp[i][1] +1);
        }
        System.out.println(N-answer);
    }
}
