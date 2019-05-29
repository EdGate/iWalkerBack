package com.play.service;

import com.play.common.ServerResponse;
import com.play.pojo.Relation;
import com.play.pojo.User;

import java.util.List;

public interface IRelationService {
    ServerResponse<String> addfriend(String applicant, String receiver);
    ServerResponse<String> confirmfriend(String applicant,String receiver,Byte status);
    ServerResponse<List<Relation>> showrequest(User user);
    ServerResponse<List<User>> showfriend(User user);
    ServerResponse<String> delfriend(User user,String delname);
}
