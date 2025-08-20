import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[] tank = new int[3];
            char[][] map = new char[N][M];

            for(int i=0; i<N; i++){
                String str = br.readLine();
                for(int j=0; j<M; j++){
                    char c = str.charAt(j);
                    if(c=='^') tank = new int[] {i, j, 0};
                    else if(c=='>') tank = new int[] {i, j, 1};
                    else if(c=='v') tank = new int[] {i, j, 2};
                    else if(c=='<') tank = new int[] {i, j, 3};
                    map[i][j] = c;
                }
            }

            int cnt = Integer.parseInt(br.readLine());
            String str = br.readLine();
            for(int i=0; i<cnt; i++){
                char c = str.charAt(i);
                int cr = tank[0];
                int cc = tank[1];
                int nr = tank[0];
                int nc = tank[1];
                char d = ' ';
                if(c=='U'){
                    tank[2] = 0;
                    nr--;
                    d = '^';
                }
                else if(c=='D'){
                    tank[2] = 2;
                    nr++;
                    d = 'v';
                }
                else if(c=='L') {
                    tank[2] = 3;
                    nc--;
                    d = '<';
                }
                else if(c=='R'){
                    tank[2] = 1;
                    nc++;
                    d = '>';
                }

                if(c=='S'){
                    while(0<=nr && nr<N && 0<=nc && nc<M && map[nr][nc]!='#'){
                        if(map[nr][nc]=='*'){
                            map[nr][nc] = '.';
                            break;
                        }
                        if(tank[2]==0) nr--;
                        else if(tank[2]==2) nr++;
                        else if(tank[2]==1) nc++;
                        else if(tank[2]==3) nc--;
                    }
                }
                else if(0<=nr && nr<N && 0<=nc && nc<M && map[nr][nc]=='.'){
                    map[cr][cc] = '.';
                    map[nr][nc] = d;
                    tank[0] = nr;
                    tank[1] = nc;
                }
                else map[cr][cc] = d;
                
            }
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
}