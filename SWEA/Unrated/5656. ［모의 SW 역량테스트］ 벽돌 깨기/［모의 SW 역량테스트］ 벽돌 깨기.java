import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int N, W, H;
    static ArrayDeque<int[]> q = new ArrayDeque<>();
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int answer;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            answer = Integer.MAX_VALUE;

            int[][] map = new int[H][W];
            for(int i=0; i<H; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<W; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            perm(map, 0);
            sb.append("#").append(tc).append(" ").append(answer==Integer.MAX_VALUE?0:answer).append("\n");
        }
        System.out.println(sb);
    }
    static void perm(int[][] map, int idx) {
    	if(idx==N) {
    		int n = countBrick(map);
    		answer = Math.min(answer, n);
    		return;
    	}
    	for(int c=0; c<W; c++) {
    		int[][] nextMap = new int[H][W];
            for (int i = 0; i < H; i++) {
                nextMap[i] = Arrays.copyOf(map[i], W);
            }
            int r = findBrick(c, nextMap);
            if(r==-1) continue;
    		boolean[][] visited = bfs(r, c, nextMap);
    		perm(initMap(nextMap, visited), idx+1);
    	}
    }
    
    static int findBrick(int j, int[][] map){
        for(int k=0; k<H; k++){
            if(map[k][j]!= 0) return k;
        }
        return -1;
    }

    static boolean[][] bfs(int r, int c, int[][] map){
    	q.clear();
    	boolean[][] visited = new boolean[H][W];
        q.offer(new int[] {r, c});
        visited[r][c] = true;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cr = cur[0];
            int cc = cur[1];
            int cn = map[cr][cc];
            if(cn>1) {
            	for(int i=1; i<cn; i++){
                    for(int j=0; j<4; j++){
                        int nr = cr + dr[j]*i;
                        int nc = cc + dc[j]*i;
                        if(0<=nr && nr<H && 0<=nc && nc<W && !visited[nr][nc] && map[nr][nc]!=0){
                            visited[nr][nc] = true;
                            q.offer(new int[] {nr, nc});
                        }
                    }
                }
            }
        }
        return visited;
    }
    static int[][] initMap(int[][] map, boolean[][] visited){
        for(int i=0; i<W; i++){
            int idx = H-1;
            for(int j=H-1; 0<=j; j--){
                if(!visited[j][i]){
                    map[idx][i] = map[j][i];
                    idx--;
                }
            }
            while(idx>=0){
                map[idx][i] = 0;
                idx--;
            }
        }
        return map;
    }
    static int countBrick(int[][] map) {
    	int n = 0;
    	for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				if(map[i][j]!=0) n++;
			}
		}
    	return n;
    }
}
