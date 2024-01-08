package com.fivestar.careerexploration02.DAO_Repository;

import com.fivestar.careerexploration02.DAO_Repository.Mapper.UserLoginMapper;
import com.fivestar.careerexploration02.model.userModel.UserLoginModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserLoginDao
{
    //找資料庫已註冊的帳號
    @Autowired
    JdbcTemplate jdbcTemplate;
//    public List<UserLoginModel> getAccPassWd(String memberid,String accountnum, String passwd)
//    {
//        UserLoginMapper userLoginMapper =new UserLoginMapper();
//        List<UserLoginModel> MemberAP =
//                jdbcTemplate.query("SELECT memberid,accountnum,passwd from userdata where accountnum=? and passwd=?", userLoginMapper);
//        return MemberAP;
//    }
    public UserLoginModel getAccPassWd(String accountnum, String passwd) {
    UserLoginMapper userLoginMapper = new UserLoginMapper();

    List<UserLoginModel> MemberAP = jdbcTemplate.query(
            "SELECT memberid, accountnum, passwd FROM userdata WHERE accountnum = ? AND passwd = ?",
            userLoginMapper,
            accountnum,
            passwd);
        // 如果結果集為空，則返回null；否則返回第一個匹配的用戶
        return MemberAP.isEmpty() ? null : MemberAP.get(0);
    }
}
