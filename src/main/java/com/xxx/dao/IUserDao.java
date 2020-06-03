package com.xxx.dao;

import com.xxx.domain.QueryVo;
import com.xxx.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {
    //@Results({@Result(property = "id",column = "id")})
    @ResultMap("com.xxx.dao.IUserDao.userMap")
    @Select("select * from user")
    List<User> findAll();

    @SelectKey(statement = "select last_insert_id()",keyProperty = "id",keyColumn = "id",resultType = Integer.class,before = false)
    @Insert("insert into user(username,address,sex,birthday) values(#{username},#{address},#{sex},#{birthday})")
    void saveUser(User user);

    @Update("update user set username=#{username},address=#{address},sex=#{sex},birthday=#{birthday} where id=#{id}")
    void updateUser(User user);

    @Delete("delete from user where id=#{一个参数可写任意占位符}")
    void deleteUser(Integer userId);

    @Select("Select * from user where id=#{id}")
    User findById(Integer userId);

    @Select("Select * from user where username like #{name}")
    List<User> findByName(String username);

    //@Select("Select count(*) from user")
    Long findTotal();

    /**
     * 根据QueryVo中的查询条件
     * @param vo
     * @return
     */
    @Select("select * from user where username like #{user.username}")
    List<User> findUserByVo(QueryVo vo);
}
