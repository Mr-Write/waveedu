package com.zhulang.waveedu.edu.service;

import com.zhulang.waveedu.common.entity.Result;
import com.zhulang.waveedu.edu.po.ProgramHomeworkProblem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhulang.waveedu.edu.vo.programhomeworkvo.ModifyProblemVO;
import com.zhulang.waveedu.edu.vo.programhomeworkvo.SaveProblemVO;

/**
 * <p>
 * 编程作业表的题目表 服务类
 * </p>
 *
 * @author 狐狸半面添
 * @since 2023-03-12
 */
public interface ProgramHomeworkProblemService extends IService<ProgramHomeworkProblem> {

    /**
     * 保存一道题目
     *
     * @param saveProblemVO 作业id + 问题标题
     * @return 题目id
     */
    Result saveProblem(SaveProblemVO saveProblemVO);

    /**
     * 修改问题
     *
     * @param modifyProblemVO 问题信息
     * @return 修改状况
     */
    Result modifyProblem(ModifyProblemVO modifyProblemVO);

    /**
     * 校验身份与发布状况
     *
     * @param problemId 问题id
     * @param creatorId 创建者id
     * @return null-校验成功，未发布。
     */
    Result verifyIdentityHomeworkStatus(Integer problemId,Long creatorId);

    /**
     * 删除题目
     *
     * @param problemId 题目id
     * @return 删除状况
     */
    Result removeProblem(Integer problemId);

    /**
     * 作业创建者获取作业问题列表
     *
     * @param homeworkId 作业id
     * @return 列表信息
     */
    Result tchGetHomeworkProblemList(Integer homeworkId);

    /**
     * 学生获取问题的详细信息
     *
     * @param problemId 问题id
     * @return 详细信息
     */
    Result stuGetHomeworkProblemDetailInfo(Integer problemId);

    /**
     * 教师获取问题的详细信息
     *
     * @param problemId 问题id
     * @return 详细信息
     */
    Result tchGetHomeworkProblemDetailInfo(Integer problemId);

    /**
     * 查询有测试案例的问题个数
     *
     * @param homeworkId 作业id
     * @return 问题个数
     */
    Integer getProblemCountHaveCase(Integer homeworkId);
}
