package EULER5;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/5.
 * 2520是最小的能够被1到10整除的数。
 * 最小的能够被1到20整除的正数是多少？
 */
public class EULER5 {
    public static void main(String[] args){
        int result = 1;
        for (int i=2;i<=20;i++){
            result = leastCommonMutiple(result,i);
            System.out.println(result);
        }
        System.out.println("result:" + result);
    }

    public static int leastCommonMutiple(int a,int b){
        int max;
        max = a>b?a:b;
        b = a*b/max;
        a = max;
        if (a%b==0)
            return a;
        int result = 1;
        int end = 0;
        while (end == 0) {
            end = 1;
            for (int i = 2; i <= b/2; i++) {
                if (b%i==0&&a%i==0){
                    end = 0;
                    result *= i;
                    a /= i;
                    b /= i;
                    break;
                }
            }
        }
        result *= a*b;
        return result;
    }
}
