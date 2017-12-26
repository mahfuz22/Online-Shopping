package net.kzn.shoppingbackend.dao;

import java.util.List;

import net.kzn.shoppingbackend.dto.OrderDetail;
import net.kzn.shoppingbackend.dto.OrderItem;
import net.kzn.shoppingbackend.dto.User;

public interface OrderDao {
	List<OrderDetail> list(User user);
	List<OrderItem> getOrderList(OrderDetail orderDetail);
}
