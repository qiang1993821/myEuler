package EULER27;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/11.
 * 欧拉发现了这个著名的二次多项式：
 *n2 + n + 41
 * 对于连续的整数n从0到39，这个二次多项式生成了40个素数。然而，当n = 40时，402 + 40 + 41 = 40(40 + 1) + 41能够被41整除，同时显然当n = 41时，412 + 41 + 41也能被41整除。
 * 随后，另一个神奇的多项式n2 − 79n + 1601被发现了，对于连续的整数n从0到79，它生成了80个素数。这个多项式的系数-79和1601的乘积为-126479。
 * 考虑以下形式的二次多项式：
 * n2 + an + b, 满足|a| < 1000且|b| < 1000
 *其中|n|指n的模或绝对值
 * 例如|11| = 11以及|−4| = 4
 * 这其中存在某个二次多项式能够对从0开始尽可能多的连续整数n都生成素数，求其系数a和b的乘积。
 * 思路：n=0可推出b一定是素数，素数除了2以外都是是奇数，所以n=奇数时，若a为偶数，奇数+偶数+b为偶一定不是素数，所以a为奇数
 * 数学思路：n*n+n+41其实从-40到39都能生成素数，所以(n-40)*(n-40)+(n-40)+41，n从0到79都生成素数，展开就是n2 − 79n + 1601
 * 所以令n=n-p，带入，用p表示系数a，b，更具绝对值小于1000求限制，p越大，连续越长，求出p为31，正好是结果。
 * 但是这是根据欧拉二项式推出来的，逻辑上不是确定解
 */
public class EULER27 {
    public static void main(String[] args){
        long start = System.currentTimeMillis();
        //通过素数筛先找出3000以内的素数备用，超过的再单算
        int[] primes = getPrimesArr(3000);
        List<Integer> bList = new ArrayList<Integer>();
        for (int i=0;i<1000;i++){
            if (primes[i]==0){
                bList.add(i);
            }
        }
        int maxA=0,maxB=0;
        int maxN = 0;
        for (int a=-999;a<1000;a+=2){
            for (int b:bList){
                int n = 0;
                while (isPrimes(n*n+a*n+b,primes)){
                    n++;
                }
                n--;
                if (n > maxN){
                    maxA = a;
                    maxB = b;
                    maxN = n;
                }
            }
        }
        System.out.println(maxA*maxB);
        long end = System.currentTimeMillis();
        System.out.print("总用时:"+(end-start)+"毫秒");
    }

    //判断是否为素数
    public static boolean isPrimes(int num,int[] primes){
        if (num < 0)
            return false;
        if (num<primes.length){
            return primes[num] == 0;
        }else {
            for (int i = 2; i < num / 2; i++) {
                if (num % i == 0)
                    return false;
            }
            return true;
        }
    }

    //素数筛找出num以内的素数,primes[n]=m,m为0代表n为素数
    public static int[] getPrimesArr(int num){
        int[] primes = new int[num];
        primes[0] = 1;
        primes[1] = 1;
        for (int i=2;i<num;i++){
            for (int j=i;i*j<num;j++)
                primes[i*j] = 1;
        }
        return primes;
    }
}
