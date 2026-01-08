import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long answer = -1;
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        HashSet<Long> set = new HashSet<>();
        ArrayDeque<long[]> q = new ArrayDeque<>();

        q.offer(new long[] {A, 1});
        while(!q.isEmpty()){
            long[] cur = q.poll();
            long n = cur[0];
            long cnt = cur[1];

            if(n==B){
                answer = cnt;
                break;
            }
            cnt++;
            long next = n*10+1;
            if(next<=B && !set.contains(next)) {
                q.offer(new long[] {next, cnt});
                set.add(next);
            }
            next = n*2;
            if(next<=B && !set.contains(next)) {
                q.offer(new long[] {next, cnt});
                set.add(next);
            }
        }
        System.out.println(answer);
    }
}