package EULER22;

import java.io.FileInputStream;

/**
 * Created by Administrator on 2016/9/20.
 * 在这个46K的文本文件names.txt（右击并选择“目标另存为……”）中包含了五千多个姓名。首先将它们按照字母序排列，然后计算出每个姓名的字母值，乘以它在按字母顺序排列后的位置，以计算出姓名得分。
 * 例如，按照字母序排列后，位于第938位的姓名COLIN的字母值是3 + 15 + 12 + 9 + 14 = 53。因此，COLIN的姓名得分是938 × 53 = 49714。
 * 文件中所有姓名的姓名得分之和是多少？
 * 思路：读取文件，快排，遍历和。快排时间复杂度n*log(n),n为名字个数,遍历和时间复杂度为N，N为字母个数=n*平均单词长度
 * 若n为五千多，则log(n)>平均单词长度
 */
public class EULER22 {
    public static void main(String[] args){
        long start = System.currentTimeMillis();
        try {
            FileInputStream inputStream = new FileInputStream("src/EULER22/names.txt");
            byte[] data = new byte[inputStream.available()];
            inputStream.read(data);
            String names = new String(data);
            names = names.replaceAll("\"","");
            String[] nameArr = names.split(",");
            //快排
            nameArr = getAscArr(nameArr);
            long sum = 0;
            for (int i=0;i<nameArr.length;i++){
                char[] charArr = nameArr[i].toCharArray();
                int charSum = 0;
                for (char c:charArr){
                    charSum += (int)c-64;
//                    System.out.print(c + ":" + ((int)c-64) + "|");
                }
                sum += charSum*(i+1);
//                System.out.println("charSum:" + charSum + "|sum:" + sum + "|name:" + nameArr[i]);
            }
            System.out.println(sum);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        long end = System.currentTimeMillis();
        System.out.print("总用时:"+(end-start)+"毫秒");
    }

    public static String[] getAscArr(String[] nameArr){
        if (nameArr.length == 1){
            return nameArr;
        }
        int nowIndex = 0;
        int compareIndex = nameArr.length-1;
        while (compareIndex != nowIndex){
            if (nameArr[compareIndex].compareTo(nameArr[nowIndex])*(compareIndex-nowIndex)<0){//升序
                String exchange = nameArr[nowIndex];
                nameArr[nowIndex] = nameArr[compareIndex];
                nameArr[compareIndex] = exchange;
                int num = nowIndex;
                nowIndex = compareIndex;
                compareIndex = num;
            }
            compareIndex += (compareIndex-nowIndex)>0?-1:1;
        }
        if (nowIndex>0){
            String[] newName = new String[nowIndex];
            for (int i=0;i<newName.length;i++){
                newName[i] = nameArr[i];
            }
            newName = getAscArr(newName);
            for (int i=0;i<newName.length;i++){
                nameArr[i] = newName[i];
            }
        }
        if (nowIndex<nameArr.length-1){
            String[] newName = new String[nameArr.length-1-nowIndex];
            for (int i=0;i<newName.length;i++){
                newName[i] = nameArr[nowIndex+1+i];
            }
            newName = getAscArr(newName);
            for (int i=0;i<newName.length;i++){
                nameArr[nowIndex+1+i] = newName[i];
            }
        }
        return nameArr;
    }
}
