package EULER14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/3.
 * 最长考拉兹序列
 * 在正整数集上定义如下的迭代序列：
 * n → n/2 （若n为偶数）
 * n → 3n + 1 （若n为奇数）
 * 从13开始应用上述规则，我们可以生成如下的序列：
 * 13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
 * 可以看出这个序列（从13开始到1结束）共有10项。尽管还没有被证明，但我们普遍认为，从任何数开始最终都能迭代至1（“考拉兹猜想”）。
 * 从小于一百万的哪个数开始，能够生成最长的序列呢？
 * 注： 序列开始生成后允许其中的项超过一百万。
 * 从999999开始遍历，遍历过的放入map，碰到就跳，时间复杂度O(n)
 * 碰到了一个问题，中间超出了int最大值，导致结果出错，以后注意。
 * 妈的，应该是实例类不但占内存还很占时间，直接暴力遍历居然更快。。。。
 * 事实证明时间复杂度低不一定快。。。注释中的代码更快。。。
 */
public class EULER14 {
    public static void main(String[] args){
        long start = System.currentTimeMillis();
        Map<Long,Point> map = new HashMap<Long, Point>();
        Point one = new Point();
        one.setLength(1);
        one.setValue(1);
        map.put(one.getValue(),one);
        long i = 999999;
        while (i>1){
            setMap(map,i);
            i--;
        }
        Point max = one;
        for (Point point:map.values()){
            if (point.getLength()>max.getLength())
                max = point;
        }
        System.out.println("num:" + max.getValue() + ",length:" + max.getLength());
//        int longest = 0;
//        int terms = 0;
//        int i;
//        long j;
//
//        for (i = 1; i <= 1000000; i++)
//        {
//            j = i;
//            int this_terms = 1;
//
//            while (j != 1)
//            {
//                this_terms++;
//
//                if (this_terms > terms)
//                {
//                    terms = this_terms;
//                    longest = i;
//                }
//
//                if (j % 2 == 0)
//                {
//                    j = j / 2;
//                }
//                else
//                {
//                    j = 3 * j + 1;
//                }
//            }
//        }
//        System.out.println("num:" + longest + ",length:" + terms);
        long end = System.currentTimeMillis();
        System.out.print("总用时:"+(end-start)+"毫秒");
    }

    public static void setMap(Map<Long,Point> map,long num){
        long length = 0;
        long baseLength = 0;
        List<Long> keyList = new ArrayList<Long>();
        while (num>1){
            if (map.get(num)!=null)
                break;
            length++;
            keyList.add(num);
            num = num%2==1?3*num+1:num/2;
            if (map.get(num)!=null){//遍历过
                baseLength = map.get(num).getLength();
                break;
            }
        }
        for (long i:keyList){
            Point point = new Point();
            point.setValue(i);
            point.setLength(baseLength+length);
            map.put(i,point);
            length--;
        }
    }

}
