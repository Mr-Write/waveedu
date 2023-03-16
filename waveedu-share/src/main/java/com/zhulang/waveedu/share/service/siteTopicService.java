package com.zhulang.waveedu.share.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhulang.waveedu.common.entity.Result;
import com.zhulang.waveedu.share.po.site;
import com.zhulang.waveedu.share.po.siteApply;
import com.zhulang.waveedu.share.po.siteTopic;

/**
 * @author 飒沓流星
 * @create 2023/3/12 11:29
 */
public interface siteTopicService extends IService<siteTopic> {
    /**
     * 添加站点主题
     * @return 添加结果
     */
    Result addSiteTopic(siteTopic siteTopic);

    /**
     * 通过id删站点主题
     * @return 删除结果
     */
    Result removeSiteTopicById(Long id);

    /**
     * 通过id查询站点主题
     * @param id
     * @return 查询结果
     */
    Result getSiteTopicById(Long id);

    /**
     * 修改站点主题
     * @param siteTopic
     * @return 修改结果
     */
    Result modifySiteTopicById(siteTopic siteTopic);

    Result getSiteTopicall();
    Result getTypeByTopicid(Long id);
    Result getTypeAndSiteByTopicid(Long id);
}
