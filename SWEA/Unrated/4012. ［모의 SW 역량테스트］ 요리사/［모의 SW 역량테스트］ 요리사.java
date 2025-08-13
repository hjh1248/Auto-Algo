import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
    static int[][] syns;
    static int N;
    static int minDiff;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++){
            N = Integer.parseInt(br.readLine());
            minDiff = Integer.MAX_VALUE;
            syns = new int[N][N];

            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++){
                    syns[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            Set<Integer> aSet = new HashSet<>();
            Set<Integer> bSet = new HashSet<>();
            aSet.add(0);
            div(aSet, bSet, 0, 0, 1, 1);
            sb.append("#").append(tc).append(" ").append(minDiff).append("\n");
        }
        System.out.println(sb);
    }
    static void div(Set<Integer> aSet, Set<Integer> bSet, int aYum, int bYum, int cnt, int idx){
        if(cnt>N/2) return;
        if(idx>=N){
            if(cnt==N/2){
                minDiff = Math.min(minDiff, Math.abs(aYum-bYum));
            }
            return;
        }

        aSet.add(idx);
        int beforeaYum = aYum;
        for(int a: aSet){
            aYum += syns[idx][a] + syns[a][idx];
        }
        div(aSet, bSet, aYum, bYum, cnt+1, idx+1);
        aYum = beforeaYum;
        aSet.remove(idx);

        bSet.add(idx);
        int beforebYum = bYum;
        for(int b: bSet){
            bYum += syns[idx][b] + syns[b][idx];
        }
        div(aSet, bSet, aYum, bYum, cnt, idx+1);
        bYum = beforebYum;
        bSet.remove(idx);
    }
}
