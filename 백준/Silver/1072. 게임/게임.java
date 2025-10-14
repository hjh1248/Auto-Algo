import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long X = Long.parseLong(st.nextToken());
        long Y = Long.parseLong(st.nextToken());
        long Z = X==0 ? 0 : Y*100/X;

        if(Z>=99){
            System.out.println(-1);
            return;
        }

        int i=0;
        while((Y + (1 << i)) * 100 / (X + (1 << i)) == Z) i++;
        
        int n = 1 << i;
        if(i<2){
            System.out.println(n);
            return;
        }
        for(int j=i-2; j>=0; j--){
            if((Y + n - (1 << j)) * 100 / (X + n - (1 << j)) != Z) n -= 1 << j;
        }
        System.out.println(n);
    }
}
