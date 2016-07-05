package EULER4;

/**
 * Created by Administrator on 2016/7/4.
 */
public class EULER4 {
    public static void main(String[] args){
//999*999=998001,所以最大回文数为997799，然后往下遍历，幸亏够大。
// 别人的答案里有个公式，八位回文数公式：11(9091a + 910b + 100c)，偶数回文数能被11整除
        long num = 997799;
        while (num>0) {
            if (num % 1000 < 100) {
                if (num % 100 < 10) {
                    num -= 100001;
                    num += 99990;
                } else {
                    num -= 10010;
                    num +=9900;
                }
            } else {
                num -= 1100;
            }
            System.out.println(num);
            for (long i=num/2;i>1;i--){
                if (num%i==0&&i>99&&i<1000&&num/i>99&&num/i<1000) {
                    System.out.print("回文数：" + num + ",因数："+i+"*"+num/i);
                    num = 0;
                    break;
                }
            }
        }
    }
}
