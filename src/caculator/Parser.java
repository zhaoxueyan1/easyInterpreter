package caculator;

import java.io.FileNotFoundException;
import java.util.*;

@FunctionalInterface
interface ParserFunctionPointer {
    public void f(String s, Drawer drawer) throws NoSuchMethodException, FileNotFoundException;
}


public class Parser {
    static private DFA keywords = null;
    static private HashMap<String,ParserFunctionPointer> Mp=null;
    private Drawer drawer;
    Parser(){
        drawer=new Drawer();
    }
    static void loadKeywords() {
        keywords = new DFA();
        Mp = new HashMap<>();
        Mp.put("origin",ParserOrigin::f);
        Mp.put("rot",ParserRot::f);
        Mp.put("scale",ParserScale::f);
        Mp.put("for",ParserDraw::f);
        keywords.insert("origin");
        keywords.insert("rot");
        keywords.insert("scale");
        keywords.insert("for");
    }

    void parse(String s) throws NoSuchMethodException, FileNotFoundException {
        for (int i = 0; i < s.length(); i++) {
            if (keywords.move(s.charAt(i)) && keywords.isEndOp()) {
                Mp.get(keywords.getNowStr()).f(s,drawer);
                keywords.init_state();
                break;
            }
        }
    }

}
