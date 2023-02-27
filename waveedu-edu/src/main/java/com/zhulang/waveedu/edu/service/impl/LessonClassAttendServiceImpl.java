package com.zhulang.waveedu.edu.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.zhulang.waveedu.common.constant.HttpStatus;
import com.zhulang.waveedu.common.entity.Result;
import com.zhulang.waveedu.common.util.UserHolderUtils;
import com.zhulang.waveedu.edu.po.LessonClassAttend;
import com.zhulang.waveedu.edu.dao.LessonClassAttendMapper;
import com.zhulang.waveedu.edu.service.LessonClassAttendService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhulang.waveedu.edu.service.LessonClassService;
import com.zhulang.waveedu.edu.vo.classvo.SaveClassAttendVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 课程班级的上课时间表 服务实现类
 * </p>
 *
 * @author 狐狸半面添
 * @since 2023-02-27
 */
@Service
public class LessonClassAttendServiceImpl extends ServiceImpl<LessonClassAttendMapper, LessonClassAttend> implements LessonClassAttendService {
    @Resource
    private LessonClassAttendMapper lessonClassAttendMapper;
    @Resource
    private LessonClassService lessonClassService;

    @Override
    public Result saveOne(SaveClassAttendVO saveClassAttendVO) {
        // 1.校验是否为课程创建者
        if (!lessonClassService.existsByUserIdAndClassId(UserHolderUtils.getUserId(),saveClassAttendVO.getLessonClassId())) {
            return Result.error(HttpStatus.HTTP_FORBIDDEN.getCode(), HttpStatus.HTTP_FORBIDDEN.getValue());
        }
        // 2.取出课程名的前后空格
        saveClassAttendVO.setLessonName(saveClassAttendVO.getLessonName().trim());
        // 3.对象属性转换
        LessonClassAttend lessonClassAttend = BeanUtil.copyProperties(saveClassAttendVO, LessonClassAttend.class);
        try {
            // 4.保存
            lessonClassAttendMapper.insert(lessonClassAttend);
            // 5.返回信息Id
            return Result.ok(lessonClassAttend.getId());
        } catch (Exception e) {
            return Result.error(HttpStatus.HTTP_REPEAT_SUCCESS_OPERATE.getCode(), "此时间段已存在上课安排，请勿重复操作");
        }
    }
}
