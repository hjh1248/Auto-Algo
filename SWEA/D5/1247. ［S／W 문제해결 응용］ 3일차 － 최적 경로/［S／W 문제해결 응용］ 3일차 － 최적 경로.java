import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int cX, cY, hX, hY;
    static int[] xArray, yArray;
    static int N;
    static boolean[] visited;
    static int min;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++){
            N = Integer.parseInt(br.readLine());
            min = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            cX = Integer.parseInt(st.nextToken());
            cY = Integer.parseInt(st.nextToken());
            hX = Integer.parseInt(st.nextToken());
            hY = Integer.parseInt(st.nextToken());

            xArray = new int[N];
            yArray = new int[N];
            for(int i=0; i<N; i++){
                xArray[i] = Integer.parseInt(st.nextToken());
                yArray[i] = Integer.parseInt(st.nextToken());
            }
            visited = new boolean[N];
            perm(cX, cY, 0, 0);
            sb.append("#").append(tc).append(" ").append(min).append("\n");
        }
        System.out.println(sb);
    }
    static void perm(int preX, int preY, int dist, int idx){
        if(idx==N){
            dist += Math.abs(preX - hX) + Math.abs(preY - hY);
            min = Math.min(dist, min);
            return;
        }
        for(int i=0; i<N; i++){
            if(!visited[i]){
                visited[i] = true;
                int newDist = dist + Math.abs(xArray[i]-preX) + Math.abs(yArray[i]-preY);
                perm(xArray[i], yArray[i], newDist, idx+1);
                visited[i] = false;
            }
        }
    }
}
