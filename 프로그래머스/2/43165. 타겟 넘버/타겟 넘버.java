import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] numbers, int target) {
        int answer = 0;
        int len = numbers.length;
        // number, idx
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 0});
        while(!q.isEmpty()){
            int[] cur = q.poll();
            if(cur[1] == len){
                if(cur[0] == target) answer++;
                continue;
            }
            q.offer(new int[] {cur[0] + numbers[cur[1]], cur[1]+1});
            q.offer(new int[] {cur[0] - numbers[cur[1]], cur[1]+1});
        }
        
        return answer;
    }
}