package com.zhulang.waveedu.edu.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author 狐狸半面添
 * @create 2023-03-13 21:36
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ToJudgeDTO implements Serializable {

    private static final long serialVersionUID = 999L;

    /**
     * 问题id
     */
    private Integer problemId;

    /**
     * 语言
     */
    private String language;

    /**
     * 代码
     */
    private String code;

}