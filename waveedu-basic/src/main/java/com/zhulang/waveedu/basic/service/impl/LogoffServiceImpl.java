package com.zhulang.waveedu.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhulang.waveedu.basic.dao.LogoffMapper;
import com.zhulang.waveedu.basic.po.Logoff;
import com.zhulang.waveedu.basic.query.LogoffRecordOverEndTimeQuery;
import com.zhulang.waveedu.basic.service.LogoffService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 狐狸半面添
 * @create 2023-01-19 14:00
 */
@Service
public class LogoffServiceImpl extends ServiceImpl<LogoffMapper, Logoff> implements LogoffService {

    @Resource
    private LogoffMapper logoffMapper;

    @Override
    public LogoffRecordOverEndTimeQuery getOneOfOverEndTime() {
        return logoffMapper.getOneOfOverEndTime();
    }
}
