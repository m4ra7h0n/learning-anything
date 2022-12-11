package com.xjjlearning.springframework.security;

import com.xjjlearning.springframework.security.entity.SysUser;
import com.xjjlearning.springframework.security.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Objects;

/**
 * 代理 {@link org.springframework.security.provisioning.UserDetailsManager} 所有功能
 *
 * @author dax
 */
public class UserDetailsRepository {

    @Autowired
    private SysUserService sysUserService;

    /**
     * Create user.
     *
     * @param user the user
     */
    public void createUser(UserDetails user) {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(user.getUsername());
        sysUser.setEncodePassword(user.getPassword());
        sysUser.setAge(18);
        //todo 自行排重
        sysUserService.addUser(sysUser);

    }


    /**
     * Update user.
     *
     * @param user the user
     */
    public void updateUser(UserDetails user) {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(user.getUsername());
        sysUser.setEncodePassword(user.getPassword());
        sysUser.setAge(18);

        sysUserService.updateUser(sysUser);
    }


    /**
     * Delete user.
     *
     * @param username the username
     */
    public void deleteUser(String username) {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);

        sysUserService.removeUser(sysUser);
    }


    /**
     * Change password.
     *
     * @param oldPassword the old password
     * @param newPassword the new password
     */
    public void changePassword(String oldPassword, String newPassword) {
        Authentication currentUser = SecurityContextHolder.getContext()
                .getAuthentication();

        if (currentUser == null) {
            // This would indicate bad coding somewhere
            throw new AccessDeniedException(
                    "Can't change password as no Authentication object found in context "
                            + "for current user.");
        }

        String username = currentUser.getName();

        UserDetails user = this.loadUserByUsername(username);


        if (user == null) {
            throw new IllegalStateException("Current user doesn't exist in database.");
        }

        // 实现具体的更新密码逻辑

        if (!Objects.equals(oldPassword, user.getPassword())) {
            throw new IllegalStateException("Current password doesn't  match the password provided");
        }

        SysUser sysUser = new SysUser();
        sysUser.setUsername(user.getUsername());
        sysUser.setEncodePassword(newPassword);

        sysUserService.updateUser(sysUser);

    }


    /**
     * User exists boolean.
     *
     * @param username the username
     * @return the boolean
     */
    public boolean userExists(String username) {
        return Objects.nonNull(this.sysUserService.queryByUsername(username));
    }


    /**
     * Load user by username user details.
     *
     * @param username the username
     * @return the user details
     * @throws UsernameNotFoundException the username not found exception
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.queryByUsername(username);

        if (Objects.nonNull(sysUser)) {
            return User.withUsername(username).password(sysUser.getEncodePassword())
                    .authorities(AuthorityUtils.NO_AUTHORITIES)
                    .build();
        }
        throw new UsernameNotFoundException("username: " + username + " notfound");
    }


}
