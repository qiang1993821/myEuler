package EULER16;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/5.
 * 幂的数字和
 * 2^15 = 32768，而32768的各位数字之和是 3 + 2 + 7 + 6 + 8 = 26。
 * 2^1000的各位数字之和是多少？
 * 这个就是看那么大的数怎么存，没发现有什么规律之类的
 * 注意：
 *  1、遍历map过程中修改该map会有异常
 *  2、for(Long key:map.keySet()),这种方式key不是挨着的
 */
public class EULER16 {
    public static void main(String[] args){
        long start = System.currentTimeMillis();
        Map<Long,String> map = new HashMap<Long, String>();
        map.put(0l,"1");
        for (int i=0;i<1000;i++){
            for (long j=0;j<map.size();j++){
                float value = Float.valueOf(map.get(j));
                value *= 2;
                int num = (int)value;
                map.put(j,num%10000+"");
                if (num/10000>0) {//其实不是0就是1，乘二后进位只可能为一
                    if (map.get(j + 1) != null) {
                        float nextNum = Float.valueOf(map.get(j + 1));
                        nextNum += 0.5;//从地位开始遍历，之后会乘2，相当于进位1
                        map.put(j+1,nextNum+"");
                    } else {
                        map.put(j+1,"1");
                        break;
                    }
                }
            }
        }
        long sum = 0;
        String total = "";
        for (Long key:map.keySet()){
            String num = map.get(key);
            for (char n:num.toCharArray()){
                sum += Integer.valueOf(String.valueOf(n));
            }
            total = map.get(key)+total;
        }
        System.out.println(total);
        System.out.println(sum);
        long end = System.currentTimeMillis();
        System.out.print("总用时:"+(end-start)+"毫秒");
    }
}
