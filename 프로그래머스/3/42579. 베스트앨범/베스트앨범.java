import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Integer[]> album = new HashMap<>();
        
        for(int i=0; i<genres.length; i++){
            Integer[] v = new Integer[]{i,-1,plays[i]};
            
            if(album.containsKey(genres[i])){
                Integer[] value = album.get(genres[i]);
                v[2] += value[2];

                if(plays[value[0]] > plays[v[0]]){
                    if(value[1] == -1 || plays[value[1]] < plays[v[0]]){
                        v[1] = v[0];
                        v[0] = value[0];
                    } else {
                        v[0] = value[0];
                        v[1] = value[1];
                    }
                } else if (plays[value[0]] == plays[v[0]]){
                    v[1] = v[0];
                    v[0] = value[0];
                } else {
                    v[1] = value[0];
                }
                
            }
            
            album.put(genres[i], v);
        }
        
        List<String> keySet = new ArrayList<>(album.keySet());
        keySet.sort((o1, o2) -> album.get(o2)[2].compareTo(album.get(o1)[2]));
        
        List<Integer> ans = new LinkedList<>();
        for(String key : keySet){
            Integer[] tmp = album.get(key);
            ans.add(tmp[0]);
            if (tmp[1] != -1 ) {
                ans.add(tmp[1]);
            }
        }
        
        int[] answer = new int[ans.size()];
        int index=0;
        for(Integer i : ans){
            answer[index++] = i;
        }
        
        return answer;
    }
}