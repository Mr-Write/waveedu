package com.zhulang.waveedu.edu.service;

import com.zhulang.waveedu.common.entity.Result;
import com.zhulang.waveedu.edu.po.ProgramHomeworkProblemCase;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhulang.waveedu.edu.vo.programhomeworkvo.SaveCaseVO;

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
}
