package EULER3;

/**
 * Created by Administrator on 2016/7/4.
 * 13195的所有质因数为5、7、13和29。
 * 600851475143最大的质因数是多少？
 */
public class EULER3 {
    public static void main(String[] args){
        long start = System.currentTimeMillis();
        long num = 600851475143l;
        /**
         * 从小到大，如果是因数就不变再试着去除，如果不是因数就+1，这样除到最后num不会被之前所有的因数整除且比他们都大，num就是最大的质因数
         * 程序里max会遍历到等于num为止，也就是num成为最大质因数之后，max遍历到num，num=1了，max=num了，所以最后max为结果。
         *
         */
        long max = 3;
        while (num > 1){
            if ((num%max)==0){
                num/=max;
                System.out.println(num+"|"+max);
                max--;
            }
            max++;
        }
        long end = System.currentTimeMillis();
        System.out.print("总用时:"+(end-start)+"毫秒,结果为"+max);
    }

    //是否是质数，遍历2到num/2，看余数是否为0
    public static boolean isPrimeNumber(long num){
        if (num<2)
            return false;
        if (num==2)
            return true;
        for (int i = 2;i < num/2; i++){
            if (num%i==0)
                return false;
        }
        return true;
    }
}
