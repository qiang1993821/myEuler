package EULER34;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/18.
 * 各位数字的阶乘
 * 145是个有趣的数，因为1! + 4! + 5! = 1 + 24 + 120 = 145。
 * 找出所有各位数字的阶乘和等于其本身的数，并求它们的和。
 * 注意：因为1! = 1和2! = 2不是和的形式，所以它们并不在讨论范围内。
 * 思路:9!=362880,设n位数，当362880*n<10^n,时，各位阶乘和不可能等于其本身，求出n大于6，所以只有n<=6时才可能满足
 */
public class EULER34 {
    public static void main(String[] args){
        long start = System.currentTimeMillis();
        int result = 0;
        for (int i=10;i<10000000;i++){
            List<Integer> bits = new ArrayList<Integer>();
            int bit = i;
            while (bit>0){
                bits.add(bit%10);
                bit /= 10;
            }
            int sum = 0;
            for (int j:bits){
                int muti = 1;
                for (int k=1;k<=j;k++){
                    muti *= k;
                }
                sum += muti;
            }
            if (sum == i) {
                result += sum;
                System.out.println(i);
            }
        }
        System.out.println("result:"+result);
        long end = System.currentTimeMillis();
        System.out.print("总用时:"+(end-start)+"毫秒");
    }
}
