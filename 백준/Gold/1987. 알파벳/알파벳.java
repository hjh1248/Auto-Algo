import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.HashSet;

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
        HashSet<Character> set = new HashSet<>();

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][];
        boolean[][] visited = new boolean[R][C];

        for(int i=0; i<R; i++){
            map[i] = br.readLine().toCharArray();
        }

        visited[0][0] = true;
        set.add(map[0][0]);
        dfs(0, 0, set, visited, 1);

        System.out.println(max);
    }
    
    static void dfs(int r, int c, HashSet<Character> set, boolean[][] visited, int dist){
        
        max = Math.max(max, dist);

        for(int i=0; i<4; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(0<=nr && nr<R && 0<=nc && nc<C && !visited[nr][nc] && !set.contains(map[nr][nc])){
                visited[nr][nc] = true;
                char ch = map[nr][nc];
                set.add(ch);
                dfs(nr, nc, set, visited, dist+1);
                visited[nr][nc] = false;
                set.remove(ch);
            }
        }

    }
}
