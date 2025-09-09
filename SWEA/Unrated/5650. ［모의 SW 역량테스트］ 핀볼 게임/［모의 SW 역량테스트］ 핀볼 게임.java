
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] map;
    static int N, startR, startC;
    static ArrayList<Integer>[] wormHole;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine().trim());

        for(int tc=1; tc<=T; tc++){
            N = Integer.parseInt(br.readLine().trim());
            map = new int[N][N];
            int max = 0;

            // 웜홀 좌표 저장
            wormHole = new ArrayList[5];
            for(int i=0; i<5; i++){
                wormHole[i] = new ArrayList<>();
            }

            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine().trim());
                for(int j=0; j<N; j++){
                    int n = Integer.parseInt(st.nextToken());
                    if(6<=n){
                        wormHole[n-6].add(i);
                        wormHole[n-6].add(j);
                    }
                    map[i][j] = n;
                }
            }

            //각 좌표에서 출발
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(map[i][j]!=0) continue;
                    max = Math.max(max, start(i, j));
                }
            }
            sb.append("#").append(tc).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }
    static int start(int r, int c){
        int max = 0;
        startR = r;
        startC = c;
        for(int i=0; i<4; i++){
            max = Math.max(max, simulate(r, c, i));
        }
        return max;
    }
    static int simulate(int r, int c, int d){
        int cnt = 0;
        while(true){
            // 다음 좌표
            r += dr[d];
            c += dc[d];

            // 맵 가장자리
            if(r<0 || N<=r || c<0 || N<=c){
                if(d==0) d=1;
                else if(d==1) d=0;
                else if(d==2) d=3;
                else if(d==3) d=2;
                cnt++;
                //좌표 복귀
                r += dr[d];
                c += dc[d];
            }
            int n = map[r][c];

            if(r==startR && c==startC || n==-1) return cnt;

            // 벽일 때
            if(0<n && n<=5){
                if(n==1){
                    if(d==0) d=1;
                    else if(d==1) d=3;
                    else if(d==2) d=0;
                    else if(d==3) d=2;
                }
                else if(n==2){
                    if(d==0) d=3;
                    else if(d==1) d=0;
                    else if(d==2) d=1;
                    else if(d==3) d=2;
                }
                else if(n==3){
                    if(d==0) d=2;
                    else if(d==1) d=0;
                    else if(d==2) d=3;
                    else if(d==3) d=1;
                }
                else if(n==4){
                    if(d==0) d=1;
                    else if(d==1) d=2;
                    else if(d==2) d=3;
                    else if(d==3) d=0;
                }
                else if(n==5){
                    if(d==0) d=1;
                    else if(d==1) d=0;
                    else if(d==2) d=3;
                    else if(d==3) d=2;
                }
                cnt++;
            }

            // 웜홀일 때
            else if(6<=n){
                if(wormHole[n-6].get(0)==r && wormHole[n-6].get(1)==c){
                    r = wormHole[n-6].get(2);
                    c = wormHole[n-6].get(3);
                }
                else{
                    r = wormHole[n-6].get(0);
                    c = wormHole[n-6].get(1);
                }
            }
        }
        
    }
}
