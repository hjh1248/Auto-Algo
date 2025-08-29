import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

// 메모리: 62,620kb 실행시간: 246ms

public class Solution {
    static int N;
    static int answer;
    static int[][] map;
    static boolean[] visited;
    static int[] dr = {-1, 1, 1, -1};
    static int[] dc = {1, 1, -1, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++){
            answer = -1;
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];

            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            for(int i=1; i<N-1; i++){
                for(int j=0; j<N-2; j++){
                    visited = new boolean[101];
                    tour(i, j, 0, 0, 0, 0, visited);
                }
            }
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
    static void tour(int r, int c, int an, int bn, int dir, int n, boolean[] visited){
        if(dir==3 && an==0 && bn==0){
            answer = Math.max(answer, n);
            return;
        }
        int nr = r + dr[dir];
        int nc = c + dc[dir];
        if(0<=nr && nr<N && 0<=nc && nc<N){
            if(visited[map[nr][nc]]) return;
            visited[map[nr][nc]] = true;
            if(dir==0){
                tour(nr, nc, an+1, bn, dir, n+1, visited);
                tour(nr, nc, an+1, bn, dir+1, n+1, visited);
            }
            else if(dir==1){
                tour(nr, nc, an, bn+1, dir, n+1, visited);
                tour(nr, nc, an, bn+1, dir+1, n+1, visited);
            }
            else if(dir==2){
                if(an>1) tour(nr, nc, an-1, bn, dir, n+1, visited);
                else tour(nr, nc, an-1, bn, dir+1, n+1, visited);
            }
            else if(dir==3){
                tour(nr, nc, an, bn-1, dir, n+1, visited);
            }
            visited[map[nr][nc]] = false;
        }
    }
}
