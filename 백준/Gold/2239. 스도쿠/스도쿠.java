
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][] map = new int[9][9];
    static boolean[][] visitedR = new boolean[9][9];
    static boolean[][] visitedC = new boolean[9][9];
    static boolean[][] visited3 = new boolean[9][9];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0; i<9; i++){
            char[] chars = br.readLine().toCharArray();
            for(int j=0; j<9; j++){
                int n = chars[j] - '0';
                if(n!=0){
                    map[i][j] = n;
                    visitedR[i][n-1] = true;
                    visitedC[j][n-1] = true;
                    visited3[3*(i/3)+j/3][n-1] = true;
                }
            }
        }
        sudoku(0, 0);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    static boolean sudoku(int i, int j){
        int nr = i + (j+1)/9;
        int nc = (j+1)%9;

        if(map[i][j]!=0) return nr==9? true:sudoku(nr, nc);

        for(int k=0; k<9; k++){
            if(visitedR[i][k] || visitedC[j][k] || visited3[3*(i/3)+j/3][k]) continue;
            visitedR[i][k] = true;
            visitedC[j][k] = true;
            visited3[3*(i/3)+j/3][k] = true;
            map[i][j] = k+1;
            if(nr==9) return true;
            if(sudoku(nr, nc)) return true;
            visitedR[i][k] = false;
            visitedC[j][k] = false;
            visited3[3*(i/3)+j/3][k] = false;
            map[i][j] = 0;

        }
        return false;
    }
}
