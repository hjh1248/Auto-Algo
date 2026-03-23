import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int answer, M, homeIdx, chickenIdx;
    static boolean[] available;
    static int[][] dists;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        answer = Integer.MAX_VALUE;
        M = Integer.parseInt(st.nextToken());

        homeIdx = 0;
        int[][] homes = new int[2 * N][2];
        chickenIdx = 0;
        int[][] chickens = new int[13][2];
        
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                int x = Integer.parseInt(st.nextToken());
                if(x==1){
                    homes[homeIdx][0] = i;
                    homes[homeIdx][1] = j;
                    homeIdx++;
                }
                else if(x==2){
                    chickens[chickenIdx][0] = i;
                    chickens[chickenIdx][1] = j;
                    chickenIdx++;
                }
            }
        }

        dists = new int[homeIdx][chickenIdx];
        for(int i=0; i<homeIdx; i++){
            for(int j=0; j<chickenIdx; j++){
                dists[i][j] = Math.abs(homes[i][0] - chickens[j][0]) + Math.abs(homes[i][1] - chickens[j][1]);
            }
        }
        
        available = new boolean[chickenIdx];
        
        comb(0, 0);

        System.out.println(answer);
    }
    static void comb(int start, int idx){
        if(idx==M){
            cal();
            return;
        }
        for(int i=start; i<chickenIdx; i++){
            if(chickenIdx - i < M - idx) break;
            available[i] = true;
            comb(i+1, idx+1);
            available[i] = false;
        }
    }

    static void cal(){
        int sum = 0;
        for(int i=0; i<homeIdx; i++){
            int tmp = 100;
            for(int j=0; j<chickenIdx; j++){
                if(available[j]) tmp = Math.min(tmp, dists[i][j]);
            }
            sum += tmp;
        }
        answer = Math.min(answer, sum);
    }
}
