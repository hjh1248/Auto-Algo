import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int answer = 0;

        ArrayList<int[]> map = new ArrayList<>();

        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map.add(new int[] {a, b});
        }

        map.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        
        int start = -1000000000;
        int end = -1000000000;
        for(int[] node: map){
            if(end<node[0]){
                answer += end-start;
                start = node[0];
                end = node[1];
            }
            else end=Math.max(end, node[1]);
        }
        answer += end-start;

        System.out.println(answer);

    }
}
