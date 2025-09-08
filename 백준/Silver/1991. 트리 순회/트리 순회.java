import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	static int[][] tree;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		tree = new int [N][2];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int j = st.nextToken().charAt(0) - 'A';
			tree[j][0] = st.nextToken().charAt(0) - 'A';
			tree[j][1] = st.nextToken().charAt(0) - 'A';
		}
		
		// 전위
		dfs1(0);
		sb.append("\n");
		
		// 중위
		dfs2(0);
		sb.append("\n");
		
		// 후위
		dfs3(0);
		sb.append("\n");
		
		System.out.println(sb);
	}
	static void dfs1(int n) {
		sb.append((char)(n+'A'));
		for(int i: tree[n]) {
			if(i==-19) continue;
			dfs1(i);
		}
	}
	
	static void dfs2(int n) {
		int i = tree[n][0];
		if(i !=-19) {
			dfs2(i);
		}
		sb.append((char)(n+'A'));
		i = tree[n][1];
		if(i !=-19) {
			dfs2(i);
		}
	}
	
	static void dfs3(int n) {
		for(int i: tree[n]) {
			if(i==-19) continue;
			dfs3(i);
		}
		sb.append((char)(n+'A'));
	}
}
