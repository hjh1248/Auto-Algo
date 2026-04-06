class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int h = triangle.length;
        int[][] sum = new int[h][h];
        sum[0][0] = triangle[0][0];
        for(int i=1; i<h; i++){
            for(int j=0; j<i+1; j++){
                sum[i][j] = Math.max(j==0 ? 0:sum[i-1][j-1], j==i ? 0:sum[i-1][j]) + triangle[i][j];
            }
        }
        for(int i=0; i<h; i++){
            answer = Math.max(answer, sum[h-1][i]);
        }
        return answer;
    }
}