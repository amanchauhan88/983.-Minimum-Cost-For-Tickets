class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        // Create a DP array to store the minimum cost up to each day
        int[] dp = new int[366]; // Days range from 1 to 365
        boolean[] travelDays = new boolean[366];
        
        // Mark the days where travel occurs
        for (int day : days) {
            travelDays[day] = true;
        }
        
        for (int i = 1; i <= 365; i++) {
            // If no travel on this day, the cost is the same as the previous day
            if (!travelDays[i]) {
                dp[i] = dp[i - 1];
            } else {
                // Calculate the cost for each type of ticket
                int oneDayPass = dp[i - 1] + costs[0];
                int sevenDayPass = dp[Math.max(0, i - 7)] + costs[1];
                int thirtyDayPass = dp[Math.max(0, i - 30)] + costs[2];
                
                // Take the minimum of the three options
                dp[i] = Math.min(oneDayPass, Math.min(sevenDayPass, thirtyDayPass));
            }
        }
        
        // Return the cost for day 365
        return dp[365];
    }
}
