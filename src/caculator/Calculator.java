package caculator;

import java.util.*;

class Calculator {
    private String s;
    private static HashMap<String, operator> Map = null;
    public static HashMap<String,Double> Const=null;
    private static DFA dfa = null;
    public static DFA end_op_set =null;
    private int[] link = new int[120];

    public static void load_operator() {
        Calculator.Map = new HashMap<>();
        Const=new HashMap<>();
        dfa = new DFA();
        end_op_set = new DFA();
        end_op_set.insert("1");
        end_op_set.insert("2");
        end_op_set.insert("3");
        end_op_set.insert("4");
        end_op_set.insert("5");
        end_op_set.insert("6");
        end_op_set.insert("7");
        end_op_set.insert("8");
        end_op_set.insert("9");
        end_op_set.insert("0");
        end_op_set.insert("sin");
        end_op_set.insert("cos");
        end_op_set.insert("tan");
        end_op_set.insert("ln");
        end_op_set.insert("(");
        end_op_set.insert("pi");
        end_op_set.insert("e");
        //end_op_set.insert("var");
        dfa.insert("sin");
        dfa.insert("cos");
        dfa.insert("tan");
        dfa.insert("ln");
        dfa.insert("+");
        dfa.insert("-");
        dfa.insert("*");
        dfa.insert("/");
        dfa.insert("**");
        Map.put("sin", new single_operator(5, "sin", 1, "left" , operator_sin::f));
        Map.put("cos", new single_operator(5, "cos", 1, "left" , operator_cos::f));
        Map.put("tan", new single_operator(5, "tan", 1, "left" , operator_tan::f));
        Map.put("ln" , new single_operator(5, "ln" , 1, "left" , operator_ln::f));
        Map.put("+"  , new double_operator(2, "+"  , 2, "left" , operator_add::f));
        Map.put("-"  , new double_operator(2, "-"  , 2, "right", operator_sub::f));
        Map.put("*"  , new double_operator(3, "*"  , 2, "left" , operator_mul::f));
        Map.put("/"  , new double_operator(3, "/"  , 2, "right" , operator_div::f));
        Map.put("**" , new double_operator(4, "**" , 2, "right", operator_pow::f));
        Const.put("pi",3.1415926535);
        Const.put("e",2.732);
        //Const.put("var",1.0);
    }

    public Calculator(String s) {
        this.s = s;
        Arrays.fill(link, -1);
        get_link();
    }

    private boolean isDigit(char t) {
        return t <= '9' && t >= '0';
    }

    public Double get_value(int l, int r) throws NoSuchMethodException {
        int level = 10;
        int pos = -1;
        //String last;
        TreeMap<operator, Integer> operator_set = new TreeMap<>(operator::compareTo);
        if (this.s.charAt(l) == '(' && link[l] == r) {
            return get_value(l + 1, r - 1);
        }
        for (int i = l; i <= r; i++) {
            if (isDigit(this.s.charAt(i))) {
                continue;
            } else if (this.s.charAt(i) == '(') {
                i = link[i];
            } else if (i < r  && dfa.move(this.s.charAt(i)) && end_op_set.isEndOp(s.substring(i+1,r+1))&& dfa.isEndOp()) {
                operator op = Map.get(dfa.getNowStr());
                if (operator_set.containsKey(op)) {
                    if (op.operator_ass.f(i, operator_set.get(op)))
                        operator_set.put(op, i);
                } else
                    operator_set.put(op, i);
                dfa.init_state();
            }
        }
        if (!operator_set.isEmpty()) {
            pos = operator_set.get(operator_set.firstKey());
            operator f = operator_set.firstKey();
            if (f.operator_mu == 2) {
                double_operator dop = (double_operator) f;
                return dop.e.f(get_value(l, pos - f.operator_symbol.length()), get_value(pos + 1, r));
            } else if (f.operator_mu == 1) {
                single_operator sig = (single_operator) f;
                return sig.e.f(get_value(pos + 1, r));
            }
        }
        if (Const.containsKey(s.substring(l,r+1)))
            return Const.get(s.substring(l,r+1));
        return Double.valueOf(s.substring(l, r + 1));
    }

    private void get_link() {
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                st.push(i);
            } else if (s.charAt(i) == ')') {
                int tt = st.pop();
                link[tt] = i;
            }
        }
    }
    public static void main(String[] args) throws NoSuchMethodException {
        Calculator.load_operator();
        Scanner cin = new Scanner(System.in);
        while (cin.hasNext()) {
            String s = cin.next();
            Calculator cal = new Calculator(s);
            System.out.println(cal.get_value(0, s.length() - 1));
        }
    }
}