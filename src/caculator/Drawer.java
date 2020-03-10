package caculator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class Drawer {
    private Double origin_x=0.0;
    private Double origin_y=0.0;
    private Double rot=0.0;
    private Double scale_x=1.0;
    private Double scale_y=1.0;
//    Drawer(){}
    public void origin(Double x,Double y){
        this.origin_x=x;
        this.origin_y=y;
    }

    public void rot(Double x){
        this.rot = x;
    }

    public void scale(Double x,Double y){
        this.scale_x=x;
        this.scale_y=y;
    }

    public void draw(Double start,Double end,Double step,String t,String s1,String s2) throws NoSuchMethodException, FileNotFoundException {
        Integer num =(int)Math.floor((end-start)/step);
        Double degree = start;
        Calculator a1 = new Calculator(s1);
        Calculator.end_op_set.insert(t);
        Calculator a2 = new Calculator(s2);
        PrintStream out = System.out;
        FileOutputStream f= new FileOutputStream(".//output");
        PrintStream ps = new PrintStream(f);
        System.setOut(ps);
        for(int i=0;i<=num;i++)
        {
            degree = start+i*step+rot;
            //System.out.println(degree);
            Calculator.Const.put(t,degree);
            System.out.printf("%.5f,%.5f\n",scale_x*a1.get_value(0,s1.length()-1),scale_y*a2.get_value(0,s2.length()-1));
        }
        Process proc;
        try {
            proc = Runtime.getRuntime().exec("python ./plot.py");
            proc.waitFor();
            f.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        System.setOut(out);
        System.out.println("绘图成功!");
    }

}
