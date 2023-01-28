package com.zhulang.waveedu.basic.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

/**
 * 注销表（定时任务）
 *
 * @author 狐狸半面添
 * @create 2023-01-19 13:53
 */
@TableName("basic_logoff")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Logoff {
    /**
     * 主键id（数据库id自增策略）
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 注销时间
     */
    private LocalDateTime logoffTime;
}
