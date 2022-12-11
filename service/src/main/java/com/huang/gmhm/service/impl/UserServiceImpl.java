package com.huang.gmhm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.gmhm.mapper.UserMapper;
import com.huang.gmhm.model.User;
import com.huang.gmhm.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * 用户登录 ， 判断手机号是否存在 与 密码是否正确 ， 并判断是否为管理员
     *
     * @param phoneNumber 手机号即账号
     * @param password    密码
     * @return Map 存放用户对象及登录错误原因
     */
    @Override
    public Map<String, Object> login(String phoneNumber, String password) {
        //最终返回对象
        Map<String, Object> result = new HashMap<>();
        //创造查询条件
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("phone_number", phoneNumber);
        //查询
        User selectOne = baseMapper.selectOne(wrapper);
        if (selectOne == null) {
            //若selectOne不存在则说明手机号未注册
            result.put("user", null);
            result.put("error", "phoneNumberError");
        } else {
            if (!selectOne.getPassword().equals(password)) {
                //密码错误
                result.put("user", null);
                result.put("error", "passwordError");
            } else {
                //登录成功
                result.put("user", selectOne);
                result.put("error", null);
            }
        }
        return result;
    }

    /**
     * 用户注册
     *
     * @param userName    用户姓名
     * @param phoneNumber 手机号码
     * @param password    密码
     * @param sex         性别
     * @return Map 返回 user对象 以及 错误原因
     */
    @Override
    public Map<String, Object> register(String userName, String phoneNumber, String password, Integer sex) {
        //最终返回对象
        Map<String, Object> result = new HashMap<>();

        //创造条件对象
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("phone_number", phoneNumber);
        //查询用户是否已注册
        User selectOne = baseMapper.selectOne(wrapper);
        if (selectOne != null) {
            //手机号已注册
            result.put("user", null);
            result.put("error", "phoneNumberExistError");
        } else {
            //创建用户对象并加入数据库中
            User user = new User();
            //默认为普通用户 ， 由于数据库中添加用户会默认设置为普通用户 ， 所以无需特别设置
            user.setId(UUID.randomUUID().toString());
            user.setUserName(userName);
            user.setPhoneNumber(phoneNumber);
            user.setPassword(password);
            user.setSex(sex);
            baseMapper.insert(user);
            result.put("user", user);
            result.put("error", null);
        }
        return result;
    }

    /**
     * 根据用户ID号查询用户信息
     *
     * @param userId 用户ID号码
     * @return user  查询所得的用户对象
     */
    @Override
    public User getUserById(String userId) {
        User user = baseMapper.selectById(userId);
        return user;
    }

    /**
     * 获取用户列表
     *
     * @return list 用户列表
     */
    @Override
    public List<User> getUserList() {
        List<User> userList = baseMapper.selectList(null);
        return userList;
    }

    /**
     * 根据Id删除用户
     *
     * @param userId 用户Id
     * @return i     删除的记录数，若不为0则删除成功
     */
    @Override
    public int deleteUserById(String userId) {
        int i = baseMapper.deleteById(userId);
        return i;
    }

    /**
     * 更新用户信息
     *
     * @param user 信息体
     * @return map 数据体
     */
    @Override
    public Map<String, Object> updateUser(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("success" , false);
        map.put("user" , null);
        //查看数据库中是否存在手机号码
        User selectOne = baseMapper.selectOne(new QueryWrapper<User>().eq("phone_number", user.getPhoneNumber()));
        if (selectOne == null || selectOne.getPhoneNumber().equals(user.getPhoneNumber())) {
            //不存在 ， 或是手机号码就是本人号码
            //删除原先的信息，再加入
            baseMapper.deleteById(user.getId());
            //插入数据
            user.setId(UUID.randomUUID().toString());
            int i = baseMapper.insert(user);
            if (i!=0){
                map.put("success" , true);
                map.put("user" , user);
            }
        }

        return map;
    }
}
