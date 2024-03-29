package com.zhulang.waveedu.edu.controller.commonhomework;


import com.zhulang.waveedu.common.entity.Result;
import com.zhulang.waveedu.edu.service.CommonHomeworkQuestionService;
import com.zhulang.waveedu.edu.vo.commonhomeworkvo.BatchSaveCommonHomeworkQuestionVO;
import com.zhulang.waveedu.edu.vo.commonhomeworkvo.ModifyCommonHomeworkQuestionVO;
import com.zhulang.waveedu.edu.vo.commonhomeworkvo.SaveCommonHomeworkQuestionVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
     * 添加多个题目
     *
     * @param batchSaveCommonHomeworkQuestionVO 题目内容
     * @return 是否添加成功
     */
    @PostMapping("/batchSaveQuestion")
    public Result batchSaveQuestion(@Validated @RequestBody BatchSaveCommonHomeworkQuestionVO batchSaveCommonHomeworkQuestionVO) {
        return commonHomeworkQuestionService.batchSaveQuestion(batchSaveCommonHomeworkQuestionVO);
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
     * 删除多个题目
     * 只有未发布的作业才可以删除题目
     *
     * @param questionIds 多个题目Ids
     * @return 是否删除
     */
    @DeleteMapping("/batchDelQuestion")
    public Result delQuestion(@RequestParam("questionIds") List<Integer> questionIds) {
        return commonHomeworkQuestionService.batchDelQuestion(questionIds);
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
    @GetMapping("/get/tmp/homeworkTotalScore")
    public Result getTmpHomeworkTotalScore(@RequestParam("homeworkId") Integer homeworkId) {
        return commonHomeworkQuestionService.getTmpTotalScore(homeworkId);
    }


    /**
     * 创建者预览作业的所有题目
     * 0：主键，题目类型，问题描述，分值
     * 1：主键，题目类型，问题描述，分值，答案，解析
     *
     * @param homeworkId 作业Id
     * @param pattern 预览模式，0-普通预览（无答案与解析），1-详细预览（有答案与解析）
     * @return 题目列表
     */
    @GetMapping("/get/tch/homeworkQuestionListInfo")
    public Result getTchHomeworkQuestionListInfo(@RequestParam("homeworkId")Integer homeworkId,
                                                 @RequestParam("pattern")Integer pattern){
        return commonHomeworkQuestionService.getTchHomeworkQuestionListInfo(homeworkId,pattern);
    }

    /**
     * 班级学生获取作业的所有问题信息
     *
     * @param homeworkId 作业id
     * @return 根据学生对作业的状态进行返回
     */
    @GetMapping("/get/stu/homeworkQuestionListInfo")
    public Result getStuHomeworkQuestionListInfo(@RequestParam("homeworkId")Integer homeworkId){
        return commonHomeworkQuestionService.getStuHomeworkQuestionListInfo(homeworkId);
    }

}
