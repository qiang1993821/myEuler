package EULER26;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/29.
 * 倒数的循环节
 * 单位分数指分子为1的分数。分母为2至10的单位分数的十进制表示如下所示：
 * 1/2= 0.5
 * 1/3= 0.(3)
 * 1/4= 0.25
 * 1/5= 0.2
 * 1/6= 0.1(6)
 * 1/7= 0.(142857)
 * 1/8= 0.125
 * 1/9= 0.(1)
 * 1/10= 0.1
 * 这里0.1(6)表示0.166666…，括号内表示有一位循环节。可以看出，1/7有六位循环节。
 * 找出正整数d < 1000，其倒数的十进制表示小数部分有最长的循环节。
 * 思路：能用分数表示的数都是有理数，有理数的无限小数必有循环节（定理，自己没证过）
 * 不要从结果上去验证小数部分何时循环，有循环说明除的过程中出现了相同的余数
 */
public class EULER26 {
    public static void main(String[] args){
        long start = System.currentTimeMillis();
        int maxIndex = 0;
        int maxLength = 0;
        for (int i = 2; i < 1000; i++){
            int length = recurringCycle(i);
            if (length > maxLength){
                maxLength = length;
                maxIndex = i;
            }
        }
        System.out.println(maxIndex);
        long end = System.currentTimeMillis();
        System.out.print("总用时:"+(end-start)+"毫秒");
    }

    static int recurringCycle(int num){
        //余数重复了就有循环
        int[] remainderArr = new int[1000];
        int index = 1;
        int remainder = 1;
        while (remainderArr[remainder%num]==0){
            if (remainder % num == 0)
                return 0;
            remainderArr[remainder%num] = index;
            remainder = (remainder%num) * 10;
            index++;
        }
        return index - remainderArr[remainder%num];
    }
}
