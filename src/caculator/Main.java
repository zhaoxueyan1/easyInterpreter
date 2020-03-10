package caculator;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, FileNotFoundException {
        Scanner cin = new Scanner(System.in);
        Parser.loadKeywords();
        Parser parser = new Parser();
        Calculator.load_operator();
        while (cin.hasNext()) {
            String s = cin.nextLine();
            parser.parse(s);
        }
    }
}
//1+2+(3+4+5)+7+8+(8)
