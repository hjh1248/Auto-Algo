import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        int count = 0;
        int num = 666;
        
        while (true) {
            if (String.valueOf(num).contains("666")) {
                count++;
            }
            
            if (count == N) {
                System.out.println(num);
                break;
            }
            
            num++;
        }
    }
}