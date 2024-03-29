package com.zhulang.waveedu.edu.dao;

import com.zhulang.waveedu.edu.po.ProgramHomeworkProblem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhulang.waveedu.edu.query.programhomeworkquery.ProgramHomeworkProblemQuery;
import com.zhulang.waveedu.edu.query.programhomeworkquery.StuHomeworkProblemDetailInfoQuery;
import com.zhulang.waveedu.edu.query.programhomeworkquery.TchSimpleHomeworkProblemInfoQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 编程作业表的题目表 Mapper 接口
 * </p>
 *
 * @author 狐狸半面添
 * @since 2023-03-12
 */
public interface ProgramHomeworkProblemMapper extends BaseMapper<ProgramHomeworkProblem> {

    /**
     * 根据 问题id 与 创建者查询 所属作业的状态
     *
     * @param problemId 问题id
     * @param creatorId 创建者id
     * @return 0-未发布，1-已发布，2-发布中
     */
    Integer selectIsPublishByProblemIdAndCreatorId(@Param("problemId") Integer problemId, @Param("creatorId") Long creatorId);

    /**
     * 查询作业的问题列表
     *
     * @param homeworkId 作业id
     * @return 问题列表
     */
    List<TchSimpleHomeworkProblemInfoQuery> selectTchSimpleHomeworkProblemInfoList(@Param("homeworkId") Integer homeworkId);

    /**
     * 查询作业id
     *
     * @param problemId 问题id
     * @return 作业id
     */
    Integer selectHomeworkIdByProblemId(@Param("problemId") Integer problemId);


    /**
     * 学生获取问题的详细信息
     *
     * @param problemId 问题id
     * @param stuId 学生id
     * @return 详细信息
     */
    StuHomeworkProblemDetailInfoQuery selectStuHomeworkProblemDetailInfo(@Param("problemId") Integer problemId,
                                                                         @Param("stuId") Long stuId);

    /**
     * 查询问题的详细信息
     *
     * @param problemId 问题id
     * @return 信息
     */
    ProgramHomeworkProblemQuery selectProblemInfoById(@Param("problemId") Integer problemId);

    /**
     * 查询有测试案例的问题个数
     *
     * @param homeworkId 作业id
     * @return 问题个数
     */
    Integer selectProblemCountHaveCase(@Param("homeworkId") Integer homeworkId);
}
