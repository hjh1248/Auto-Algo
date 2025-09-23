import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, min = 11;
    static int[] red = new int[2];
    static int[] blue = new int[2];
    static int[] hole = new int[2];
    static char[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for(int i=0; i<N; i++){
            map[i] = br.readLine().toCharArray();
            for(int j=0; j<M; j++){
                int n = map[i][j];
                switch (n) {
                    case 'R' -> {
                        red[0] = i;
                        red[1] = j;
                    }
                    case 'B' -> {
                        blue[0] = i;
                        blue[1] = j;
                    }
                    case 'O' -> {
                        hole[0] = i;
                        hole[1] = j;
                    }
                    default -> {
                    }
                }
            }
        }
        dfs(0);
        System.out.println(min == 11 ? -1 : min);
    }

    static void dfs(int cnt) {
        cnt++;
        if(cnt>=min) return;
        A: for(int i=0; i<4; i++){
            int[] oriRed = red.clone();
            int[] oriBlue = blue.clone();
            while(true){
                int nr = blue[0] + dr[i];
                int nc = blue[1] + dc[i];
                if(map[nr][nc] == '#') break;
                if(map[nr][nc] == 'O'){
                    blue = oriBlue;
                    continue A;
                }
                blue[0] = nr;
                blue[1] = nc;
            }
            while(true){
                int nr = red[0] + dr[i];
                int nc = red[1] + dc[i];
                if(map[nr][nc] == '#') break;
                if(map[nr][nc] == 'O'){
                    min = Math.min(min, cnt);
                    return;
                } 
                red[0] = nr;
                red[1] = nc;
            }
            if(red[0] == blue[0] && red[1] == blue[1]){
                switch (i) {
                    case 0 -> {
                        if(oriRed[0] < oriBlue[0]) blue[0]++;
                        else red[0]++;
                    }
                    case 1 -> {
                        if(oriRed[0] < oriBlue[0]) red[0]--;
                        else blue[0]--;
                    }
                    case 2 -> {
                        if(oriRed[1] < oriBlue[1]) blue[1]++;
                        else red[1]++;
                    }
                    case 3 -> {
                        if(oriRed[1] < oriBlue[1]) red[1]--;
                        else blue[1]--;
                    }
                    default -> {
                    }
                }
            }
            dfs(cnt);
            red = oriRed;
            blue = oriBlue;
        }
    }
}