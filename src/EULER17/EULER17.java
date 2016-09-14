package EULER17;

/**
 * Created by Administrator on 2016/9/14.
 * 如果把1到5写成英文单词，分别是：one, two, three, four, five，这些单词一共用了3 + 3 + 5 + 4 + 4 = 19个字母。
 * 如果把1到1000都写成英文单词，一共要用多少个字母？
 * 注意： 不要算上空格和连字符。例如，342（three hundred and forty-two）包含23个字母，而115（one hundred and fifteen）包含20个字母。单词“and”的使用方式遵循英式英语的规则。
 * 20以下和整十数是全部数字单词，逐个位算长度再稍微加点逻辑判断就成
 * 有时设计算法除了时间和空间，还要考虑扩展性
 */
public class EULER17 {
    public static void main(String[] args){
        int letterNum = 0;
        for (int i=1;i<1001;i++){
            letterNum += letterNum(i);
        }
        System.out.print(letterNum);
    }
    //获取一个数的英文字母总数
    public static int letterNum(int num){
        if (num == 1000)
            return 11;
        int letterNum = 0;
        int[] bitArr = new int[4];
        int i = 0;
        do {
            bitArr[i] = num%10;
            num /= 10;
            i++;
        }while (num > 0 && i < 4);
        if (bitArr[1] < 2){
            letterNum += getSpecialNum(bitArr[1] * 10 + bitArr[0]);
        }else {
            letterNum += (getSpecialNum(bitArr[1]*10)+getSpecialNum(bitArr[0]));
        }
        if (bitArr[2] > 0){
            letterNum += letterNum>0?10:7;//hundred或hundred and
            letterNum += getSpecialNum(bitArr[2]);
        }
        return letterNum;
    }
    //获取1-9，11-19，和整十的字母数
    public static int getSpecialNum(int num){
        switch (num){
            case 1://one
            case 2://two
            case 6://six
            case 10:return 3;//ten
            case 4://four
            case 5://five
            case 9:return 4;//nine
            case 3://three
            case 7://seven
            case 8://eight
            case 40://forty
            case 50://fifty
            case 60:return 5;//sixty
            case 11://eleven
            case 12://twelve
            case 20://twenty
            case 30://thirty
            case 80://eighty
            case 90:return 6;//ninety
            case 15://fifteen
            case 16://sixteen
            case 70:return 7;//seventy
            case 13://thirteen
            case 14://fourteen
            case 18://eighteen
            case 19:return 8;//nineteen
            case 17:return 9;//seventeen
            default:return 0;
        }
    }
}
