package com.specialyang.secondstokill.dao;

import com.specialyang.secondstokill.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Fan Yang in 2018/12/4 1:57 PM.
 */
@Mapper
public interface UserDAO {

    @Select("Select * from user where id = #{id}")
    User getUserById(long id);

    @Insert("insert into user(id, username) values(#{id}, #{username})")
    int insert(User user);

}
