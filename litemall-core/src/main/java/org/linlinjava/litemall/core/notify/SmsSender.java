package org.linlinjava.litemall.core.notify;

/**
 * 通知提醒功能，支持邮件通知、短信通知和微信通知。
 */
public interface SmsSender {

    /**
     * 发送短信息
     *
     * @param phone   接收通知的电话号码
     * @param content 短消息内容
     */
    SmsResult send(String phone, String content);


    /**
     * 通过短信模版发送短信息
     *
     * @param phone      接收通知的电话号码
     * @param templateId 通知模板ID
     * @param params     通知模版内容里的参数，类似"您的验证码为{1}"中{1}的值
     */
    SmsResult sendWithTemplate(String phone, int templateId, String[] params);
}