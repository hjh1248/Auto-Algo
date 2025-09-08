
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 메모리: 116,204 kb, 실행시간: 2,147 ms

public class Solution {
    static int N, M, K;
    static int[][] map;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++){
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[N][M];
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<M; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            if(K==1){
                sb.append(0).append("\n");
                continue;
            }
            for(int i=0; i<=K; i++){
                if(comb(0, 0, i)){
                sb.append(i).append("\n");
                break;
                }
            }
        }
        System.out.println(sb);
    }
    static boolean check(){
        A:for(int j=0; j<M; j++){
            int tmp = map[0][j];
            int cnt = 1;
            for(int i=1; i<N; i++){
                if(map[i][j]==tmp){
                    cnt++;
                    if(cnt==K) continue A;
                }
                else{
                    cnt = 1;
                    tmp = map[i][j];
                }
            }
            return false;
        }
        return true;
    }
    static boolean comb(int cnt, int start, int max){
        if(cnt==max){
            return check();
        }
        for(int i=start; i<N; i++){
            int[] original = map[i].clone();

            for(int j=0; j<M; j++) map[i][j] = 0;
            if(comb(cnt+1, i+1, max)) return true;

            for(int j=0; j<M; j++) map[i][j] = 1;
            if(comb(cnt+1, i+1, max)) return true;

            map[i] = original;
        }
        return false;
    }
}
