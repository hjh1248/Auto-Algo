
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        double[] xList = new double[N];
        double[] yList = new double[N];
        double sum1 = 0;
        double sum2 = 0;

        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            xList[i] = Double.parseDouble(st.nextToken());
            yList[i] = Double.parseDouble(st.nextToken());
        }
        for(int i=0; i<N-1; i++){
            sum1 += xList[i]*yList[i+1];
            sum2 += yList[i]*xList[i+1];
        }
        sum1 += xList[N-1]*yList[0];
        sum2 += yList[N-1]*xList[0];

        System.out.printf("%.1f%n", Math.abs((sum1 - sum2)/2));
    }
}
