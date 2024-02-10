import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        List<Integer> pQueue = new LinkedList<>();
        
        for(String op : operations){
            String[] order = op.split(" ");
            int value = Integer.parseInt(order[1]);
            
            if(order[0].equals("I")){
                if(pQueue.isEmpty()){
                    pQueue.add(value);
                    continue;
                }
                
                ListIterator<Integer> it = pQueue.listIterator();
                while(true){
                    if(value<=it.next()){
                        pQueue.add(it.previousIndex(),value);
                        break;
                    }
                    if(!it.hasNext()){
                        pQueue.add(value);
                        break;
                    }
                }
            }
            
            if(order[0].equals("D")&&!pQueue.isEmpty()){
                if(value==-1){
                    pQueue.remove(0);
                }
                if(value==1){
                    pQueue.remove(pQueue.size()-1);
                }
            }
        }
        
        if(pQueue.isEmpty()){
            return new int[2];
        }
        
        return new int[] {pQueue.get(pQueue.size()-1), pQueue.get(0)};
    }
}