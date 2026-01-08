import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int answer = -1;
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int cnt = 1;

        while(A < B){
            if(B%10 == 1) B /= 10;
            else if(B%2 == 0) B /= 2;
            else break;
            cnt++;
            if(A==B){
                answer = cnt;
                break;
            }
        }
        System.out.println(answer);
    }
}