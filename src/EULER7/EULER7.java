package EULER7;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Administrator on 2016/7/5.
 * 列出前6个素数，它们分别是2、3、5、7、11和13。我们可以看出，第6个素数是13。
 * 第10,001个素数是多少？
 * 跳过所有偶数，既然是奇数，判断是否有因数时也跳过偶数
 */
public class EULER7 {
    public static void main(String[] args){
        System.out.print("输入第几个素数：");
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        long start = System.currentTimeMillis();
        List<Long> primeList = new ArrayList<Long>();
        primeList.add(2l);
        long i = 3;
        boolean isPrime = true;
        while (primeList.size()<num){
            isPrime = true;
            for (long j=3;j<=i/2;j+=2){
                if (i%j==0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primeList.add(i);
            }
            i += 2;
        }
        System.out.println("第"+num+"个素数为"+primeList.get(num-1));
        long end = System.currentTimeMillis();
        System.out.print("总用时:"+(end-start)+"毫秒");
    }
}
