package caculator;

@FunctionalInterface
interface single_operator_operation {
    public Double f(Double x);
}

@FunctionalInterface
interface double_operator_operation {
    public Double f(Double x, Double y);
}

@FunctionalInterface
interface operator_ass {
    public boolean f(Integer now, Integer another);
}

class left_operator {
    static boolean left_operator_ass(Integer x, Integer y) {
        return x < y;
    }
}

class right_operator {
    static boolean right_operator_ass(Integer x, Integer y) {
        return x > y;
    }
}


class operator implements Comparable<operator> {
    protected int priority_level;
    protected String operator_symbol;
    public int operator_mu;
    public operator_ass operator_ass;

    operator(int priority_level, String operator_symbol, int operator_mu, String s) {
        this.operator_symbol = operator_symbol;
        this.priority_level = priority_level;
        this.operator_mu = operator_mu;
        if (s.equals("left"))
            this.operator_ass = left_operator::left_operator_ass;
        else
            this.operator_ass = right_operator::right_operator_ass; //结合性 ,
    }

    @Override
    public int compareTo(operator e) {
        if (this.priority_level - e.priority_level > 0) {
            return 1;
        } else if (this.priority_level - e.priority_level < 0) {
            return -1;
        } else {
            return operator_symbol.compareTo(e.operator_symbol);
        }
    }

}

class single_operator extends operator {
    public single_operator(int priority_level, String operator_symbol, int operator_mu, String s, single_operator_operation e) {
        super(priority_level, operator_symbol, operator_mu, s);
        this.e = e;
    }

    single_operator_operation e;
}

class double_operator extends operator {
    public double_operator(int priority_level, String operator_symbol, int operator_mu, String s, double_operator_operation e) {
        super(priority_level, operator_symbol, operator_mu, s);
        this.e = e;
    }

    public double_operator_operation e;
}