import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int[] cnts = new int[2];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		solve(0, 0, N);
		for(int cnt:cnts) System.out.println(cnt);
	}
	static void solve(int rStart, int cStart, int size) {
		int status = map[rStart][cStart];
		int half = size/2;
		for(int i=rStart; i<rStart + size; i++) {
			for(int j=cStart; j<cStart + size; j++) {
				if(map[i][j]!=status) {
					solve(rStart, cStart, half);
					solve(rStart+half, cStart, half);
					solve(rStart, cStart+half, half);
					solve(rStart+half, cStart+half, half);
					return;
				}
			}
		}
		cnts[status]++;
	}
}
