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

    String FIELD = "id, nickname, password, salt, register_date, last_login_date, login_count";

    String TABLE = "user";

    @Select("Select * from user where id = #{id}")
    User getUserById(long id);

    @Insert({"insert into", TABLE, "(", FIELD, ")", "values (#{id}, #{nickname}, " +
            "#{password}, #{salt}, #{registerDate}, #{lastLoginDate}, #{loginCount})"})
    int insert(User user);

}
