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

        boolean[] visited = new boolean[100001];
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(N);
        visited[N] = true;

        int cnt = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0; i<size; i++){
                int node = q.poll();
                if(node==K){
                    System.out.println(cnt);
                    return;
                }
                q.offer(node);
                while(node<K){
                    node*=2;
                    if(node > 100000 || visited[node]) break;
                    if(node==K){
                        System.out.println(cnt);
                        return;
                    }
                    visited[node] = true;
                    q.offer(node);
                }
            }
            size = q.size();
            cnt++;
            for(int i=0; i<size; i++){
                int node = q.poll();
                if(node-1==K || node+1 ==K){
                    System.out.println(cnt);
                    return;
                }
                if(node>0 && !visited[node-1]){
                    visited[node-1] = true;
                    q.offer(node-1);
                }
                if(node<K && !visited[node+1]){
                    visited[node+1] = true;
                    q.offer(node+1);
                }
            }
        }
    }
}