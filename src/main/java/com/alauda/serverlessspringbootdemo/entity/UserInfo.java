package com.alauda.serverlessspringbootdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName UserInfo
 * @Description TODO
 * @Author zhuangxinjie
 * @Date 10:44 上午 2021/12/4
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String road;
    private String sex;
    private String tel;
    private String email;

//    public String toString(){
//        return "{'name':'"+name+"','road':'"+road+"','sex':'"+sex+"','tel':'"+tel+"','email':'"+email+"'}";
//    }

}
