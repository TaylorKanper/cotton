package com.cotton.test;

import com.cotton.model.bean.MemberBean;
import com.cotton.model.common.Response;
import com.cotton.service.IMemberService;
import com.cotton.util.MessageSender;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.List;


@RunWith(BlockJUnit4ClassRunner.class)
public class Test extends UnitTestBase {
    public Test() {
        super("classpath:spring/applicationContext-base.xml");
    }



    @org.junit.Test
    public void testUnit() {
        SendMessage();
    }

    private void SendMessage() {
        IMemberService service = super.getBean("memberServiceImpl");
        Response res = service.findAll(274,560);
        List<MemberBean> list = (List<MemberBean>) res.getResult();
        Iterator<MemberBean> it = list.iterator();
        while (it.hasNext()){
            MemberBean m = it.next();
            m.setMemberName(m.getMemberName());
            m.setMemberPhone(m.getMemberPhone());
            m.setMemberScore(10);
            MessageSender.sendMessage(m);
        }


//
    }
}
