package caculator;

import java.util.HashMap;

class DFA{
    private int[][] node =new int [1000][256];
    private HashMap<Integer,String> mp = new HashMap<Integer,String>();
    private int cnt=0;
    private int now;
    public void insert(String s){
        int now=0;
        for (int i=0;i<s.length();i++) {
            if(node[now][s.charAt(i)]!=0) {
                now=node[now][s.charAt(i)];
            }
            else{
                node[now][s.charAt(i)]=++cnt;
                now = cnt;
            }
        }
        mp.put(now,s);
    }
    public void init_state(){
        this.now = 0;
    }
    public boolean move(char s){
        if (node[now][s]!=0){
            now = node[now][s];
            return true;
        }
        else
            return false;
    }
    public boolean isEndOp(){
        return mp.containsKey(now);
    }
    public String getNowStr(){
        return mp.get(now);
    }
    public String fit(String s) {
        int now = 0;
        for (int i=0;i<s.length();i++) {
            if(node[now][s.charAt(i)]!=0) {
                now=node[now][s.charAt(i)];
                if (mp.containsKey(now)){
                    return mp.get(now);
                }
            }
        }
        return "Error";
    }
    public boolean isEndOp(String s) {
        int now = 0;
        for (int i=0;i<s.length();i++) {
            if(node[now][s.charAt(i)]!=0) {
                now=node[now][s.charAt(i)];
                if (mp.containsKey(now)){
                    return true;
                }
            }
            else
                return false;
        }
        return false;
    }
}