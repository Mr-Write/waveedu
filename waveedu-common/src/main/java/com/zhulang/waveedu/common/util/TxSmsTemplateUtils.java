package com.zhulang.waveedu.common.util;


import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.zhulang.waveedu.common.constant.TxSmsConst;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 发送短信工具类，需要注入容器
 *
 * @author 狐狸半面添
 * @create 2023-01-16 20:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TxSmsTemplateUtils {
    /**
     * 应用的AppID
     */
    private int appId;
    /**
     * 应用的AppKey
     */
    private String appKey;
    /**
     * 签名的内容
     */
    private String signContent;
    /**
     * 登录&注册短信模板的id
     */
    private int loginTemplateId;

    /**
     * 发送登录&注册验证码
     *
     * @param phone 手机号
     * @param code  验证码
     * @return 是否成功
     */
    public boolean sendLoginCode(String phone, String code) {
        SmsSingleSenderResult result = null;
        try {
            // 短信的模板参数
            String[] params = {code, Integer.toString(TxSmsConst.LOGIN_EFFECTIVE_TIME)};
            // 构建短信发送器
            SmsSingleSender sender = new SmsSingleSender(appId, appKey);
            // 发送短信
            sender.sendWithParam(TxSmsConst.CHINA_NATION_CODE, phone, loginTemplateId, params, signContent
                    , "", "");
        } catch (Exception e) {
            return false;
        }

        return true;
    }

}
