
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {-1, 0, 1};
    static boolean[][] map;
    static int R, C, answer;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        answer = 0;
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new boolean[R][C];
        for(int i=0; i<R; i++){
            String str = br.readLine();
            for(int j=0; j<C; j++){
                if(str.charAt(j)=='x') map[i][j] = true;
            }
        }
        for(int i=0; i<R; i++){
            if(dfs(i, 0)){
                answer++;
            }
        }
        System.out.println(answer);
    }
    static boolean dfs(int r, int c){
        map[r][c] = true;
        if(c==C-1) return true;
        for(int i=0; i<3; i++){
            int nr = r + dr[i];
            if(nr<0 || R<=nr) continue;
            if(map[nr][c+1]) continue;
            if(dfs(nr, c+1)) return true;
        }
        return false;
    }
}
