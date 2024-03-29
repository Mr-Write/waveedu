package com.zhulang.waveedu.edu.controller.lesson;


import com.alibaba.fastjson.JSONObject;
import com.zhulang.waveedu.common.entity.Result;
import com.zhulang.waveedu.edu.service.LessonChapterService;
import com.zhulang.waveedu.edu.vo.chaptervo.ModifyChapterNameVO;
import com.zhulang.waveedu.edu.vo.chaptervo.SaveChapterVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 课程章节表 前端控制器
 * </p>
 *
 * @author 狐狸半面添
 * @since 2023-02-15
 */
@RestController
@RequestMapping("/lesson-chapter")
public class LessonChapterController {
    @Resource
    private LessonChapterService lessonChapterService;

    /**
     * 创建新章节
     *
     * @param saveChapterVO 课程id + 章节名
     * @return 创建状况
     */
    @PostMapping("/save/chapter")
    public Result saveChapter(@Validated @RequestBody SaveChapterVO saveChapterVO){
        return lessonChapterService.saveChapter(saveChapterVO.getLessonId(),saveChapterVO.getName());
    }

    /**
     * 删除章节
     * 规则；必须先删除章节下的所有小节才能删除该章节
     *
     * @param chapterId 章节id
     * @return 删除结果
     */
    @DeleteMapping("/del/chapter")
    public Result delChapter(@RequestParam("chapterId") Integer chapterId){
            return lessonChapterService.removeChapter(chapterId);
    }

    /**
     * 修改章节的名字
     *
     * @param modifyChapterNameVO 章节id + 新的章节name
     * @return 修改状况
     */
    @PutMapping("/modify/chapterName")
    public Result modifySectionName(@Validated @RequestBody ModifyChapterNameVO modifyChapterNameVO){
        return lessonChapterService.modifyChapterName(modifyChapterNameVO.getChapterId(),modifyChapterNameVO.getChapterName());
    }

    /**
     * 获取全部章节和小节的名字信息列表
     *
     * @return 章节+小节 id与name信息
     */
    @GetMapping("/get/chapterSectionNameList")
    public Result getChapterSectionNameList(){
        return null;
    }
}
