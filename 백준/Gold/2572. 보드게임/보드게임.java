import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, M, answer;
    static int[][] dp;
    static ArrayList<int[]>[] map;
    static int[] cards;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        answer = 0;
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        
        cards = new int[N+1];
        for(int i=0; i<N; i++){
            cards[i] = convert(st.nextToken().charAt(0));
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        dp = new int[N+1][M+1];
        for(int i=0; i<=N; i++) {
            for(int j=0; j<=M; j++) {
                dp[i][j] = -1;
            }
        }

        map = new ArrayList[M+1];
        for(int i=1; i<=M; i++) map[i] = new ArrayList<>();

        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int color = convert(st.nextToken().charAt(0));

            map[u].add(new int[] {v, color});
            map[v].add(new int[] {u, color});
        }
        
        dp[0][1] = 0;
        for(int i=0; i<N; i++){
            for(int j=1; j<=M; j++){
                if(dp[i][j] == -1) continue;
                for(int[] next: map[j]){
                    int nVillage = next[0];
                    int nColor = next[1];
                    int nPoint = dp[i][j];
                    if(nColor == cards[i]) nPoint += 10;
                    dp[i+1][nVillage] = Math.max(dp[i+1][nVillage], nPoint);
                }
            }
        }
        for(int i=1; i<=M; i++) answer = Math.max(answer, dp[N][i]);
        System.out.println(answer);
    }

    static int convert(char c){
        if(c=='R') return 1;
        else if(c=='G') return 2;
        else if(c=='B') return 3;
        else return 0;
    }
}
