package com.specialyang.secondstokill.exception;

import com.specialyang.secondstokill.entity.CodeMsg;

/**
 * Created by Fan Yang in 2018/12/6 7:18 PM.
 */
public class GlobalException extends RuntimeException {

    private static final long serialVersionId = 1L;

    private CodeMsg cm;

    public GlobalException(CodeMsg cm) {
        super(cm.toString());
        this.cm = cm;
    }

    public CodeMsg getCm() {
        return cm;
    }
}
