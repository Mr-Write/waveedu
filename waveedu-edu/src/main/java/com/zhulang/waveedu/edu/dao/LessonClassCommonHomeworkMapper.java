package com.zhulang.waveedu.edu.dao;

import com.zhulang.waveedu.edu.po.LessonClassCommonHomework;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 课程班级的普通作业表 Mapper 接口
 * </p>
 *
 * @author 狐狸半面添
 * @since 2023-02-27
 */
public interface LessonClassCommonHomeworkMapper extends BaseMapper<LessonClassCommonHomework> {

    /**
     *根据 作业id 查询对应的班级学生
     *
     * @param commonHomeworkId 作业id
     * @return 学生列表
     */
    List<Long> selectStuIdListByCommonHomeworkId(Integer commonHomeworkId);
}
