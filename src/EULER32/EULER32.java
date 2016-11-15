package EULER32;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/10.
 * 思路：如果是1-9全数字只能：一位数*四位数=四位数，二位数*三位数=四位数
 * 根据乘积位数的限制，确定两个乘数的首位，在剩下的七个数里排序7*6*5种可能确定两个乘数，求出乘积判断是否为全数字
 * 桶排去重的话要1w次，用map的话满足条件的应该比100个少，map快一点
 * 其他时间复杂度较高但代码简单思路：列出1-9全部排列为9！按144判断一遍是否符合，按234判断一遍是否组合
 */
public class EULER32 {
    private static long start = System.currentTimeMillis();
    private static List<String> numList = new ArrayList<String>();
    static {
        numList.add(String.valueOf(1));
        numList.add(String.valueOf(2));
        numList.add(String.valueOf(3));
        numList.add(String.valueOf(4));
        numList.add(String.valueOf(5));
        numList.add(String.valueOf(6));
        numList.add(String.valueOf(7));
        numList.add(String.valueOf(8));
        numList.add(String.valueOf(9));
    }
    public static void main(String[] args){
        Map<Integer,Integer> map = new HashMap<Integer, Integer>();
        int multiplier = 0;
        int product = 0;
        //一位数乘四位数
        for(int i=1;i<10;i++){//一位数i
            numList.remove(String.valueOf(i));
            for (int j=10/i;j>0;j--){//j为四位数的千位，当j>10/i，时，乘积为五位数,等于10/i也可能为五位数，在判断是否全数的逻辑控制
                if (i == j || j > 9)
                    continue;
                numList.remove(String.valueOf(j));
                for (int a=0;a<numList.size();a++){
                    int hundred = Integer.valueOf(numList.get(a));
                    numList.remove(a);
                    for (int b=0;b<numList.size();b++){
                        int ten = Integer.valueOf(numList.get(b));
                        numList.remove(b);
                        for (int c=0;c<numList.size();c++){
                            int one = Integer.valueOf(numList.get(c));
                            multiplier = j*1000+hundred*100+ten*10+one;
                            product = i*multiplier;
                            if (isPandigital(product,i,j,one,ten,hundred)){
                                map.put(product,1);
                                System.out.println(i + "*" + multiplier+"="+product);
                            }
                        }
                        numList.add(b,String.valueOf(ten));
                    }
                    numList.add(a,String.valueOf(hundred));
                }
                numList.add(String.valueOf(j));
            }
            numList.add(String.valueOf(i));
        }
        //二位数乘三位数
        for(int i=1;i<10;i++){//两位数的十位数i
            numList.remove(String.valueOf(i));
            for (int j=10/i;j>0;j--){//j为三位数的百位，当j>10/i，时，乘积不可能为四位数
                if (i == j || j > 9)
                    continue;
                numList.remove(String.valueOf(j));
                for (int a=0;a<numList.size();a++){
                    int one_i = Integer.valueOf(numList.get(a));
                    numList.remove(a);
                    for (int b=0;b<numList.size();b++){
                        int ten = Integer.valueOf(numList.get(b));
                        numList.remove(b);
                        for (int c=0;c<numList.size();c++){
                            int one = Integer.valueOf(numList.get(c));
                            multiplier = j*100+ten*10+one;
                            product = (i*10+one_i)*multiplier;
                            if (isPandigital(product,i,j,one,ten,one_i)){
                                map.put(product,1);
                                System.out.println(i*10+one_i + "*" + multiplier+"="+product);
                            }
                        }
                        numList.add(b,String.valueOf(ten));
                    }
                    numList.add(a,String.valueOf(one_i));
                }
                numList.add(String.valueOf(j));
            }
            numList.add(String.valueOf(i));
        }
        int totle = 0;
        for (Integer i:map.keySet()){
            totle += i;
        }
        System.out.println(totle);
        long end = System.currentTimeMillis();
        System.out.print("总用时:"+(end-start)+"毫秒");
    }

    public static boolean isPandigital(int num, int... var){
        if (num < 1000 || num > 9999)//乘积不是四位数
            return false;
        List<Integer> bitList = new ArrayList<Integer>();
        while (num > 0) {
            int bit = num%10;
            if (bit == 0)
                return false;
            for (int i = 0; i < bitList.size(); i++){
                if (bitList.get(i) == bit)
                    return false;
            }
            bitList.add(bit);
            for (int i = 0; i < var.length; i++) {
                if (var[i] == bit)
                    return false;
            }
            num /= 10;
        }
        return true;
    }
}
