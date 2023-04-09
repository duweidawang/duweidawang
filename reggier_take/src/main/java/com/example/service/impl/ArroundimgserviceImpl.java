package com.example.service.impl;

import com.example.dao.ArroundimgDao;
import com.example.entity.ResponseResult;
import com.example.entity.faceimg;
import com.example.service.Arroundimgservice;
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

    /**
     * 实现周边的上传功能
     * 返回成功信息
     * @param text
     * @param list
     * @return
     */
    @Override
    public ResponseResult insertimgmes(String text, List list) {
        faceimg mes = new faceimg();
        mes.setMes(text);
        arroundimgDao.insertmes(mes);
        int id = mes.getId();
        for (Object url: list
             ) {
            arroundimgDao.insertimgurl((String) url,id);
        }
        return new ResponseResult(201,"上传成功","");


    }

    @Override
    public ResponseResult selectimg() {
        List<faceimg> list=arroundimgDao.selectimg();
        List<faceimg> returnlist=new ArrayList<>();
        int p=0;
        for(int i=0;i< list.size();i++){
            if(list.get(i).getId()!=p){
                String ss=list.get(i).getMes();
                if (ss.length()<20){
                    list.get(i).setMes(ss);
                }
                else {
                    list.get(i).setMes(ss.substring(0,20));
                }

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

    @Override
    public ResponseResult deletearround(int id) {
        arroundimgDao.deletearround(id);
        arroundimgDao.deletearrround1(id);
        return new ResponseResult(201,"删除成功");
    }
}
