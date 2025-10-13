import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int sum = 0;
            for(int j=0; j<N; j++){
                sum += Integer.parseInt(st.nextToken());
                map[i][j] = sum + (i==0 ? 0 : map[i-1][j]);
            }
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;

            int sum = map[x2][y2];
            if(x1!=0) sum -= map[x1-1][y2];
            if(y1!=0) sum -= map[x2][y1-1];
            if(x1!=0 && y1!=0) sum += map[x1-1][y1-1];

            sb.append(sum).append("\n");
        }
        System.out.println(sb);
    }
}
