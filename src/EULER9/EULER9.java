package EULER9;

/**
 * Created by ROC on 2016/7/6.
 * 毕达哥拉斯三元组是三个自然数a < b < c组成的集合，并满足
 * a^2 + b^2 = c^2
 * 例如，3^2 + 4^2 = 9 + 16 = 25 = 5^2。
 * 有且只有一个毕达哥拉斯三元组满足 a + b + c = 1000。求这个三元组的乘积abc。
 * 将平方和式代入和式求出a=1000*（500-b)/(1000-b),a和b在公式里是对称的，所以也有b=1000*（500-a)/(1000-a)
 * 遍历1-499
 */
public class EULER9 {
    public static void main(String[] args){
        int j;
        for (int i = 1;i<500;i++){
            if ((1000-i)%(500-i)==0){
                int k = (1000-i)/(500-i);
                if (1000%k==0){
                    System.out.println("i:"+i+",k:"+k);
                    j=1000/k;
                    if ((i*i+j*j)==(1000-i-j)*(1000-i-j)&&j!=i&&(1000-i-j)>j){
                        int a,b,c;
                        if (i>j){
                            a = j;
                            b = i;
                        }else {
                            a = i;
                            b = j;
                        }
                        c = 1000-i-j;
                        System.out.println("a:"+a+",b:"+b+",c:"+c+",abc:"+a*b*c);
                    }
                }
            }
        }
    }
}
