package EULER18;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/14.
 * 从下面展示的三角形的顶端出发，不断移动到在下一行与其相邻的元素，能够得到的最大路径和是23。
 * 3
 * 7 4
 * 2 4 6
 * 8 5 9 3
 * 如上图，最大路径和为 3 + 7 + 4 + 9 = 23。
 * 求从下面展示的三角形顶端出发到达底部，所能够得到的最大路径和：
 * 75
 * 95 64
 * 17 47 82
 * 18 35 87 10
 * 20 04 82 47 65
 * 19 01 23 75 03 34
 * 88 02 77 73 07 63 67
 * 99 65 04 28 06 16 70 92
 * 41 41 26 56 83 40 80 70 33
 * 41 48 72 33 47 32 37 16 94 29
 * 53 71 44 65 25 43 91 52 97 51 14
 * 70 11 33 28 77 73 17 78 39 68 17 57
 * 91 71 52 38 17 14 91 43 58 50 27 29 48
 * 63 66 04 68 89 53 67 30 73 16 69 87 40 31
 * 04 62 98 27 23 09 70 98 73 93 38 53 60 04 23
 * 注意： 在这个问题中，由于只有16384条路径，通过尝试所有的路径来解决问题是可行的。但是，对于第67题，虽然是一道相同类型的题目，但是三角形将拥有一百行，此时暴力破解将不能解决，而需要一个更加聪明的办法！;o)
 * 思路：递推，但不能横向比较，因为不知之后的发展情况，统一行的每个数都有可能在最大路径上
 * 已知上一行每个数的最大路径，就能推出当前行每个数的最大路径，以此没基础递推
 * 这样大大减少了遍历情况
 */
public class EULER18 {
    public static void main(String[] args){
        int[] triangle = new int[]{75,95,64,17,47,82,18,35,87,10,20,4,82,47,65,19,1,23,75,3,34,88,2,77,73,7,63,67,99,65,4,28,6,16,70,92,41,41,26,56,83,40,80,70,33,41,48,72,33,47,32,37,16,94,29,53,71,44,65,25,43,91,52,97,51,14,70,11,33,28,77,73,17,78,39,68,17,57,91,71,52,38,17,14,91,43,58,50,27,29,48,63,66,4,68,89,53,67,30,73,16,69,87,40,31,4,62,98,27,23,9,70,98,73,93,38,53,60,4,23};
        Map<String,Point> pointMap = new HashMap<String, Point>();
        int row = 1;
        int column = 1;
        for (int i=0;i<triangle.length;i++){
            Point point = new Point();
            String key = "";
            if (column>row){
                row++;
                column = 1;
            }
            key = row + "-" +column;
            point.setValue(triangle[i]);
            if (column == 1){//最左
                point.setPath(row == 1 ? "" : pointMap.get((row - 1) + "-1").getPath() + "-");
                point.setPath(point.getPath()+point.getValue());
                point.setPathValue(row==1?0:pointMap.get((row-1)+"-1").getPathValue());
                point.setPathValue(point.getPathValue()+point.getValue());
            }else if (column == row){//最右
                point.setPath(pointMap.get((row-1)+"-"+(column-1)).getPath()+"-"+point.getValue());
                point.setPathValue(pointMap.get((row-1)+"-"+(column-1)).getPathValue()+point.getValue());
            }else {
                String bigger = pointMap.get((row-1)+"-"+(column)).getPathValue()>pointMap.get((row-1)+"-"+(column-1)).getPathValue()?((row-1)+"-"+(column)):((row-1)+"-"+(column-1));
                point.setPath(pointMap.get(bigger).getPath() + "-" + point.getValue());
                point.setPathValue(pointMap.get(bigger).getPathValue()+point.getValue());
            }
            pointMap.put(key,point);
            column++;
        }
        String key = "";
        int max = 0;
        for (int i=1;i<=row;i++){
            if(pointMap.get(row+"-"+i).getPathValue()>max){
                max = pointMap.get(row+"-"+i).getPathValue();
                key = row+"-"+i;
            }
        }
        System.out.println("path:" + pointMap.get(key).getPath());
        System.out.println("maximum:"+max);
    }
}
