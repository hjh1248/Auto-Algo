
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int X = Integer.parseInt(st.nextToken());
            int answer = 0;
            int[][] map = new int[N][N];

            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            A:for(int r=0; r<N; r++){
                boolean[] visited = new boolean[N];
                for(int c=1; c<N; c++){
                    if(map[r][c] > map[r][c-1]){
                        int x = c-X;
                        if(x<0) continue A;
                        for(int i=x; i<c; i++){
                            if(map[r][i] != map[r][c]-1 || visited[i]) continue A;
                            visited[i] = true;
                        }
                    }
                    if(map[r][c] < map[r][c-1]){
                        int x = c+X;
                        if(x>N) continue A;
                        for(int i=c; i<x; i++){
                            if(map[r][i] != map[r][c-1]-1 || visited[i]) continue A;
                            visited[i] = true;
                        }
                    }
                }
                answer++;
            }

            A:for(int c=0; c<N; c++){
                boolean[] visited = new boolean[N];
                for(int r=1; r<N; r++){
                    if(map[r][c] > map[r-1][c]){
                        int x = r-X;
                        if(x<0) continue A;
                        for(int i=x; i<r; i++){
                            if(map[i][c] != map[r][c]-1 || visited[i]) continue A;
                            visited[i] = true;
                        }
                    }
                    if(map[r][c] < map[r-1][c]){
                        int x = r+X;
                        if(x>N) continue A;
                        for(int i=r; i<x; i++){
                            if(map[i][c] != map[r-1][c]-1 || visited[i]) continue A;
                            visited[i] = true;
                        }
                    }
                }
                answer++;
            }
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
}
