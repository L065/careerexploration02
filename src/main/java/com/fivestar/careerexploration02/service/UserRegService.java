package com.fivestar.careerexploration02.service;

import com.fivestar.careerexploration02.DAO_Repository.UserRegDao;
import com.fivestar.careerexploration02.model.userModel.UserRegModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//先使用老師的Service
@Service
public class UserRegService {

    @Autowired
    UserRegDao userRegDao;

    public int Registration(UserRegModel userModel) {
        // 1:成功 , 2: 帳號已經存在 3: 包含惡意或是禁用字串
        // 收到註冊需求  先檢查帳號是否存在
        // 存在  退申請
        if (userModel.getUsername().contains("select") || userModel.getUsername().contains("delete")) {
            return 3;
        }
        if( isUserExists(userModel.getUsername())) {
            return 2;
        }
        // 不存在  開始檢查資料是否合規
        // 過濾 惡意字詞 可能異常字串(select , inser, update, delete 等)
        // 開始進行 帳號新增作業
        int cnt = userRegDao.saveUserForm(userModel);
        if(cnt > 0 ) {
            return 1;
        } else {
            return cnt;
        }
    }

    // 獨立寫成一個公用方法 讓確認帳號是否存在的功能可以分享 , 改密碼也需要檢查是有此帳號
    public boolean isUserExists(String username) {
        long count = userRegDao.testUserExist(username);
        if( count > 0) {
            return true;
        } else {
            return false;
        }
    }
}