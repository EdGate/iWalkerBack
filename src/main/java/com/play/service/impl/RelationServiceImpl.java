package com.play.service.impl;

import com.play.common.ServerResponse;
import com.play.dao.RelationMapper;
import com.play.dao.UserMapper;
import com.play.pojo.Relation;
import com.play.pojo.User;
import com.play.service.IRelationService;
import com.play.service.IUserService;
import com.play.util.ImageUtil;
import com.play.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service("iRelationService")
public class RelationServiceImpl implements IRelationService {

    @Autowired
    private RelationMapper relationMapper;

    @Override
    public ServerResponse<String> addfriend(String applicant, String receiver){
        Relation relation=new Relation();
        relation.setApplicant(applicant);
        relation.setReceiver(receiver);
        byte default_status=0;
        relation.setStatus(default_status);
        int statu_num=relationMapper.getfriendStatus(applicant,receiver);
        if(statu_num!=0){
            return ServerResponse.createBySuccessMessage("好友请求申请失败");
        }
        relationMapper.insertSelective(relation);
        return ServerResponse.createBySuccessMessage("好友请求申请成功");
    }

    @Override
    public ServerResponse<String> confirmfriend(String applicant,String receiver,Byte status){
        Relation relation=new Relation();
        relation.setApplicant(applicant);
        relation.setReceiver(receiver);
        relation.setStatus(status);
        relationMapper.updateByPrimaryKeySelective(relation);
        return ServerResponse.createBySuccessMessage("好友请求确认");
    }
    @Override
    public ServerResponse<List<Relation>> showrequest(User user){
        List<Relation> requestlist= relationMapper.showrequest(user.getUserName());
        return ServerResponse.createBySuccess("显示好友请求列表成功",requestlist);
    }

    @Override
    public ServerResponse<List<User>> showfriend(User user){
        List<Relation> friendname = relationMapper.showfriend(user.getUserName());
        List<User> friendList =null;
        IUserService iUserService= new UserServiceImpl();
        for(int i=0;i<friendname.size();i++){
            if(friendname.get(i).getApplicant()==user.getUserName()){
                friendList.add(iUserService.findUserByUsername(friendname.get(i).getApplicant()));
            }
            else if(friendname.get(i).getReceiver()==user.getUserName()){
                friendList.add(iUserService.findUserByUsername(friendname.get(i).getReceiver()));
            }
        }

        return ServerResponse.createBySuccess("查询成功", friendList);
    }

    @Override
    public ServerResponse<String> delfriend(User user,String delname){
        relationMapper.deleteByUsername(user.getUserName(),delname);
        return ServerResponse.createBySuccessMessage("删除好友成功");
    }

}
