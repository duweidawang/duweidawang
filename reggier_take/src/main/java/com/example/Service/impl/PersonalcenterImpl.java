package com.example.Service.impl;

import com.example.Dao.BokeDao;
import com.example.Dao.Userdao;
import com.example.Entity.LoginUser;
import com.example.Entity.ResponseResult;
import com.example.Entity.User;
import com.example.Entity.selectboke;
import com.example.Service.Personalcenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PersonalcenterImpl implements Personalcenter {
    @Autowired
    private Userdao userdao;
    @Autowired
    private BokeDao bokeDao;


    /**
     * 返回个人中心的个人信息
     * @param
     * @return
     */
    @Override
    public ResponseResult getuser() {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId  = loginUser.getUser().getId();
        User selectuser = userdao.selectuser(userId);
        ResponseResult responseResult=new ResponseResult(201,"成功",selectuser);
        return responseResult;

    }

    @Override
    public ResponseResult getotheruser(int id) {
        User selectuser = userdao.selectuser((id));
        ResponseResult responseResult=new ResponseResult(201,"成功",selectuser);
        return responseResult;

    }

    @Override
    public ResponseResult attention(int id) {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId  = loginUser.getUser().getId();
        userdao.atention(id,(int)userId);
        return new ResponseResult(201,"关注成功","");
    }

    @Override
    public ResponseResult notattention(int id) {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId  = loginUser.getUser().getId();
        userdao.notattention(id,(int)userId);
        return new ResponseResult(201,"取消成功","");



    }


    /**
     * 获取别人头像与是否关注
     * @param id
     * @return
     */

    @Override
    public ResponseResult getotherimg(int id) {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId  = loginUser.getUser().getId();
        String gethead = userdao.gethead(id);
        Map<String,String> map=new HashMap<>();
        map.put("gethead",gethead);
        int ifattention = userdao.ifattention(id, (int) userId);
        if(ifattention!=0){
            map.put("attention","true");
        }else{
            map.put("attention","false");
        }
        return new ResponseResult(200,"成功",map);

    }

    /**
     * 更改个人信息
     * @param user
     * @return
     */

    @Override
    public ResponseResult updatemessage(User user) {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId  = loginUser.getUser().getId();
        user.setId(userId);
        boolean updatemessage = userdao.updatemessage(user);
        return new ResponseResult(201,"成功","ok");

    }

    @Override
    public ResponseResult getbokebyid(int id) {
        List<selectboke> selectbokebyid = bokeDao.selectbokebyid(id);
        List<User> selectheadurl = bokeDao.selectheadurl(id);
        for (selectboke s :selectbokebyid) {
            s.setHeadurl(selectheadurl.get(0).getImg());
            s.setUsername(selectheadurl.get(0).getUsername());

        }
        return new ResponseResult(201,"c",selectbokebyid);

    }


}
