package EULER21;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/18.
 * 记d(n)为n的所有真因数（小于n且整除n的正整数）之和。
 * 如果d(a) = b且d(b) = a，且a ≠ b，那么a和b构成一个亲和数对，a和b被称为亲和数。
 * 例如，220的真因数包括1、2、4、5、10、11、20、22、44、55和100，因此d(220) = 284；而284的真因数包括1、2、4、71和142，因此d(284) = 220。
 * 求所有小于10000的亲和数的和。
 * 思路：用素数筛类似的思想，找真因数时时间复杂度会小点：
 * 开个数组或list，下标对应，value是真因数和（list[100]的值就是100的真因数的和）
 * 两层循环，找出从1开始与所有不小于自身的数的乘积，直到乘积大于10000，相当于找出该乘积的两个真因数
 * 8128的真因数和也是8128
 */
public class EULER21 {
    public static void main(String[] args){
        long start = System.currentTimeMillis();
        List<Integer> divisors = new ArrayList<Integer>();
        divisors.add(0);
        for (int i=1;i<10000;i++){
            for (int j=i;j*i<10000;j++){
                int num = 0;
                try {
                    num = divisors.get(j*i);
                }catch (IndexOutOfBoundsException e){
                    divisors.add(num);
                }
                num += i;
                if (i!=1 && i!=j)
                    num += j;
                divisors.set(i*j,num);
            }
        }
        int sum = 0;
        for (int i=2;i<divisors.size();i++){
            if (divisors.get(i)<10000 && i==divisors.get(divisors.get(i)) && i != divisors.get(i)) {
                sum += i;
                System.out.println(i + "-" + divisors.get(i));
            }
        }
        System.out.println("sum is " + sum);
        long end = System.currentTimeMillis();
        System.out.print("总用时:"+(end-start)+"毫秒");
    }
}
