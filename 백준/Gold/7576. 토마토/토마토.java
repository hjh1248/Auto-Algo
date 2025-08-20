import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        ArrayDeque<int[]> q = new ArrayDeque<>();
        int answer = 0;
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int[][] tomatoes = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                tomatoes[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(tomatoes[i][j]==1){
                    q.offer(new int[] {i, j, 0});
                }
            }
        }

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cr = cur[0];
            int cc = cur[1];
            int cd = cur[2];
            answer = Math.max(answer, cd);

            for(int i=0; i<4; i++){
                int nr = cr + dr[i];
                int nc = cc + dc[i];

                if(0<=nr && nr<N && 0<=nc && nc<M && tomatoes[nr][nc]==0){
                    tomatoes[nr][nc] = 1;
                    q.offer(new int[] {nr, nc, cd+1});
                }
            }
        }

        A: for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(tomatoes[i][j]==0){
                    answer = -1;
                    break A;
                }
            }
        }
        System.out.println(answer);
    }
}