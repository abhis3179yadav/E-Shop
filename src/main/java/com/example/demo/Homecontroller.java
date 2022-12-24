package com.example.demo;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.hibernate.boot.model.source.spi.EmbeddableSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.cartdetails.CartDetails;
import com.example.demo.cartdetails.CartdetailsServ;
import com.example.demo.categories.Category;

import com.example.demo.categories.categoryServ;
import com.example.demo.orderdetails.orderdetails;
import com.example.demo.orderdetails.orderdetailserv;
import com.example.demo.productdetails.Product;
import com.example.demo.productdetails.ProductService;

import com.example.demo.users.UserServices;
import com.example.demo.users.Users;

@Controller
public class Homecontroller {
    
	@Autowired
	UserServices User_Services;
	
	@Autowired
	categoryServ category_Serv;
	
	@Autowired
	ProductService Product_Service;
	
	@Autowired
	CartdetailsServ CartDetails;
	
	@Autowired
	orderdetailserv orderdetailserv;
	
	Category cr;
	Product  pr;
	Users ur;
	CartDetails crdt;
	@GetMapping("/")
	public String Home(Model m){
		int i=0;
			Product_Service.displayproduct().stream().filter((g)->g.getStock_Unit()>0).collect(Collectors.toList()).forEach(b->{
			System.out.println(b.getProduct_name() +"   "+b.getStock_Unit());
			});
			
		List<Product> L = Product_Service.displayproduct().stream().filter((g)->g.getStock_Unit()>0).collect(Collectors.toList());
		ArrayList<Product> A = new ArrayList<Product>();
		for(Product p : L)
		{
			if(i==3)
				break;
			A.add(p);
			i++;
					
		}
		m.addAttribute("listprdt",A); 
		return "index";
	}
	
	@GetMapping("/Register")
	public String getRegisteration(Model m) {
		
		m.addAttribute("user", new Users());
		return "register";
	}
	
	@GetMapping("/Loginuser")
	public String GetLoginpage(Model m) {

		m.addAttribute("usrlogin", new Users());
		return "Login";

	}
	@GetMapping("/admin")
	public String GetAdminpage() {

		
		return "adminhome";

	}
	@GetMapping("/about")
	public String GetAbout() {

		
		return "about";

	}
	@GetMapping("/Contact")
	public String GetContact() {

		
		return "contact";

	}
	@PostMapping("/Saveuser")
	public String register(Model m, @Valid Users usr, BindingResult r) {
	    usr.setRole("User");
		User_Services.saveusers(usr); 
		m.addAttribute("usrlogin", new Users());
		
	return "Login";
}
	@PostMapping("/login")
	public String FindUser(Model m, @Valid Users usr) {
		m.addAttribute("usrlogin", new Users());
		String em = usr.getE_mail();
		String p = usr.getPassword();
		Users u = User_Services.login(em);
        String mail  =u.getE_mail();
        String Role =u.getRole();
		System.out.println(mail);
		if (mail.equalsIgnoreCase(em) && u.getPassword().equalsIgnoreCase(p) && Role.equalsIgnoreCase("Admin")){

			return "adminhome";
		}
		if (mail.equalsIgnoreCase(em) && u.getPassword().equalsIgnoreCase(p)) {
			ur=u;
			int i=0;
			Product_Service.displayproduct().stream().filter((g)->g.getStock_Unit()>0).collect(Collectors.toList()).forEach(b->{
				System.out.println(b.getProduct_name() +"   "+b.getStock_Unit());
				});
			List<Product> L = Product_Service.displayproduct().stream().filter((g)->g.getStock_Unit()>0).collect(Collectors.toList());
			ArrayList<Product> A = new ArrayList<Product>();
			for(Product P : L)
			{
				if(i==2)
					break;
				A.add(P);
				i++;
						
			}
			m.addAttribute("listprdt",A); 
			
			return "index";
		}
		else {
			return "Login";
		}


		
	}
		
	
	
	/* For Add Category Page start here */
	@GetMapping("/admin/categories")
	public String homecategory(Model m) {
		m.addAttribute("category", category_Serv.displaycategory());
		return "categories";
	}
	@GetMapping("/admin/categories/addcategory")
	public String addcategorypage(Model m) {
		
		m.addAttribute("category", new Category());
		return "addcategory";
	}
	@PostMapping("/admin/categories/addcategory/add")
	public String addcategory(Model m,@Valid Category c) {
		
		category_Serv.saverecord(c);
		m.addAttribute("category", category_Serv.displaycategory());
		
		return "categories";
	}
	@GetMapping("/admin/categories/delete/{id}")
	public String deleteProduct(@PathVariable("id") int id, Model m) {

		category_Serv.deletecategory(id);
		
		m.addAttribute("category", new Category());
		m.addAttribute("category", category_Serv.displaycategory());
		

		return "categories";
	}
	@GetMapping("/admin/categories/update/{id}")
	public String updatepage(@PathVariable("id") int id,Model m) {
		
		cr =category_Serv.FindByID(id);
		System.out.println(id);
		
		m.addAttribute("category", cr);
		return "updateCategory";
	}
	
	@PostMapping("/admin/categories/updatecategory/add")
	public String updatedcategory(@ModelAttribute("category") Category c, Model m) {
		
		category_Serv.updateproduct(c);
		m.addAttribute("category",category_Serv.displaycategory());
	
		return "categories";
	}
	

	/* For Add Category Page end here */
	
	/* For Add Product Page Start here */
	@GetMapping("/admin/products")
	public String ProductHome(Model m) {
		
		m.addAttribute("product",Product_Service.displayproduct());
		return"adminProduct";
	}
	
	@GetMapping("/admin/products/add")
	public String AddProduct(Model m) {
		List<Category> C = category_Serv.displaycategory();
		ArrayList<String> S = new ArrayList<String>();
		for (Category ur : C) {
			S.add(ur.getCategory_name());
		}
		m.addAttribute("allCategory", S);
		m.addAttribute("product", new Product());
		
		return "addProduct";
	}
	
	
	@PostMapping("/admin/products/addproduct")
	public String AddproductPage(Model m,@Valid Product p, BindingResult r, @RequestParam("file") MultipartFile mpf) {
		
		
		String fnm = mpf.getOriginalFilename();
		p.setProduct_img(fnm);
		String fpath= "C:\\Users\\abhishek\\Documents\\workspace-spring-tool-suite-4-4.15.2.RELEASE\\E-store\\src\\main\\resources\\static\\Images\\upload\\" + fnm;
		try {
			Homecontroller.uploadfile(fnm, fpath, mpf);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		Product_Service.saveproduct(p);
		
		m.addAttribute("product",Product_Service.displayproduct());
		return "adminProduct";
	}
	@GetMapping("/admin/product/delete/{id}")
	public String deleteproduct(@PathVariable("id")int id,Model m) {
		
		Product_Service.deleteproductbyid(id);
		m.addAttribute("product", new Product());
		m.addAttribute("product",Product_Service.displayproduct());
		
		return"adminProduct";
	}
	@GetMapping("/admin/product/update/{id}")
	public String updateproductpage(@PathVariable("id") int id,Model m) {
		
	    pr=Product_Service.FindproductByid(id);
	    List<Category> C = category_Serv.displaycategory();
		ArrayList<String> S = new ArrayList<String>();
		for (Category ur : C) {
			S.add(ur.getCategory_name());
		}
		m.addAttribute("allCategory", S);
	    
	    m.addAttribute("product", pr);
		return "updateproduct";
		
	}
	@PostMapping("/admin/product/update/updatedrecord")
	public String updatedrecord(@ModelAttribute("Product") Product p,Model m) {
		
		Product_Service.updateproduct(p);
		m.addAttribute("product", new Product());
		m.addAttribute("product",Product_Service.displayproduct());
		return"adminProduct";
	}
	/* User Part start here */
	@GetMapping("/displayproducts")
	public String DisplayProject(Model m) {
		List<Category> C = category_Serv.displaycategory();
		ArrayList<String> S = new ArrayList<String>();
		for (Category ur : C) {
			S.add(ur.getCategory_name());
		}
		m.addAttribute("allCategory", S);
		Product_Service.displayproduct().stream().filter((g)->g.getStock_Unit()>0).collect(Collectors.toList()).forEach(b->{
		System.out.println(b.getProduct_name() +"   "+b.getStock_Unit());
		});
		m.addAttribute("listprdt",Product_Service.displayproduct().stream().filter((g)->g.getStock_Unit()>0).collect(Collectors.toList()));
		
		return "DisplayProduct";
	}
	
	/* User Part end here */
	
	/* View product start here */
	@GetMapping("displayproducts/{id}")
	public String ViewProduct(@PathVariable("id") int id,Model m) {
		
	    pr=Product_Service.FindproductByid(id);
	    
		ArrayList<String> S = new ArrayList<String>();
		
		
	    
	    m.addAttribute("listprdt", pr);
		return "ViewProduct";
		
	}
	/* View product End here */
	/* Adding Product into cart Start here*/
	@PostMapping("/AddToCartDetails")
	public String AddToCart(@RequestParam("unit")String un, @Valid Product p, CartDetails cr, Model m){
		try {
			int unit1 = Integer.parseInt(un);
		if (ur == null) {
			m.addAttribute("usrlogin", new Users());
			return "Login";
		} else {

			String loggeduser=ur.getE_mail();
			System.out.println(loggeduser);
			System.out.println(unit1);
			String Product_name=pr.getProduct_name();
			System.out.println(Product_name);
			int total_unit = pr.getStock_Unit();
			int price = pr.getPrice();
			int product_id=pr.getProduct_id();
			int total_Amount = unit1 * price;
			int stock_unit=total_unit-unit1;
			System.out.println(total_Amount);
			System.out.println(stock_unit);
			cr.setProduct_name(Product_name);
			cr.setAmount(total_Amount);
			cr.setUnit(unit1);
			cr.setAddedByUser(loggeduser);
			cr.setProduct_img(pr.getProduct_img());
			CartDetails.savercartdetails(cr);
		    Product_Service.updatestock(stock_unit, product_id);
			
		}
		}
	
			catch(SQLException e){
				
				e.printStackTrace();
				
			}
		String em = ur.getE_mail();
		
		
		m.addAttribute("crdt",CartDetails.FilterRecordAddedByUser(em));
		m.addAttribute("unit",CartDetails.unit);
		
		
		return "Cart";
			
			

		}
 
	@GetMapping("/AddToCart")
	public String CartItems(Model m) {
		
		if(ur==null) {
			m.addAttribute("usrlogin", new Users());
			return "Login";
		}
		
		if(ur.getE_mail()!=null) {
		String em = ur.getE_mail();
		m.addAttribute("crdt",CartDetails.FilterRecordAddedByUser(em));
		m.addAttribute("unit",CartDetails.unit);
		
		
		return "Cart";
		}
		else {
			return "index";
		}
		
	}
	@GetMapping("/{id}")
	public String RemoveFromList(@PathVariable("id")int id,Model m) {
		
		CartDetails.RemoveFromCart(id);
		if(ur.getE_mail()!=null) {
			String em = ur.getE_mail();
		
		m.addAttribute("crdt",CartDetails.FilterRecordAddedByUser(em));
		m.addAttribute("unit",CartDetails.unit);
		}
		return"Cart";
	}
	
	
	@GetMapping("/checkout")
	public String Chcekout(Model m) {
		
		m.addAttribute("order",new  orderdetails());	
		m.addAttribute("tot",CartDetails.tot);
		
		return "checkout";
	}
	@PostMapping("/PayNow")
	public String PayNow(Model m,@Valid orderdetails or, BindingResult r) throws SQLException {
		
		if(ur.getE_mail()!=null) {
		
		String em = ur.getE_mail();
		m.addAttribute("crdt",CartDetails.FilterRecordAddedByUser(em));
		CartDetails.DeleteRecord(em);
		orderdetailserv.savecheckout(or);
		m.addAttribute("order",new  orderdetails());
		m.addAttribute("orderno",orderdetailserv.getOrderNumber());
		System.out.println(orderdetailserv.getOrderNumber());
		m.addAttribute("tot",CartDetails.tot);
		
		}
		return "orderPlaced";
	}
	@GetMapping("/FinalSubmit")
	public String FinalSubmit() throws SQLException {
		if(ur.getE_mail()!=null) {
			String em = ur.getE_mail();
			CartDetails.DeleteRecord(em);
		}
		return "index";
	}
	
	
	/* Adding Product into cart End here*/
	public static void uploadfile(String fnm, String fpath, MultipartFile mpf) throws IOException {
		try {
			InputStream in = mpf.getInputStream();
			System.out.println(in);
			Path target = Paths.get(fpath);
			System.out.println(target);
			Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
			in.close();

		} catch (IOException e) {
			throw new IOException("Image not saved ! ");
		}

	}
	

	/* For Add Product Page end here */  
	
	
}
