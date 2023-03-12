package com.zhulang.waveedu.edu.controller.programhomework;


import com.zhulang.waveedu.common.entity.Result;
import com.zhulang.waveedu.edu.service.ProgramHomeworkProblemService;
import com.zhulang.waveedu.edu.vo.programhomeworkvo.SaveProblemVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 编程作业表的题目表 前端控制器
 * </p>
 *
 * @author 狐狸半面添
 * @since 2023-03-12
 */
@RestController
@RequestMapping("/program-homework-problem")
public class ProgramHomeworkProblemController {
    @Resource
    private ProgramHomeworkProblemService programHomeworkProblemService;

    /**
     * 保存一道题目
     *
     * @param saveProblemVO 作业id + 问题标题
     * @return 题目id
     */
    @PostMapping("/save")
    public Result saveProblem(@RequestBody @Validated SaveProblemVO saveProblemVO){
        return programHomeworkProblemService.saveProblem(saveProblemVO);
    }
}
