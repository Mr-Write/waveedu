package com.zhulang.waveedu.edu.dao;

import com.zhulang.waveedu.edu.po.LessonClassFile;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhulang.waveedu.edu.query.classquery.LessonClassFileInfoQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程班级资料表 Mapper 接口
 * </p>
 *
 * @author 狐狸半面添
 * @since 2023-02-25
 */
public interface LessonClassFileMapper extends BaseMapper<LessonClassFile> {

    /**
     * 根据 文件Id 获取 文件所属班级
     *
     * @param id 文件id
     * @return 班级Id
     */
    Long selectLessonClassIdById(@Param("id") Long id);

//    /**
//     * 获取班级文件信息
//     *
//     * @param lessonClassId   课程班级id
//     * @param fileId     文件id
//     * @param queryLimit 查询最大条数
//     * @return 文件列表信息：文件id + 文件名 + 文件类型 + 文件格式 + 文件大小 + 上传的时间 + 上传者id与名字 + 下载次数，按照时间由近到远排序
//     */
//    List<LessonClassFileInfoQuery> selectInfoList(@Param("lessonClassId") Long lessonClassId,
//                                                  @Param("fileId") Long fileId,
//                                                  @Param("queryLimit") Integer queryLimit);
    /**
     * 获取班级文件信息
     *
     * @param lessonClassId   课程班级id
     * @return 文件列表信息：文件id + 文件名 + 文件类型 + 文件格式 + 文件大小 + 上传的时间 + 上传者id与名字 + 下载次数，按照时间由近到远排序
     */
    List<LessonClassFileInfoQuery> selectInfoList(@Param("lessonClassId") Long lessonClassId);


    /**
     * 增加一次下载次数
     *
     * @param id 班级文件id
     */
    void updateDownloadCountOfInsertOne(Long id);

    /**
     * 根据 文件id 查询 文件路径 和 下载次数
     *
     * @param id 文件id
     * @return 文件路径和下载次数
     */
    @SuppressWarnings("MybatisXMapperMethodInspection")
    Map<String,Object> selectFilePathAndDownLoadCount(@Param("id") Long id);
}
