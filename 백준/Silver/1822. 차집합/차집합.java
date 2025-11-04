
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        List<Integer> list = new ArrayList<>();
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for(int i=0; i<B; i++){
            int n = Integer.parseInt(st1.nextToken());
            list.add(n);
        }
        HashSet<Integer> set = new HashSet<>(list);

        list = new ArrayList<>();
        int cnt = 0;
        for(int i=0; i<A; i++){
            int n = Integer.parseInt(st.nextToken());
            if(!set.contains(n)){
                list.add(n);
                cnt++;
            }
        }
        list.sort((a, b) -> a - b);
        if(cnt==0){
            System.out.println(0);
            return;
        }
        sb.append(cnt).append("\n");
        for(int n: list){
            sb.append(n).append(" ");
        }
        System.out.println(sb);
    }
}
