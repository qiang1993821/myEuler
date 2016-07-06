package EULER2;

/**
 * Created by Administrator on 2016/7/4.
 * 斐波那契数列中的每一项都是前两项的和。由1和2开始生成的斐波那契数列前10项为：
 * 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, …
 * 考虑该斐波那契数列中不超过四百万的项，求其中为偶数的项之和。
 */
public class EULER2 {
    public static void main(String[] args){
        int a = 1;
        int b = 2;
        int total = 2;
        do {
            int c = a + b;
            a = b;
            b = c;
            if (b%2==0&&b<4000000)
                total+=b;
        }while (a+b<4000000);
        System.out.print(total+"|||"+"a:"+a+",b:"+b);
    }
}
