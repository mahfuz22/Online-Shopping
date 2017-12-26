package net.kzn.onlineshopping.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import net.kzn.onlineshopping.model.UserModel;
import net.kzn.shoppingbackend.dao.UserDAO;
import net.kzn.shoppingbackend.dto.Address;
import net.kzn.shoppingbackend.dto.User;

@ControllerAdvice
public class GlobalController {
	
	
	@Autowired
	private UserDAO userDAO;
	
	
	
	@Autowired
	private HttpSession session;
	
	private UserModel userModel = null;
	private User user = null;	
	
	
	@ModelAttribute("userModel")
	public UserModel getUserModel() {		
		if(session.getAttribute("userModel")==null) {
			// get the authentication object
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			
			
			if(!authentication.getPrincipal().equals("anonymousUser")){
				// get the user from the database
				user = userDAO.getByEmail(authentication.getName());
				
				if(user!=null) {
					// create a new model
					userModel = new UserModel();
					// set the name and the id
					userModel.setId(user.getId());
					userModel.setFullName(user.getFirstName() + " " + user.getLastName());
					userModel.setRole(user.getRole());
					
					if(user.getRole().equals("USER")) {
						userModel.setCart(user.getCart());					
					}				
	
					session.setAttribute("userModel", userModel);
					return userModel;
				}			
			}
		}
		
		return (UserModel) session.getAttribute("userModel");		
	}
	
	
//	
	@ModelAttribute("userInfo")
	public User getUserInfo() {		
		if(session.getAttribute("userInfo")==null) {
			// get the authentication object
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if(!authentication.getPrincipal().equals("anonymousUser")){
				// get the user from the database
				user = userDAO.getByEmail(authentication.getName());
				
				if(user!=null) {
					return user;
				}			
			}
		}
		
		return (User) session.getAttribute("userInfo");		
	}
	
	
	@ModelAttribute("billingAddress")
	public Address getBillingInfo() {		
		if(session.getAttribute("userInfo")==null) {
			// get the authentication object
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if(!authentication.getPrincipal().equals("anonymousUser")){
				// get the user from the database
				user = userDAO.getByEmail(authentication.getName());
				
				if(user!=null) {
					return userDAO.getBillingAddress(user.getId());
				}			
			}
		}
		
		return (Address) session.getAttribute("billingAddress");		
	}
	
	@ModelAttribute("addAdmin") 
	public User addUser() {
		if(session.getAttribute("addAdmin")==null) {
			return new User();
		}
		return (User) session.getAttribute("addAdmin");
		
	}
	
//	@ModelAttribute("shippingAddress")
//	public List<Address> getShippingInfo() {		
//		if(session.getAttribute("userInfo")==null) {
//			// get the authentication object
//			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//			if(!authentication.getPrincipal().equals("anonymousUser")){
//				// get the user from the database
//				user = userDAO.getByEmail(authentication.getName());
//				
//				if(user!=null) {
//					
//					return userDAO.listShippingAddresses(user.getId());
//				}			
//			}
//		}
//		
//		return (List<Address>) session.getAttribute("shippingAddress");		
//	}
	
	
		
}



