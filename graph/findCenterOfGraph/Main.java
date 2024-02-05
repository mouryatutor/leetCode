package findCenterOfGraph;
import java.util.HashMap;

public class Main {
    
    public static void main(String[] args) {
        int[][] edges = {{1,2},{2,3},{4,2}};

        // findCenter(edges);
        findCenterV2(edges);
    }

    
    public static void findCenter(int[][] edges)
    {
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        Node  node = new Node(0,0);
        for(int i = 0; i < edges.length; i++)
        {
            hashMap.put(edges[i][0], 1 + hashMap.getOrDefault(edges[i][0], 0));
            hashMap.put(edges[i][1], 1 + hashMap.getOrDefault(edges[i][1], 0));

            if(node.getFreq() < hashMap.get(edges[i][0]))
            {
                node.setVal(edges[i][0]);
                node.setFreq(hashMap.get(edges[i][0]));
            }

            if(node.getFreq() < hashMap.get(edges[i][1]))
            {
                node.setVal(edges[i][1]);
                node.setFreq(hashMap.get(edges[i][1]));
            }
        }

        System.out.println("Node : " + node.getVal()  + "Freq : " + node.getFreq());

    }


    public static void findCenterV2(int[][] edges)
    {
        if(edges[0][0] == edges[1][0] || edges[0][0] == edges[1][1])
        {
            System.out.println(edges[0][0]);
        }else {
            System.out.println(edges[0][1]);
        }
    }
}
