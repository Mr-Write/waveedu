package com.zhulang.waveedu.program.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhulang.waveedu.common.constant.HttpStatus;
import com.zhulang.waveedu.common.entity.Result;
import com.zhulang.waveedu.common.util.UserHolderUtils;
import com.zhulang.waveedu.program.constant.AuthorTypeConstants;
import com.zhulang.waveedu.program.dao.ProblemBankMapper;
import com.zhulang.waveedu.program.po.ProblemBank;
import com.zhulang.waveedu.program.service.ProblemBankService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhulang.waveedu.program.vo.ModifyProblemVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.zhulang.waveedu.program.constant.AuthorTypeConstants.ADMIN;
import static com.zhulang.waveedu.program.constant.AuthorTypeConstants.USER;

/**
 * <p>
 * 编程问题题库表 服务实现类
 * </p>
 *
 * @author 狐狸半面添
 * @since 2023-03-11
 */
@Service
public class ProblemBankServiceImpl extends ServiceImpl<ProblemBankMapper, ProblemBank> implements ProblemBankService {
    @Resource
    private ProblemBankMapper problemBankMapper;

    @Override
    public Result saveProblem(String title, Integer authorType) {
        // 1.校验格式
        if (StrUtil.isBlank(title)) {
            return Result.error(HttpStatus.HTTP_BAD_REQUEST.getCode(), "无效标题");
        }
        if (title.length() > 255) {
            return Result.error(HttpStatus.HTTP_BAD_REQUEST.getCode(), "标题最多255字");
        }
        // 2.设置信息
        ProblemBank problemBank = new ProblemBank();
        // 作者id
        problemBank.setAuthorId(UserHolderUtils.getUserId());
        // 作者类型
        problemBank.setAuthorType(authorType);
        // 设置标题
        problemBank.setTitle(title);

        // 3.保存信息到数据库
        problemBankMapper.insert(problemBank);
        // 4.返回题目信息
        return Result.ok(problemBankMapper.selectById(problemBank.getId()));
    }

    @Override
    public Result modifyProblem(ModifyProblemVO modifyProblemVO, Integer authorType) {
        // 0.校验格式
        if (modifyProblemVO.getTitle()!=null&&StrUtil.isBlank(modifyProblemVO.getTitle())){
            return Result.error(HttpStatus.HTTP_BAD_REQUEST.getCode(),"无效标题");
        }
        // 1.对象转换
        ProblemBank problemBank = BeanUtil.copyProperties(modifyProblemVO, ProblemBank.class);
        // 2.保存
        int updateCount = problemBankMapper.update(problemBank, new LambdaQueryWrapper<ProblemBank>()
                .eq(ProblemBank::getId, problemBank.getId())
                .eq(ProblemBank::getAuthorType, authorType)
                .eq(authorType == USER, ProblemBank::getAuthorId, UserHolderUtils.getUserId()));
        // 3.返回
        return updateCount != 0 ? Result.ok() : Result.error(HttpStatus.HTTP_INFO_NOT_EXIST.getCode(), "未找到问题信息");
    }
}
