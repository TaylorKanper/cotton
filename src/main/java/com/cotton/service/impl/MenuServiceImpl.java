package com.cotton.service.impl;

import com.cotton.dao.IMenuDao;
import com.cotton.model.bean.MenuBean;
import com.cotton.model.bean.UserBean;
import com.cotton.model.common.Response;
import com.cotton.service.IMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hp on 2016/12/12.
 */
@Service
@Slf4j
public class MenuServiceImpl implements IMenuService {
    @Resource
    private IMenuDao menuDao;

    @Override
    public Response<List<MenuBean>> findMenuByUser(UserBean user) {
        try {
            boolean isTrue = false;
            if("王芮".equals(user.getUserName())||"康鹏".equals(user.getUserName())){
                isTrue = true;
            }
            List<MenuBean> list = menuDao.findAll(isTrue);
            return Response.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return Response.fail("未查询到菜单");
    }
}
