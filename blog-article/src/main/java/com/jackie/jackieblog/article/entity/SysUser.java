package com.jackie.jackieblog.article.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @作者 Jackie Wang
 * @微信号 ilovepython12138
 * @GitHub https://github.com/wangjm12138
 * @博客 http://www.jackieblog.top
 * @date 2023年03月18日 20:28
 */

//public class SysUser implements UserDetails {
public class SysUser {
//public class SysUser {
    //    @TableId(type = IdType.ASSIGN_ID) // 默认id类型
    // 以后 用户多了之后，要进行分表操作，id就需要用分布式id了
    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;

    private Boolean enabled;

    private Boolean locked;

    private String avatar;

    @TableField("create_date")
    private Long createDate;

    private Integer deleted;

    private String email;

    @TableField("last_login")
    private Long lastLogin;

    @TableField("mobile_phone_number")
    private String mobilePhoneNumber;

    private String nickname;

    @TableField(exist = false)
    private List<SysRole> roles;

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        for (SysRole role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//        }
//
//        return authorities;
//    }

//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return !locked;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return enabled;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    @Override
//    public String getUsername() {
//        return username;
//    }

    public void setUsername(String username) {
        this.username = username;
    }

//    @Override
//    public String getPassword() {
//        return password;
//    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }


    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Long lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
