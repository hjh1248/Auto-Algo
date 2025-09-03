
import java.util.Scanner;

public class Main {
    static int[] memo;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        memo = new int[N+2];
        memo[1] = 1;
        memo[2] = 2;
        
        System.out.println(fibo(N));
    }
    static int fibo(int n){
        if(memo[n]!=0) return memo[n];
        memo[n] = (fibo(n-1) + fibo(n-2))%10007;
        return memo[n];
    }
}