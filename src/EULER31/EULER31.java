package EULER31;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/9.
 * 英国的货币单位包括英镑£和便士p，在流通中的硬币一共有八种：
 * 1p, 2p, 5p, 10p, 20p, 50p, £1 (100p), £2 (200p)
 * 以下是组成£2的其中一种可行方式：
 * 1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
 * 不限定使用的硬币数目，组成£2有多少种不同的方式？
 * 思路：
 * 最简单的就8层循环找，稍微设计一下进位，只遍历所有满足条件的组合，但是map存储查找很费时间
 */
public class EULER31 {
    public static void  main(String[] args){
        long start = System.currentTimeMillis();
        Map<Integer,Integer> map = new HashMap<Integer, Integer>();
        int num = 0;
        do {
            num++;
            nextStep(map);
//            System.out.println("200:" + map.get(200) + ",100:" + map.get(100) + ",50:" + map.get(50) + ",20:" + map.get(20) + ",10:" + map.get(10) + ",5:" + map.get(5)+",2:"+map.get(2)+",1:"+map.get(1));
        }while (map.get(200) == 0);
        System.out.println(num);
        long end = System.currentTimeMillis();
        System.out.print("总用时:"+(end-start)+"毫秒");
    }

    public static void nextStep(Map<Integer,Integer> map){
        if (map == null || map.size() == 0) {
            map.put(1, 200);
            map.put(2, 0);
            map.put(5, 0);
            map.put(10, 0);
            map.put(20, 0);
            map.put(50, 0);
            map.put(100, 0);
            map.put(200, 0);
        }else if (map.get(1) != 0){
            if (map.get(1) == 1){
                map.put(2,0);
                map.put(5,map.get(5)+1);
                map.put(1,200-200*map.get(200)-100*map.get(100)-50*map.get(50)-20*map.get(20)-10*map.get(10)-5*map.get(5)-2*map.get(2));
            }else {
                map.put(2,map.get(2)+1);
                map.put(1,200-200*map.get(200)-100*map.get(100)-50*map.get(50)-20*map.get(20)-10*map.get(10)-5*map.get(5)-2*map.get(2));
            }
        }else {
            int[] numArr = new int[]{2,5,10,20,50,100,200};
            int num = 0;
            while (map.get(numArr[num]) == 0){
                num++;
            }
            if (map.get(numArr[num+1]) != 0 && numArr[num+1] > numArr[num]*map.get(numArr[num])){
                map.put(numArr[num+2],map.get(numArr[num+2])+1);
                map.put(numArr[num+1],0);
                map.put(numArr[num],0);
            }else {
                map.put(numArr[num+1],map.get(numArr[num+1])+1);
                map.put(numArr[num],0);
            }
            map.put(1,200-200*map.get(200)-100*map.get(100)-50*map.get(50)-20*map.get(20)-10*map.get(10)-5*map.get(5)-2*map.get(2));
        }
    }
}
