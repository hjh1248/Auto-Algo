
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for(int i=0; i<n; i++){
            int[] cnt = new int[26];
            char[] chars = br.readLine().toLowerCase().toCharArray();

            for(char c: chars){
                if(c-'a' >=0 && c-'a' <26){
                    cnt[c-'a']++;
                }
            }
            int min = 3;
            for(int j=0; j<26; j++){
                min = Math.min(min, cnt[j]);
            }

            System.out.print("Case " + (i+1) + ": ");
            if(min == 0) {
                System.out.println("Not a pangram");
            } else if(min == 1) {
                System.out.println("Pangram!");
            } else if(min == 2) {
                System.out.println("Double pangram!!");
            } else {
                System.out.println("Triple pangram!!!");
            }
            
        }
        
    }
}
