package com.bean;

import lombok.Data;

/**
 * @ClassName: Word
 * @description: 封装返回结果
 * @author: nping
 * @create: 2020-06-07 22:26
 **/
@Data
public class Word {
    private int lefttopx;
    private int lefttopy;
    private int rightdownx;
    private int rightdowny;
    private String word;


}
