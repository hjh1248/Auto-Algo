
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        int INF = Integer.MAX_VALUE;
        for(int tc=1; tc<=T; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int[][] graph = new int[N][N];
            
            for(int i=0; i<N; i++){
                Arrays.fill(graph[i], INF);
                for(int j=0; j<N; j++){
                    int a = Integer.parseInt(st.nextToken());
                    if(i==j) graph[i][j] = 0;
                    else if(a==1) graph[i][j] = 1;
                }
            }
            for(int k=0; k<N; k++){
                for(int i=0; i<N; i++){
                    for(int j=0; j<N; j++){
                        if(graph[i][k] == INF || graph[k][j] == INF) continue;
                        graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                    }
                }
            }
            int min = Integer.MAX_VALUE;
            A:for(int i=0; i<N; i++){
                int tmp = 0;
                for(int j=0; j<N; j++){
                    if(graph[i][j]==INF) continue A;
                    tmp += graph[i][j];
                }
                min = Math.min(min, tmp);
            }
            sb.append("#").append(tc).append(" ").append(min).append("\n");
        }
        System.out.println(sb);
    }
}
