package com.zhulang.waveedu.judge.judge.task;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.zhulang.waveedu.judge.exception.SystemError;
import com.zhulang.waveedu.judge.judge.AbstractJudge;
import com.zhulang.waveedu.judge.judge.SandboxRun;
import com.zhulang.waveedu.judge.judge.entity.JudgeDTO;
import com.zhulang.waveedu.judge.judge.entity.JudgeGlobalDTO;
import com.zhulang.waveedu.judge.judge.entity.LanguageConfig;
import com.zhulang.waveedu.judge.judge.entity.SandBoxRes;
import com.zhulang.waveedu.judge.util.Constants;
import com.zhulang.waveedu.judge.util.JudgeUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;


import java.nio.charset.StandardCharsets;

/**
 * @Author: Himit_ZH
 * @Date: 2022/1/2 21:18
 * @Description: 普通评测
 */
@Component
public class DefaultJudge extends AbstractJudge {
    @Override
    public JSONArray judgeCase(JudgeDTO judgeDTO, JudgeGlobalDTO judgeGlobalDTO) throws SystemError {
        // 语言配置信息
        LanguageConfig runConfig = judgeGlobalDTO.getRunConfig();
        // 调用安全沙箱使用测试点对程序进行测试
        /*
            返回：
                0. status -> 0
                1. exitStatus -> 0
                2. time -> 88361171
                3. memory -> 11141120
                4. runTime -> 102205702
                5. files ->   stderr->""
                              stdout->5
                6. originalStatus -> Accepted
         */
        return SandboxRun.testCase(
                JudgeUtils.translateCommandline(runConfig.getRunCommand()),
                runConfig.getRunEnvs(),
                // 输入文件的绝对路径
                judgeDTO.getTestCaseInputPath(),
                // null
                judgeDTO.getTestCaseInputContent(),
                // 最大时间:2200
                judgeGlobalDTO.getTestTime(),
                // 最大占用内存：18
                judgeGlobalDTO.getMaxMemory(),
                // 最大输出大小：33554432
                judgeDTO.getMaxOutputSize(),
                // 最大栈大小：128
                judgeGlobalDTO.getMaxStack(),
                // Main.jar
                runConfig.getExeName(),
                // 编译文件id
                judgeGlobalDTO.getUserFileId(),
                // 用户代码
                judgeGlobalDTO.getUserFileContent());
    }

    @Override
    public JSONObject checkResult(SandBoxRes sandBoxRes, JudgeDTO judgeDTO, JudgeGlobalDTO judgeGlobalDTO) {

        JSONObject result = new JSONObject();

        StringBuilder errMsg = new StringBuilder();
        // 如果测试跑题无异常
        if (sandBoxRes.getStatus().equals(Constants.Judge.STATUS_ACCEPTED.getStatus())) {

            // 对结果的时间损耗和空间损耗与题目限制做比较，判断是否mle和tle
            if (sandBoxRes.getTime() > judgeGlobalDTO.getMaxTime()) {
                result.set("status", Constants.Judge.STATUS_TIME_LIMIT_EXCEEDED.getStatus());
                result.set("errMsg","Time Limit Exceeded");
            } else if (sandBoxRes.getMemory() > judgeGlobalDTO.getMaxMemory() * 1024) {
                result.set("status", Constants.Judge.STATUS_MEMORY_LIMIT_EXCEEDED.getStatus());
                result.set("errMsg","Memory Limit Exceeded");
            } else {
                // 与原测试数据输出的md5进行对比 AC（通过）或者是WA（答案错误）
                JSONObject testcaseInfo = (JSONObject) ((JSONArray) judgeGlobalDTO.getTestCaseInfo().get("testCases")).get(judgeDTO.getTestCaseNum() - 1);
                result.set("status", compareOutput(sandBoxRes.getStdout(), judgeGlobalDTO.getRemoveEolBlank(), testcaseInfo));
            }
        } else if (sandBoxRes.getStatus().equals(Constants.Judge.STATUS_TIME_LIMIT_EXCEEDED.getStatus())) {
            // 超出了 语言配置的最大时间限制
            result.set("status", Constants.Judge.STATUS_TIME_LIMIT_EXCEEDED.getStatus());
        } else if (sandBoxRes.getExitCode() != 0) {
            // 单个程序运行退出码错误
            result.set("status", Constants.Judge.STATUS_RUNTIME_ERROR.getStatus());
            if (sandBoxRes.getExitCode() < 32) {
                errMsg.append(String.format("The program return exit status code: %s (%s)\n", sandBoxRes.getExitCode(), SandboxRun.signals.get(sandBoxRes.getExitCode())));
            } else {
                errMsg.append(String.format("The program return exit status code: %s\n", sandBoxRes.getExitCode()));
            }
        } else {
            result.set("status", sandBoxRes.getStatus());
            // 输出超限的特别提示
            if ("Output Limit Exceeded".equals(sandBoxRes.getOriginalStatus())) {
                errMsg.append("The output character length of the program exceeds the limit");
            }
        }

        // 用户代码运行的内存（b）
        result.set("memory", sandBoxRes.getMemory());
        // 用户代码运行的时间（ms）
        result.set("time", sandBoxRes.getTime());

        // 记录该测试点的错误信息
        if (!StringUtils.isEmpty(errMsg.toString())) {
            String str = errMsg.toString();
            result.set("errMsg", str.substring(0, Math.min(1024 * 1024, str.length())));
        }
        // 如果正确，则包含运行时间，消耗内存，状态为0
        return result;
    }


    // 根据评测结果与用户程序输出的字符串MD5进行对比
    private Integer compareOutput(String userOutput, Boolean isRemoveEolBlank, JSONObject testcaseInfo) {

        // 如果当前题目选择默认去掉字符串末位空格
        if (isRemoveEolBlank) {
            String userOutputMd5 = DigestUtils.md5DigestAsHex(rtrim(userOutput).getBytes(StandardCharsets.UTF_8));
            if (userOutputMd5.equals(testcaseInfo.getStr("EOFStrippedOutputMd5"))) {
                return Constants.Judge.STATUS_ACCEPTED.getStatus();
            } else {
                return Constants.Judge.STATUS_WRONG_ANSWER.getStatus();
            }
        } else { // 不选择默认去掉文末空格 与原数据进行对比
            String userOutputMd5 = DigestUtils.md5DigestAsHex(userOutput.getBytes(StandardCharsets.UTF_8));
            if (userOutputMd5.equals(testcaseInfo.getStr("outputMd5"))) {
                return Constants.Judge.STATUS_ACCEPTED.getStatus();
            }
        }
        // 如果不AC（通过）,进行PE（格式）判断，否则为WA（答案错误）
        String userOutputMd5 = DigestUtils.md5DigestAsHex(userOutput.replaceAll("\\s+", "").getBytes(StandardCharsets.UTF_8));
        if (userOutputMd5.equals(testcaseInfo.getStr("allStrippedOutputMd5"))) {
            return Constants.Judge.STATUS_PRESENTATION_ERROR.getStatus();
        } else {
            return Constants.Judge.STATUS_WRONG_ANSWER.getStatus();
        }
    }


}