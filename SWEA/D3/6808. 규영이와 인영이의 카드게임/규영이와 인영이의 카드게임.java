import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static boolean[] arrayVisited = new boolean[19];
    static boolean[] visited = new boolean[9];
    static int[] a;
    static int[] b;
    static int win;
    static int lose;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine());
            a = new int[9];
            b = new int[9];
            Arrays.fill(arrayVisited, false);
            Arrays.fill(visited, false);
            win = 0;
            lose = 0;

            for(int i=0; i<9; i++){
                int n = Integer.parseInt(st.nextToken());
                arrayVisited[n] = true;
                a[i] = n;
            }
            int cnt=0;
            for(int i=1; i<=18; i++){
                if(!arrayVisited[i]){
                    b[cnt] = i;
                    cnt++;
                }
            }
            perm(0, visited, 0, 0);
            sb.append("#").append(tc).append(" ").append(win).append(" ").append(lose).append("\n");
        }
        System.out.println(sb);
    }
    static void perm(int cnt, boolean[] visited, int winPoint, int losePoint){
        if(cnt==9){
            if (winPoint>losePoint) win++;
            else lose++;
            return;
        }
        for(int i=0; i<9; i++){
            if(!visited[i]){
                visited[i] = true;
                if(a[cnt]>b[i]) perm(cnt+1, visited, winPoint + a[cnt] + b[i], losePoint);
                else perm(cnt+1, visited, winPoint, losePoint + a[cnt] + b[i]);
                visited[i] = false;
            }
            
        }
        
    }
}
