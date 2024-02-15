package minimum_cost_to_convert_stringi;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        String source = "abcd";
        String target = "acbe";
        char[] original = {'a','b','c','c','e','d'};
        char[] changed = {'b','c','b','e','b','e'};
        int[] cost = {1,5,2,3,5,7,11,7};
        int n = original.length;
        
        Map<Character,ArrayList<node>> hashMap = new HashMap<>();

        for(char ch = 'a'; ch <= 'z'; ch++)
        {
            hashMap.put(ch, new ArrayList<>());
        }

        for(int i = 0; i < n; i++)
        {
            hashMap.get(original[i]).add(new node(changed[i], cost[i]));
        }

        // System.out.println(hashMap);

        int totalCost = 0;
        for(int i = 0; i < source.length(); i++)
        {
            if(target.charAt(i) != source.charAt(i))
            {
                Map<Character,Integer> distances = dijKstrasAlgorithm(hashMap, new node(source.charAt(i), 0));
                System.out.println(distances);
                System.out.println("***********************");
                if(distances.size() == 0 || distances.get(target.charAt(i)) == Integer.MAX_VALUE)
                {
                    totalCost = -1;
                    break;
                }else 
                {
                    totalCost = totalCost + distances.get(target.charAt(i));
                }
            }

        }

        System.out.println("Total cost " + totalCost);


    }

    private static Map<Character,Integer> dijKstrasAlgorithm(Map<Character,ArrayList<node>> adjList , node source)
    {

        Map<Character,Integer> distance =  new HashMap<>();
        Map<Character,Boolean> visited =  new HashMap<>();

        if(adjList.get(source.chr).size() == 0)
        {
            return distance;
        }

        for(char ch = 'a'; ch <= 'z'; ch++)
        {
           distance.put(ch, Integer.MAX_VALUE);
           visited.put(ch, false);
        }
        
        distance.put(source.chr,0);
        visited.put(source.chr,true);

        PriorityQueue<node> pq = new PriorityQueue<>((a,b) -> a.cost - b.cost);
        pq.add(source);
        
         while(pq.isEmpty() == false) {

            node current = pq.poll();
            visited.put(current.chr,true);
            
            for(node temp: adjList.get(current.chr))
            {
                // System.out.print(temp.chr + " ");
                if(visited.get(temp.chr) == false)
                {
                    distance.put(temp.chr, Math.min(distance.get(current.chr) + temp.cost, distance.get(temp.chr)));
                    pq.add(temp);
                }
            }
            // System.out.println();
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