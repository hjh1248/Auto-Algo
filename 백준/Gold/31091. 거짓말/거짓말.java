import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        int positiveCnt = 0;
        int[] positive = new int[N+1];
        int[] negative = new int[N+1];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for(int i=0; i<N; i++){
            int n = Integer.parseInt(st.nextToken());
            if(n>0){
                positive[n]++;
                positiveCnt++;
            }
            else if(-n!=N) negative[-n+1]++;
        }
        
        int cnt = 0;
        int tmp = positiveCnt;
        for(int i=0; i<=N; i++){
            tmp -= positive[i];
            tmp += negative[i];
            if(i==tmp){
                cnt++;
                sb.append(i).append(" ");
            }
        }
        
        System.out.println(cnt);
        System.out.println(sb);
        
    }
}