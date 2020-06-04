package com.xxx.test;

import com.xxx.dao.IUserDao;
import com.xxx.domain.QueryVo;
import com.xxx.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {
    private IUserDao userDao;
    private SqlSession session;
    private InputStream in;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        userDao = session.getMapper(IUserDao.class);
    }
    @After
    public void destroy() throws IOException {
        session.commit();
        session.close();
        in.close();
    }

    @Test
    public void testFindAll() throws IOException {
        List<User> users = userDao.findAll();
        for (User user :
                users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("测试用户");
        user.setAddress("青岛市");
        user.setSex('男');
        user.setBirthday(new Date());
        System.out.println("保存操作之前：" + user);
        userDao.saveUser(user);
        System.out.println("保存操作之后：" + user);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(49);
        user.setUsername("测试用户Update");
        user.setAddress("青岛市");
        user.setSex('女');
        user.setBirthday(new Date());
        userDao.updateUser(user);
    }

    @Test
    public void testDelete(){
        userDao.deleteUser(49);
    }

    @Test
    public void testFindById(){
        User user = userDao.findById(48);
        System.out.println(user);
        Assert.assertEquals(user.getId(),(Integer) 48);
    }

    @Test
    public void testFindByName(){
        List<User> users = userDao.findByName("小%");
        for (User user:users){
            System.out.println(user);
        }
    }

    @Test
    public void testFindTotal(){
        System.out.println(userDao.findTotal());
    }

    @Test
    public void testFindUserByVo(){
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUsername("%王%");
        vo.setUser(user);
        List<User> users = userDao.findUserByVo(vo);
        for (User u :
                users) {
            System.out.println(u);
        }
    }
}
