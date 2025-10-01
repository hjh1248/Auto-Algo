import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        ArrayList<Integer> answer = new ArrayList<>();
        
        int[] todayArray = new int[3];
        String[] splitToday = today.split("\\.");
        for(int i=0; i<3; i++){
            todayArray[i] = Integer.parseInt(splitToday[i]);
        }
        
        HashMap<String, int[]> expiryBaseMap = new HashMap<>();
        for(String term: terms){
            String[] strings = term.split(" ");
            int months = Integer.parseInt(strings[1]);
            
            int year = todayArray[0];
            int month = todayArray[1];
            int day = todayArray[2];
            
            month -= months;
            while(month <= 0){
                year--;
                month += 12;
            }
            
            day++;
            if(day > 28){
                day = 1;
                month++;
                if(month > 12){
                    year++;
                    month = 1;
                }
            }
            
            expiryBaseMap.put(strings[0], new int[]{year, month, day});
        }
        
        int idx=1;
        for(String privacy: privacies){
            String[] strings = privacy.split(" ");            
            String[] split = strings[0].split("\\.");
            
            int year = Integer.parseInt(split[0]);
            int month = Integer.parseInt(split[1]);
            int day = Integer.parseInt(split[2]);
            
            int[] baseDate = expiryBaseMap.get(strings[1]);
            
            if(year < baseDate[0]) answer.add(idx);
            else if(year == baseDate[0] && month < baseDate[1]) answer.add(idx);
            else if(year == baseDate[0] && month == baseDate[1] && day < baseDate[2]) answer.add(idx);
            idx++;
        }
        
        int[] result = new int[answer.size()];
        for(int i=0; i<answer.size(); i++){
            result[i] = answer.get(i);
        }
        return result;
    }
}