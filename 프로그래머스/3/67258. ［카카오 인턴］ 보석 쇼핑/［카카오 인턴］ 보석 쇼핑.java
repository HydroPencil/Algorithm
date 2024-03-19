import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        Map<String,Integer> map = new HashMap<>();
        for(String str : gems) {
            map.put(str,0);
        }
        
        int left = 0;
        int right = 0;
        int[] answer = {0, gems.length-1};
        int variety = map.size();
        map = new HashMap<>();
        while(right < gems.length && left < gems.length) {
            while(right < gems.length && map.size() < variety) {
                int count = 1;
                if(map.containsKey(gems[right])) {
                    count += map.get(gems[right]);
                }
                map.put(gems[right], count);
                right++;
            }
            while(left <= right && map.size() == variety) {
                if(answer[1]-answer[0] > right - left -1) {
                    answer[0] = left;
                    answer[1] = right -1;
                }
                int count = map.get(gems[left]) -1;
                if(count == 0) {
                    map.remove(gems[left]);
                } else {
                    map.put(gems[left], count);
                }
                left++;
            }
        }
        
        answer[0]++;
        answer[1]++;
        return answer;
    }
}