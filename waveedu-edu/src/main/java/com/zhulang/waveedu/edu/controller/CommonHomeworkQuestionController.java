package com.zhulang.waveedu.edu.controller;


import com.zhulang.waveedu.common.entity.Result;
import com.zhulang.waveedu.edu.service.CommonHomeworkQuestionService;
import com.zhulang.waveedu.edu.vo.homework.ModifyCommonHomeworkQuestionVO;
import com.zhulang.waveedu.edu.vo.homework.SaveCommonHomeworkQuestionVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 普通作业表的题目表 前端控制器
 * </p>
 *
 * @author 狐狸半面添
 * @since 2023-02-28
 */
@RestController
@RequestMapping("/common-homework-question")
public class CommonHomeworkQuestionController {
    @Resource
    private CommonHomeworkQuestionService commonHomeworkQuestionService;

    /**
     * 添加一个题目
     *
     * @param saveCommonHomeworkQuestionVO 题目内容
     * @return 题目id
     */
    @PostMapping("/saveQuestion")
    public Result saveQuestion(@Validated @RequestBody SaveCommonHomeworkQuestionVO saveCommonHomeworkQuestionVO) {
        return commonHomeworkQuestionService.saveQuestion(saveCommonHomeworkQuestionVO);
    }

    /**
     * 删除一个题目
     * 只有未发布的作业才可以删除题目
     *
     * @param questionId 题目Id
     * @return 是否删除
     */
    @DeleteMapping("/delQuestion")
    public Result delQuestion(@RequestParam("questionId") Integer questionId) {
        return commonHomeworkQuestionService.delQuestion(questionId);
    }

    /**
     * 修改一个题目
     *
     * @param modifyCommonHomeworkQuestionVO 修改后的题目内容
     * @return 修改状况
     */
    @PutMapping("/modifyQuestion")
    public Result modifyQuestion(@Validated @RequestBody ModifyCommonHomeworkQuestionVO modifyCommonHomeworkQuestionVO) {
        return commonHomeworkQuestionService.modifyQuestion(modifyCommonHomeworkQuestionVO);
    }

    /**
     * 查询作业的总分数
     *
     * @param homeworkId 作业id
     * @return 总分
     */
    @GetMapping("/get/homeworkTotalScore")
    public Result getHomeworkTotalScore(@RequestParam("homeworkId") Integer homeworkId) {
        return commonHomeworkQuestionService.getHomeworkTotalScore(homeworkId);
    }
}
