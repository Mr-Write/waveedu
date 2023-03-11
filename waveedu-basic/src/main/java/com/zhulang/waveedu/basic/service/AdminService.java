package com.zhulang.waveedu.basic.service;

import com.zhulang.waveedu.basic.po.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhulang.waveedu.common.entity.Result;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author 狐狸半面添
 * @since 2023-03-11
 */
public interface AdminService extends IService<Admin> {

    /**
     * 管理员登录
     *
     * @param username 账号
     * @param password 密码
     * @return 登录状况，成功则返回token
     */
    Result login(String username, String password);
}