class Solution {
    public int solution(int sticker[]) {
        if(sticker.length == 1) return sticker[0];
        if(sticker.length == 2) {
            return Math.max(sticker[0], sticker[1]);
        }
        if(sticker.length == 3) {
            int ans = sticker[0];
            ans = Math.max(ans, sticker[1]);
            ans = Math.max(ans, sticker[2]);
            return ans;
        }
        
        int[] dp = new int[sticker.length - 1];
        
        dp[0] = sticker[0];
        dp[1] = sticker[0];
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(sticker[i] + dp[i - 2], dp[i - 1]);
        }
        
        int ans = dp[dp.length - 1];
        
        dp[0] = sticker[1];
        dp[1] = Math.max(sticker[1],sticker[2]);
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(sticker[i + 1] + dp[i - 2], dp[i - 1]);
        }
        
        ans = Math.max(ans, dp[dp.length - 1]);
        
        return ans;
    }
}