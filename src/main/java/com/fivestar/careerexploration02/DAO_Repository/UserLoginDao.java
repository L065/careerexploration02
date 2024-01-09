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
    public UserLoginModel getAccPassWd(String accountnum, String passwd)
    {
        UserLoginMapper userLoginMapper = new UserLoginMapper();

        List<UserLoginModel> MemberAP = jdbcTemplate.query(
                "SELECT * FROM userdata WHERE accountnum = ? AND passwd = ?",
                userLoginMapper, accountnum, passwd);
            // 如果結果集為空，則返回null；否則返回第一個資料符合的會員
           if( MemberAP.isEmpty())
           {
               System.out.println(" 查無此使用者");
           }
           else
           {
               System.out.println(" 有查到該會員");
           }
            return MemberAP.isEmpty() ? null : MemberAP.get(0);
    }
//    public List<UserLoginModel> getAllUsers()
//    {
//        String sql = "SELECT * FROM userdata";
//        // 使用 JdbcTemplate 查資料庫
//        List<UserLoginModel> userList = jdbcTemplate.query(sql, new UserLoginMapper());
//        //System.out.println(userList);
//        return userList;
//    }
}
