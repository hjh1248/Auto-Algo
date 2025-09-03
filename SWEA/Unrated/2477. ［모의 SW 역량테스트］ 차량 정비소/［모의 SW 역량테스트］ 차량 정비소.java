
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/*
 * 시간 순서대로 시뮬레이션
 * 각 창구 int[2](시간, 고객번호), 들어가면 처리 시간으로 변경
 * 접수, 정비 기다리는 줄 큐 생성
 */

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++){
            int answer = 0;

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            
            // 각 창구 소요 시간
            int[] aTimes = new int[N];
            int[] bTimes = new int[M];

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++){
                aTimes[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<M; i++){
                bTimes[i] = Integer.parseInt(st.nextToken());
            }
            
            // aq[0]: 고객번호, aq[1]: 도착 시간
            // bq[0]: 고객번호, bq[1]: 해당 고객이 이용한 접수 창구
            ArrayDeque<int[]> aq = new ArrayDeque<>();
            ArrayDeque<int[]> bq = new ArrayDeque<>();

            // 입력값에서 고객 번호와 방문 시간을 aq에 offer
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=K; i++){
                int time = Integer.parseInt(st.nextToken());
                aq.offer(new int[] {i, time});
            }

            // a창구, b창구
            // list[0]: 남은 시간 - 0이면 빈 상태
            // list[1]: 창구 내 고객 번호
            int[][] a = new int[N][2];
            int[][] b = new int[M][2];

            // 현재 지난 시간
            int time = 0;
            // 정비소에 들어간 고객 수
            int cnt = 0;
            while(cnt<K){
                // 접수창구 돌면서 진행
                for(int i=0; i<N; i++){
                    // a창구 남은시간이 0이 아니면 1 감소
                    if(a[i][0]!=0){
                        a[i][0]--;
                        // 남은시간이 0이 된다면 bq에 고객 번호와 
                        if(a[i][0]==0) bq.offer(new int[] {a[i][1], i});
                    }
                    if(a[i][0]==0){
                        // aq 제일 위에 있는 원소가 null이 아니고
                        // 방문시간이 현재 시간보다 작으면 poll 하고 a창구 이용
                        int[] aPeek = aq.peek();
                        if(aPeek != null && aPeek[1] <= time){
                            int[] aPoll = aq.poll();
                            a[i][0] = aTimes[i];
                            a[i][1] = aPoll[0];
                        }
                    }
                }
                for(int i=0; i<M; i++){
                    if(b[i][0]!=0) b[i][0]--;
                    if(b[i][0]==0){
                        // bq 제일 위에 있는 원소가 null이 아니면 b창구 이용
                        int[] bPeek = bq.peek();
                        if(bPeek != null){
                            int[] bPoll = bq.poll();
                            b[i][0] = bTimes[i];
                            b[i][1] = bPoll[0];
                            cnt++;
                            // a창구와 b창구가 A랑 B면 answer 증가
                            if(bPoll[1] == A-1 && i == B-1) answer += bPoll[0];
                        }
                    }
                }
                time++;
            }
            sb.append("#").append(tc).append(" ").append(answer == 0?-1:answer).append("\n");
        }
        System.out.println(sb);
    }
}
