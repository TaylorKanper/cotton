package com.cotton.util;

import com.alibaba.fastjson.JSON;
import com.cotton.config.Constant;
import com.cotton.model.bean.MemberBean;
import com.cotton.model.dto.MessageTemple;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class MessageSender {
    /**
     * 发送短信,并返回发送成功的用户手机号
     *
     * @return
     */
    public static String sendMessage(MemberBean member) {
        TaobaoClient client = new DefaultTaobaoClient(Constant.TAOBAO_URL, "\n" +
                Constant.TAOBAO_APPKEY, Constant.TAOBAO_APPSECRET);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend(member.getMemberPhone());
        req.setSmsType(Constant.TAOBAO_MESSAGE_TYPE);
        req.setSmsFreeSignName(Constant.TAOBAO_MESSAGE_SIGNNAME);
        MessageTemple m = new MessageTemple();
        m.setName(member.getMemberName());
        m.setPhone(member.getMemberPhone());
        m.setMoney(member.getMemberScore().toString());
        req.setSmsParamString(JSON.toJSONString(m));
        req.setRecNum(member.getMemberPhone());
        req.setSmsTemplateCode(Constant.TAOBAO_MESSAGE_TEMPLATECODE_NEW_YEAR);
        AlibabaAliqinFcSmsNumSendResponse rsp = null;
        try {
            rsp = client.execute(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsp.getParams().get("sms_param");
    }
}
