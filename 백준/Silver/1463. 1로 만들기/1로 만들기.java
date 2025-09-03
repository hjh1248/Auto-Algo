import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] memo = new int[N+2];
        memo[1] = 0;
        memo[2] = 1;
        
        for(int i=3; i<=N; i++){
            int min = Integer.MAX_VALUE;
            if(i%3==0) min = Math.min(min, memo[i/3]);
            if(i%2==0) min = Math.min(min, memo[i/2]);
            min = Math.min(min, memo[i-1]);
            memo[i] = min+1; 
        }
        System.out.println(memo[N]);
    }
}
