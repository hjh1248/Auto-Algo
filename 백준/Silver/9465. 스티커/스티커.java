
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
            int[] cur = new int[3];
            
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            StringTokenizer st2 = new StringTokenizer(br.readLine());


            for(int i=1; i<=N; i++){
                int[] next = new int[3];
                next[0] = Math.max(cur[1], cur[2]) + Integer.parseInt(st1.nextToken());
                next[1] = Math.max(cur[0], cur[2]) + Integer.parseInt(st2.nextToken());
                next[2] = Math.max(cur[0], cur[1]);
                cur = next;
            }

            sb.append(Math.max(cur[0], cur[1])).append("\n");
        }
        System.out.println(sb);
    }
}
