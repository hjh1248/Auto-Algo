
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int[][] magnets = new int[5][8];
    static int[] rotates = new int[5];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++){
            Arrays.fill(rotates, 0);
            int K = Integer.parseInt(br.readLine());
            int answer = 0;
            for(int i=1; i<5; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<8; j++){
                    magnets[i][j] = Integer.parseInt(st.nextToken());
                    
                }
            }

            for(int i=0; i<K; i++){
                st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                rotate(check(n, d));
            }
            for(int i=1; i<5; i++){
                if(magnets[i][Math.floorMod(-rotates[i],8)]==1) answer += Math.pow(2, i-1);
            }
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
    static void rotate(int[] dirs){
        for(int i=1; i<5; i++){
            if(dirs[i]==1){
                rotates[i]++;
            }
            else if(dirs[i]==-1){
                rotates[i]--;
            }
        }
    }
    static int[] check(int n, int d){
        int[] dirs = new int[5];
        dirs[n] = d;
        int nd = d;
        int nn = n;
        while(1<nn){
            if(magnets[nn][Math.floorMod(6-rotates[nn],8)]!=magnets[nn-1][Math.floorMod(2-rotates[nn-1],8)]) dirs[nn-1] = -nd;
            else break;
            nd = -nd;
            nn--;
        }
        nd = d;
        nn = n;
        while(nn<4){
            if(magnets[nn][Math.floorMod(2-rotates[nn],8)]!=magnets[nn+1][Math.floorMod(6-rotates[nn+1],8)]) dirs[nn+1] = -nd;
            else break;
            nd = -nd;
            nn++;
        }
        return dirs;
    }
}
