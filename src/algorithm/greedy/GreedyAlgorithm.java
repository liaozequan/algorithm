package algorithm.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 贪心算法解决集合覆盖问题:
 * 给定多个电台（K1,K2...）
 * 每个电台都有所覆盖的城市
 * 目的：选择尽可能少的电台，来覆盖所有城市
 */
public class GreedyAlgorithm {
    //存放电台和所覆盖的城市，如<"k1", ["北京", "上海", "天津"]> 说明k1电台可以覆盖"北京","上海","天津"三个城市
    static HashMap<String, HashSet<String>> radioStations = new HashMap<>();
    //存放所有城市集合["北京", "上海", "天津", "广州", "深圳", "成都", "杭州", "大连"]
    static HashSet<String> allAreas = new HashSet<>();
    //存放贪心算法选择的电台，尽可能选择少的电台来覆盖所以城市，保持城市的key值
    static ArrayList<String> selectStations = new ArrayList<>();
    public static void main(String[] args) {
        //初始化数据
        initData();
        //定义一个临时数组，存放遍历过程中选择的电台所覆盖的城市和目前未覆盖城市的交集
        HashSet<String> tempSet = new HashSet<>();
        //maxKey:保存在单次遍历过程中，能够覆盖最多未覆盖城市的电台key,maxKey会加入到selectStations中
        String maxKey;
        while (allAreas.size() != 0 ){  //allAreas.size()!=0 说明电台没有覆盖所有地图，已选择电台所覆盖的地区，都会从allAreas中删除
            maxKey = null;
            //遍历所有电台的key
            for(String key : radioStations.keySet()){
                tempSet.clear();
                //从每个电台得到所覆盖地区的集合
                HashSet<String> areas = radioStations.get(key);
                tempSet.addAll(areas);
                //tempSet(当前电台所覆盖的城市) allAreas(目前未覆盖的城市集合) 取交集，返回给tempSet
                //retainAll方法作用：删除tempSet中allAreas所没有的元素
                tempSet.retainAll(allAreas);
                if(tempSet.size() != 0 && (maxKey==null || tempSet.size() > radioStations.get(maxKey).size())){
                    //如果 当前电台辐射的城市有 目前还没覆盖过的城市
                    //且  当前电台所覆盖的城市数量比上一个maxkey指向电台所覆盖的城市多（这些电台覆盖的城市数量均是目前未覆盖过的）
                    //tempSet.size() > radioStations.get(maxKey).size()) 每次选择最大，体现出贪心策略
                    maxKey = key;
                }
            }
            if(maxKey != null){
                //全部电台搜索过后，将maxKey（选择的电台key）放入selectStations
                selectStations.add(maxKey);
                //将选择的电台所覆盖的城市从allAreas中删除
                allAreas.removeAll(radioStations.get(maxKey));
                //将选择的电台从电台集合中删除
                radioStations.remove(maxKey);
            }
        }

        System.out.println(selectStations);

    }

    public static void initData(){
        //将各个电台放入radioStations
        HashSet<String> tempHashSet1 = new HashSet<>();
        tempHashSet1.add("北京");tempHashSet1.add("上海");tempHashSet1.add("天津");
        radioStations.put("k1", tempHashSet1);   //表示k1电台可以覆盖北京、上海、天津3个地区
        HashSet<String> tempHashSet2 = new HashSet<>();
        tempHashSet2.add("广州");tempHashSet2.add("北京");tempHashSet2.add("深圳");
        radioStations.put("k2", tempHashSet2);
        HashSet<String> tempHashSet3 = new HashSet<>();
        tempHashSet3.add("成都");tempHashSet3.add("上海");tempHashSet3.add("杭州");
        radioStations.put("k3", tempHashSet3);
        HashSet<String> tempHashSet4 = new HashSet<>();
        tempHashSet4.add("上海");tempHashSet4.add("天津");
        radioStations.put("k4", tempHashSet4);
        HashSet<String> tempHashSet5 = new HashSet<>();
        tempHashSet5.add("杭州");tempHashSet5.add("大连");
        radioStations.put("k5", tempHashSet5);

        //将所有地区放入一个hashset
        allAreas.add("北京");allAreas.add("上海");allAreas.add("天津");allAreas.add("成都");
        allAreas.add("广州");allAreas.add("深圳");allAreas.add("杭州");allAreas.add("大连");
    }
}
