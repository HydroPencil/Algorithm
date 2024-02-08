class Solution {
    public String solution(String s) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        String[] nums = s.split(" ");
        
        for(String n : nums){
            int num = Integer.parseInt(n);
            if(min>num){
                min = num;
            }
            if(max<num){
                max = num;
            }
        }
        
        StringBuilder ans = new StringBuilder();
        ans.append(min);
        ans.append(" ");
        ans.append(max);
        
        return ans.toString();
    }
}