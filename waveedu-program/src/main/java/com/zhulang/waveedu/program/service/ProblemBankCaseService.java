package com.zhulang.waveedu.program.service;

import com.zhulang.waveedu.common.entity.Result;
import com.zhulang.waveedu.program.po.ProblemBankCase;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhulang.waveedu.program.query.ProblemCaseInfoQuery;
import com.zhulang.waveedu.program.vo.ModifyCaseVO;
import com.zhulang.waveedu.program.vo.SaveProblemCaseVO;

import java.util.List;

/**
 * <p>
 * 编程问题题库测试实例表 服务类
 * </p>
 *
 * @author 狐狸半面添
 * @since 2023-03-12
 */
public interface ProblemBankCaseService extends IService<ProblemBankCase> {

    /**
     * 添加测试案例
     *
     * @param saveProblemCaseVO 案例信息
     * @param authorType 作者身份
     * @return 案例id
     */
    Result saveCase(SaveProblemCaseVO saveProblemCaseVO, Integer authorType);

    /**
     * 删除测试案例
     *
     * @param caseId 案例id
     * @param authorType 作者身份
     * @return 删除状况
     */
    Result removeCase(Integer caseId, int authorType);

    /**
     * 修改测试案例
     *
     * @param modifyCaseVO 案例信息
     * @param authorType 作者身份
     * @return 修改状况
     */
    Result modifyCase(ModifyCaseVO modifyCaseVO, int authorType);

    /**
     * 获取实例测试信息
     *
     * @param problemId 问题id
     * @return 信息列表
     */
    List<ProblemCaseInfoQuery> getProblemCaseInfoByProblemId(Integer problemId);
}
