import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int N, M, answer;
    static char[][] map;
    static boolean[][][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        answer = -1;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int startR = 0;
        int startC = 0;

        map = new char[N][M];
        visited = new boolean[N][M][64];

        for(int i=0; i<N; i++){
            String str = br.readLine();
            for(int j=0; j<M; j++){
                char c = str.charAt(j);
                if(c=='0'){
                    startR = i;
                    startC = j;
                }
                map[i][j] = c;
            }
        }

        bfs(startR, startC);

        System.out.println(answer==Integer.MAX_VALUE ? -1 : answer);
    }
    static void bfs(int r, int c){
        // r, c, 거리, 소유 열쇠 비트
        ArrayDeque<int[]> q = new ArrayDeque<>();
        visited[r][c][0] = true;
        q.add(new int[] {r, c, 0, 0});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cr = cur[0];
            int cc = cur[1];
            int dist = cur[2];
            int bit = cur[3];

            for(int i=0; i<4; i++){
                int nr = cr + dr[i];
                int nc = cc + dc[i];
                int nDist = dist+1;
                int nBit = bit;

                if(nr<0 || nr>=N || nc<0 || nc>=M) continue;

                char nextChar = map[nr][nc];
                if(nextChar == '#') continue;
                else if(nextChar == '1'){
                    answer = nDist;
                    return;
                }
                else if(nextChar - 'a' >= 0){
                    nBit = nBit | 1 << (nextChar - 'a');
                }
                else if(nextChar - 'A' >= 0){
                    if((nBit & 1 << (nextChar - 'A')) ==0) continue;
                }
                if(visited[nr][nc][nBit]) continue;
                visited[nr][nc][nBit] = true;
                q.add(new int[] {nr, nc, nDist, nBit});
            }
        }
    }
}