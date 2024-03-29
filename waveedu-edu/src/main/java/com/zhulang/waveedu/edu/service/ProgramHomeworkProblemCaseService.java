package com.zhulang.waveedu.edu.service;

import com.zhulang.waveedu.common.entity.Result;
import com.zhulang.waveedu.edu.po.ProgramHomeworkProblemCase;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhulang.waveedu.edu.query.programhomeworkquery.ProblemCaseInfoQuery;
import com.zhulang.waveedu.edu.vo.programhomeworkvo.ModifyCaseVO;
import com.zhulang.waveedu.edu.vo.programhomeworkvo.SaveCaseVO;

import java.util.List;

/**
 * <p>
 * 编程作业问题测试实例表 服务类
 * </p>
 *
 * @author 狐狸半面添
 * @since 2023-03-13
 */
public interface ProgramHomeworkProblemCaseService extends IService<ProgramHomeworkProblemCase> {

    /**
     * 添加测试案例
     *
     * @param saveCaseVO 案例信息
     * @return 案例id
     */
    Result saveCase(SaveCaseVO saveCaseVO);

    /**
     * 删除测试案例
     *
     * @param caseId 案例Id
     * @return 删除状况
     */
    Result removeCase(Integer caseId);

    /**
     * 校验身份与发布状况
     *
     * @param caseId 案例id
     * @param creatorId 创建者id
     * @return null-校验成功，未发布。
     */
    Result verifyIdentityHomeworkStatus(Integer caseId,Long creatorId);

    /**
     * 修改测试案例
     *
     * @param modifyCaseVO 信息
     * @return 修改状况
     */
    Result modifyCase(ModifyCaseVO modifyCaseVO);

    /**
     * 获取问题的案例信息
     *
     * @param problemId 问题id
     * @return 信息列表
     */
    List<ProblemCaseInfoQuery> getProblemCaseList(Integer problemId);
}
