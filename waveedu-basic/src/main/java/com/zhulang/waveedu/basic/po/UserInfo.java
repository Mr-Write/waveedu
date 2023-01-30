package com.zhulang.waveedu.basic.po;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 用户详情表
 *
 * @author 狐狸半面添
 * @create 2023-01-18 23:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("basic_user_info")
public class UserInfo {
    /**
     * 用户id
     */
    @TableId
    private Long id;
    /**
     * 用户名
     */

    private String name;
    /**
     * 用户头像
     */
    private String icon;
    /**
     * 用户个性签名
     */
    private String signature;
    /**
     * 性别：男或女
     */
    private String sex;
    /**
     * 生日
     */
    private LocalDate born;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
