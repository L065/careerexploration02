package com.fivestar.careerexploration02.DAO_Repository.Mapper;

import com.fivestar.careerexploration02.model.userModel.UserLoginModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserLoginMapper implements RowMapper<UserLoginModel> //把DB會員資料裝進陣列口袋
{
    @Override
    public UserLoginModel mapRow(ResultSet rs, int rowNum) throws SQLException
    {
       UserLoginModel uLModel = new UserLoginModel();
       uLModel.setMemberid(rs.getInt("memberid"));
       uLModel.setAccountnum(rs.getNString("accountnum"));
       uLModel.setPasswd(rs.getNString("passwd"));
       uLModel.setUsername(rs.getNString("username"));
       uLModel.setEmail(rs.getNString("email"));
       uLModel.setMobile(rs.getNString("mobile"));
       uLModel.setRegdate(rs.getDate("regdate"));
       return uLModel;
    }
}
