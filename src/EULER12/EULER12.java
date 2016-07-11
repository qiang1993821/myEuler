package EULER12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/11.
 * 三角形数数列是通过逐个加上自然数来生成的。例如，第7个三角形数是 1 + 2 + 3 + 4 + 5 + 6 + 7 = 28。三角形数数列的前十项分别是：
 * 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, …
 * 让我们列举出前七个三角形数的所有约数：
 * 1: 1
 * 3: 1,3
 * 6: 1,2,3,6
 * 10: 1,2,5,10
 * 15: 1,3,5,15
 * 21: 1,3,7,21
 * 28: 1,2,4,7,14,28
 * 我们可以看出，28是第一个拥有超过5个约数的三角形数。
 * 第一个拥有超过500个约数的三角形数是多少？
 * 三角形数为n*(n+1)/2，都可以写成a*b的形式
 * a*b的因数=a的因数逐一乘b的因数，再去掉重复的，每个奇数都被用到了两次
 */
public class EULER12 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Map<Integer,List<Integer>> map = new HashMap<Integer, List<Integer>>();
        Map<Integer,String> result = new HashMap<Integer, String>();
        int minOdd = 1;
        int n = 1;
        int odd,even;//三角形数数列为连续几项的和，n*(n+1)/2，即奇数*（偶数/2），odd为奇数，even为偶数/2
        List<Integer> evenList;
        List<Integer> oddList;
        while (result.size()<500){
            result.clear();
            odd = n%2==0?(n+1):n;
            even = n%2==0?(n/2):((n+1)/2);
            oddList = getFactors(odd);
            map.put(n,oddList);
            if (even == minOdd){
                evenList = map.get(minOdd);
                map.remove(minOdd);
                minOdd += 2;
            }else {
                evenList = getFactors(even);
            }
            for (Integer i:oddList){
                for (Integer j:evenList){
                    result.put(i*j,"");//放进map，去重
                }
            }
            n++;
        }
        System.out.println(n*(n-1)/2);
        long end = System.currentTimeMillis();
        System.out.print("总用时:"+(end-start)+"毫秒");
    }

    public static List<Integer> getFactors(int num){
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i < num/2; i++){
            if (num%i==0)
                list.add(i);
        }
        list.add(num);
        return list;
    }
}
