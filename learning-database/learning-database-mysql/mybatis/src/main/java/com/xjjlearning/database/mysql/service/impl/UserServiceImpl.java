package com.xjjlearning.database.mysql.service.impl;

import com.xjjlearning.database.mysql.mapper.UserMapper;
import com.xjjlearning.database.mysql.model.SysRole;
import com.xjjlearning.database.mysql.model.SysUser;
import com.xjjlearning.database.mysql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<SysUser> selectUsers() {
//        return userMapper.selectAll();
        return userMapper.selectAllUserAndRoles(); //1 to many
    }
    @Override
    public List<SysUser> selectUsers(SysUser sysUser) {
        return userMapper.selectByUser(sysUser);
    }

    @Override
    public List<SysUser> selectUsers(List<Long> idList) {
        return userMapper.selectByIdList(idList);
    }

    @Override
    public SysUser selectUser(Long id) {
//        return userMapper.selectById(id); //没有Role的映射
        return userMapper.selectUserAndRoleById(id); //注意使用1个角色的id
    }

    @Override
    public SysUser selectUser(SysUser sysUser) {
        return userMapper.selectByIdOrUserName(sysUser);
    }


    @Override
    public List<SysRole> selectRole(Long userId, Integer enabled) {
        return userMapper.selectRolesByUserIdAndRoleEnabled(userId, enabled);
    }

    @Override
    public List<SysRole> selectRole(Long userId) {
        return userMapper.selectRolesByUserId(userId);
    }

    @Override
    public List<SysRole> selectRole(SysUser user, SysRole role) {
        return userMapper.selectRolesByUserAndRole(user, role);
    }


    @Override
    public int insertUser(String name, String password, String email, String info, byte[] headImg, Date createTime) {
        SysUser sysUser = new SysUser();
        try {
            sysUser.setUserName(name);
            sysUser.setUserInfo(info);
            sysUser.setUserEmail(email);
            sysUser.setUserPassword(password);
            sysUser.setHeadImg(headImg);
            sysUser.setCreateTime(createTime);
            return userMapper.insert(sysUser);
        } finally {
            //xml --> useGeneratedKeys="true" keyProperty="id"
            System.out.println(sysUser.getId());
        }
    }

    @Override
    public int insertUsers(List<SysUser> userList) {
        return userMapper.insertList(userList);
    }

    @Override
    public int updateUser(SysUser sysUser) {
        return userMapper.updateByIdSelective(sysUser);
    }

    @Override
    public int updateUser(Map<String, Object> map) {
        return userMapper.updateByMap(map);
    }

    @Override
    public int deleteUser(Long id) {
        return userMapper.deleteUserById(id);
    }
}
