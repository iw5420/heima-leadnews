package com.heima.model.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TaskTypeEnum {

    NEWS_SCAN_TIME(1001, 1,"文章定時審核"),
    REMOTEERROR(1002, 2,"第三方接口調用失敗，重試");
    private final int taskType; //對應具體業務
    private final int priority; //業務不同級別
    private final String desc; //描述信息
}