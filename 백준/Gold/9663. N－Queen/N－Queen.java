import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        answer = 0;
        
        dfs(0, new boolean[N], new boolean[N*2-1], new boolean[N*2-1]);

        System.out.println(answer);
    }
    static void dfs(int r, boolean[] cVisited, boolean[] visited1, boolean[] visited2){
        if(r==N){
            answer++;
            return;
        }
        for(int c=0; c<N; c++){
            if(cVisited[c]) continue;
            if(visited1[c-r+N-1]) continue;
            if(visited2[c+r]) continue;

            cVisited[c] = true;
            visited1[c-r+N-1] = true;
            visited2[c+r] = true;

            dfs(r+1, cVisited, visited1, visited2);

            cVisited[c] = false;
            visited1[c-r+N-1] = false;
            visited2[c+r] = false;
        }
    }
}
