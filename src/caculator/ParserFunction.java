package caculator;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ParserOrigin {
    static String pattern = "\\((.*),[\\s]*(.*)\\)";
    static Pattern part = Pattern.compile(pattern);
    static void f(String s, Drawer drawer) throws NoSuchMethodException {
        Matcher m = part.matcher(s);
        if (m.find())
        {
            Calculator a = new Calculator(m.group(1));
            Calculator b = new Calculator(m.group(2));
            drawer.origin(a.get_value(0,m.group(1).length()-1),b.get_value(0,m.group(2).length()-1));
        }
    }
}

class ParserRot {
    static String pattern = "is[\\s]*([^;]*);";
    static Pattern part = Pattern.compile(pattern);
    static void f(String s, Drawer drawer) throws NoSuchMethodException {
        Matcher m = part.matcher(s);
        if (m.find())
        {
            Calculator a = new Calculator(m.group(1));
            drawer.rot(a.get_value(0,m.group(1).length()-1));
        }
    }
}

class ParserScale {
    static String pattern = "\\((.*),[\\s]*(.*)\\)";
    static Pattern part = Pattern.compile(pattern);
    static void f(String s, Drawer drawer) throws NoSuchMethodException {
        Matcher m = part.matcher(s);
        if (m.find())
        {
            String s1 = m.group(1);
            String s2 = m.group(2);
            Calculator a = new Calculator(s1);
            Calculator b = new Calculator(s2);
            drawer.scale(a.get_value(0,s1.length()-1),b.get_value(0,s2.length()-1));
        }
    }
}


class ParserDraw {
    static String pattern = "for[\\s]*([^\\s]+)[\\s]*from[\\s]*([^\\s]*)[\\s]*to[\\s]*([^\\s]*)[\\s]*step[\\s]*([^\\s]*)[\\s]*draw[\\s]*\\([\\s]*([^\\s]*)[\\s]*,[\\s]*([^\\s]*)[\\s]*\\)";
    static Pattern part = Pattern.compile(pattern);
    static void f(String s, Drawer drawer) throws NoSuchMethodException, FileNotFoundException {
        Matcher m = part.matcher(s);
        if (m.find())
        {
//            Calculator s1 = new Calculator(m.group(1));
            Calculator start = new Calculator(m.group(2));
            Calculator end = new Calculator(m.group(3));
            Calculator step = new Calculator(m.group(4));

//            Calculator exp1 = new Calculator(m.group(5));
//            Calculator exp2 = new Calculator(m.group(6));
            drawer.draw(start.get_value(0,m.group(2).length()-1),
                        end.get_value(0,m.group(3).length()-1),
                        step.get_value(0,m.group(4).length()-1),
                        m.group(1),m.group(5),m.group(6));
        }
    }
}

