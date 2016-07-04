package EULER2;

/**
 * Created by Administrator on 2016/7/4.
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
