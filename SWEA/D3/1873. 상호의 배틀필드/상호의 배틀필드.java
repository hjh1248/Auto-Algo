import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 메모리: 27,392 kb 실행시간: 93 ms

public class Solution {
    static char[][] map;
    static int[] tank = new int[3];
    static int N;
    static int M;
    static char[] dir = {'^', 'v', '<', '>'};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            
            map = new char[N][M];

            for(int i=0; i<N; i++){
                String str = br.readLine();
                for(int j=0; j<M; j++){
                    char c = str.charAt(j);
                    if(c=='^' || c=='v' || c=='>' || c=='<'){
                        tank[0] = i;
                        tank[0] = j;
                        if(c=='^') tank = new int[] {i, j, 0};
                        else if(c=='v') tank = new int[] {i, j, 1};
                        else if(c=='<') tank = new int[] {i, j, 2};
                        else if(c=='>') tank = new int[] {i, j, 3};
                        c = '.';
                    }
                    map[i][j] = c;
                }
            }

            int cnt = Integer.parseInt(br.readLine());
            String cmds = br.readLine();
            for(int i=0; i<cnt; i++){
                char c = cmds.charAt(i);
                if(c=='S') shoot();
                else move(c);
            }

            map[tank[0]][tank[1]] = dir[tank[2]];

            sb.append("#").append(tc).append(" ");
            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }

        }
        System.out.println(sb);
    }

    static void move(char c){
        int nr = tank[0];
        int nc = tank[1];
        if(c=='U'){
            tank[2] = 0;
            nr--;
        }
        else if(c=='D'){
            tank[2] = 1;
            nr++;
        }
        else if(c=='L') {
            tank[2] = 2;
            nc--;
        }
        else if(c=='R'){
            tank[2] = 3;
            nc++;
        }

        if(0<=nr && nr<N && 0<=nc && nc<M && map[nr][nc]=='.'){
            tank[0] = nr;
            tank[1] = nc;
        }
    }

    static void shoot(){
        int nr = tank[0];
        int nc = tank[1];
        while(0<=nr && nr<N && 0<=nc && nc<M && map[nr][nc]!='#'){
            if(map[nr][nc]=='*'){
                map[nr][nc] = '.';
                break;
            }
            if(tank[2]==0) nr--;
            else if(tank[2]==1) nr++;
            else if(tank[2]==2) nc--;
            else if(tank[2]==3) nc++;
        }
    }
}