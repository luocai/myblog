//package test;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.blog.pojo.User;
//import com.blog.service.UserService;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({"classpath:spring-mybatis.xml","classpath:spring-service.xml"})
//public class Test1 {
//
//	@Autowired
//	private UserService userService;
//
//	@Test
//	public void addUser(){
//		User user = new User();
//		user.setUserId(1);
//		user.setLoginIp("127.0.0.1");
//		user.setNickname("诸葛财财");
//		user.setUsername("6666");
//		user.setUserRole(0);
//		user.setPassword("6666");
//
//		userService.insertUser(user);
//	}
//}
