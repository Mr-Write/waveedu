package com.zhulang.waveedu.basic.controller;

import com.zhulang.waveedu.basic.po.UserInfo;
import com.zhulang.waveedu.basic.service.UserInfoService;
import com.zhulang.waveedu.basic.service.UserService;
import com.zhulang.waveedu.common.entity.Result;
import com.zhulang.waveedu.common.util.UserHolderUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 飒沓流星
 * @create 2023/1/28 22:38
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {
    @Resource
    private UserInfoService userInfoService;

    /**
     * 通过id获取本用户信息
     * @return 用户信息
     */
    @GetMapping("/get/self")
    public Result getUserInfo(){
        return userInfoService.GetUserInfoById(UserHolderUtils.getUserId());
    }

    /**
     * 通过name来获取其他用户的信息
     * @param name
     * @return 用户信息
     */
    @GetMapping("/get/name")
    public Result getUserInfoByName(@RequestParam("name") String name){
        Result R = userInfoService.getIdByName(name);
        return userInfoService.GetUserInfoById((Long)R.getData());
    }

    /**
     * 修改本用户信息
     * @param userInfo
     * @return 修改结果
     */
    @PostMapping("/modify")
    public Result modifyUserInfoByUserInfo(@RequestBody UserInfo userInfo){
        // 拿到本用户的id，加进去
        userInfo.setId(UserHolderUtils.getUserId());

        return userInfoService.modifyUserInfoByUserInfo(userInfo);
    }
}
