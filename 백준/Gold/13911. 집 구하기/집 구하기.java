import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static int[] mcDists, starDists;
	static ArrayList<int[]>[] roads;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		roads = new ArrayList[V+1];
		mcDists = new int[V+1];
		starDists = new int[V+1];
		for(int i=1; i<=V; i++) roads[i] = new ArrayList<>();
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			roads[a].add(new int[] {b, d});
			roads[b].add(new int[] {a, d});
		}
		
		Arrays.fill(mcDists, Integer.MAX_VALUE);
		Arrays.fill(starDists, Integer.MAX_VALUE);
		
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken()); 
		int x = Integer.parseInt(st.nextToken());
		
		int[] mc = new int[M+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=M; i++) mc[i] = Integer.parseInt(st.nextToken());
		dijkstra(M, mc, true, x);
		
		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken()); 
		int y = Integer.parseInt(st.nextToken());
		
		int[] star = new int[S+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=S; i++) star[i] = Integer.parseInt(st.nextToken());
		dijkstra(S, star, false, y);
		int min = Integer.MAX_VALUE;
		
		for(int i=1; i<=V; i++) {
			int cDist = mcDists[i];
			int bDist = starDists[i];
			if(cDist == 0 || bDist == 0 || cDist == Integer.MAX_VALUE || bDist == Integer.MAX_VALUE) continue;
			min = Math.min(min, cDist + bDist);
		}
		 System.out.println(min==Integer.MAX_VALUE ? -1:min);
	}

	static void dijkstra(int N, int[] starts, boolean isMc, int R) {
		pq.clear();
		int[] dists;
		if(isMc) dists = mcDists;
		else dists = starDists;
		
		for(int i=1; i<=N; i++) {
			int s = starts[i];
			dists[s] = 0;
			pq.offer(new Edge(s, 0));
		}
		
		while(!pq.isEmpty()){
			Edge cur = pq.poll();
			int curN = cur.to;
			int curDist = cur.dist;
			if(dists[curN]<curDist) continue;
			for(int[] road: roads[curN]) {
				int nextN = road[0];
				int nextDist = cur.dist + road[1];
				if(dists[nextN]<nextDist) continue;
				if(nextDist > R) continue;
				dists[nextN] = nextDist;
				pq.offer(new Edge(nextN, nextDist));
			}
		}
	}
	static class Edge implements Comparable<Edge>{
		int to, dist;
		Edge(int to, int dist){
			this.to = to;
			this.dist = dist;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.dist, o.dist);
		}
	}
}
