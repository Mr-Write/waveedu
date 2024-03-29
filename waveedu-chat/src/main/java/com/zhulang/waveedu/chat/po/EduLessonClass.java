package com.zhulang.waveedu.chat.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 课程班级表
 * </p>
 *
 * @author 狐狸半面添
 * @since 2023-02-17
 */
@TableName("edu_lesson_class")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EduLessonClass implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id（雪花算法）
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 创建者的用户id
     */
    private Long creatorId;

    /**
     * 班级名，不可超过24长度
     */
    private String name;

    /**
     * 班级封面
     */
    private String cover;

    /**
     * 学生总数
     */
    private Integer num;

    /**
     * 是否删除，0表示否，1表示删除
     */
    @TableLogic
    private Integer isDeleted;

    /**
     * 是否开启结课，成绩将不再变化，0表示不开启，1表示开启
     */
    private Integer isEndClass;

    /**
     * 所属课程id
     */
    private Long lessonId;

    /**
     * 是否禁止加入班级，0表示否，1表示是
     */
    private Integer isForbidJoin;

    /**
     * 加入班级邀请码（随机六位）
     */
    private String inviteCode;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
