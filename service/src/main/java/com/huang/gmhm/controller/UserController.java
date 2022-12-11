package com.huang.gmhm.controller;

import com.huang.gmhm.model.User;
import com.huang.gmhm.service.ReserveService;
import com.huang.gmhm.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/service/user")
@Api(tags = "用户管理")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ReserveService reserveService;

    @ApiOperation(value = "注册用户")
    @PostMapping("register/{userName}/{phoneNumber}/{password}/{sex}")
    public Map<String, Object> register(@PathVariable String userName,
                                        @PathVariable String phoneNumber,
                                        @PathVariable String password,
                                        @PathVariable Integer sex) {
        Map<String, Object> map = userService.register(userName, phoneNumber, password, sex);
        return map;
    }

    @ApiOperation(value = "登录")
    @GetMapping("login/{phoneNumber}/{password}")
    public Map<String, Object> login(@PathVariable String phoneNumber, @PathVariable String password) {
        Map<String, Object> map = userService.login(phoneNumber, password);
        return map;
    }

    @ApiOperation(value = "获取用户列表")
    @GetMapping("getUserList")
    public Map<String, Object> getUserList() {
        Map<String, Object> map = new HashMap<>();
        List<User> userList = userService.getUserList();
        map.put("userList", userList);
        return map;
    }

    @ApiOperation(value = "根据ID删除用户")
    @DeleteMapping("deleteUser/{userId}")
    public Map<String, Object> deleteUser(@PathVariable String userId) {
        Map<String, Object> map = new HashMap<>();
        //先将用户的预约订单删除
        reserveService.deleteReserveByUserId(userId);
        //再将用户记录删除
        int i = userService.deleteUserById(userId);
        map.put("success", i != 0);
        return map;
    }

    @ApiOperation(value = "修改用户信息")
    @PutMapping("updateUser/{userName}/{phoneNumber}/{password}/{sex}/{id}")
    public Map<String , Object> updateUser(@PathVariable String userName ,
                                           @PathVariable String phoneNumber ,
                                           @PathVariable String password ,
                                           @PathVariable Integer sex ,
                                           @PathVariable String id){
        User user = new User();
        user.setId(id);
        user.setSex(sex);
        user.setUserName(userName);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);

        Map<String , Object> map = userService.updateUser(user);
        if((boolean)map.get("success")){
            //如果修改成功了，那么预约订单上的userId也要变化
            String newUserId = ((User)map.get("user")).getId();
            reserveService.updateUserId(id , newUserId);
        }
        return map;
    }

}
