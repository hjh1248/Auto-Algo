import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int answer = Integer.MAX_VALUE;
        int start = 0;
        
        int[] sum = new int[N+1];
        
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            sum[i] = sum[i-1] + Integer.parseInt(st.nextToken());
            while(i-start>=answer) start++;
            while(sum[i]-sum[start]>=S){
                answer = i-start;
                if(sum[i]-sum[start+1]<S) break;
                start++;
                answer = i-start;
            }
        }
        System.out.println(answer==Integer.MAX_VALUE?0:answer);
    }
}
