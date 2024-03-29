package com.zhulang.waveedu.edu.service;

import com.zhulang.waveedu.common.entity.Result;
import com.zhulang.waveedu.edu.po.LessonClassStu;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程班级与学生的对应关系表 服务类
 * </p>
 *
 * @author 狐狸半面添
 * @since 2023-02-17
 */
public interface LessonClassStuService extends IService<LessonClassStu> {

    /**
     * 通过邀请码加入班级
     *
     * @param classId 班级Id
     * @param inviteCode 邀请码
     * @return 加入成功，返回班级Id
     */
    Result joinLessonClass(Long classId, String inviteCode);

    /**
     * 获取所有加入的课程班级的信息
     * 按照加入时间由近及远排序
     *
     * @param userId 用户id
     * @return 班级id + 班级名 + 是否结课 + 课程id + 课程名 + 课程封面
     */
    Result getJoinClassInfoList(Long userId);

    /**
     * 判断是否为该课程的班级普通成员
     *
     * @param lessonId 课程id
     * @param userId 用户id
     * @return true-说明是
     */
    boolean existsByLessonIdAndUserId(Long lessonId, Long userId);

    /**
     * 查询该用户是否为该班级的普通成员
     *
     * @param classId 班级Id
     * @param userId 用户id
     * @return null-说明不是
     */
    boolean existsByClassIdAndUserId(Long classId,Long userId);


    /**
     * 删除学生
     *
     * @param classId 班级Id
     * @param stuId 学生id
     * @return 删除状况
     */
    Result delStu(Long classId,Long stuId);

    /**
     * 退出班级
     *
     * @param classId 班级id
     * @return 退出情况
     */
    Result delSelfExit(Long classId);

    /**
     * 获取班级的学生信息列表，只有创建者与班级成员可获取
     *
     * @param classId 班级id
     * @return 信息列表
     */
    Result getStuInfoList(Long classId);

    /**
     * 用户退出班级：
     * 1.删除对应表记录信息
     * 2.班级表的人数-1
     *
     * @param classId 班级id
     * @param userId 用户id
     */
    void exitClass(Long classId, Long userId) ;

    /**
     * 用户加入班级：
     * 1.对应表添加记录
     * 2.班级表的人数+1
     *
     * @param lessonClassStu 课程id + 班级id + 用户id
     */
    void joinClass(LessonClassStu lessonClassStu);
}
