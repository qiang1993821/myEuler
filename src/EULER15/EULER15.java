package EULER15;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/4.
 * 从一个2×2方阵的左上角出发，只允许向右或向下移动，则恰好有6条通往右下角的路径
 * 对于20×20方阵来说，这样的路径有多少条？
 * 我的思路：运动到其上的点后接下来只有唯一的的选择直行到终点，最右的边和最下的边对称，只研究一条边，以右侧边为例
 * 探究n到n+1的变化，用n(k)表示从起点到边上第k个点的路径数，到达边上第一个点的路径始终只有一条
 * 在n+1中，到达n+1(k)的路径数，由n(k)点右移，和n+1(k-1)下移，不难得出到达n+1(k)路径数=n(1)+n(2)+...+n(k)
 * 边长+1后，右下角的点有两条到达路径，到达数为2倍的n+1(n)点到达数
 * ---------------------------------------这是一条分割线-------------------------------
 * 回答中有一个很nb，这种二选的虽然想到了二叉树，但他的更吊，是0和1
 * 0代表向右，1代表向下，在20乘20的正方中，无论怎么走，都要走40步，其中20布向右，20步向下
 * 也就是40取20，40!/(20!*20!)
 * 做的时候其实固定步数也闪过一次，可能是由于想到一种方式就解下去了放弃了其他想法吧
 */
public class EULER15 {
    public static void main(String[] args){
        List<Long> result = new ArrayList<Long>();
        result.add(1l);
        result.add(2l);
        while (result.size()<21){
            result = addOne(result);
        }
    }

    //根据边长n的list获取边长n+1的list
    public static List<Long> addOne(List<Long> list){
        List<Long> result = new ArrayList<Long>();
        result.add(1l);
        long last = 1;
        for (int i=1;i<list.size();i++){
            long newNum = list.get(i);
            for (int j=0;j<i;j++){
                newNum += list.get(j);
            }
            result.add(newNum);
            last += list.get(i);
        }
        last *= 2;
        System.out.println(last);
        result.add(last);
        return result;
    }
}
