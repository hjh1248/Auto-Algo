import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0; i<N; i++){
            int n = Integer.parseInt(br.readLine());
            pq.offer(n);
        }
        
        while(true){
            if(pq.size()==1){
                System.out.println(answer);
                return;
            }
            int a = pq.poll();
            int b = pq.poll();
            pq.offer(a+b);
            answer += a+b;
        }
    }
}
