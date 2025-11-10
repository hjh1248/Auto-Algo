import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] bridges = new int[N][2];
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            bridges[i][0] = Integer.parseInt(st.nextToken());
            bridges[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(bridges, (a, b) -> a[0] - b[0]);
        ArrayList<int[]> mergedBridges = new ArrayList<>();

        int L = 0, R = 0;
        for(int i=0; i<N; i++){
            if(bridges[i][0] > R){
                mergedBridges.add(new int[] {L, R});
                L = bridges[i][0];
                R = bridges[i][1];
            }
            else R = Math.max(R, bridges[i][1]);
        }
        mergedBridges.add(new int[] {L, R});

        boolean[] visited = new boolean[mergedBridges.size()];
        // arr[0] = 점프 가능 거리, arr[1] = idx
        ArrayDeque<int[]> q = new ArrayDeque<>();
        int[] bridge = mergedBridges.get(0);
        int answer = bridge[1];
        q.add(new int[] {bridge[1] + bridge[1] - bridge[0], 0});
        visited[0] = true;
        while(!q.isEmpty()){
            int[] jump = q.poll();
            for(int i= jump[1]+1; i<mergedBridges.size(); i++){
                if(visited[i]) continue;
                bridge = mergedBridges.get(i);
                if(bridge[0] > jump[0]) break;
                q.add(new int[] {bridge[1] + bridge[1] - bridge[0], i});
                visited[i] = true;
                answer = Math.max(answer, bridge[1]);
            }
        }
        System.out.println(answer);
    }
}
