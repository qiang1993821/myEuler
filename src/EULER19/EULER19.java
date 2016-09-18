package EULER19;

/**
 * Created by Administrator on 2016/9/16.
 * 下列信息是已知的，当然你也不妨自己再验证一下。
 * 1900年1月1日是星期一。
 * 三十天在九月中，
 * 四六十一也相同。
 * 剩下都是三十一，
 * 除去二月不统一。
 * 二十八天平常年，
 * 多加一天在闰年。
 * 闰年指的是能够被4整除却不能被100整除的年份，或者能够被400整除的年份。
 * 在二十世纪（1901年1月1日到2000年12月31日）中，有多少个月的1号是星期天？
 * 思路：计算每月初都是周几
 */
public class EULER19 {
    public static void main(String[] args){
        long start = System.currentTimeMillis();
        int week = (365+1)%7;//1900不是闰年,+1是因为1900.1.1是星期一,得出1901.1.1是周二
        int times = 0;
        for (int year = 1901; year < 2001; year++){
            for (int month = 1;month < 13; month++){
                if (week == 0){
                    System.out.println(year+"-"+month+"-1");
                    times++;
                }
                week = isSunday(year,month,week);
            }
        }
        System.out.println(times);
        long end = System.currentTimeMillis();
        System.out.print("总用时:"+(end-start)+"毫秒");
    }

    public static int isSunday(int year,int month,int week){
        switch (month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:week += 31;break;//其实直接+=3就行，反正要对7取余
            case 4:
            case 6:
            case 9:
            case 11:week += 30;break;
            case 2:week += IsLeapYear(year)?29:28;break;
        }
        return week%7;
    }

    public static boolean IsLeapYear(int year){
        if (year%400==0){
            return true;
        }else if (year%100==0){
            return false;
        }else if (year%4==0){
            return true;
        }
        return false;
    }
}
