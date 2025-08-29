import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

// 메모리: 32,640kb, 실행시간: 156ms

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int n = N/4;
            HashSet<Integer> set = new HashSet<>();
            int K = Integer.parseInt(st.nextToken());
            String str = br.readLine();
            for(int i=0; i<n; i++){
                for(int j=0; j<4; j++){
                    if(j==3){
                        set.add(Integer.parseInt(str.substring(3*n+i) + str.substring(0, i), 16));
                        continue;
                    }
                    set.add(Integer.parseInt(str.substring(j*n+i, j*n+n+i), 16));
                }
            }
            ArrayList<Integer> nums = new ArrayList<>(set);
            Collections.sort(nums, Collections.reverseOrder());
            int answer = nums.get(K-1);
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
}
