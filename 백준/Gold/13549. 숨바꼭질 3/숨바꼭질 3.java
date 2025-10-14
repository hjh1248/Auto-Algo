import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        final int MAX = 100001;

        int[] dist = new int[MAX];
        for(int i=0; i<MAX; i++) dist[i] = MAX;
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(N);
        dist[N] = 0;

        while(!q.isEmpty()){
            int cur = q.poll();

            if(cur == K){
                System.out.println(dist[cur]);
                return;
            }

            int next = cur*2;
            
            if(next<MAX && dist[cur] < dist[next]){
                q.offerFirst(next);
                dist[next] = dist[cur];
            }
            
            int nDist = dist[cur] + 1;

            next = cur - 1;
            if(0<=next && nDist < dist[next]){
                q.offer(next);
                dist[next] = nDist;
            }

            next = cur + 1;
            if(next<MAX && nDist < dist[next]){
                q.offer(next);
                dist[next] = nDist;
            }
        }
    }
}