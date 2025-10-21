import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static int answer;
    static ArrayList<int[]>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        answer = 0;

        graph = new ArrayList[N+1];
        for(int i=1; i<=N; i++) graph[i] = new ArrayList<>();
        arr = new int[N+1];

        for(int i=1; i<=N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            while(true){
                int v = Integer.parseInt(st.nextToken());
                if(v==-1) break;
                int d = Integer.parseInt(st.nextToken());
                graph[u].add(new int[] {v, d});
            }
            
        }

        dfs(1, 0);

        System.out.println(answer);

    }
    static int dfs(int n, int parent){
        PriorityQueue<Integer> subList = new PriorityQueue<>((a,b) -> b-a);
        int subSum = 0;
        for(int[] next: graph[n]){
            int nextNode = next[0];
            if(nextNode==parent) continue;
            int dist = next[1];
            int longest = dfs(nextNode, n);
            arr[n] = Math.max(arr[n], longest + dist);
            subList.offer(longest+dist);

        }
        if(!subList.isEmpty()) subSum += subList.poll();
        if(!subList.isEmpty()) subSum += subList.poll();
        answer = Math.max(answer, subSum);

        return arr[n];
    }
}
