import java.util.Map;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        String source = "abcd";
        String target = "abce";
        char[] original = {'a','c'};
        char[] changed = {'c','b'};
        int[] cost = {1,2};
        Map<Character,node> distanceHistories =  new HashMap<>();
        int n = original.length;

        
        Map<Character,ArrayList<node>> hashMap = new HashMap<>();

        for(int i = 0; i < n; i++)
        {
            if(hashMap.get(original[i]) == null)
            {
                hashMap.put(original[i], new ArrayList<>());
            }
            hashMap.get(original[i]).add(new node(changed[i], cost[i]));
        }
        for(int i = 0; i < n; i++)
        {
            if(hashMap.get(changed[i]) == null)
            {
                hashMap.put(changed[i], new ArrayList<>());
            }
        }

        int totalCost = 0;
        for(int i = 0; i < source.length(); i++)
        {
            if(hashMap.get(source.charAt(i)) == null)
            {
                totalCost = -1;
                break;
            }  
            if(source.charAt(i) == target.charAt(i))
            {
                totalCost = totalCost + 0;
            }else if(distanceHistories.get(source.charAt(i)) == null || (distanceHistories.get(source.charAt(i)) != null && distanceHistories.get(source.charAt(i)).chr != target.charAt(i))) {
                Map<Character,Integer> distances = dijKstrasAlgorithm(hashMap,original,changed,new node(source.charAt(i),cost[i]));
                System.out.println(distances);
                char destination = target.charAt(i);
                distanceHistories.put(source.charAt(i), new node(destination,distances.get(destination)));
                totalCost =  totalCost + distances.get(destination);
            }else {
                totalCost =  totalCost + distanceHistories.get(source.charAt(i)).cost;
            }
        }

        System.out.println("Total cost " + totalCost);


    }

    private static Map<Character,Integer> dijKstrasAlgorithm(Map<Character,ArrayList<node>> adjList ,char[] original, char[] changed, node source)
    {
        
        Map<Character,Integer> distance =  new HashMap<>();
        Map<Character,Boolean> visited =  new HashMap<>();
        for(char chr: original)
        {
           distance.put(chr, Integer.MAX_VALUE);
           visited.put(chr, false);
        }
        for(char chr: changed)
        {
           distance.put(chr, Integer.MAX_VALUE);
           visited.put(chr, false);
        }
        
        distance.put(source.chr,0);
        visited.put(source.chr,true);

        PriorityQueue<node> pq = new PriorityQueue<>((a,b) -> a.cost - b.cost);
        pq.add(source);
        
        while (pq.isEmpty() == false) {

            node current = pq.poll();
            visited.put(current.chr,true);

            for(node temp: adjList.get(current.chr))
            {
                if(visited.get(temp.chr) == false)
                {
                    distance.put(temp.chr, Math.min(distance.get(current.chr) + temp.cost, distance.get(temp.chr)));
                    pq.add(temp);
                }
            }
        }

        return distance;

    }

    
}


class node {
    char chr;
    int cost;

    public node(char chr, int cost) {
        this.chr = chr;
        this.cost = cost;
    }

}