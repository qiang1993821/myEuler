package EULER1;

/**
 * Created by Administrator on 2016/7/4.
 * 如果我们列出10以内所有3或5的倍数，我们将得到3、5、6和9，这些数的和是23。
 * 求1000以内所有3或5的倍数的和。
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
