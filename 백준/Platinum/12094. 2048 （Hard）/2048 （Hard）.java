
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, max = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];

        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // int[][] newMap = new int[N][];
        // for(int i=0; i<N; i++) newMap[i] = map[i].clone;
        dfs(0, map.clone());
        System.out.println(max);
    }

    static void dfs(int cnt, int[][] map) {
        if(cnt==10) {
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++) max = Math.max(max, map[i][j]);
            }
            return;
        }
        for(int i=0; i<4; i++){
            dfs(cnt+1, push(i, map));
        }
    }

    static int[][] push(int dir, int[][] map){
        int[][] newMap = new int[N][N];
        if(dir==0){
            for(int c=0; c<N; c++){
                int idx = 0;
                int tmp = 0;
                for(int r=0; r<N; r++){
                    int n = map[r][c];
                    if(n==0)continue;
                    if(n==tmp){
                        n *= 2;
                        idx--;
                        tmp = 0;
                    }
                    else tmp = n;
                    newMap[idx][c] = n;
                    idx++;
                }
            }
        }
        else if(dir==1){
            for(int c=0; c<N; c++){
                int idx = N-1;
                int tmp = 0;
                for(int r=N-1; r>=0; r--){
                    int n = map[r][c];
                    if(n==0)continue;
                    if(n==tmp){
                        n *= 2;
                        idx++;
                        tmp = 0;
                    }
                    else tmp = n;
                    newMap[idx][c] = n;
                    idx--;
                }
            }
        }
        else if(dir==2){
            for(int r=0; r<N; r++){
                int idx = 0;
                int tmp = 0;
                for(int c=0; c<N; c++){
                    int n = map[r][c];
                    if(n==0)continue;
                    if(n==tmp){
                        n *= 2;
                        idx--;
                        tmp = 0;
                    }
                    else tmp = n;
                    newMap[r][idx] = n;
                    idx++;
                }
            }
        }
        else if(dir==3){
            for(int r=0; r<N; r++){
                int idx = N-1;
                int tmp = 0;
                for(int c=N-1; c>=0; c--){
                    int n = map[r][c];
                    if(n==0)continue;
                    if(n==tmp){
                        n *= 2;
                        idx++;
                        tmp = 0;
                    }
                    else tmp=n;
                    newMap[r][idx] = n;
                    idx--;
                    
                }
            }
        }
        return newMap;
    }
}