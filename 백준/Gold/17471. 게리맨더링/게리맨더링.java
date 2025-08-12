import java.io.*;
import java.util.*;

class Main {
    static int N = 0;
    static int[] population;
    static int wholePopulation;
    static boolean devideCheck;
    static ArrayList<Integer>[] list;
    static int minDiff = Integer.MAX_VALUE;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        population = new int[N + 1];
        list = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        for(int i=1; i<=N; i++){
            list[i] = new ArrayList<>();
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            int pop = Integer.parseInt(st.nextToken());;
            population[i] = pop;
            wholePopulation += pop;
        }
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for(int j=1; j<=cnt; j++){
                list[i].add(Integer.parseInt(st.nextToken()));
            }
        }
        Set<Integer> trueSet = new HashSet<>();
        trueSet.add(1);
        devide(2, trueSet, new HashSet<>(), population[1]);
        System.out.println(minDiff == Integer.MAX_VALUE ? -1 : minDiff);
    }
    public static void devide(int idx, Set<Integer> trueSet, Set<Integer> falseSet, int zonePopulation){
        if(idx>N){
            check(trueSet, falseSet, zonePopulation);
            return;
        }
        falseSet.add(idx);
        devide(idx + 1, trueSet, falseSet, zonePopulation);
        falseSet.remove(idx);

        trueSet.add(idx);
        int newZonePopulation = zonePopulation + population[idx];
        devide(idx + 1, trueSet, falseSet, newZonePopulation);
        trueSet.remove(idx);
    }
    public static void check(Set<Integer> trueSet, Set<Integer> falseSet, int zonePopulation){
        if(trueSet.isEmpty() || falseSet.isEmpty()) return;
        boolean trueOk = false;
        boolean falseOk = false;
        for(int i=1; i<=N; i++){
            if(!trueOk && trueSet.contains(i)){
                if(!bfs(i, trueSet)) return;
                else trueOk = true;
            }
            else if(!falseOk && falseSet.contains(i)){
                if(!bfs(i, falseSet)) return;
                else falseOk = true;
            }
            if(trueOk && falseOk){
                minDiff = Math.min(Math.abs(2*zonePopulation - wholePopulation), minDiff);
                return;
            }
        }
    }
    public static boolean bfs(int start, Set<Integer> set){
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        Arrays.fill(visited, false);
        visited[start] = true;
        queue.offer(start);
        int cnt = 1;
        while(!queue.isEmpty()){
            int now = queue.poll();
            for(int next:list[now]){
                if(!visited[next] && set.contains(next)){
                    visited[next] = true;
                    cnt++;
                    queue.offer(next);
                }
            }
        }
        if(cnt==set.size()){
            return true;
        }
        else return false;
    }
}
