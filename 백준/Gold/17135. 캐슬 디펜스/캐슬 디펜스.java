import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

// 메모리: 22060kb, 시간: 176ms

public class Main {
    static int N, M, D;
    // 위로만 이동하도록 설정, 좌측으로 먼저 이동하도록 설정
    static int[] dr = {0, -1, 0};
    static int[] dc = {-1, 0, 1};
    static int[][] map;
    static boolean[][] visited;
    static int answer;
    static int tmp;
    static ArrayDeque<Node> q = new ArrayDeque<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        comb(M);
        System.out.println(answer);

    }
    static void comb(int M){
        for(int i=0; i<M-2; i++){
            for(int j=i+1; j<M-1; j++){
                for(int k=j+1; k<M; k++){
                    // 각 조합에 대한 제거 수
                    tmp = 0;
                    // map 복사
                    int[][] newMap = new int[N][M];
                    for(int r=0; r<N; r++){
                        newMap[r] = Arrays.copyOf(map[r], M);
                    }
                    defense(i, j, k, newMap);
                    answer = Math.max(answer, tmp);
                }
            }
        }
    }
    static void defense(int i, int j, int k, int[][] newMap){
        int[][] rc = new int[3][];
        // 궁수 위치를 N-1부터 0까지 이동하며 bfs
        for(int r=N-1; r>=0; r--){
            rc[0] = bfs(r, i, newMap);
            rc[1] = bfs(r, j, newMap);
            rc[2] = bfs(r, k, newMap);
            // 좌표를 저장해뒀다가 한꺼번에 병사 제거
            for(int[] ij: rc){
                if(ij==null) continue;
                if(newMap[ij[0]][ij[1]]==1){
                    newMap[ij[0]][ij[1]] = 0;
                    // 해당 조합의 제거 수 증가
                    tmp++;
                }
            }
        }
    }
    static int[] bfs(int r, int c, int[][] newMap){
        for(int i=0; i<N; i++){
            Arrays.fill(visited[i], false);
        }
        q.clear();
        visited[r][c] = true;
        q.offer(new Node(r, c, 1));

        while(!q.isEmpty()){
            Node cur = q.poll();
            // 사거리 안에 병사가 있을 시 좌표 반환
            if(newMap[cur.r][cur.c]==1){
                return new int[] {cur.r, cur.c};
            }
            // 사거리 도달하면 종료
            if(cur.dist==D) continue;
            for(int i=0; i<3; i++){
                // 다음 좌표, 거리, 방향
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                int dist = cur.dist+1;

                if(nr<0 || N<=nr || nc<0 || M<=nc) continue;
                if(visited[nr][nc]) continue;

                visited[nr][nc] = true;
                q.offer(new Node(nr, nc, dist));
            }
        }
        return null;
    }
    static class Node{
        int r, c, dist;
        Node(int r, int c, int dist){
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
    }
}
