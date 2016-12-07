package Klotski;

import java.util.*;

/**
 * Created by roc_s on 2016/11/30.
 */
public class Klotski {
    public static Map<String,Hero> heroMap = new HashMap<String, Hero>();
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        while (true){
            System.out.println("请选择模式1或2(1为随机生成华容道，2为横刀立马可自动完成):");
            try {
                int model = Integer.valueOf(input.next());
                switch (model){
                    case 1:model1();break;
                    case 2:model2();break;
                    default:System.out.println("输入内容有误!");continue;
                }
                break;
            }catch (Exception e){
                System.out.println("输入内容有误!");
                e.printStackTrace();
            }
        }
    }


    public static void model1(){
        Scanner input = new Scanner(System.in);
        while (true) {
            Random random = new Random();
            int num = random.nextInt(6);
//        System.out.println("横放武将个数："+num);
            buidHeroMap(num);
            printMap();
            System.out.print("是否重新生成（y/n）：");
            String reBuild = input.next();
            if (!reBuild.equals("y"))
                break;
        }
        System.out.println("开始游戏，移动棋子时输入武将名称和方向，如令曹操向上则输入：曹上,如需结束游戏直接输入：结束");
        while (!(heroMap.get("曹").getNode() == 18)){
            printMap();
            System.out.print("请输入指令：");
            String order = input.next();
            if (order.equals("结束"))
                break;
            String[] step = order.split("");
            if (step.length > 2 && heroMap.get(step[1]) != null){
                if (step[2].equals("上") && moveable(heroMap.get(step[1]).getName(),1)){
                    move(heroMap.get(step[1]),1);
                    continue;
                }else if (step[2].equals("下") && moveable(heroMap.get(step[1]).getName(),2)){
                    move(heroMap.get(step[1]),2);
                    continue;
                }else if (step[2].equals("左") && moveable(heroMap.get(step[1]).getName(),3)){
                    move(heroMap.get(step[1]),3);
                    continue;
                }else if (step[2].equals("右") && moveable(heroMap.get(step[1]).getName(),4)){
                    move(heroMap.get(step[1]),4);
                    continue;
                }
            }
            System.out.println("该步无法移动或指令输入有误");
        }
        System.out.print("恭喜过关！");
    }
    public static void model2(){
        System.out.print("时间原因没能完成，并未在短时间内建立一个较为经典的数学模型，查找资料发现横刀立马最短的81步当时也是通过暴力遍历获得" +
                "，所以大体思路为生成树进行广度搜索（每种解法约为几百步，不适合深度搜索），生成子节点过程中去重，设置遍历最大步数，遍历到为止");
    }

    /**
     * 随机生成横竖个数不定，位置不定的华容道，布局为随机，并不是每种生成布局都能获得胜利
     */
    public static void buidHeroMap(int num){
        String[] heroName = new String[]{"关","张","赵","马","黄"};
        String[] soldierName = new String[]{"甲","乙","丙","丁","A","B"};
        int[] caoNode = new int[]{4,5,6,8,9,10,12,13,14,16,18};//曹操可以放置的点
        //先将曹操随机放置
        Hero caoCao = new Hero();
        caoCao.setName("曹");
        caoCao.setHeight(2);
        caoCao.setWidth(2);
        Random random = new Random();
        caoCao.setNode(caoNode[random.nextInt(caoNode.length)]);
        heroMap.put("曹",caoCao);
        //曹操放任何位置，无论num为几，显然存在某种排列可以满足，所以某种随机结果不满足后曹操不动，只其他的再次随机
        while (true) {
            int[] allNode = new int[20];//全部点
            //根据曹操的位置，对不能再放置其他武将的点做标识
            allNode[caoCao.getNode()] = 1;
            allNode[caoCao.getNode()-4] = 1;
            allNode[caoCao.getNode()-3] = 1;
            allNode[caoCao.getNode()+1] = 1;
            boolean restart = false;
            for (int i = 0; i < num; i++) {//宽2高1的武将放置
//                System.out.println("开始放置第"+(i+1)+"个横武将："+heroName[i]);
                Hero hero = new Hero();
                hero.setName(heroName[i]);
                hero.setHeight(1);
                hero.setWidth(2);
                List<Integer> nodeList = new ArrayList<Integer>();
                for (int j=0;j<allNode.length;j++){
                    if (allNode[j] == 0 ){//该点未被占用
                        if ((j+1) % 4 == 0 || allNode[j+1] == 1){//这些点不能放置宽2高1的武将
                            continue;
                        }else {
                            nodeList.add(j);
                        }
                    }
                }
                if (nodeList.size() == 0) {//无可放置位置，重新随机
                    restart = true;
                    break;
                }
                hero.setNode(nodeList.get(random.nextInt(nodeList.size())));
//                System.out.println("放在了："+hero.getNode());
                heroMap.put(heroName[i],hero);
                //根据该武将的位置，对不能再放置其他武将的点做标识
                allNode[hero.getNode()] = 1;
                allNode[hero.getNode()+1] = 1;
            }
            if (restart)
                continue;
            for (int i = 4; i > num-1; i--) {//宽1高2的武将放置
//                System.out.println("开始放置第"+(i+1)+"个竖武将："+heroName[i]);
                Hero hero = new Hero();
                hero.setName(heroName[i]);
                hero.setHeight(2);
                hero.setWidth(1);
                List<Integer> nodeList = new ArrayList<Integer>();
                for (int j=0;j<allNode.length;j++){
                    if (allNode[j] == 0 ){//该点未被占用
                        if (j < 4 || allNode[j-4] == 1){//这些点不能放置宽1高2的武将
                            continue;
                        }else {
                            nodeList.add(j);
                        }
                    }
                }
                if (nodeList.size() == 0) {//无可放置位置，重新随机
                    restart = true;
                    break;
                }
                hero.setNode(nodeList.get(random.nextInt(nodeList.size())));
//                System.out.println("放在了："+hero.getNode());
                heroMap.put(heroName[i],hero);
                //根据该武将的位置，对不能再放置其他武将的点做标识
                allNode[hero.getNode()] = 1;
                allNode[hero.getNode()-4] = 1;
            }
            if (restart)
                continue;
            //获取剩余的可放置点，此时只剩4个1*1小卒，任何随机情况都会满足
            List<Integer> nodeList = new ArrayList<Integer>();
            for (int i=0;i<allNode.length;i++){
                if (allNode[i] == 0 ){//该点未被占用
                    nodeList.add(i);
                }
            }
//            System.out.println("剩下的个数："+nodeList.size());
            for (int i=0;i<soldierName.length;i++){
                Hero hero = new Hero();
                hero.setName(soldierName[i]);
                hero.setWidth(1);
                hero.setHeight(1);
                int node = random.nextInt(nodeList.size());
                hero.setNode(nodeList.get(node));
                heroMap.put(soldierName[i],hero);
                nodeList.remove(node);
            }
            break;
        }
    }

    /**
     * 输出华容道
     */
    public static void printMap(){
        String[][] result = new String[5][4];
        for (String key:heroMap.keySet()){
            Hero hero = heroMap.get(key);
//            System.out.println(hero.getName()+"："+hero.getNode());
            for (int i=0;i<hero.getHeight();i++){
                for (int j=0;j<hero.getWidth();j++){
                    String name = (key.equals("A") || key.equals("B"))?"  ":key;
                    result[hero.getNode()/4-i][hero.getNode()%4+j] = name;
                }
            }
        }
        for (int i=0;i<result.length;i++){
            for (int j=0;j<result[0].length;j++){
                System.out.print(result[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 判断某武将是否可移动
     * @param name
     * @param direction 1，2，3，4分别为上，下，左，右
     * @return
     */
    public static boolean moveable(String name,int direction){
        if (heroMap.get(name) != null){
            Hero nullA = heroMap.get("A");
            Hero nullB = heroMap.get("B");
            Hero hero = heroMap.get(name);
            switch (direction){
                case 1:
                    if (hero.getNode() < 4)//在最上侧无法上移
                        return false;
                    if (hero.getWidth() == 1){//宽为1
                        if (nullA.getNode() == hero.getNode()-hero.getHeight()*4 || nullB.getNode() == hero.getNode()-hero.getHeight()*4)//可上移
                            return true;
                    }else {//宽为2
                        if (nullA.getNode() == nullB.getNode()-1) {//A在B左面
                            if (nullA.getNode() == hero.getNode()-hero.getHeight()*4)//向上
                                return true;
                        }
                        if (nullB.getNode() == nullA.getNode()-1) {//B在A左面
                            if (nullB.getNode() == hero.getNode()-hero.getHeight()*4)//向上
                                return true;
                        }
                    }
                    break;
                case 2:
                    if (hero.getNode() > 15)//在最下侧无法下移
                        return false;
                    if (hero.getWidth() == 1){//宽为1
                        if (nullA.getNode() == hero.getNode()+4 || nullB.getNode() == hero.getNode()+4)//可下移
                            return true;
                    }else {//宽为2
                        if (nullA.getNode() == nullB.getNode()-1) {//A在B左面
                            if (nullA.getNode() == hero.getNode()+4)//向下
                                return true;
                        }
                        if (nullB.getNode() == nullA.getNode()-1) {//B在A左面
                            if (nullB.getNode() == hero.getNode()+4)//向下
                                return true;
                        }
                    }
                    break;
                case 3:
                    if (hero.getNode() % 4 == 0)//在最左侧无法左移
                        return false;
                    if (hero.getHeight() == 1){//高为1
                        if (nullA.getNode() == hero.getNode()-1 || nullB.getNode() == hero.getNode()-1)//可左移
                            return true;
                    }else{//高为2
                        if (nullA.getNode() == nullB.getNode()+4) {//B在A上面
                            if (nullA.getNode() == hero.getNode()-1)//向左
                                return true;
                        }
                        if (nullB.getNode() == nullA.getNode()+4) {//A在B上面
                            if (nullB.getNode() == hero.getNode() - 1)//向左
                                return true;
                        }
                    }
                    break;
                case 4:
                    if ((hero.getNode()+1) % 4 == 0)//在最右侧无法右移
                        return false;
                    if (hero.getHeight() == 1){//高为1
                        if (nullA.getNode() == hero.getNode()+hero.getWidth() || nullB.getNode() == hero.getNode()+hero.getWidth())//可右移
                            return true;
                    }else{//高为2
                        if (nullA.getNode() == nullB.getNode()+4) {//B在A上面
                            if (nullA.getNode() == hero.getNode()+hero.getWidth())//向右
                                return true;
                        }
                        if (nullB.getNode() == nullA.getNode()+4) {//A在B上面
                            if (nullB.getNode() == hero.getNode() + hero.getWidth())//向右
                                return true;
                        }
                    }
                    break;
                default:return false;
            }
        }
        return false;
    }

    /**
     * 移动
     * @param hero 要移动的武将
     * @param direction 1，2，3，4分别为上，下，左，右
     */
    public static void move(Hero hero,int direction){
        if (hero == null)
            return;
        Hero nullA = heroMap.get("A");
        Hero nullB = heroMap.get("B");
        switch (direction){
            case 1://上移
                if (hero.getWidth() == 2){
                    hero.setNode(hero.getNode()-4);
                    nullA.setNode(hero.getNode()+4);
                    nullB.setNode(nullA.getNode()+1);
                }else {
                    if (nullA.getNode() == hero.getNode() - 4*hero.getHeight()){
                        hero.setNode(hero.getNode()-4);
                        nullA.setNode(hero.getNode()+4);
                    }else if (nullB.getNode() == hero.getNode() - 4*hero.getHeight()){
                        hero.setNode(hero.getNode()-4);
                        nullB.setNode(hero.getNode()+4);
                    }
                }
                break;
            case 2://下移
                if (hero.getWidth() == 2){
                    hero.setNode(hero.getNode()+4);
                    nullA.setNode(hero.getNode() - 4*hero.getHeight());
                    nullB.setNode(nullA.getNode()+1);
                }else {
                    if (nullA.getNode() == hero.getNode() + 4){
                        hero.setNode(hero.getNode()+4);
                        nullA.setNode(hero.getNode() - 4*hero.getHeight());
                    }else if (nullB.getNode() == hero.getNode() + 4){
                        hero.setNode(hero.getNode()+4);
                        nullB.setNode(hero.getNode() - 4*hero.getHeight());
                    }
                }
                break;
            case 3://左移
                if (hero.getHeight() == 2){
                    hero.setNode(hero.getNode()-1);
                    nullA.setNode(hero.getNode() + hero.getWidth());
                    nullB.setNode(nullA.getNode()-4);
                }else {
                    if (nullA.getNode() == hero.getNode() -1){
                        hero.setNode(hero.getNode()-1);
                        nullA.setNode(hero.getNode()+hero.getWidth());
                    }else if (nullB.getNode() == hero.getNode() - 1){
                        hero.setNode(hero.getNode()-1);
                        nullB.setNode(hero.getNode()+hero.getWidth());
                    }
                }
                break;
            case 4://右移
                if (hero.getHeight() == 2){
                    hero.setNode(hero.getNode()+1);
                    nullA.setNode(hero.getNode() - 1);
                    nullB.setNode(nullA.getNode()-4);
                }else {
                    if (nullA.getNode() == hero.getNode() + hero.getWidth()){
                        hero.setNode(hero.getNode()+1);
                        nullA.setNode(hero.getNode()-1);
                    }else if (nullB.getNode() == hero.getNode() + hero.getWidth()){
                        hero.setNode(hero.getNode()+1);
                        nullB.setNode(hero.getNode()-1);
                    }
                }
                break;
        }
        heroMap.put(hero.getName(),hero);
        heroMap.put("A",nullA);
        heroMap.put("B",nullB);
    }
}
class Hero{
    String name;
    int height;
    int width;
    int node;//以矩形左下角的点为定位点，4*5方格中从左到右，从上到下有20个定位点，分别用数字0-19表示

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getNode() {
        return node;
    }

    public void setNode(int node) {
        this.node = node;
    }
}
