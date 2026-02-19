import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        int answer = 0;
        int idx = 0;
        while(arr[idx]<0){
            if(idx+1 < N && arr[idx+1]<=0){
                answer += arr[idx] * arr[idx+1];
                idx += 2;
            }
            else{
                answer += arr[idx];
                idx++;
            }
            if(idx >= N) break;
        }
        idx = N-1;
        while(arr[idx] >= 1){
            if(idx-1 >= 0 && arr[idx-1] > 1){
                answer += arr[idx] * arr[idx-1];
                idx -= 2;
            }
            else{
                answer += arr[idx];
                idx--;
            }
            if(idx < 0) break;
        }
        System.out.println(answer);
    }
}
