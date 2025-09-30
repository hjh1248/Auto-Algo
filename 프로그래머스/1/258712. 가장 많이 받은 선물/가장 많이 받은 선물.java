import java.util.*;
import java.io.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        HashMap<String, Integer> friendsMap = new HashMap<>();
        int friendsLength = friends.length;
        int[][] gives = new int[friendsLength][friendsLength];
        int[] points = new int[friendsLength];
        int[] take = new int[friendsLength];
        
        for(int i=0; i<friends.length; i++){
            friendsMap.put(friends[i], i);
        }
        
        for(String give: gifts){
            StringTokenizer st = new StringTokenizer(give);
            int aIdx = friendsMap.get(st.nextToken());
            int bIdx = friendsMap.get(st.nextToken());
            gives[aIdx][bIdx]++;
            points[aIdx]++;
            points[bIdx]--;
        }
        
        for(int i=0; i<friendsLength-1; i++){
            for(int j=i+1; j<friendsLength; j++){
                if(gives[i][j] > gives[j][i]) take[i]++;
                else if(gives[i][j] < gives[j][i]) take[j]++;
                else if(gives[i][j] == gives[j][i]){
                    if(points[i]>points[j]) take[i]++;
                    else if(points[i]<points[j]) take[j]++;
                }
            }
        }
        
        for(int i=0; i<friendsLength; i++) answer = Math.max(answer, take[i]);
        
        return answer;
    }
}