package com.fivestar.careerexploration02.DAO_Repository;

import com.fivestar.careerexploration02.DAO_Repository.Mapper.UserLoginMapper;
import com.fivestar.careerexploration02.model.userModel.UserLogModel02;
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

    public UserLogModel02 getAccPassWd(String accountnum, String passwd)
    {
        UserLoginMapper userLoginMapper = new UserLoginMapper();

        List<UserLogModel02> MemberAP = jdbcTemplate.query(
                "SELECT * FROM userdata WHERE accountnum = ? AND passwd = ?",
                userLoginMapper, accountnum, passwd);
            // 如果結果集為空，則返回null；否則返回第一個資料符合的會員
           if( MemberAP.isEmpty())
           {
               System.out.println(" 查無此使用者");      //測試是否含有該帳號密碼?
           }
           else
           {
               System.out.println(" 有查到該會員");
           }
            return MemberAP.isEmpty() ? null : MemberAP.get(0);
    }


    public UserLogModel02 getUserNameAtDao(String accountnum, String passwd)
    {
        String sql03= "SELECT * FROM userdata WHERE accountnum = ? AND passwd = ?";
        return jdbcTemplate.queryForObject(sql03, new UserLoginMapper(), accountnum, passwd);
    }
}
