package EULER24;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Administrator on 2016/9/21.
 *排列指的是将一组物体进行有顺序的放置。例如，3124是数字1、2、3、4的一个排列。如果把所有排列按照数字大小或字母先后进行排序，我们称之为字典序排列。0、1、2的字典序排列是：
 * 012   021   102   120   201   210
 * 数字0、1、2、3、4、5、6、7、8、9的字典序排列中第一百万位的排列是什么？
 * 思路：求第n位的数，一共十个数字，左起第一位是n/9!,如果是整除，第一位是0，剩下的数从大往小排。
 */
public class EULER24 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.print("请输入要查询第几位字典序：");
        getNum(input.nextInt());
    }

    public static void getNum(int index){
        long start = System.currentTimeMillis();
        int factorial = 1;
        List<Integer> available = new ArrayList<Integer>();
        available.add(0);
        for (int i=1;i<10;i++){//求9的阶乘，因为一共有10个字符（不是因为内容是数字）
            factorial *= i;
            available.add(i);
        }
        if (index/factorial>10 || index <= 0){
            System.out.println("越界");
        }
        StringBuilder result = new StringBuilder();
        int num = 0;
        while (available.size() > 0){
            if (index%factorial==0){
                result.append(available.get(index/factorial-1));
                available.remove(index/factorial-1);
                for (int i=available.size()-1;i>=0;i--){
                    result.append(available.get(i));
                }
                break;
            }else {
                result.append(available.get(index/factorial));
                available.remove(index/factorial);
            }
            if (available.size()>0) {
                index %= factorial;
                factorial /= (9 - num);
                num++;
            }
        }
        System.out.println(result);
        long end = System.currentTimeMillis();
        System.out.print("总用时:"+(end-start)+"毫秒");
    }
}
