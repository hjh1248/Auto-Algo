
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, M, C, max;
    static int[] map;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            map = new int[N*N];
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++){
                    map[i*N + j] = Integer.parseInt(st.nextToken());
                }
            }

            int[] gain = new int[N*N];
            for(int i=0; i<N; i++){
                for(int j=0; j<=N-M; j++){
                    max = 0;
                    subset(i, j, 0, 0, 0);
                    gain[i*N + j] = max;
                }
            }
            int answer = 0;
            for(int i=0; i<N*N-M; i++){
                int a = gain[i];
                if(a==0) continue;
                for(int j=i+M; j<N*N; j++){
                    int b = gain[j];
                    if(b==0) continue;
                    answer = Math.max(answer, a + b);
                }
            }
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
    static void subset(int r, int c, int sum, int gain, int idx){
        if(sum > C) return;
        if(idx==M){
            max = Math.max(max, gain);
            return;
        }
        int n = map[r*N + c];
        subset(r, c+1, sum + n, gain + n*n, idx+1);
        subset(r, c+1, sum, gain, idx+1);

    }
}
