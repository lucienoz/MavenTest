package com.atguigu.guli;

import org.apache.hadoop.io.Text;

/**
 * Description:
 * <p>
 * Create by lucienoz on 2021/9/3.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
public class ETLUtils {

    /**
     * 1. videoId	视频唯一id（String）
     * 2. uploader	视频上传者（String）
     * 3. age	视频年龄（int）
     * 4. category	视频类别（Array<String>）
     * 5. length	视频长度（Int）
     * 6. views	观看次数（Int）
     * 7. rate	视频评分（Double）
     * 8. Ratings	流量（Int）
     * 9. conments	评论数（Int）
     * 10. relatedId	相关视频id（Array<String>）
     *
     * @param line
     * @return
     */
    public static Text getVideoText(Text line) {
        String lineData = line.toString();
        //检测变量长度
        String[] strDatas = lineData.split("\t");
        StringBuilder stringBuilder = new StringBuilder();
        if (strDatas.length < 9) {
            return null;
        }
        for (int i = 0; i < strDatas.length; i++) {
            if (i < 9) {
                if (i != 3) {
                    if (i == strDatas.length - 1) {
                        stringBuilder.append(strDatas[i] );
                    }else {
                        stringBuilder.append(strDatas[i] + "\t");

                    }
                } else {
                    stringBuilder.append(strDatas[i]
                            .replace(" ", "")
                            .replace("^", "@")
                            .replace("&", "^") + "\t");
                }
            } else {
                if (i == strDatas.length - 1) {
                    stringBuilder.append(strDatas[i]
                            .replace(" ", "")
                            .replace("^", "@")
                    );
                } else {
                    stringBuilder.append(strDatas[i]
                            .replace(" ", "")
                            .replace("^", "@")
                            + "^");
                }
            }


        }

        return new Text(stringBuilder.toString());
    }

}
