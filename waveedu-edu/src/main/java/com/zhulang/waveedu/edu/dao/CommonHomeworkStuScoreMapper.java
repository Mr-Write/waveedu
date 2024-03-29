package com.zhulang.waveedu.edu.dao;

import com.zhulang.waveedu.edu.po.CommonHomeworkStuScore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhulang.waveedu.edu.query.commonhomeworkquery.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 普通作业表的学生成绩表 Mapper 接口
 * </p>
 *
 * @author 狐狸半面添
 * @since 2023-03-01
 */
public interface CommonHomeworkStuScoreMapper extends BaseMapper<CommonHomeworkStuScore> {

    /**
     * 获取未提交作业的学生信息
     *
     * @param homeworkId 作业id
     * @return id + name
     */
    List<HomeworkNoCommitStuInfoQuery> selectHomeworkNoCommitStuInfoListByHomeworkId(@Param("homeworkId") Integer homeworkId);

    /**
     * 获取该作业所有的学生信息
     *
     * @param homeworkId 作业id
     * @return id + name + 状态 + 分数 + 提交时间
     */
    List<HomeworkAnyStatusStuInfoQuery> selectHomeworkAllStuInfoListByHomeworkId(@Param("homeworkId") Integer homeworkId);

    /**
     * 获取该作业待批阅的学生的信息
     *
     * @param homeworkId 作业id
     * @return id + name + 提交时间
     */
    List<HomeworkNoCheckStuInfoQuery> selectHomeworkNoCheckStuInfoListByHomeworkId(@Param("homeworkId") Integer homeworkId);
    /**
     * 获取该作业已批阅的学生的信息
     *
     * @param homeworkId 作业id
     * @return id + name + 提交时间
     */
    List<HomeworkCheckedStuInfoQuery> selectCheckedStuInfoListByHomeworkId(@Param("homeworkId") Integer homeworkId);

    /**
     * 获取本班级所有未批阅的作业的信息
     *
     * @param classId 班级id
     * @param scoreId 更新时间
     * @param queryLimit 查询限制数量
     * @return 信息列表
     */
    List<HomeworkNoCheckTaskInfoQuery> selectHomeworksNoCheckTaskInfoList(@Param("classId") Long classId,
                                                                          @Param("scoreId") Integer scoreId,
                                                                          @Param("queryLimit")Integer queryLimit);

    /**
     * 根据作业id和学生id获取该学生本次作业的总分数
     *
     * @param homeworkId 作业id
     * @param stuId 学生id
     * @return 学生总分数
     */
    Integer selectScoreByHomeworkIdAndStuId(@Param("homeworkId") Integer homeworkId,@Param("stuId") Long stuId);

    /**
     * 根据 作业Id 和 学生id 得到该学生对于作业的状态
     *
     * @param homeworkId 作业id
     * @param stuId      学生id
     * @return 对作业的状态：null-未提交，0-批阅中，1-已提交
     */
    Integer selectStatusByHomeworkIdAndStuId(@Param("homeworkId") Integer homeworkId, @Param("stuId") Long stuId);

    /**
     * 修改学生的分数,评语,状态
     *
     * @param homeworkId 作业id
     * @param stuId 学生id
     * @param comment 教师评论
     */
    void updateScoreAndCommentAndStatus(@Param("homeworkId") Integer homeworkId,
                                        @Param("stuId")Long stuId,
                                        @Param("comment") String comment);
}
