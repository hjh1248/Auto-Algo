import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    
    // 좌표를 저장할 클래스
    static class Point {
        double x, y;
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    // PQ에 넣을 노드 정보 (정점 번호, 현재까지의 비용)
    static class Node implements Comparable<Node> {
        int idx;
        double w;

        public Node(int idx, double w) {
            this.idx = idx;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return Double.compare(this.w, o.w);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Point[] points = new Point[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            points[i] = new Point(x, y);
        }

        // 이미 연결된 통로(M개)를 O(1)에 확인하기 위해 인접 행렬 사용
        // N이 최대 1,000이라서 1000*1000 boolean 배열은 메모리 문제 없음
        boolean[][] connected = new boolean[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            connected[u][v] = connected[v][u] = true;
        }

        // --- 프림 알고리즘 시작 ---
        
        // minEdge[i]: MST 집합에서 i번 노드로 갈 수 있는 최소 비용
        double[] minEdge = new double[N + 1];
        Arrays.fill(minEdge, Double.MAX_VALUE);
        
        // MST 포함 여부 체크
        boolean[] visited = new boolean[N + 1];
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        // 1번 노드부터 시작
        minEdge[1] = 0;
        pq.offer(new Node(1, 0));

        double result = 0;
        int cnt = 0; // 연결된 정점의 개수

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int u = cur.idx;

            // 이미 MST에 포함된 노드라면 스킵
            if (visited[u]) continue;
            
            visited[u] = true;
            result += cur.w;
            
            // 모든 정점(N개)이 연결되면 종료 (최적화)
            if (++cnt == N) break;

            // 현재 정점(u)에서 다른 모든 정점(v)을 보며 거리 갱신
            // (완전 그래프이므로 모든 v를 확인)
            for (int v = 1; v <= N; v++) {
                if (!visited[v]) {
                    // 이미 연결된 통로라면 비용 0, 아니면 유클리드 거리 계산
                    double cost = (connected[u][v]) ? 0.0 : getDist(points[u], points[v]);

                    // 더 저렴한 비용으로 도달 가능하다면 갱신
                    if (cost < minEdge[v]) {
                        minEdge[v] = cost;
                        pq.offer(new Node(v, cost));
                    }
                }
            }
        }

        System.out.printf("%.2f", result);
    }

    // 두 점 사이의 거리 계산
    static double getDist(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }
}