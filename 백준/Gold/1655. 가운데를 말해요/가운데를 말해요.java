import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();

        for(int i=0; i<N; i++){
            int a = Integer.parseInt(br.readLine());

            if(i==0){
                maxPQ.add(a);
            }
            else if(i%2==0){
                if(minPQ.peek() < a){
                    int tmp = a;
                    a = minPQ.poll();
                    minPQ.add(tmp);
                }
                maxPQ.add(a);
            }
            else{
                if(maxPQ.peek() > a){
                    int tmp = a;
                    a = maxPQ.poll();
                    maxPQ.add(tmp);
                }
                minPQ.add(a);
            }
            sb.append(maxPQ.peek()).append("\n");
        }
        System.out.println(sb);
    }
}
