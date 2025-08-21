import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Integer>[] list;
    static int answer = 0;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        list = new ArrayList[N];
        for(int i=0; i<N; i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }

        for(int i=0; i<N; i++){
        	boolean[] visited = new boolean[N];
        	visited[i] = true;
            dfs(i, visited, 0);
            if(answer==1) break;
        }
        System.out.println(answer);
    }
    static void dfs(int i, boolean[] visited, int dist){
    	if(dist==4) {
    		answer = 1;
    		return;
    	}
        for(int next:list[i]){
            if(!visited[next]){
                visited[next] = true;
                dfs(next, visited, dist + 1);
                visited[next] = false;
            }
        }
    }
}
