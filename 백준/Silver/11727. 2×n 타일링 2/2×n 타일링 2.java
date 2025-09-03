import java.util.Scanner;

public class Main {
    static int[] memo;
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        memo = new int[N+2];
        memo[1] = 1;
        memo[2] = 3;
        
        for(int i=3; i<=N; i++){
            memo[i] = (memo[i-1] + memo[i-2]*2)%10007;
        }
        System.out.println(memo[N]);
    }
}