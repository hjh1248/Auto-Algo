
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution {
    static int peopleCount;
    static int min;
    static int[][] dists;
    static int[] stairLength;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++){
            int N = Integer.parseInt(br.readLine());
            // 계단 좌표
            int[][] stairs = new int[2][2];
            stairLength = new int[2];
            // 사람들 좌표
            ArrayList<int[]> peoples = new ArrayList<>();

            // 계단, 사람들 좌표 저장
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++){
                    int n = Integer.parseInt(st.nextToken());
                    if(n==1) peoples.add(new int[] {i, j});
                    else if(n!=0){
                        if(stairLength[0]==0){
                            stairs[0][0] = i;
                            stairs[0][1] = j;
                            stairLength[0] = n;
                        }
                        else{
                            stairs[1][0] = i;
                            stairs[1][1] = j;
                            stairLength[1] = n;
                        }
                    }
                }
            }
            peopleCount = peoples.size();

            // 계단 두 개와 사람들 간의 거리 측정
            dists = new int[peopleCount][2];
            for(int i=0; i<peopleCount; i++){
                for(int j=0; j<2; j++){
                    dists[i][j] = Math.abs(peoples.get(i)[0] - stairs[j][0]) + Math.abs(peoples.get(i)[1] - stairs[j][1]);
                }
            }

            min=Integer.MAX_VALUE;
            subset(0, new ArrayList<>(), new ArrayList<>());
            sb.append("#").append(tc).append(" ").append(min + 1).append("\n");
        }
        System.out.println(sb);
    }

    // 두개의 무리로 나눠서 시간 계산
    static void subset(int idx, ArrayList<Integer> set1, ArrayList<Integer> set2){
        if(idx==peopleCount){
            min = Math.min(min, count(set1, set2));
            return;
        }

        set1.add(dists[idx][0]);
        subset(idx+1, set1, set2);
        set1.remove(set1.size()-1);

        set2.add(dists[idx][1]);
        subset(idx+1, set1, set2);
        set2.remove(set2.size()-1);
    }

    static int count(ArrayList<Integer> set1, ArrayList<Integer> set2){
        ArrayList<Integer> newSet1 = new ArrayList<>(set1);
        ArrayList<Integer> newSet2 = new ArrayList<>(set2);
        Collections.sort(newSet1);
        Collections.sort(newSet2);

        // set[i-3]과 set[i] 의 차이가 계단높이보다 작을 때 뒤에 도착시간 증가
        for(int i=3; i<newSet1.size(); i++){
            if(newSet1.get(i)<newSet1.get(i-3)+stairLength[0]) newSet1.set(i, newSet1.get(i-3)+stairLength[0]);
        }
        for(int i=3; i<newSet2.size(); i++){
            if(newSet2.get(i)<newSet2.get(i-3)+stairLength[1]) newSet2.set(i, newSet2.get(i-3)+stairLength[1]);
        }

        if(newSet1.isEmpty()) return newSet2.get(newSet2.size()-1)+stairLength[1];
        else if(newSet2.isEmpty()) return newSet1.get(newSet1.size()-1)+stairLength[0];
        
        return Math.max(newSet1.get(newSet1.size()-1)+stairLength[0], newSet2.get(newSet2.size()-1)+stairLength[1]);
    }
}
