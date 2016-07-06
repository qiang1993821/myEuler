package EULER6;

/**
 * Created by Administrator on 2016/7/5.
 * 前十个自然数的平方的和是
 * 12 + 22 + … + 102 = 385
 * 前十个自然数的和的平方是
 * (1 + 2 + … + 10)2 = 552 = 3025
 * 因此前十个自然数的平方的和与和的平方之差是 3025 − 385 = 2640。
 * 求前一百个自然数的平方的和与和的平方之差。
 * 各项平方的和的通项公式为n * (n+1) * (2n+1) * 1/6，可用递归证明
 */
public class EULER6 {
    public static void main(String[] args){
        long add = 0;
        for (int i=1;i<101;i++){
            add += i*i;
        }
        System.out.print(5050*5050-add);
    }
}
