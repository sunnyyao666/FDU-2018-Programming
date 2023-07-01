package Task1;

public class Task1_2 {
    public static void main(String[] args) {
        double s=0,dx=0.000001,x=-2*Math.PI;
        while (2*Math.PI-x>0.0000000001){
            s=s+dx*Math.cos(2*Math.PI-x);
            x=x+dx;
        }
        System.out.println(s);
    }
}
