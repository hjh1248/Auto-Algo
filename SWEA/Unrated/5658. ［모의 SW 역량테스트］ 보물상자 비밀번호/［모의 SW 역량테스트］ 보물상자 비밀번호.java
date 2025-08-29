import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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
            Integer[] nums = new Integer[N];
            int K = Integer.parseInt(st.nextToken());
            String str = br.readLine();
            for(int i=0; i<n; i++){
                for(int j=0; j<4; j++){
                    if(j==3){
                        nums[i*4+3] = Integer.parseInt(str.substring(3*n+i) + str.substring(0, i), 16);
                        continue;
                    }
                    nums[i*4+j] = Integer.parseInt(str.substring(j*n+i, j*n+n+i), 16);
                }
            }
            Arrays.sort(nums, (a, b) -> b - a);
            int tmp = 0;
            for(int i=0; i<K; i++){
                if(tmp==nums[i]) K++;
                tmp = nums[i];
            }
            int answer = tmp;         
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
}
