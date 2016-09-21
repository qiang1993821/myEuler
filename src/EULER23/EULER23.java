package EULER23;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/21.
 * 完全数是指真因数之和等于自身的那些数。例如，28的真因数之和为1 + 2 + 4 + 7 + 14 = 28，因此28是一个完全数。
 * 一个数n被称为亏数，如果它的真因数之和小于n；反之则被称为盈数。
 * 由于12是最小的盈数，它的真因数之和为1 + 2 + 3 + 4 + 6 = 16，所以最小的能够表示成两个盈数之和的数是24。通过数学分析可以得出，所有大于28123的数都可以被写成两个盈数的和；尽管我们知道最大的不能被写成两个盈数的和的数要小于这个值，但这是通过分析所能得到的最好上界。
 * 找出所有不能被写成两个盈数之和的正整数，并求它们的和。
 * 思路：素数筛思想找出每个数对应的真因数和，找出全部盈数所能得到的小于28123的和，将和标识出来（两种方案），求未被标识的正整数和。
 *   方案一：找出全部盈数，遍历全部数，每一个数从盈数list的第一个开始减，判断差是否是盈数，如果是则标识，不是则减list里下一个盈数，直到判断出盈数和或list的盈数大于等于当前数，得到标识数组。
 *   方案二：从小到达遍历，若遇到盈数，则遍历其之后的数，如果是盈数与其相加，将和标识出来，得到标识数组。
 * 第一种比第二种快将近一倍
 */
public class EULER23 {
    public static void main(String[] args){
        long start = System.currentTimeMillis();
        List<Integer> divisors = new ArrayList<Integer>();
        divisors.add(0);
        for (int i=1;i<28123;i++){
            for (int j=i;j*i<28123;j++){
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
        int[] divisorSum = new int[divisors.size()];
        //方案一
        List<Integer> abundant = new ArrayList<Integer>();
        for (int i=12;i<divisorSum.length;i++) {//12为最小盈数
            if (divisors.get(i)>i)
                abundant.add(i);
        }
        for (int i=24;i<divisorSum.length;i++){
            for (int j=0;j<abundant.size();j++){
                int another = i-abundant.get(j);
                if (i<=abundant.get(j))
                    break;
                if (divisors.get(another)>another){
                    divisorSum[i] = 1;
                    break;
                }
            }
        }
        //方案二
//        for (int i=12;i<divisorSum.length;i++){//12为最小盈数
//            if (divisors.get(i)<=i)
//                continue;
//            for (int j=i;j+i<divisorSum.length;j++){
//                if (divisorSum[i+j] == 1)
//                    continue;
//                if (divisors.get(j)>j){
//                    divisorSum[i+j] = 1;
//                }
//            }
//        }
        long sum = 0;
        for (int i=1;i<divisorSum.length;i++){
            if (divisorSum[i]==0) {
                sum += i;
//                System.out.println(i);
            }
        }
        System.out.println("Non-abundant sums：" + sum);
        long end = System.currentTimeMillis();
        System.out.print("总用时:"+(end-start)+"毫秒");
    }
}
