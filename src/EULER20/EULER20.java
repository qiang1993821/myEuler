package EULER20;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/18.
 * n! 的意思是 n × (n − 1) × … × 3 × 2 × 1
 * 例如，10! = 10 × 9 × … × 3 × 2 × 1 = 3628800，所以10!的各位数字和是 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27。
 * 求出100!的各位数字和。
 * 思路：把和存进一个long的list里，long最大值9223372036854775807l
 */
public class EULER20 {
    public static void main(String[] args){
        //计算100的阶乘
        List<Long> num = new ArrayList<Long>();
        num.add(1l);
        for (long i=1;i<=100;i++){
            long carryBit = 0;
            for (int j=0;j<num.size();j++){
                long value = num.get(j)*i+carryBit;
                if (value>10000000000000000l){
                    carryBit = value/10000000000000000l;
                    value = value%10000000000000000l;
                }else {
                    carryBit = 0;
                }
                num.set(j,value);
            }
            if (carryBit != 0)
                num.add(carryBit);
        }
        int sum = 0;
        for (long value:num){
            while (value>0){
                sum += value%10;
                value /= 10;
            }
        }
        System.out.println(sum);
    }
}
