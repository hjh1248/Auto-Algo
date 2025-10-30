import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int answer = 0;

        int[][] map = new int[R][C];
        int filterR = 0;

        for(int i=0; i<R; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<C; j++){
                int n = Integer.parseInt(st.nextToken());
                map[i][j] = n;
                if(n==-1) filterR = i;
            }
        }

        for(int time=0; time<T; time++){

            //확산 값 정리
            int[][] spread = new int[R][C];
            for(int i=0; i<R; i++){
                for(int j=0; j<C; j++){
                    int n = map[i][j];
                    if(n<5) continue;
                    int x = n/5;
                    int cnt = 0;
                    for(int dir=0; dir<4; dir++){
                        int nr = i + dr[dir];
                        int nc = j + dc[dir];
                        if(nr<0 || nr>=R || nc<0 || nc>=C) continue;
                        if(map[nr][nc] == -1) continue;
                        spread[nr][nc] += x;
                        cnt++;
                    }
                    spread[i][j] -= x*cnt;
                }
            }

            // 확산
            for(int i=0; i<R; i++){
                for(int j=0; j<C; j++){
                    map[i][j] += spread[i][j];
                }
            }

            //이동
            int upPrivious = 0;
            int downPrivious = 0;
            for(int i=1; i<C; i++){
                int tmp = map[filterR-1][i];
                map[filterR-1][i] = upPrivious;
                upPrivious = tmp;

                tmp = map[filterR][i];
                map[filterR][i] = downPrivious;
                downPrivious = tmp;
            }
            for(int i=filterR-2; i>=0; i--){
                int tmp = map[i][C-1];
                map[i][C-1] = upPrivious;
                upPrivious = tmp;
            }
            for(int i=filterR+1; i<R; i++){
                int tmp = map[i][C-1];
                map[i][C-1] = downPrivious;
                downPrivious = tmp;
            }
            for(int i=C-2; i>=0; i--){
                int tmp = map[0][i];
                map[0][i] = upPrivious;
                upPrivious = tmp;

                tmp = map[R-1][i];
                map[R-1][i] = downPrivious;
                downPrivious = tmp;
            }
            for(int i=1; i<filterR-1; i++){
                int tmp = map[i][0];
                map[i][0] = upPrivious;
                upPrivious = tmp;
            }
            for(int i=R-2; i>filterR; i--){
                int tmp = map[i][0];
                map[i][0] = downPrivious;
                downPrivious = tmp;
            }
        }
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                answer += map[i][j];
            }
        }
        System.out.println(answer+2);
    }
}
