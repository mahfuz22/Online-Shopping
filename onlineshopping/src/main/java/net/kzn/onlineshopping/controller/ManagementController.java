package net.kzn.onlineshopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.kzn.onlineshopping.util.FileUtil;
import net.kzn.onlineshopping.validator.ProductValidator;
import net.kzn.shoppingbackend.dao.CategoryDAO;
import net.kzn.shoppingbackend.dao.OrderDao;
import net.kzn.shoppingbackend.dao.ProductDAO;
import net.kzn.shoppingbackend.dao.UserDAO;
import net.kzn.shoppingbackend.dto.Address;
import net.kzn.shoppingbackend.dto.Category;
import net.kzn.shoppingbackend.dto.OrderDetail;
import net.kzn.shoppingbackend.dto.Product;
import net.kzn.shoppingbackend.dto.User;

@Controller
@RequestMapping("/manage")
public class ManagementController {

	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private UserDAO userDao;

	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@RequestMapping("/product")
	public ModelAndView manageProduct(@RequestParam(name = "success", required = false) String success) {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Product Management");
		mv.addObject("userClickManageProduct", true);

		Product nProduct = new Product();

		// assuming that the user is ADMIN
		// later we will fixed it based on user is SUPPLIER or ADMIN
		nProduct.setSupplierId(1);
		nProduct.setActive(true);

		mv.addObject("product", nProduct);

		if (success != null) {
			if (success.equals("product")) {
				mv.addObject("message", "Product submitted successfully!");
			} else if (success.equals("category")) {
				mv.addObject("message", "Category submitted successfully!");
			}
		}

		return mv;

	}

	@RequestMapping("/{id}/product")
	public ModelAndView manageProductEdit(@PathVariable int id) {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Product Management");
		mv.addObject("userClickManageProduct", true);

		// Product nProduct = new Product();
		mv.addObject("product", productDAO.get(id));

		return mv;

	}

	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public String managePostProduct(@Valid @ModelAttribute("product") Product mProduct, BindingResult results,
			Model model, HttpServletRequest request) {

		// mandatory file upload check
		if (mProduct.getId() == 0) {
			new ProductValidator().validate(mProduct, results);
		} else {
			// edit check only when the file has been selected
			if (!mProduct.getFile().getOriginalFilename().equals("")) {
				new ProductValidator().validate(mProduct, results);
			}
		}

		if (results.hasErrors()) {
			model.addAttribute("message", "Validation fails for adding the product!");
			model.addAttribute("userClickManageProduct", true);
			return "page";
		}

		if (mProduct.getId() == 0) {
			productDAO.add(mProduct);
		} else {
			productDAO.update(mProduct);
		}

		// upload the file
		if (!mProduct.getFile().getOriginalFilename().equals("")) {
			FileUtil.uploadFile(request, mProduct.getFile(), mProduct.getCode());
		}

		return "redirect:/manage/product?success=product";
	}

	@RequestMapping(value = "/product/{id}/activation", method = RequestMethod.GET)
	@ResponseBody
	public String managePostProductActivation(@PathVariable int id) {
		Product product = productDAO.get(id);
		boolean isActive = product.isActive();
		product.setActive(!isActive);
		productDAO.update(product);
		return (isActive) ? "Product Dectivated Successfully!" : "Product Activated Successfully";
	}

	@RequestMapping(value = "/user/{id}/activation", method = RequestMethod.GET)
	@ResponseBody
	public String managePostUserActivation(@PathVariable int id) {
		User user = userDao.get(id);
		boolean isActive = user.isEnabled();
		user.setEnabled(!isActive);
		userDao.update(user);
		return (isActive) ? "User Dectivated Successfully!" : "User Activated Successfully";
	}

	@RequestMapping(value = "/category", method = RequestMethod.POST)
	public String managePostCategory(@ModelAttribute("category") Category mCategory, HttpServletRequest request) {
		categoryDAO.add(mCategory);
		return "redirect:" + request.getHeader("Referer") + "?success=category";
	}

	@ModelAttribute("categories")
	public List<Category> modelCategories() {
		return categoryDAO.list();
	}

	@ModelAttribute("category")
	public Category modelCategory() {
		return new Category();
	}

	@RequestMapping("/user")
	public ModelAndView manageUser() {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "User Management");
		mv.addObject("adminClickManageUser", true);

		return mv;

	}

	// showing user address
	@RequestMapping("/user/{id}/address")
	public ModelAndView showAddress(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("page");

		// get the billing address from the database based on user id
		Address billingAddress = userDao.getBillingAddress(id);

		// get the shipping address from the database based on user id
		List<Address> shippingAddressList = userDao.listShippingAddresses(id);

		// pass those address to the
		if (shippingAddressList != null) {
			mv.addObject("shipping", shippingAddressList);
		}

		if (billingAddress != null) {
			mv.addObject("billing", billingAddress);
		}

		mv.addObject("title", "User Management");
		mv.addObject("adminClickAddress", true);
		return mv;
	}

	// showing user order
	@RequestMapping("/user/{id}/order")
	public ModelAndView showUserOrder(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "User Management");
		mv.addObject("adminClickOrder", true);

		// get the order details from database by user id
		User user = userDao.get(id);
		List<OrderDetail> orderList = orderDao.list(user);
		// for(OrderDetail order: orderList) {
		// mv.addObject("billing", order.getBilling().getId());
		// }

		mv.addObject("orderList", orderList);

		return mv;
	}
	
	
	//adding a new admin
	@RequestMapping(value="/addAdmin", method=RequestMethod.POST)
	public String addAdmin(@Valid @ModelAttribute("addAdmin") User user,
			BindingResult result, Model model) {
		
		
		if(user.getPassword().equals(user.getConfirmPassword())) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userDao.add(user);
		}
		
		return "redirect:/";
	}

	// admin profile
	@RequestMapping(value = "/admininfo")
	public ModelAndView adminProfile(@RequestParam(name = "update", required = false) String update, Model model) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Admin Info");
		mv.addObject("adminClickAdminInfo", true);
		// model.addAttribute("userInfo", new User());
		
		if(update!=null) {
			if(update.equals("success")) {
				model.addAttribute("message", "updated Successfully !!");
			}
			else {
				model.addAttribute("message", "update failed !!");
			}
		}
		
		return mv;
	}

	@RequestMapping(value = "/post/adminInfo")
	public String postAdminInfo(@Valid @ModelAttribute("userInfo") User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("title", "Admin Info");
			model.addAttribute("adminClickAdminInfo", true);
			return "page";
		}

		// update user
		userDao.update(user);

		return "redirect:/manage/admininfo?update=success";
	}

	@RequestMapping(value = "/post/adminPass")
	public String postAdminPass(@Valid @ModelAttribute("userInfo") User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("title", "Admin Info");
			model.addAttribute("adminClickAdminInfo", true);
			return "page";
		}

		String previousPass = user.getPreviuosPassword();
		String newPassword = user.getConfirmPassword();

		// comparing between previous pass and original pass
		if (passwordEncoder.matches(previousPass, user.getPassword())) {
			user.setPassword(passwordEncoder.encode(newPassword));
			userDao.update(user);
			return "redirect:/manage/admininfo?update=success";
		} else {
			return "redirect:/manage/admininfo?update=failed";
		}
	}

}
