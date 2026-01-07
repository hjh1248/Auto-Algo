import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] prev = new int[1000001];
        int[] next = new int[1000001];

        st = new StringTokenizer(br.readLine());
        int[] tmp = new int[N];
        for(int i=0; i<N; i++){
            tmp[i] = Integer.parseInt(st.nextToken());
        }

        prev[tmp[0]] = tmp[N-1];
        next[tmp[N-1]] = tmp[0];
        for(int i=0; i<N-1; i++){
            next[tmp[i]] = tmp[i+1];
            prev[tmp[i+1]] = tmp[i];
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            int cur = Integer.parseInt(st.nextToken());

            if(cmd.charAt(0)=='B'){
                int newP = Integer.parseInt(st.nextToken());
                if(cmd.charAt(1)=='N'){
                    int nxt = next[cur];
                    next[cur] = newP;
                    prev[nxt] = newP;
                    prev[newP] = cur;
                    next[newP] = nxt;
                    bw.write(nxt + "\n");
                }
                else{
                    int prv = prev[cur];
                    prev[cur] = newP;
                    next[prv] = newP;
                    prev[newP] = prv;
                    next[newP] = cur;
                    bw.write(prv + "\n");
                }
            }
            else{
                if(cmd.charAt(1)=='N'){
                    int nxt = next[cur];
                    int nxtNxt = next[nxt];
                    next[cur] = nxtNxt;
                    prev[nxtNxt] = cur;
                    bw.write(nxt + "\n");
                }
                else{
                    int prv = prev[cur];
                    int prvPrv = prev[prv];
                    prev[cur] = prvPrv;
                    next[prvPrv] = cur;
                    bw.write(prv + "\n");
                }
            }
        }

        bw.flush();
        bw.close();
    }
}