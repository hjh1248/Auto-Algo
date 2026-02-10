import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] dice;
    static int[][] map;
    static int N, M, x, y;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 위, 동, 서, 북, 남, 아래
        dice = new int[6];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<K; i++){
            int cmd = Integer.parseInt(st.nextToken());
            if(move(cmd)){
                System.out.println(dice[0]);
            }
        }
    }
    static boolean move(int cmd){
        int del = 0;
        if(cmd==1){
            if(y+1 == M) return false;
            y++;
            del = dice[1];
            dice[1] = dice[0];
            dice[0] = dice[2];
            dice[2] = dice[5];
        }
        else if(cmd==2){
            if(y-1 == -1) return false;
            y--;
            del = dice[2];
            dice[2] = dice[0];
            dice[0] = dice[1];
            dice[1] = dice[5];
        }
        else if(cmd==3){
            if(x-1 == -1) return false;
            x--;
            del = dice[3];
            dice[3] = dice[0];
            dice[0] = dice[4];
            dice[4] = dice[5];
        }
        else if(cmd==4){
            if(x+1 == N) return false;
            x++;
            del = dice[4];
            dice[4] = dice[0];
            dice[0] = dice[3];
            dice[3] = dice[5];
        }

        if(map[x][y]==0){
            map[x][y] = del;
            dice[5] = del;
        }
        else{
            dice[5] = map[x][y];
            map[x][y] = 0;
        }
        return true;
    }
}
