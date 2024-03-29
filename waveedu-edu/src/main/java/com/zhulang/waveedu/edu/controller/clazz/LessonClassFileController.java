package com.zhulang.waveedu.edu.controller.clazz;


import com.alibaba.fastjson.JSONObject;
import com.zhulang.waveedu.common.constant.HttpStatus;
import com.zhulang.waveedu.common.entity.Result;
import com.zhulang.waveedu.common.util.UserHolderUtils;
import com.zhulang.waveedu.edu.service.LessonClassFileService;
import com.zhulang.waveedu.edu.vo.classvo.SaveClassFileVO;
import com.zhulang.waveedu.edu.vo.ModifyFileNameVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 课程班级资料表 前端控制器
 * </p>
 *
 * @author 狐狸半面添
 * @since 2023-02-25
 */
@RestController
@RequestMapping("/lesson-class-file")
public class LessonClassFileController {
    @Resource
    private LessonClassFileService lessonClassFileService;

    /**
     * 上传/保存 班级资料
     *
     * @param saveClassFileVO 班级id + 文件名 + 文件信息
     * @return 资料信息
     */
    @PostMapping("/saveFile")
    public Result saveFile(@Validated @RequestBody SaveClassFileVO saveClassFileVO) {
        saveClassFileVO.setUserId(UserHolderUtils.getUserId());
        return lessonClassFileService.saveFile(saveClassFileVO);
    }

    /**
     * 删除班级的资料
     *
     * @param lessonClassFileId 班级资料id
     * @return 删除状况
     */
    @DeleteMapping("/delFile")
    public Result delFile(@RequestParam("lessonClassFileId") Long lessonClassFileId) {
        return lessonClassFileService.removeFile(lessonClassFileId);
    }

    /**
     * 修改资料的文件名
     *
     * @param modifyFileNameVO 文件Id + 文件名
     * @return 修改状况
     */
    @PutMapping("/modify/fileName")
    public Result modifyFileName(@Validated @RequestBody ModifyFileNameVO modifyFileNameVO) {
        return lessonClassFileService.modifyFileName(modifyFileNameVO.getFileId(), modifyFileNameVO.getFileName());
    }

//    /**
//     * 获取班级文件信息
//     *
//     * @param lessonClassId 班级id
//     * @param fileId        文件id
//     * @return 文件列表信息：文件id + 文件名 + 文件类型 + 文件格式 + 文件大小 + 上传的时间 + 上传者id与名字 + 下载次数，按照时间由近到远排序
//     */
//    @GetMapping("/get/infoList")
//    public Result getInfoList(
//            @RequestParam(value = "lessonClassId") Long lessonClassId,
//            @RequestParam(value = "fileId", required = false) Long fileId
//    ) {
//        return lessonClassFileService.getInfoList(lessonClassId, fileId);
//    }

    /**
     * 获取班级文件信息
     *
     * @param lessonClassId 班级id
     * @return 文件列表信息：文件id + 文件名 + 文件类型 + 文件格式 + 文件大小 + 上传的时间 + 上传者id与名字 + 下载次数，按照时间由近到远排序
     */
    @GetMapping("/get/infoList")
    public Result getInfoList(
            @RequestParam(value = "lessonClassId") Long lessonClassId
    ) {
        return lessonClassFileService.getInfoList(lessonClassId);
    }

    /**
     * 用于下载班级文件
     * 获取文件路径并增加一次下载次数
     * 只允许班级创建者与班级成员下载
     *
     * @param object 班级文件id
     * @return 新的下载次数 + 文件路径
     */
    @PostMapping("/download/lessonClassFile")
    public Result downloadLessonFile(@RequestBody JSONObject object) {
        try {
            return lessonClassFileService.downloadLessonFile(Long.parseLong(object.getString("lessonClassFileId")));
        } catch (NumberFormatException e) {
            return Result.error(HttpStatus.HTTP_BAD_REQUEST.getCode(), "班级文件id格式错误");
        }
    }
}
