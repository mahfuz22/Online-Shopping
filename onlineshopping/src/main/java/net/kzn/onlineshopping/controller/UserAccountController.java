package net.kzn.onlineshopping.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.kzn.shoppingbackend.dao.UserDAO;
import net.kzn.shoppingbackend.dto.Address;
import net.kzn.shoppingbackend.dto.User;

@Controller
@RequestMapping("/user")
public class UserAccountController {

	@Autowired
	 UserDAO userDao;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	User user=null;
	
	@RequestMapping(value="/manage/account", method=RequestMethod.GET)
	public ModelAndView manageUserAccount(@RequestParam(name="update",required= false) String update) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickManageAccount",true);
		mv.addObject("title", "User Account");
		
		if(update != null) {
			if(update.equals("success")) {
				mv.addObject("message", "Updated Successfully !!");
			}
		}
		
		return mv;
	}
	
	@RequestMapping(value="/manage/update", method=RequestMethod.POST)
	public String managePostUserAccount(@Valid @ModelAttribute("userInfo") User user,BindingResult result,Model model) {
		if(result.hasErrors()) {
			model.addAttribute("message", "Validation fails for Updating!");
			model.addAttribute("userClickManageAccount",true);
			model.addAttribute("title", "User Account");
			return "page";
		}
		
		userDao.update(user);
		return "redirect:/user/manage/account?update=success";
	}
	
	@RequestMapping(value="/manage/password", method=RequestMethod.GET)
	public ModelAndView manageUserPassword(@RequestParam(name="change", required=false) String change, User user) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickManagePassword",true);
		//mv.addObject("userClickManageAccount",true);
		mv.addObject("title", "User Password");
//		
//		User userInfo = new User();
//		mv.addObject("passInfo", userInfo);
		
		if(change != null) {
			if(change.equals("success")) {
				mv.addObject("message", "Updated Successfully !!");
			}else if(change.equals("failed")) {
				mv.addObject("message", "update failed");
			}
		}
		return mv;
	}
	
	@RequestMapping(value="/manage/passchange", method=RequestMethod.POST)
	public String managePostUserPassword(@Valid @ModelAttribute("userInfo") User user, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("message", "Validation fails for changing Password!");
			model.addAttribute("userClickManagePassword",true);
			model.addAttribute("title", "User Password");
			return "page";
		}
		
		String previousPass=user.getPreviuosPassword();
		String newPassword = user.getConfirmPassword();
		
		//comparing between previous pass and original pass
		if(passwordEncoder.matches(previousPass, user.getPassword())) {
			user.setPassword(passwordEncoder.encode(newPassword));
			userDao.update(user);
			return "redirect:/user/manage/password?change=success";
		}
		else {
			return "redirect:/user/manage/password?change=failed";
		}
		
		
	}
	
	@RequestMapping(value="/manage/billing",method=RequestMethod.GET)
	public ModelAndView manageBillingAddress(@RequestParam(name="update", required=false) String update) {
		ModelAndView mv = new ModelAndView("page");
		//mv.addObject("userClickManageAccount",true);
		mv.addObject("userClickManageBillingAddress",true);
		mv.addObject("title", "User Billing");
		return mv;
	}
	
	@RequestMapping(value="/manage/post/billing", method=RequestMethod.POST)
	public String postManageBilling(@Valid @ModelAttribute("billingAddress") Address address,
			BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("message", "Validation fails for updating address!");
			model.addAttribute("userClickManageBillingAddress",true);
			model.addAttribute("title", "User Billing");
			return "page";
		}
		
		userDao.updateAddress(address);
		
		return "redirect:/user/manage/billing?update=success";
	}
	
	@RequestMapping("/manage/shipping")
	public ModelAndView manageUsershippingAddress() {
		ModelAndView mv = new ModelAndView("page");
		//mv.addObject("userClickManageAccount",true);
		mv.addObject("userClickManageShippingAddress",true);
		mv.addObject("title", "User Shipping");
		return mv;
	}
}
