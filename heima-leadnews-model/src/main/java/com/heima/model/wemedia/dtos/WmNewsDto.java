package com.heima.model.wemedia.dtos;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class WmNewsDto {

    private Integer id;
    /**
     * 標題
     */
    private String title;
    /**
     * 頻道id
     */
    private Integer channelId;
    /**
     * 標簽
     */
    private String labels;
    /**
     * 發布時間
     */
    private Date publishTime;
    /**
     * 文章內容
     */
    private String content;
    /**
     * 文章封面類型  0 無圖 1 單圖 3 多圖 -1 自動
     */
    private Short type;
    /**
     * 提交時間
     */
    private Date submitedTime;
    /**
     * 狀態 提交為1  草稿為0
     */
    private Short status;

    /**
     * 封面圖片列表 多張圖以逗號隔開
     */
    private List<String> images;

    /**
     * 上下架 0 下架  1 上架
     */
    private Short enable;
}