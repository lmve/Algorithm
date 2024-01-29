package Dynamic_programming;

/*
 * Create at 1.16
 * test one
 * 假设有排成一行的N个位置，记为 1 ~ N, N >= 2
 * 开始时机器人在其中的 M 位置上 ( 1 <= M <= N)
 * 如果机器人来到 1 位置，那么下一步只能往右来到 2 位置
 * 如果机器人来到 N 位置，那么下一步只能往左来到 N-1 位置
 * 如果机器人在中间位置，那么下一步可以往左或者往右
 * 规定机器人必须走 K 步，最终能来到 P 位置( 1 <= p <= N) 的方法有多少种
 * 给定四个参数 N M K P 返回方法数
 *
 */
public class test1{

    /*
     * N -----------------> 表示有 N 个位置
     * start -------------> 表示开始位置
     * aim ---------------> 表示目标位置
     * K -----------------> 表示有 k 步可以走
     */
    public static int ways1(int N, int start, int aim, int k){
        return process1(start,k,aim,N);
    }
    /*
     * 机器人当前来到的位置 cur
     * 还有 rest 步需要走
     * 最终目标是 aim
     * 有哪些位置 1 ~ N
     * return 存 cur 出发，走过 rest 步后，最终到 aim 位置的方法数
     */
    public static int process1(int cur,int rest, int aim, int N){
        if(rest == 0){ //已经走完了
            return cur == aim ? 1 : 0;
        }
        // rest > 0
        if (cur == 1){ //只能走向 2 位置
            return process1(2,rest - 1,aim,N);
        }
        if (cur == N){ //只能走向 N - 1 位置
            return process1(N-1,rest - 1,aim,N);
        }
        return process1(cur - 1,rest - 1,aim,N) + process1(cur + 1,rest - 1,aim,N);

    }
    /*
     * 缓存方案 (自顶向下)
     * 对有重复解的暴力递归的优化方案
     * dp[][] 缓存表
     * dp[cur][rest] == -1    ->  process2(cur,rest) 没算过
     */
    public static int ways2(int N, int start, int aim, int K){
       int [][] dp = new int[N + 1][K + 1];
       for (int i=0; i < N+1; i++){
           for (int j=0; j < K+1; j++){
               dp[i][j] = -1;
           }
       }
       return process2(start,K,aim,N,dp);

    }
    public static int process2(int cur, int rest, int aim, int N, int[][] dp){
        //之前已经算过
        if (dp[cur][rest] != -1){
            return dp[cur][rest];
        }
        //之前没算过
        int ans = 0;
        if (rest == 0){ //不能走了
            ans = cur == aim ? 1 : 0;
        }else if (cur == 1) { //只能往右走
            ans = process2(cur + 1, rest - 1, aim, N, dp);
        }else if (cur == N) { //只能往左走
            ans = process2(cur - 1, rest - 1, aim, N, dp);
        }else {
            ans = process2(cur + 1, rest - 1, aim, N, dp) + process2(cur - 1, rest - 1, aim, N, dp);
        }
        dp[cur][rest] = ans;
        return ans;
    }
    /*
     * 二次优化
     *
     */
    public static int ways3(int N,int start,int aim,int K){
        int dp[][] = new int[N + 1][K + 1];
        dp[aim][0] = 1;// dp[...][0] = 0
        for (int rest=1; rest<=K; rest++){
            dp[1][rest] = dp[2][rest-1];
            for (int cur=2; cur<K; cur++) {
                dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
            }
            dp[N][rest] = dp[N-1][rest-1];
        }
        return dp[start][K];
    }
    public static void main(String[] args) {
        System.out.println(ways1(4,2,4,4));
        System.out.println(ways2(4,2,4,4));
        System.out.println(ways3(4,2,4,4));
    }
}