package net.kzn.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.kzn.shoppingbackend.dao.OrderDao;
import net.kzn.shoppingbackend.dao.UserDAO;
import net.kzn.shoppingbackend.dto.OrderDetail;
import net.kzn.shoppingbackend.dto.OrderItem;
import net.kzn.shoppingbackend.dto.User;

public class OrderTestCase {

	private static AnnotationConfigApplicationContext context;
	private static UserDAO userdao;
	private static OrderDao orderDao;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.kzn.shoppingbackend");
		context.refresh();
		userdao = (UserDAO) context.getBean("userDAO");
		orderDao = (OrderDao) context.getBean("orderDao");
	}
	
	/*@Test
	public void testUserOrder() {
		User user= userdao.get(33);
		List<OrderDetail> order = orderDao.list(user);
		assertEquals("failed to fetch the order info",3,order.size());
	}*/
	
	@Test
	public void testUserOrder() {
		User user= userdao.get(33);
		List<OrderDetail> order = orderDao.list(user);
		
		List<OrderItem> orderItem = order.get(1).getOrderItems();
//		List<OrderItem> orderItem=null;
//		for(OrderDetail od: order) {
//			 orderItem= orderDao.getOrderList(od);
////			 for(OrderItem oi: orderItem) {
////				 oi.getOrderDetail().getUser().getId();
////			 }
//		}
		assertEquals("failed to fetch the order info",4,orderItem.get(0).getProduct().getId());
	}
}
