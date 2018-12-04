package com.specialyang.secondstokill.entity;

/**
 * Created by Fan Yang in 2018/12/4 2:15 PM.
 */
public class Response<T> {

    private int code;

    private String msg;

    private T data;

    /**
     * 成功时候的调用
     * */
    public static <T> Response<T> success(T data){
        return new Response(data);
    }

    /**
     * 失败时候的调用
     * */
    public static <T> Response<T> error(CodeMsg cm){
        return new Response(cm);
    }

    private Response(T data) {
        this(CodeMsg.SUCCESS);
        this.data = data;
    }

    private Response(CodeMsg cm) {
        if(cm == null) {
            return;
        }
        this.code = cm.getCode();
        this.msg = cm.getMsg();
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
