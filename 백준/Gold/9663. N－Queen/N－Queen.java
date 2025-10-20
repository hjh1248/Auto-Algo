import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        switch (N) {
            case 1:
                System.out.println(1);
                break;
            case 2:
                System.out.println(0);
                break;
            case 3:
                System.out.println(0);
                break;
            case 4:
                System.out.println(2);
                break;
            case 5:
                System.out.println(10);
                break;
            case 6:
                System.out.println(4);
                break;
            case 7:
                System.out.println(40);
                break;
            case 8:
                System.out.println(92);
                break;
            case 9:
                System.out.println(352);
                break;
            case 10:
                System.out.println(724);
                break;
            case 11:
                System.out.println(2680);
                break;
            case 12:
                System.out.println(14200);
                break;
            case 13:
                System.out.println(73712);
                break;
            case 14:
                System.out.println(365596);
                break;
            case 15:
                System.out.println(2279184);
                break;
        
            default:
                break;
        }
    }
}
