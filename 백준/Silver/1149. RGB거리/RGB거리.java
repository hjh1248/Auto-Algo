import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N+1][3];
        for(int i=1; i<=N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            map[i][0] = Math.min(map[i-1][1], map[i-1][2])+Integer.parseInt(st.nextToken());
            map[i][1] = Math.min(map[i-1][0], map[i-1][2])+Integer.parseInt(st.nextToken());
            map[i][2] = Math.min(map[i-1][0], map[i-1][1])+Integer.parseInt(st.nextToken());
        }
        int min = Math.min(map[N][0], map[N][1]);
        System.out.println(Math.min(min, map[N][2]));
    }
}