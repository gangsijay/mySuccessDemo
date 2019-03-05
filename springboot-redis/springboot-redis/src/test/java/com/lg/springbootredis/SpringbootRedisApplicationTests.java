package com.lg.springbootredis;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lg.springbootredis.bean.Customer;
import com.lg.springbootredis.bean.User;
import com.lg.springbootredis.utils.RedisUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedisApplicationTests {
	
//	@Resource
//    private RedisTemplate<String, User> redisTemplate;
	
	@Resource
	private RedisUtil redisUtil;

	@Test
	public void contextLoads() {
		
		// 保存对象
		User user = new User("超人", 20);
		Customer customer = new Customer("客户", 30);
//        redisTemplate.opsForValue().set(user.getUsername(), user);
        redisUtil.set(user.getUsername(), user);
        redisUtil.set(customer.getUsername(), customer);

//        user = new User("蝙蝠侠", 30);
//        redisTemplate.opsForValue().set(user.getUsername(), user);
//
//        user = new User("蜘蛛侠", 40);
//        redisTemplate.opsForValue().set(user.getUsername(), user);
//        user = new User("蜘蛛侠1", 40);

//        Assert.assertEquals(20, redisTemplate.opsForValue().get("超人").getAge().longValue());
//        Assert.assertEquals(30, redisTemplate.opsForValue().get("蝙蝠侠").getAge().longValue());
//        Assert.assertEquals(40, redisTemplate.opsForValue().get("蜘蛛侠").getAge().longValue());
        System.out.println("======="+redisUtil.get("超人").getClass());
        User use = (User)redisUtil.get("超人");
        System.out.println("======="+use.getAge()+use.getUsername());
        Customer cus = (Customer)redisUtil.get("客户");
        System.out.println("======="+cus.getAge()+cus.getUsername());

	}

}
