import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
    static int answer = 0;
    static int[] map;
    // 상 하 좌 우
    static int[] d = {-5, 5, -1, 1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[25];
        for(int i=0; i<5; i++){
            char[] chars = br.readLine().toCharArray();
            for(int j=0; j<5; j++){
                map[i*5+j] = chars[j];
            }
        }
        comb(0, 0, new boolean[25], 0);
        System.out.println(answer);
    }
    static void comb(int idx, int start, boolean[] visited, int yCnt){
        if(yCnt>3) return;
        if(idx==7){
            bfs(visited);
            return;
        }
        for(int i=start; i<25; i++){
            visited[i] = true;
            comb(idx+1, i+1, visited, map[i] == 'Y' ? yCnt + 1 : yCnt);
            visited[i] = false;
        }
    }
    static void bfs(boolean[] visited){
        // bfs 시작 노드
        int start=-1;
        boolean[] bfsVisited = new boolean[25];
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for(int i=0; i<25; i++){
            if(visited[i]){
                start = i;
                break;
            } 
        }
        // System.out.println(start);
        bfsVisited[start] = true;
        q.offer(start);
        int cnt = 0;
        while(!q.isEmpty()){
            int cur = q.poll();
            cnt++;
            if(cnt==7){
                answer++;
                return;
            }
            for(int i=0; i<4; i++){
                int next = cur + d[i];
                if(next<0 || 25<=next) continue;
                if(i==2 || i==3){
                    if(cur/5 != next/5) continue;
                }
                if(!visited[next]) continue;
                if(bfsVisited[next]) continue;
                bfsVisited[next] = true;
                q.offer(next);
            }
        }
    }
}