import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] parent, rank;
	static int cnt;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		parent = new int[n];
		rank = new int[n];
		for(int i=0; i<n; i++) {
			parent[i] = i;
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(!union(a, b)) {
				System.out.println(cnt);
				return;
			}
		}
		System.out.println(0);
	}
	static int find(int x) {
		if(x==parent[x]) return x;
		return parent[x] = find(parent[x]);
	}
	static boolean union(int a, int b) {
		cnt++;
		int pa = find(a);
		int pb = find(b);
		if(pa==pb) return false;
		
		if(rank[pa]>rank[pb]) parent[pb] = pa;
		else if(rank[pa]<rank[pb]) parent[pa] = pb;
		else {
			parent[pb] = pa;
			rank[pa]++;
		}
		return true;
	}
}
