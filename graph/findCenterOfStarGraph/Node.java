package findCenterOfGraph;

public class Node {
    int val;
    int freq;
    Node(int val,int freq)
    {
        this.val = val;
        this.freq = freq;
    }
    public int getVal() {
        return val;
    }
    public void setVal(int val) {
        this.val = val;
    }
    public int getFreq() {
        return freq;
    }
    public void setFreq(int freq) {
        this.freq = freq;
    }
    

}
