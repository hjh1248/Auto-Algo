import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

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
            String[] nums = new String[N];
            int K = Integer.parseInt(st.nextToken());
            String str = br.readLine();
            for(int i=0; i<n; i++){
                for(int j=0; j<4; j++){
                    if(j==3){
                        nums[i*4+3] = str.substring(3*n+i) + str.substring(0, i);
                        continue;
                    }
                    nums[i*4+j] = str.substring(j*n+i, j*n+n+i);
                }
            }
            Arrays.sort(nums, (a, b) -> Integer.compare(Integer.parseInt(b, 16), Integer.parseInt(a, 16)));
            String tmp = "";
            for(int i=0; i<K; i++){
                if(tmp.equals(nums[i])) K++;
                tmp = nums[i];
            }
            int answer = Integer.parseInt(tmp, 16);            
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
}
