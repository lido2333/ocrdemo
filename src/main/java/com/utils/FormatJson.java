package com.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bean.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: FormatJson
 * @description:
 * @author: nping
 * @create: 2020-06-10 23:42
 **/

/**
 * 对json文件进行格式化处理。
 */
public class FormatJson {
    /**
     * 通过json获取一个存放word的list
     * @param vals
     */
    public static List<Word> getWordsInfo(String vals){
        //返回的结果集
        List<Word> resultlist = new ArrayList<>();
        //JSONObject就是可以看成一个大括号形成的对象，这个对象的属性
        // 可以是对象（{）可以是字符串（就是直接名字）也可以是数组（[)
        JSONObject json = JSONObject.parseObject(vals);
        json = json.getJSONObject("Result");
        JSONArray regions = json.getJSONArray("regions");
        for(int i = 0;i < regions.size(); i++){
            //获取每一个region
            JSONObject temp = regions.getJSONObject(i);
            JSONArray lines = temp.getJSONArray("lines");
            for(int j = 0; j < lines.size(); j ++){
                //得到每一行的数据
                JSONObject tempj = lines.getJSONObject(j);
                Word tempword = new Word();
                tempword.setWord(tempj.get("text").toString());
                String[] boundingBoxes = tempj.get("boundingBox").toString().split(",");
                //设置坐标
                tempword.setLefttopx(Integer.parseInt(boundingBoxes[0]));
                tempword.setLefttopy(Integer.parseInt(boundingBoxes[1]));
                tempword.setRightdownx(Integer.parseInt(boundingBoxes[2]));
                tempword.setRightdowny(Integer.parseInt(boundingBoxes[5]));
                resultlist.add(tempword);
            }
        }
        return resultlist;
    }
}
