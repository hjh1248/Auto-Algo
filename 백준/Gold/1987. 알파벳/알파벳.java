import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.HashSet;

//메모리:303540KB, 시간: 2100ms

public class Main {
    static char[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int R;
    static int C;
    static int max = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][];

        for(int i=0; i<R; i++){
            map[i] = br.readLine().toCharArray();
        }
        boolean[] aVisited = new boolean['Z'+1];
        
        aVisited[map[0][0]] = true;
        dfs(0, 0, aVisited, 1);

        System.out.println(max);
    }
    
    static void dfs(int r, int c, boolean[] aVisited, int dist){
        
        max = Math.max(max, dist);

        for(int i=0; i<4; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(0<=nr && nr<R && 0<=nc && nc<C && !aVisited[map[nr][nc]]){
                aVisited[map[nr][nc]] = true;
                dfs(nr, nc, aVisited, dist+1);
                aVisited[map[nr][nc]] = false;
            }
        }

    }
}
