package EULER39;

/**
 * Created by roc on 2017/2/5.
 * Integer right triangles
 * Problem 39
 * If p is the perimeter of a right angle triangle with integral length sides, {a,b,c}, there are exactly three solutions for p = 120.
 * {20,48,52}, {24,45,51}, {30,40,50}
 * For which value of p ≤ 1000, is the number of solutions maximised?
 * 思路:两边之和大于第三边,周长不大于1000时,斜边一定小于500
 *      筛出所有符合条件的组合(类似素数筛,避免了重复),数组计数
 */
public class EULER39 {
    public static void main(String[] args){
        long start = System.currentTimeMillis();
        int[] p = new int[1001];
        int max = 0;
        for (int i=1;i<500;i++){
            for (int j=i;i*i+j*j<250000;j++){
                double hypotenuse = Math.sqrt(i*i+j*j);
                if ((double)(int)hypotenuse == hypotenuse && i+j+(int)hypotenuse<1001){
                    p[i+j+(int)hypotenuse] +=1;
                    if (p[i+j+(int)hypotenuse] > p[0]){
                        p[0] = p[i+j+(int)hypotenuse];
                        max = i+j+(int)hypotenuse;
                    }
                }
            }
        }
        System.out.println(max);
        long end = System.currentTimeMillis();
        System.out.print("总用时:"+(end-start)+"毫秒");
    }
}
