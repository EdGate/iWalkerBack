package com.play.service.impl;

import com.google.common.collect.Lists;
import com.play.common.Const;
import com.play.common.ServerResponse;
import com.play.dao.RelationMapper;
import com.play.dao.UserMapper;
import com.play.pojo.Image;
import com.play.pojo.Relation;
import com.play.pojo.User;
import com.play.service.IRelationService;
import com.play.util.ImageUtil;
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
        if(applicant.equals(receiver)){
            return  ServerResponse.createByErrorMessage("你不能加自己为好友");
        }
        Relation judgerelation=relationMapper.getfriend(applicant,receiver);
        if(judgerelation!=null&&judgerelation.getStatus()!=Const.RELATION_STATUS.REFUSE&&judgerelation.getStatus()!=Const.RELATION_STATUS.DELETE){
            if (judgerelation.getStatus() == 0) {
                if (judgerelation.getApplicant().equals(receiver)){
                    return ServerResponse.createBySuccessMessage("对方已向你提出申请，请在申请列表确认");
                }
                return ServerResponse.createBySuccessMessage("已提出好友请求");
            }
            return ServerResponse.createBySuccessMessage("已经是好友");
        }
        if(relationMapper.insertSelective(relation)==0){
            return ServerResponse.createByErrorMessage("好友请求申请失败/数据库操作失败");
        }
        return ServerResponse.createBySuccessMessage("好友请求申请成功");
    }

    @Override
    public ServerResponse<String> confirmfriend(String applicant,String receiver,Byte status){
        Relation relation = relationMapper.getfriend(applicant,receiver);
        if(relation==null){
            return ServerResponse.createByErrorMessage("操作失败");
        }
        if(relation.getStatus().equals(status)){
            return ServerResponse.createBySuccessMessage("操作成功");
        }
        relation.setStatus(status);
        if(relationMapper.updateByPrimaryKeySelective(relation)==0){
            return ServerResponse.createByErrorMessage("数据库失败");
        }

        return ServerResponse.createBySuccessMessage("操作成功");
    }
    @Override
    public ServerResponse<List<User>> showrequest(User user){
        List<Relation> requestlist= relationMapper.showrequest(user.getUserName());
        List<User> allrequest = Lists.newArrayList();
        if(requestlist==null){
            return ServerResponse.createBySuccessMessage("显示好友请求列表空");
        }
        for(Relation onerequestlist:requestlist){
            if(onerequestlist.getApplicant().equals(user.getUserName())){
                User test1=userMapper.showRequestUserbyRelation(onerequestlist.getReceiver());
                if(test1!=null){
                    test1.setPassword(null);
                    test1.setCreateTime(null);
                    test1.setModifyTime(null);
                    test1.setImage(ImageUtil.getFullImagePath(test1.getImage()));
                    allrequest.add(test1);
                }
            }else{
                User test2=userMapper.showRequestUserbyRelation(onerequestlist.getApplicant());
                if(test2!=null){
                    test2.setPassword(null);
                    test2.setCreateTime(null);
                    test2.setModifyTime(null);
                    test2.setImage(ImageUtil.getFullImagePath(test2.getImage()));
                    allrequest.add(test2);
                }
            }
        }
        return ServerResponse.createBySuccess("显示好友请求列表成功",allrequest);
    }

    @Override
    public ServerResponse<List<User>> showfriend(User user){
        List<Relation> friendname = relationMapper.showfriend(user.getUserName());
        if(friendname==null){
            return ServerResponse.createBySuccessMessage("无好友");
        }
        List<User> friendList = Lists.newArrayList();
        for(Relation onefriendname:friendname){
            User user1=userMapper.findUserByUsername(onefriendname.getApplicant());
            User user2=userMapper.findUserByUsername(onefriendname.getReceiver());
            user1.setPassword(null);
            user1.setCreateTime(null);
            user1.setModifyTime(null);
            user1.setExtra(null);
            user2.setPassword(null);
            user2.setCreateTime(null);
            user2.setModifyTime(null);
            user2.setExtra(null);
            if(onefriendname.getApplicant().equals(user.getUserName())){
                friendList.add(user2);
                user2.setImage(ImageUtil.getFullImagePath(user2.getImage()));
            }
            else if(onefriendname.getReceiver().equals(user.getUserName())){
                friendList.add(user1);
                user1.setImage(ImageUtil.getFullImagePath(user1.getImage()));
            }
        }
        return ServerResponse.createBySuccess("查询成功", friendList);
    }

    @Override
    public ServerResponse<String> delfriend(User user,String delname){
        if(relationMapper.deleteByUsername(user.getUserName(),delname)==0){
            return ServerResponse.createByErrorMessage("删除好友失败");
        }
        return ServerResponse.createBySuccessMessage("删除好友成功");
    }

}
