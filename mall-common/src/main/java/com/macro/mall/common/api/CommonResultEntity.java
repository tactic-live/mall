package com.macro.mall.common.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.TimeUnit;

/**
 * 通用返回对象
 * Created by macro on 2019/4/19.
 */
public class CommonResultEntity<T> {
    private long code;
    private String message;
    private T data;

    protected CommonResultEntity() {
    }

    protected CommonResultEntity(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> ResponseEntity<CommonResultEntity<T>> successCache(T data) {
        return success(data, ResultCode.SUCCESS.getMessage(), true);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param maxAge 缓存时间(秒)
     */
    public static <T> ResponseEntity<CommonResultEntity<T>> successCache(T data, int maxAge) {
        return success(data, ResultCode.SUCCESS.getMessage(), true, maxAge);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> ResponseEntity<CommonResultEntity<T>> success(T data) {
        return success(data, ResultCode.SUCCESS.getMessage());
    }

    /**
     * 成功返回结果
     *
     * @param data    获取的数据
     * @param message 提示信息
     */
    public static <T> ResponseEntity<CommonResultEntity<T>> success(T data, String message) {
        return success(data, message, false);
    }

    /**
     * 成功返回结果
     *
     * @param data    获取的数据
     * @param message 提示信息
     * @param cache   是否缓存
     */
    public static <T> ResponseEntity<CommonResultEntity<T>> success(T data, String message, boolean cache) {
        CommonResultEntity<T> body = new CommonResultEntity<T>(ResultCode.SUCCESS.getCode(), message, data);
        if (cache == true) {
            CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.SECONDS);
            return ResponseEntity.ok()
                    .cacheControl(cacheControl)
                    .body(body);
        }
        return ResponseEntity.ok().body(body);
    }


    /**
     * 成功返回结果
     *
     * @param data    获取的数据
     * @param message 提示信息
     * @param cache   是否缓存
     * @param maxAge  缓存时间(秒)
     */
    public static <T> ResponseEntity<CommonResultEntity<T>> success(T data, String message, boolean cache, int maxAge) {
        CommonResultEntity<T> body = new CommonResultEntity<T>(ResultCode.SUCCESS.getCode(), message, data);
        if (cache == true) {
            CacheControl cacheControl = CacheControl.maxAge(maxAge, TimeUnit.SECONDS);
            return ResponseEntity.ok()
                    .cacheControl(cacheControl)
                    .body(body);
        }
        return ResponseEntity.ok().body(body);
    }

    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     */
    public static <T> ResponseEntity<CommonResultEntity<T>> failed(IErrorCode errorCode) {
        return ResponseEntity.ok().body(
                new CommonResultEntity<T>(errorCode.getCode(), errorCode.getMessage(), null));
//        return new CommonResultEntity<T>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> ResponseEntity<CommonResultEntity<T>> failed(String message) {
        return failed(ResultCode.FAILED.getCode(), message);
    }

    /**
     * 失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> ResponseEntity<CommonResultEntity<T>> failed(long code, String message) {
        return failed(new CommonErrorCode(code, message));
    }

    /**
     * 失败返回结果
     */
    public static <T> ResponseEntity<CommonResultEntity<T>> failed() {
        return failed(ResultCode.FAILED);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> ResponseEntity<CommonResultEntity<T>> validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> ResponseEntity<CommonResultEntity<T>> validateFailed(String message) {
        return failed(ResultCode.VALIDATE_FAILED.getCode(), message);
//        return new CommonResultEntity<T>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 未登录返回结果
     */
    public static <T> CommonResultEntity<T> unauthorized(T data) {
        return new CommonResultEntity<T>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> CommonResultEntity<T> forbidden(T data) {
        return new CommonResultEntity<T>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Data
    @AllArgsConstructor
    private static class CommonErrorCode implements IErrorCode {
        private long code;
        private String message;
    }
}
