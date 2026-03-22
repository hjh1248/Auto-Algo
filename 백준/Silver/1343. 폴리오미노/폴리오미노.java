
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String board = br.readLine();

        int cnt = 0;

        for (int i = 0; i < board.length(); i++) {
            if (board.charAt(i) == 'X') {
                cnt++;
            }
            else {
                if (cnt % 2 != 0) {
                    System.out.println("-1");
                    return;
                }

                while (cnt >= 4) {
                    sb.append("AAAA");
                    cnt -= 4;
                }
                if (cnt == 2) {
                    sb.append("BB");
                    cnt = 0;
                }
                sb.append(".");
            }
        }

        if (cnt % 2 != 0) {
            System.out.println("-1");
            return;
        }
        while (cnt >= 4) {
            sb.append("AAAA");
            cnt -= 4;
        }
        if (cnt == 2) {
            sb.append("BB");
        }

        System.out.println(sb);
    }
}