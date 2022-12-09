package com.xjjlearning.database.mysql.service;

import com.xjjlearning.database.mysql.model.SysRole;
import com.xjjlearning.database.mysql.model.SysUser;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserService {
    List<SysUser> selectUsers();
    List<SysUser> selectUsers(SysUser sysUser);
    List<SysUser> selectUsers(List<Long> idList);

    SysUser selectUser(Long id);
    SysUser selectUser(SysUser sysUser);

    List<SysRole> selectRole(Long id, Integer enabled);
    List<SysRole> selectRole(Long userId);
    List<SysRole> selectRole(SysUser user, SysRole role);

    int insertUser(String name, String password, String email, String info, byte[] headImg, Date createTime);
    int insertUsers(List<SysUser> userList);

    int updateUser(SysUser sysUser);
    int updateUser(Map<String, Object> map);

    int deleteUser(Long id);
}
