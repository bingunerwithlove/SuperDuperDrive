package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.SuperUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    @Select("SELECT * FROM USERS")
    List<SuperUser> findAll();

    @Select("SELECT * FROM USERS WHERE userid = #{userid}")
    public SuperUser findOne(int userid);

    @Select("SELECT * FROM USERS WHERE username = #{username}")
    public SuperUser findByUsername(String username);

    @Insert("INSERT INTO USERS (username, password, salt, firstname, lastname) VALUES (#{username}, #{password}, #{salt}, #{firstname}, #{lastname})")
    public int insertUser(SuperUser superUser);

    @Delete("DELETE FROM USERS WHERE username = #{username}")
    public int deleteUser(String username);

    @Update("UPDATE USERS SET username = #{username}, password = #{password}, salt = #{salt}, firstname = #{firstname}, lastname = #{lastname} WHERE userid = #{userid}")
    public int updateUser(SuperUser superUser);
}
