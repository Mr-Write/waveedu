package com.zhulang.waveedu.edu.dao;

import com.zhulang.waveedu.edu.po.CommonHomeworkQuestion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhulang.waveedu.edu.query.TchHomeworkQuestionDetailInfo;
import com.zhulang.waveedu.edu.query.TchHomeworkQuestionSimpleInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 普通作业表的题目表 Mapper 接口
 * </p>
 *
 * @author 狐狸半面添
 * @since 2023-02-28
 */
public interface CommonHomeworkQuestionMapper extends BaseMapper<CommonHomeworkQuestion> {

    /**
     * 根据题目查看作业的发布状况以及创建者
     *
     * @param id 问题id
     * @return 作业情况
     */
    @SuppressWarnings("MybatisXMapperMethodInspection")
    Map<String,Object> selectHomeworkIsPublishAndCreatorIdById(@Param("id") Integer id);

    /**
     * 根据题目查看作业的发布状况，创建者，作业类型
     *
     * @param id 问题id
     * @return 作业情况
     */
    @SuppressWarnings("MybatisXMapperMethodInspection")
    Map<String,Object> selectHomeworkIsPublishAndCreatorIdAndTypeById(@Param("id") Integer id);
    /**
     * 根据 作业id 查询到该作业的总分数
     *
     * @param commonHomeworkId 作业id
     * @return 作业总分数
     */
    Integer selectTotalScoreByCommonHomeworkId(@Param("commonHomeworkId") Integer commonHomeworkId);

    /**
     * 根据id查询作业问题普通信息列表
     *
     * @param homeworkId 作业Id
     * @return 主键，题目类型，问题描述，分值
     */
     List<TchHomeworkQuestionSimpleInfo> selectTchHomeworkQuestionSimpleInfoList(@Param("homeworkId") Integer homeworkId);

    /**
     * 根据id查询作业问题普通信息列表
     *
     * @param homeworkId 作业Id
     * @return 主键，题目类型，问题描述，分值
     */
    List<TchHomeworkQuestionDetailInfo> selectTchHomeworkQuestionDetailInfoList(@Param("homeworkId") Integer homeworkId);
}
