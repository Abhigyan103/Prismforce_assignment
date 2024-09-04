import java.util.Scanner;

class Abhimanyu {
    public static void main(String[] args) {
        int power, skips, recharge,n;
        int k[];
        try (Scanner sc = new Scanner(System.in)) {
            n=11; //Total Circles
            k = new int[n]; // Powers
            power=sc.nextInt(); // Abhimanyu's Power
            skips=sc.nextInt(); // Skips
            recharge=sc.nextInt(); // Recharges
            for(int i=0;i<n;i++){ // Enemy powers
                k[i]=sc.nextInt();
            }
        }
        k[3]+=Math.ceil(k[2]/(double)2);
        k[7]+=Math.ceil(k[6]/(double)2);
        boolean dp[][][][] = new boolean[n+1][power+1][skips+1][recharge+1];

        // Tabulation solution
        for (int i = n; i >= 0; i--) {
            for (int p = 0; p <= power; p++) {
                for (int s = 0; s <= skips; s++) {
                    for (int r = 0; r <= recharge; r++) {
                        if(i==n) {
                            dp[i][p][s][r]=true;
                            continue;
                        }
                        boolean ans = false;
                        if(p>=k[i]) { // Can beat
                            ans = dp[i+1][p-k[i]][s][r];
                        }
                        if (s>0) { // Can skip
                            ans= ans | dp[i+1][p][s-1][r];
                        }
                        if(r>0) { // can recharge
                            if(power >= k[i]){// can beat
                                ans = ans | dp[i+1][power-k[i]][s][r-1];
                            }
                        }
                        dp[i][p][s][r]=ans;
                    }
                }
            }
        }
        boolean ans = dp[0][power][skips][recharge];
        System.out.println(ans?"Can cross":"Cannot cross");
    }

//     static boolean f(int i, int p, int a, int b, int[] k, int initP, int[][][][] dp) {
//         if(dp[i][p][a][b]!=-1) return dp[i][p][a][b]==1;
//         if(i ==11) { // All Charkrayus crossed
//             dp[i][p][a][b]=1;
//             return true;
//         }
//         boolean ans = false;
        
//         if(b>0) { // can recharge
//             ans= ans | f(i, initP,a,b-1,k,initP,dp);
//         }
//         if (a > 0) { // Can skip
//             ans= ans | f(i+1, p,a-1,b,k,initP,dp);
//         }
//         if(p>=k[i]) { // Can beat
//             ans = f(i+1,p-k[i], a,b,k,initP,dp);
//         }
//         dp[i][p][a][b]=(ans)?1:0;
//         return ans;
//     }
}