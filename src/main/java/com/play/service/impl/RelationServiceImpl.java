package com.play.service.impl;

import com.google.common.collect.Lists;
import com.play.common.ServerResponse;
import com.play.dao.RelationMapper;
import com.play.dao.UserMapper;
import com.play.pojo.Relation;
import com.play.pojo.User;
import com.play.service.IRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iRelationService")
public class RelationServiceImpl implements IRelationService {

    @Autowired
    private RelationMapper relationMapper;
    @Autowired
    private  UserMapper userMapper;

    @Override
    public ServerResponse<String> addfriend(String applicant, String receiver){
        Relation relation=new Relation();
        relation.setApplicant(applicant);
        relation.setReceiver(receiver);
        relation.setCreateTime(null);
        relation.setModifyTime(null);
        byte default_status=0;
        relation.setStatus(default_status);
        int statu_num=relationMapper.getfriendStatus(applicant,receiver);
        if(statu_num!=0){
            return ServerResponse.createBySuccessMessage("好友请求申请失败");
        }
        if(relationMapper.insertSelective(relation)==0){

            return ServerResponse.createBySuccessMessage("好友请求申请失败/数据库操作失败");
        }
        return ServerResponse.createBySuccessMessage("好友请求申请成功");
    }

    @Override
    public ServerResponse<String> confirmfriend(String applicant,String receiver,Byte status){
        Relation relation=new Relation();
        relation.setApplicant(applicant);
        relation.setReceiver(receiver);
        relation.setCreateTime(null);
        relation.setModifyTime(null);
        relation.setStatus(status);
        if(relationMapper.updateByPrimaryKeySelective(relation)==0){
            return ServerResponse.createBySuccessMessage("好友请求申请失败/数据库操作失败");
        }
        return ServerResponse.createBySuccessMessage("好友请求确认");
    }
    @Override
    public ServerResponse<List<Relation>> showrequest(User user){
        List<Relation> requestlist= relationMapper.showrequest(user.getUserName());
        if(requestlist==null){
            return ServerResponse.createBySuccessMessage("显示好友请求列表失败");
        }
        return ServerResponse.createBySuccess("显示好友请求列表成功",requestlist);
    }

    @Override
    public ServerResponse<List<User>> showfriend(User user){
        List<Relation> friendname = relationMapper.showfriend(user.getUserName());
        if(friendname==null){
            return ServerResponse.createBySuccessMessage("显示好友失败--关系数据库获取失败");
        }
        List<User> friendList = Lists.newArrayList();
        for(Relation onefriendname:friendname){
            if(userMapper.findUserByUsername(onefriendname.getApplicant())==null){
                return ServerResponse.createBySuccessMessage("显示好友失败");
            }else if(userMapper.findUserByUsername(onefriendname.getReceiver())==null){
                return ServerResponse.createBySuccessMessage("显示好友失败");
            }
            if(onefriendname.getApplicant().equals(user.getUserName())){
                friendList.add(userMapper.findUserByUsername(onefriendname.getApplicant()));
            }
            else if(onefriendname.getReceiver().equals(user.getUserName())){
                friendList.add(userMapper.findUserByUsername(onefriendname.getReceiver()));
            }
        }
        return ServerResponse.createBySuccess("查询成功", friendList);
    }

    @Override
    public ServerResponse<String> delfriend(User user,String delname){
        if(relationMapper.deleteByUsername(user.getUserName(),delname)==0){
            return ServerResponse.createBySuccessMessage("删除好友失败");
        }
        return ServerResponse.createBySuccessMessage("删除好友成功");
    }

}
