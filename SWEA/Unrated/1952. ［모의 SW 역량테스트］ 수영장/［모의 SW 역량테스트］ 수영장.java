
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int min, month3;
    static int[] costs;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine());
            int day = Integer.parseInt(st.nextToken());
            int month = Integer.parseInt(st.nextToken());
            month3 = Integer.parseInt(st.nextToken());
            int year = Integer.parseInt(st.nextToken());

            costs = new int[12];

            min = year;
            
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<12; i++){
                int n = Integer.parseInt(st.nextToken());
                if(month < n*day) costs[i] = month;
                else costs[i] = n*day;
            }

            dfs(0, 0);
            
            sb.append("#").append(tc).append(" ").append(min).append("\n");
        }
        System.out.println(sb);
    }
    static void dfs(int month, int cost) {
        if(12<=month){
        min = Math.min(min, cost);
        return;
        }
        dfs(month + 3, cost + month3);
        dfs(month + 1, cost + costs[month]);
    }
}
