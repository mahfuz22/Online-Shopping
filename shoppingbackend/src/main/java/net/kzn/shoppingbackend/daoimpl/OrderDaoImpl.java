package net.kzn.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.kzn.shoppingbackend.dao.OrderDao;
import net.kzn.shoppingbackend.dto.OrderDetail;
import net.kzn.shoppingbackend.dto.OrderItem;
import net.kzn.shoppingbackend.dto.User;

@Repository("orderDao")
@Transactional
public class OrderDaoImpl implements OrderDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	


	@Override
	public List<OrderDetail> list(User user) {
		String sql= "from OrderDetail where user=:user";
		try {
			return sessionFactory.getCurrentSession()
						.createQuery(sql,OrderDetail.class)
							.setParameter("user", user)
							.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}




	@Override
	public List<OrderItem> getOrderList(OrderDetail orderDetail) {
		String sql = "from OrderItem where orderDetail=:orderDetail";
		try {
			return sessionFactory.getCurrentSession()
						.createQuery(sql, OrderItem.class)
							.setParameter("orderDetail", orderDetail)
							 .getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}




	

}
