import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int N;
    static int M;
    static boolean[] visited;
    static ArrayList<Integer>[] upList;
    static ArrayList<Integer>[] downList;
    static ArrayDeque<Integer> q = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int answer = 0;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        upList = new ArrayList[N+1];
        downList = new ArrayList[N+1];
        visited = new boolean[N+1];
        
        for(int i=1; i<=N; i++){
            upList[i] = new ArrayList<>();
            downList[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            upList[a].add(b);
            downList[b].add(a);
        }
        
        for(int i=1; i<=N; i++){
            Arrays.fill(visited, false);
            int upCount = bfs(i, true);
            
            Arrays.fill(visited, false);
            int downCount = bfs(i, false);
            
            if(upCount + downCount == N - 1) answer += 1;
        }

        System.out.println(answer);

    }
    static int bfs(int node, boolean isUp){
        int cnt = 0;
        ArrayList<Integer>[] list ;
        q.clear();

        if(isUp) list = upList;
        else list = downList;

        q.offer(node);
        visited[node] = true;

        while(!q.isEmpty()){
            int cur = q.pop();
            for(int next: list[cur]){
                if(!visited[next]){
                    q.offer(next);
                    visited[next] = true;
                    cnt++;
                }
            }
        }
        return cnt;
    }
}