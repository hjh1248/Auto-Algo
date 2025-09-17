
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        boolean[][] map = new boolean[N][N];
        boolean[][] isSnake = new boolean[N][N];

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        for(int apple=0; apple<K; apple++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            map[r][c] = true;
        }

        int L = Integer.parseInt(br.readLine());
        int[][] cmds = new int[L][2];
        
        for(int i=0; i<L; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            cmds[i][0] = Integer.parseInt(st.nextToken());
            cmds[i][1] = st.nextToken().equals("L")?0:1;
        }

        int dir = 1;
        int time = 0, cnt= 0;
        ArrayDeque<int[]> snake = new ArrayDeque<>();
        snake.offer(new int[] {0, 0});
        isSnake[0][0] = true;

        while(true){
            time++;
            int cr = snake.peekLast()[0];
            int cc = snake.peekLast()[1];

            int nr = cr + dr[dir];
            int nc = cc + dc[dir];

            if(nr<0 || N<=nr || nc<0 || N<=nc){
                System.out.println(time);
                return;
            }

            if(isSnake[nr][nc]){
                System.out.println(time);
                return;
            }

            snake.offer(new int[] {nr, nc});
            isSnake[nr][nc] = true;

            if(map[nr][nc]) map[nr][nc] = false;
            else{
                int[] poll = snake.poll();
                isSnake[poll[0]][poll[1]] = false;
            }

            if(cnt<L && time==cmds[cnt][0]){
                dir = changeDir(dir, cmds[cnt][1]);
                cnt++;
            }
        }
    }
    static int changeDir(int dir, int cmd){
        if(cmd==0) return (dir+3)%4;
        else return (dir+1)%4;
    }
}
