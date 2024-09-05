import java.util.Arrays;
import java.util.Scanner;

class Abhimanyu {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int power, skips, recharge,n;
            int k[];
            n=11; //Total Circles
            k = new int[n]; // Powers
            power=sc.nextInt(); // Abhimanyu's Power
            skips=sc.nextInt(); // Skips
            recharge=sc.nextInt(); // Recharges
            for(int i=0;i<n;i++){ // Enemy powers
                k[i]=sc.nextInt();
            }
            boolean ans = solve(n, power, skips, recharge, k);
            System.out.println(ans?"Can cross":"Cannot cross");
        }
    }
    public static boolean solve(int n,int power,int skips, int recharge,int[] k) {        
        //Memoization Solution
        int dp[][][][][] = new int[n+1][power+1][skips+1][recharge+1][2];
        for(int[][][][] h:dp) {
            for(int[][][] i : h){
                for(int[][] j : i){
                    for(int[] l :j){
                        Arrays.fill(l,-1);
                    }
                }
            }
        }
        boolean ans = f(0, power,skips, recharge,0, k, power, dp);
        return ans;
    }

    static boolean f(int i, int p, int a, int b,int prevSkip, int[] k, int initP, int[][][][][] dp) {
        if(dp[i][p][a][b][prevSkip]!=-1) return dp[i][p][a][b][prevSkip]==1;
        if(i ==11) { // All Charkrayus crossed
            dp[i][p][a][b][prevSkip]=1;
            return true;
        }
        boolean ans = false;
        int enemyPower=k[i];
        if(i==3 || i==7) {
            if(prevSkip==1) {
                enemyPower=k[i]+k[i-1];
            }else {
                enemyPower=k[i]+k[i-1]/2;
            }
        }
        
        if(b>0) { // can recharge
            if(initP>=enemyPower){
                ans |= f(i+1, initP-enemyPower,a,b-1,0,k,initP,dp);
            }
        }
        if (a > 0) { // Can skip
            ans |= f(i+1, p,a-1,b,1,k,initP,dp);
        }
        if(p>=enemyPower) { // Can beat
            ans |= f(i+1,p-enemyPower, a,b,0,k,initP,dp);
        }
        dp[i][p][a][b][prevSkip]=(ans)?1:0;
        return ans;
    }
}