package EULER35;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roc_s on 2016/12/1.
 * 圆周素数
 * 197被称为圆周素数，因为将它逐位旋转所得到的数：197/971和719都是素数。
 * 小于100的圆周素数有十三个：2、3、5、7、11、13、17、31、37、71、73、79和97。
 * 小于一百万的圆周素数有多少个？
 * 思路：找素数，判断是否圆周，标记圆周内其他素数下次遍历到不再判断
 */
public class EULER35 {
    public static int[] sizeTable = {9,99,999,9999,99999,999999};
    public static int[] primes = new int[1000000];
    public static void main(String[] args){
        long start = System.currentTimeMillis();
        //素数筛，选出1000000以内素数，primes[i] = 0为素数，i大于等于2
        for (int i=2;i*i<1000000;i++){
            for (int j=i;i*j<1000000;j++){
                primes[i*j] = 1;
            }
        }
        int result = 0;
        for (int i=2;i<primes.length;i++){
//            System.out.println(i+"素数"+primes[i]);
            if (primes[i] == 1) {//非素数或包含在之前遍历过的非圆周素数中
                continue;
            }else if (primes[i] == 2){//包含在之前遍历过的圆周素数中
                result++;
//                System.out.println(i);
                continue;
            }else {//没判断过的素数
                if (isCircularPrime(i)){
                    result++;
//                    System.out.println(i);
                }
            }
        }
        System.out.println("result:"+result);
        long end = System.currentTimeMillis();
        System.out.print("总用时:"+(end-start)+"毫秒");
    }

    /**
     * 是否为圆周素数
     * @param num
     * @return
     */
    public static boolean isCircularPrime(int num){
        int size = sizeOfInt(num);
        boolean result = true;
        List<Integer> numList = new ArrayList<Integer>();
        int times = size;
        int newNum = (num%10)*size + num/10;
        while (times >= 1){
            if (primes[newNum] == 1){
                result = false;
            }else {
                numList.add(newNum);
            }
            times /= 10;
            newNum = (newNum%10)*size + newNum/10;
        }
        int value = result?2:1;
        for (int i:numList){
            primes[i] = value;//若是圆周素数，标记其他素数为2，若不是标记1，外层遍历到不用再判断，
        }
        return result;
    }

    public static int sizeOfInt(int num){//判断几位数，例如五位数就返回10000，2位数返回10
        for (int i=0;i<sizeTable.length;i++){
            if (num <= sizeTable[i])
                return (sizeTable[i]+1)/10;
        }
        return 0;
    }
}
