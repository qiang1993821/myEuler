package EULER10;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/7.
 * 所有小于10的素数的和是2 + 3 + 5 + 7 = 17。
 * 求所有小于两百万的素数的和。
 */
public class EULER10 {
    public static void main(String[] args){
        m2();
    }

    public static void m1(){
        long start = System.currentTimeMillis();
        long result = 2;
        long i = 3;
        boolean isPrime = true;
        while (i<2000000){
            isPrime = true;
            for (long j=3;j<=i/2;j+=2){
                if (i%j==0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                result += i;
            }
            i += 2;
        }
        System.out.println("二百万以下的素数和为"+result);
        long end = System.currentTimeMillis();
        System.out.print("总用时:"+(end-start)+"毫秒");
    }

    /**
     * 效率提高10000倍，新的找素数方法，在lassevk的方法基础上改进遍历奇数倍。
     * 从3开始遍历奇数，如果没有被标记，标记所有该数的奇数倍（因为只遍历奇数，除了2偶数不可能是素数），继续遍历
     * 这样一来，如果没被标记，说明他不是所有比他小的奇数的倍数，它是素数
     * 缺点可能是占内存
     */
    public static void m2(){
        long start = System.currentTimeMillis();
        long[] l = new long[2000000];
        long result = 2;
        int i = 3;
        while (i<2000000){
            if (l[i]==0){
                result += i;
                int k = i;
                while (k<2000000){
                    l[k] = 1;
                    k += 2*i;
                }
            }
            i += 2;
        }
        System.out.println("二百万以下的素数和为"+result);
        long end = System.currentTimeMillis();
        System.out.print("总用时:"+(end-start)+"毫秒");
    }
}
