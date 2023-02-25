package com.zhulang.waveedu.edu.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.zhulang.waveedu.common.constant.HttpStatus;
import com.zhulang.waveedu.common.entity.Result;
import com.zhulang.waveedu.common.util.CipherUtils;
import com.zhulang.waveedu.common.util.UserHolderUtils;
import com.zhulang.waveedu.edu.dto.LessonClassFileDTO;
import com.zhulang.waveedu.edu.dto.LessonFileDTO;
import com.zhulang.waveedu.edu.po.LessonClassFile;
import com.zhulang.waveedu.edu.dao.LessonClassFileMapper;
import com.zhulang.waveedu.edu.service.LessonClassFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhulang.waveedu.edu.service.LessonClassService;
import com.zhulang.waveedu.edu.vo.classvo.SaveClassFileVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 课程班级资料表 服务实现类
 * </p>
 *
 * @author 狐狸半面添
 * @since 2023-02-25
 */
@Service
public class LessonClassFileServiceImpl extends ServiceImpl<LessonClassFileMapper, LessonClassFile> implements LessonClassFileService {
    @Resource
    private LessonClassFileMapper lessonClassFileMapper;

    @Resource
    private LessonClassService lessonClassService;

    @Override
    public Result saveFile(SaveClassFileVO saveClassFileVO) {
        // 1.判断是否为该班级的创建者
        if(!lessonClassService.existsByUserIdAndClassId(saveClassFileVO.getUserId(), saveClassFileVO.getLessonClassId())){
            return Result.error(HttpStatus.HTTP_FORBIDDEN.getCode(),HttpStatus.HTTP_FORBIDDEN.getValue());
        }
        // 2.解密文件信息
        LessonClassFile lessonClassFile;
        try {
            lessonClassFile = JSON.parseObject(CipherUtils.decrypt(saveClassFileVO.getFileInfo()), LessonClassFile.class);
            if (lessonClassFile==null){
                return Result.error(HttpStatus.HTTP_BAD_REQUEST.getCode(),"错误的文件信息");
            }
        }catch (Exception e){
            return Result.error(HttpStatus.HTTP_BAD_REQUEST.getCode(),"错误的文件信息");
        }
        // 3.封装信息
        BeanUtils.copyProperties(saveClassFileVO,lessonClassFile);
        // 4.简单处理，前后无空格
        lessonClassFile.setFileName(lessonClassFile.getFileName().trim());
        lessonClassFile.setDownloadCount(0);
        // 5.保存到数据库
        lessonClassFileMapper.insert(lessonClassFile);
        // 5.转换到dto中
        LessonClassFileDTO lessonClassFileDTO = BeanUtil.copyProperties(lessonClassFile, LessonClassFileDTO.class);
        lessonClassFileDTO.setUserName(UserHolderUtils.getUserName());
        // 6.返回信息
        return Result.ok(lessonClassFileDTO);
    }
}
