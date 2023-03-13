package com.zhulang.waveedu.edu.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.zhulang.waveedu.common.constant.HttpStatus;
import com.zhulang.waveedu.common.entity.Result;
import com.zhulang.waveedu.common.util.RegexUtils;
import com.zhulang.waveedu.common.util.UserHolderUtils;
import com.zhulang.waveedu.edu.po.LessonClassProgramHomework;
import com.zhulang.waveedu.edu.dao.LessonClassProgramHomeworkMapper;
import com.zhulang.waveedu.edu.po.ProgramHomeworkProblem;
import com.zhulang.waveedu.edu.po.ProgramHomeworkProblemCase;
import com.zhulang.waveedu.edu.query.programhomeworkquery.TchSimpleHomeworkInfoQuery;
import com.zhulang.waveedu.edu.service.LessonClassProgramHomeworkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhulang.waveedu.edu.service.LessonClassService;
import com.zhulang.waveedu.edu.service.ProgramHomeworkProblemCaseService;
import com.zhulang.waveedu.edu.service.ProgramHomeworkProblemService;
import com.zhulang.waveedu.edu.vo.programhomeworkvo.ModifyProgramHomeworkVO;
import com.zhulang.waveedu.edu.vo.programhomeworkvo.SaveProgramHomeworkVO;
import jdk.net.SocketFlow;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 编程作业表 服务实现类
 * </p>
 *
 * @author 狐狸半面添
 * @since 2023-03-12
 */
@Service
public class LessonClassProgramHomeworkServiceImpl extends ServiceImpl<LessonClassProgramHomeworkMapper, LessonClassProgramHomework> implements LessonClassProgramHomeworkService {
    @Resource
    private LessonClassService lessonClassService;
    @Resource
    private LessonClassProgramHomeworkMapper lessonClassProgramHomeworkMapper;
    @Resource
    private ProgramHomeworkProblemService programHomeworkProblemService;
    @Resource
    private ProgramHomeworkProblemCaseService programHomeworkProblemCaseService;

    @Override
    public Result saveHomework(SaveProgramHomeworkVO saveProgramHomeworkVO) {
        Long userId = UserHolderUtils.getUserId();
        // 1.校验身份
        if (!lessonClassService.existsByUserIdAndClassId(userId, saveProgramHomeworkVO.getClassId())) {
            return Result.error(HttpStatus.HTTP_FORBIDDEN.getCode(), HttpStatus.HTTP_FORBIDDEN.getValue());
        }
        // 2.设置属性
        LessonClassProgramHomework homework = new LessonClassProgramHomework();
        homework.setClassId(saveProgramHomeworkVO.getClassId());
        homework.setCreatorId(userId);
        homework.setTitle(saveProgramHomeworkVO.getTitle());
        // 3.保存
        lessonClassProgramHomeworkMapper.insert(homework);
        // 4.返回作业Id
        return Result.ok(homework.getId());
    }

    @Override
    public Result modifyInfo(ModifyProgramHomeworkVO modifyProgramHomeworkVO) {
        // 1.校验标题
        if (modifyProgramHomeworkVO.getTitle() != null && StrUtil.isBlank(modifyProgramHomeworkVO.getTitle())) {
            return Result.error(HttpStatus.HTTP_BAD_REQUEST.getCode(), "无效标题");
        }
        // 2.修改信息
        lessonClassProgramHomeworkMapper.update(null, new LambdaUpdateWrapper<LessonClassProgramHomework>()
                .eq(LessonClassProgramHomework::getId, modifyProgramHomeworkVO.getHomeworkId())
                .eq(LessonClassProgramHomework::getCreatorId, UserHolderUtils.getUserId())
                .set(modifyProgramHomeworkVO.getTitle() != null, LessonClassProgramHomework::getTitle, modifyProgramHomeworkVO.getTitle())
                .set(modifyProgramHomeworkVO.getEndTime() != null, LessonClassProgramHomework::getEndTime, modifyProgramHomeworkVO.getEndTime()));
        // 3.返回
        return Result.ok();
    }

    @Override
    public Integer getIsPublishByHomeworkIdAndCreatorId(Integer homeworkId, Long creatorId) {
        return lessonClassProgramHomeworkMapper.selectIsPublishByHomeworkIdAndCreatorId(homeworkId, creatorId);
    }

    @Override
    public void updateNumById(Integer homeworkId) {
        lessonClassProgramHomeworkMapper.updateNumById(homeworkId);
    }

    @Override
//    @Transactional(rollbackFor = Exception.class)
    public Result removeHomework(Integer homeworkId) {
        // 1.校验权限
        if (!existsByHomeworkIdAndCreatorId(homeworkId, UserHolderUtils.getUserId())) {
            return Result.error(HttpStatus.HTTP_FORBIDDEN.getCode(), HttpStatus.HTTP_FORBIDDEN.getValue());
        }
        // 2.删除作业
        lessonClassProgramHomeworkMapper.deleteById(homeworkId);
        // 3.返回
        return Result.ok();

//        programHomeworkProblemService.remove(new LambdaQueryWrapper<ProgramHomeworkProblem>()
//                .eq(ProgramHomeworkProblem::getHomeworkId,homeworkId));
//        programHomeworkProblemCaseService.remove(new LambdaQueryWrapper<ProgramHomeworkProblemCase>()
//                .eq(ProgramHomeworkProblemCase::getHomeworkId,homeworkId));

    }

    @Override
    public boolean existsByHomeworkIdAndCreatorId(Integer homeworkId, Long creatorId) {
        return lessonClassProgramHomeworkMapper.existsByHomeworkIdAndCreatorId(homeworkId, creatorId) != null;
    }

    @Override
    public Result tchGetHomeworkInfoList(Long classId, Integer status) {
        // 1.校验数据格式
        if (RegexUtils.isSnowIdInvalid(classId)) {
            return Result.error(HttpStatus.HTTP_BAD_REQUEST.getCode(), "班级id格式错误");
        }
        if (status != null && (status < 0 || status > 3)) {
            return Result.error(HttpStatus.HTTP_BAD_REQUEST.getCode(), "作业状态格式错误");
        }
        // 2.校验身份
        if (!lessonClassService.existsByUserIdAndClassId(UserHolderUtils.getUserId(), classId)) {
            return Result.error(HttpStatus.HTTP_FORBIDDEN.getCode(), HttpStatus.HTTP_FORBIDDEN.getValue());
        }
        // 3.获取列表信息
        List<TchSimpleHomeworkInfoQuery> infoList;
        if (status == null) {
            infoList = lessonClassProgramHomeworkMapper.selectTchHomeworkInfoList(classId, status);
            infoList.forEach(info -> {
                if (info.getStatus() == 1 && LocalDateTime.now().isAfter(info.getEndTime())) {
                    info.setStatus(3);
                }
            });
        } else if (status == 3) {
            infoList = lessonClassProgramHomeworkMapper.selectTchHomeworkInfoList(classId, 1);
            LocalDateTime now = LocalDateTime.now();
            infoList = infoList.stream().filter(info -> now.isAfter(info.getEndTime())).collect(Collectors.toList());
            infoList.forEach(info -> {
                info.setStatus(3);
            });
        } else {
            infoList = lessonClassProgramHomeworkMapper.selectTchHomeworkInfoList(classId, status);
        }
        return Result.ok(infoList);
    }

    @Override
    public Result tchGetHomeworkDetailInfo(Integer homeworkId) {
        // 1.校验格式
        if (homeworkId<1000){
            return Result.error(HttpStatus.HTTP_BAD_REQUEST.getCode(), "作业id格式错误");
        }
        // 2.校验身份
        if (!existsByHomeworkIdAndCreatorId(homeworkId,UserHolderUtils.getUserId())) {
            return Result.error(HttpStatus.HTTP_FORBIDDEN.getCode(), HttpStatus.HTTP_FORBIDDEN.getValue());
        }
        // 3.获取信息
        LessonClassProgramHomework homework = lessonClassProgramHomeworkMapper.selectById(homeworkId);
        if (homework==null){
            return Result.error(HttpStatus.HTTP_INFO_NOT_EXIST.getCode(),"作业不存在");
        }
        // 4.判断状态
        if (homework.getIsPublish()==1){
            if (homework.getEndTime().isBefore(LocalDateTime.now())){
                homework.setIsPublish(3);
            }
        }
        // 5.返回
        return Result.ok(homework);
    }
}
