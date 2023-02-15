package com.zhulang.waveedu.edu.controller;


import com.alibaba.fastjson.JSONObject;
import com.zhulang.waveedu.common.entity.Result;
import com.zhulang.waveedu.edu.service.LessonTchService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 课程与教学团队的对应表 前端控制器
 * </p>
 *
 * @author 狐狸半面添
 * @since 2023-02-03
 */
@RestController
@RequestMapping("/lesson-tch")
public class LessonTchController {
    @Resource
    private LessonTchService lessonTchService;

    /**
     * 通过邀请码加入教师团队
     *
     * @param object 邀请码
     * @return 是否加入
     */
    @PostMapping("/joinTchTeam")
    public Result joinTchTeam(@RequestBody JSONObject object) {
        String encryptCode = object.getString("code");
        return lessonTchService.joinTchTeam(encryptCode);
    }

    /**
     * 获取某个课程的教学团队
     *
     * @param lessonId 课程id
     * @return 教学团队信息：用户id + 用户名 + 用户头像 + 用户所在单位，集合中首个元素是该课程创建者
     */
    @GetMapping("/get/TchTeam")
    public Result getTchTeam(@RequestParam("lessonId")Long lessonId){
        return lessonTchService.getTchTeam(lessonId);
    }


}
