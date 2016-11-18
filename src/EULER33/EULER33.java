package EULER33;

/**
 * Created by Administrator on 2016/11/18.
 * 消去数字的分数
 * 49/98是一个有趣的分数，因为缺乏经验的数学家可能在约简时错误地认为，等式49/98 = 4/8之所以成立，是因为在分数线上下同时抹除了9的缘故。
 * 我们也会想到，存在诸如30/50 = 3/5这样的平凡解。
 * 这类有趣的分数恰好有四个非平凡的例子，它们的分数值小于1，且分子和分母都是两位数。
 * 将这四个分数的乘积写成最简分数，求此时分母的值。
 * 思路：aa/ab=a/b->a=b,所以分子或分母不是aa形式的，ab/ba=a/a=b/b=1,所以分子和分母不是ab/ba或ba/ab形式的
 * 剩下的情况判断ab/ac=b/c即判断ab*c=ac*b
 */
public class EULER33 {
    public static void main(String[] args){
        for (int i=1;i<10;i++){
            for (int numerator=1;numerator<10;numerator++){
                if (numerator == i)//跳过aa/ac形式
                    continue;
                for (int denominator=1;denominator<10;denominator++){
                    if (denominator == i || denominator == numerator)//跳过ab/aa形式，跳过ab/ab形式
                        continue;
                    if ((i*10+numerator)*denominator==(i*10+denominator)*numerator && (i*10+numerator)<(i*10+denominator)){//ab/ac
                        System.out.println((i*10+numerator)+"/"+(i*10+denominator));
                    }
                    if ((i*10+numerator)*denominator==(i+denominator*10)*numerator && (i*10+numerator)<(i+denominator*10)){//ab/ca
                        System.out.println((i * 10 + numerator) + "/" + (i +denominator*10));
                    }
                    if ((i+numerator*10)*denominator==(i*10+denominator)*numerator && (i+numerator*10)<(i*10+denominator)){//ba/ac
                        System.out.println((i + numerator * 10) + "/" + (i *10+denominator));
                    }
                    if ((i+numerator*10)*denominator==(i+denominator*10)*numerator && (i+numerator*10)<(i+denominator*10)){//ba/ca
                        System.out.println((i + numerator * 10) + "/" + (i +denominator*10));
                    }
                }
            }

        }
    }
}
