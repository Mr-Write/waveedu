package com.zhulang.waveedu.edu.po;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 大学院校表
 *
 * @author 狐狸半面添
 * @create 2023-02-02 3:11
 */
@TableName("basic_college")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class College {
    /**
     * 学校编号
     */
    @TableId(type = IdType.INPUT)
    private Integer id;
    /**
     * 学校名
     */
    private String name;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
}
