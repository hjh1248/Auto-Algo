
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();
        list.add(Integer.valueOf(st.nextToken()));
        for(int i=1; i<N; i++){
            Integer a = Integer.valueOf(st.nextToken());
            if(list.get(list.size()-1)<a) list.add(a);
            else{
                int left = 0;
                int right = list.size()-1;
                while(left<right){
                    int mid = (left + right) / 2;
                    if(list.get(mid)<a) left = mid+1;
                    else right = mid;
                }
                list.set(left, a);
            }
        }
        System.out.println(list.size());
    }
}
