import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {
    static int[][] visited;
    static int N, M;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static ArrayDeque<int[]> q = new ArrayDeque<>();
    static ArrayList<int[][]> islandList;
    static ArrayList<Edge> edgeList = new ArrayList<>();;
    static int islandNumber = 1;
    static int[][] isConnected;
    static int[] parent;
    static int[] rank;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new int[N][M];
        int cnt = 0;
        int answer = 0;

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j]==1 && visited[i][j]==0){
                    findIsland(i, j);
                }
            }
        }
        islandNumber--;
        makeEdge();
        parent = new int[islandNumber];
        rank = new int[islandNumber];
        for(int i=0; i<islandNumber; i++){
            parent[i] = i;
            rank[i] = 0;
        }
        Collections.sort(edgeList);
        for(Edge e: edgeList){
            if(cnt==islandNumber-1){
                break;
            }
            if(union(e.u, e.v)){
                answer += e.w;
                cnt++;
            }
        }
        System.out.println(cnt == islandNumber - 1 ? answer : -1);
    }
    static class Edge implements Comparable<Edge>{
        int u, v, w;
        Edge(int u, int v, int w){
            this.u = u;
            this.v = v;
            this.w = w;
        }
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.w, o.w);
        }
        
    }
    static void findIsland(int r, int c){
        q.clear();
        q.offer(new int[] {r, c});
        visited[r][c] = islandNumber;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            for(int i=0; i<4; i++){
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if(0<=nr && nr<N && 0<=nc && nc<M && map[nr][nc]==1 && visited[nr][nc]==0){
                    visited[nr][nc] = islandNumber;
                    q.offer(new int[] {nr, nc});
                }
            }
        }
        islandNumber++;
    }

    static void makeEdge(){
        isConnected = new int[islandNumber][islandNumber];
        for (int i = 0; i < islandNumber; i++) {
            for (int j = 0; j < islandNumber; j++) {
                isConnected[i][j] = Integer.MAX_VALUE;
            }
        }

        for(int i=0; i<N; i++){
            int islandA = -1;
            int cnt = 0;
            for(int j=0; j<M; j++){
                if(visited[i][j] != 0){
                    int islandB = visited[i][j]-1;
                    if(islandA != -1 && islandA!=islandB && cnt>=2){
                        isConnected[islandA][islandB]=Math.min(isConnected[islandA][islandB], cnt);
                        isConnected[islandB][islandA]=Math.min(isConnected[islandB][islandA], cnt);
                    }
                    islandA = islandB;
                    cnt = 0;
                }
                else cnt++;
            }
        }
        for (int j = 0; j < M; j++) {
            int islandA = -1, cnt = 0;
            for (int i = 0; i < N; i++) {
                if (visited[i][j] != 0) {
                    int islandB = visited[i][j] - 1;
                    if (islandA != -1 && islandA != islandB && cnt >= 2) {
                        isConnected[islandA][islandB] = Math.min(isConnected[islandA][islandB], cnt);
                        isConnected[islandB][islandA] = Math.min(isConnected[islandB][islandA], cnt);
                    }
                    islandA = islandB;
                    cnt = 0;
                } else cnt++;
            }
        }

        for(int i=0; i<islandNumber; i++){
            for(int j=i+1; j<islandNumber; j++){
                if(isConnected[i][j]!=Integer.MAX_VALUE){
                    edgeList.add(new Edge(i, j, isConnected[i][j]));
                }
            }
        }
    }

    static int find(int x){
        if(x==parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    static boolean union(int a, int b){
        a = find(a);
        b = find(b);
        if(a == b) return false;
        if(rank[a] > rank[b]){
            parent[b] = a;
        }
        else if(rank[a] < rank[b]){
            parent[a] = b;
        }
        else{
            parent[b] = a;
            rank[a]++;
        }
        return true;
    }
}
