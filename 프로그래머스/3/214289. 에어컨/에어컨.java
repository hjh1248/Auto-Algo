import java.util.*;
import java.io.*;

class Solution {
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        final int INF = 1000000000;
        int answer = INF;
        temperature += 10;
        t1 += 10;
        t2 += 10;
        
        int len = onboard.length;
        int[][] dp = new int[len][51];
        for(int i=0; i<len; i++) Arrays.fill(dp[i], INF);
        
        dp[0][temperature] = 0;
        
        for(int i=1; i<len; i++){
            for(int j=0; j<=50; j++){
                if(onboard[i]==1 && (j<t1 || j>t2)) continue;
                int n = INF;
                
                // 에어컨 작동 x
                if(j>0 && j-1 < temperature) n = dp[i-1][j-1];
                if(j == temperature) n = Math.min(n, dp[i-1][j]);
                if(j<50 && j+1 > temperature) n = Math.min(n, dp[i-1][j+1]);
                
                // 에어컨 작동 o
                if(j>0) n = Math.min(n, dp[i-1][j-1] + a);
                if(j<50) n = Math.min(n, dp[i-1][j+1] + a);
                n = Math.min(n, dp[i-1][j] + b);
                
                dp[i][j] = n;
            }
        }
        
        for(int i=0; i<=50; i++){
            answer = Math.min(answer, dp[len-1][i]);
        }
            
        return answer;
    }
}