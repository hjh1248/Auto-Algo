import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int[] ops = new int[4];
    static int[] nums;
    static int N;
    static int max;
    static int min;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        for(int tc=1; tc<=T; tc++){
            N = Integer.parseInt(br.readLine());
            nums = new int[N];
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<4; i++) ops[i] = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) nums[i] = Integer.parseInt(st.nextToken());

            perm(nums[0], 1);

            sb.append("#").append(tc).append(" ").append(max-min).append("\n");
        }
        System.out.println(sb);
    }

    static void perm(int answer, int idx){

        if(idx == N){
            max = Math.max(max, answer);
            min = Math.min(min, answer);
            return;
        }

        for(int i=0; i<4; i++){
            if(ops[i]!=0){
                ops[i]--;
                if(i==0){
                    perm(answer + nums[idx], idx+1);
                }
                else if(i==1){
                    perm(answer - nums[idx], idx+1);
                }
                else if(i==2){
                    perm(answer * nums[idx], idx+1);
                }
                else if(i==3){
                    perm(answer / nums[idx], idx+1);
                }
                ops[i]++;
            }
        }
    }
}
