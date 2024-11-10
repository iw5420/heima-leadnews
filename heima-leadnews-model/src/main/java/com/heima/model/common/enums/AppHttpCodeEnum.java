package com.heima.model.common.enums;

public enum AppHttpCodeEnum {

    // 成功段固定為200
    SUCCESS(200,"操作成功"),
    // 登錄段1~50
    NEED_LOGIN(1,"需要登錄後操作"),
    LOGIN_PASSWORD_ERROR(2,"密碼錯誤"),
    // TOKEN50~100
    TOKEN_INVALID(50,"無效的TOKEN"),
    TOKEN_EXPIRE(51,"TOKEN已過期"),
    TOKEN_REQUIRE(52,"TOKEN是必須的"),
    // SIGN驗簽 100~120
    SIGN_INVALID(100,"無效的SIGN"),
    SIG_TIMEOUT(101,"SIGN已過期"),
    // 參數錯誤 500~1000
    PARAM_REQUIRE(500,"缺少參數"),
    PARAM_INVALID(501,"無效參數"),
    PARAM_IMAGE_FORMAT_ERROR(502,"圖片格式有誤"),
    SERVER_ERROR(503,"服務器內部錯誤"),
    // 數據錯誤 1000~2000
    DATA_EXIST(1000,"數據已經存在"),
    AP_USER_DATA_NOT_EXIST(1001,"ApUser數據不存在"),
    DATA_NOT_EXIST(1002,"數據不存在"),
    // 數據錯誤 3000~3500
    NO_OPERATOR_AUTH(3000,"無權限操作"),
    NEED_ADMIND(3001,"需要管理員權限"),

    // 自媒體文章錯誤 3501~3600
    MATERIASL_REFERENCE_FAIL(3501,"素材引用失效");

    int code;
    String errorMessage;

    AppHttpCodeEnum(int code, String errorMessage){
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
