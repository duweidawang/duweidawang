package com.example.Service.impl;

import com.example.Dao.ArroundimgDao;
import com.example.Entity.ResponseResult;
import com.example.Entity.faceimg;
import com.example.Service.Arroundimgservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArroundimgserviceImpl implements Arroundimgservice {

    @Autowired
    ArroundimgDao arroundimgDao;


    @Override
    public ResponseResult insertimgmes(String mes, String[] url) {
        arroundimgDao.insertmes(mes);
        int id = arroundimgDao.selectimgid();
        for (int i = 0; i < url.length; i++) {
            arroundimgDao.insertimgurl(url[i], id);

        }

        return new ResponseResult(201, "添加成功", 0);

    }

    @Override
    public void insertimgurl(String imgurl) {
        int id = arroundimgDao.selectimgid()+1;
        System.out.println(id);

          arroundimgDao.insertimgurl(imgurl,id);



    }

    @Override
    public void insertmes(String mes) {
        arroundimgDao.insertmes(mes);

    }

    @Override
    public ResponseResult selectimg() {
        List<faceimg> list=arroundimgDao.selectimg();
        List<faceimg> returnlist=new ArrayList<>();
        int p=0;
        for(int i=0;i< list.size();i++){
            if(list.get(i).getId()!=p){
                String ss=list.get(i).getMes();
                list.get(i).setMes(ss.substring(0,20));
                returnlist.add(list.get(i));
                p=list.get(i).getId();
            }
        }

        return new ResponseResult(201,"sss",returnlist);
    }

    /**
     * 返回周边完整信息
     * @param id
     * @return
     */
    @Override
    public ResponseResult selectimdbyid(int id) {
        List<faceimg> list=arroundimgDao.selectimgbyid(id);
        List<Integer> arroundid=new ArrayList<>();
        arroundid.add(list.get(1).getId());

        List<String> mes=new ArrayList<>();
        mes.add(list.get(1).getMes());

        List<String> img=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            img.add(list.get(i).getUrl());
        }
        Map<String,List> map=new HashMap<>();
        map.put("id",arroundid);
        map.put("mes",mes);
        map.put("url",img);

        return new ResponseResult(201,"ok",map);
    }
}
