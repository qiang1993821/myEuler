package EULER29;

/**
 * 不同的幂考虑所有满足2 ≤ a ≤ 5和2 ≤ b ≤ 5的整数组合生成的幂ab：
 * 2^2=4, 2^3=8, 2^4=16, 2^5=32
 * 3^2=9, 3^3=27, 3^4=81, 3^5=243
 * 4^2=16, 4^3=64, 4^4=256, 4^5=1024
 * 5^2=2^5, 5^3=125, 5^4=625, 5^5=3125
 * 如果把这些幂按照大小排列并去重，我们得到以下由15个不同的项组成的序列：
 * 4, 8, 9, 16, 25, 27, 32, 64, 81, 125, 243, 256, 625, 1024, 3125
 * 在所有满足2 ≤ a ≤ 100和2 ≤ b ≤ 100的整数组合生成的幂ab排列并去重所得到的序列中，有多少个不同的项？
 * 思路：
 * 所有重复的值都化为2,3,5,6,7,10为底的指数形式（因为范围是[2,100]）
 * 先求出每个底的最大指数范围，例：2的最大指数范围是600，因为小于100的2的最大指数值是2^6=64，所以最大范围为6*100
 * 同理，3是400，5是200，6是200，7是200，10是200
 * 然后遍历范围内每个数找出因数组合，保证每个因数作为指数时不大于100
 */
public class EULER29 {
    public static void main(String[] args){
        long start = System.currentTimeMillis();
        int max = 100;
        int total = 0;
        int[] nums = new int[]{2,3,5,6,7,10};//所有重复的幂指数都可以化为以这几个数为底的
        for (int a : nums){
            for (int n=2;n<=max*getMaxN(a);n++){
                int num = noRepeatNum(n,a);
                if (num > 1){
                    total += (num-1);
                }
            }
        }
        System.out.println(99*99 - total);
        long end = System.currentTimeMillis();
        System.out.print("总用时:"+(end-start)+"毫秒");
    }

    public static int getMaxN(int a){
        for (int i=6;i>0;i--){//底数最小为2，所以指数最大是6不超过100
            if ((int)Math.pow(a, i) < 101){
                return i;
            }
        }
        return 0;
    }

    public static int noRepeatNum(int n,int a){
        int num = 0;
        for (int i=1;i<7;i++){
            if (i >= n || (int)Math.pow(a, i)>100){
                break;
            }else {
                if (n % i == 0 && n / i < 101){
                    num++;
                }
            }
        }
        return num;
    }
}
