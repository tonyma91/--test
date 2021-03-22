package com.zr0817.news0817.enums;

import lombok.Getter;

@Getter
public enum ExceEnum {

    NULL_POINT_EXC (001,"传入参数为空"),
    FAIL_FIND(002,"查找失败");

    private Integer code;
    private String msage;


     ExceEnum(int code,String msage){
        this.msage = msage;
        this.code = code;
    }
}
