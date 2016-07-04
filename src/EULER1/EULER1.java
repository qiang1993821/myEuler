package EULER1;

/**
 * Created by Administrator on 2016/7/4.
 */
public class EULER1 {
    public static void main(String[] args){
        int total15 = getTotal(15,1000);
        int total3 = getTotal(3,1000);
        int total5 = getTotal(5,1000);
        System.out.print(total3+total5-total15);
    }

    //求big以内，num的倍数的和
    public static int getTotal(int num,int big){
        int items = big/num;
        return (num+num*items)*items/2;
    }
}
