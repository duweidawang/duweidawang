package com.example.service.impl;

import com.example.dao.BokeDao;
import com.example.entity.LoginUser;
import com.example.entity.ResponseResult;

import com.example.entity.User;
import com.example.entity.selectboke;
import com.example.service.Bokeservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BokeserviceImpl implements Bokeservice{


    @Autowired
    BokeDao bokeDao;


    /**
     * 动态的插入
     * @param text
     * @param time
     * @param list
     * @return
     */
    @Override
    public ResponseResult insertboke(String text,String time,List list) {
//        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        long userId  = loginUser.getUser().getId();
        long userId=1;
        Integer selectmax = bokeDao.selectmax((int) userId);
        if(selectmax==null){
            bokeDao.insertboke((int)userId,time,text,1);
            for (Object url:list
                 ) {
                bokeDao.insertimg((String) url,(int)userId,1);
            }
        }else {
            selectmax++;
            bokeDao.insertboke((int)userId,time,text,selectmax);
            for (Object url: list
                 ) {
                bokeDao.insertimg((String) url,(int)userId,selectmax);
            }
        }
        return new ResponseResult(201,"插入成功",1);


    }

    /**
     * 返回动态信息
     * @return
     */
    @Override
    public ResponseResult returnboke() {
        List<selectboke> selectboke = bokeDao.selectboke();
        selectboke.remove(0);



        for (int i = 0; i < selectboke.size(); i++) {
            Integer userid = selectboke.get(i).getUserid();
            List<User> selectheadurl = bokeDao.selectheadurl(userid);
            selectboke.get(i).setHeadurl(selectheadurl.get(0).getImg());
            selectboke.get(i).setUsername(selectheadurl.get(0).getUsername());
            List list=new ArrayList();
            Integer urlid = selectboke.get(i).getUrlid();
            List<String> selecturl = bokeDao.selecturl(userid, urlid);
            selectboke.get(i).setUrllist(selecturl);

        }
        Collections.reverse(selectboke);

        return new ResponseResult(201,"查询成功",selectboke);
    }

    /**
     * 更新头像
     * @param url
     * @return
     */
    @Override
    public ResponseResult updataheadimg(String url) {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId  = loginUser.getUser().getId();
        bokeDao.updataheadimg(url,(int)userId);
        return new ResponseResult(201,"更新成功",1);
    }
    /**
     * 插入动态评论
     * @param selectboke
     * @return
     */
    @Override
    public ResponseResult insertbokecontent(selectboke selectboke) {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId  = loginUser.getUser().getId();
        bokeDao.insertbokecontent(selectboke,(int)userId);
        return new ResponseResult(201,"插入成功",1);
    }
    /**
     * 获得bokecontent
     * @param selectboke
     * @return
     */
    @Override
    public ResponseResult getbokecontent(selectboke selectboke) {
        List<com.example.entity.selectboke> selectbokecontent = bokeDao.selectbokecontent(selectboke);
        Collections.reverse(selectbokecontent);
        return new ResponseResult(201,"成功",selectbokecontent);
    }
}
