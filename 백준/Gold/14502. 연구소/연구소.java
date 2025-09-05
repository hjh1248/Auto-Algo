
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int N, M, answer, cnt0;
    static ArrayDeque<Integer> originalQ = new ArrayDeque<>();
    static int[] d;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        boolean[] originalVisited = new boolean[N*M];
        d = new int[] {-M, M, -1, 1};
        answer = 0;
        cnt0 = -3;

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                int idx = i*M+j;
                int n = Integer.parseInt(st.nextToken());
                if(n==0) cnt0++;
                else {
                    originalVisited[idx]=true;
                    if(n==2) originalQ.offer(idx);
                }
                
            }
        }
        comb(originalVisited, 0, 0);
        System.out.println(answer);
    }
    static void comb(boolean[] visited, int cnt, int start){
        if(cnt==3){
            bfs(visited.clone());
            return;
        }
        for (int i = start; i < N * M; i++) {
            if (!visited[i]) { 
                visited[i] = true;
                comb(visited, cnt + 1, i + 1);
                visited[i] = false;
            }
        }
    }
    static void bfs(boolean[] visited){
        int cnt = cnt0;
        ArrayDeque<Integer> copyQ = new ArrayDeque<>(originalQ);
        while(!copyQ.isEmpty()){
            int cur = copyQ.poll();
            for(int i=0; i<4; i++){
                int next = cur + d[i];
                if(next<0 || N*M<=next) continue;
                if(i==2 || i==3){
                    if(cur/M != next/M) continue;
                }
                if(visited[next]) continue;
                visited[next] = true;
                cnt--;
                copyQ.offer(next);
            }
        }
        answer = Math.max(answer, cnt);
    }
}
