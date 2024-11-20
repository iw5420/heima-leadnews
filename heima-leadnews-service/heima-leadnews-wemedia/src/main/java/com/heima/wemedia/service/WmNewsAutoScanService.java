package com.heima.wemedia.service;

import com.heima.model.wemedia.pojos.WmNews;

public interface WmNewsAutoScanService {

    /**
     * 自媒体文章审核
     * @param wmNews  自媒体文章 對象 wmNews
     */
    public void autoScanWmNews(WmNews wmNews);
}
