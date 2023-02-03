package com.zhulang.waveedu.edu.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zhulang.waveedu.common.entity.Result;
import com.zhulang.waveedu.edu.po.Lesson;
import com.zhulang.waveedu.edu.vo.SaveLessonVO;

/**
 * 课程表 服务类
 *
 * @author 狐狸半面添
 * @create 2023-02-03 16:09
 */
public interface LessonService extends IService<Lesson> {

    /**
     * 创建课程
     *
     * @param saveLessonVO 课程信息
     * @return 情况
     */
    Result save(SaveLessonVO saveLessonVO);
}
