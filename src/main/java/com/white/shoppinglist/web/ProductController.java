package com.white.shoppinglist.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.white.shoppinglist.domain.Product;
import com.white.shoppinglist.domain.ProductRepository;

@Controller
public class ProductController {

	private static final Logger log = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductRepository productRepository;

	@GetMapping({ "/", "/productlist" })
	public String productlist(Model model) {
		List<Product> products;
		products = (List<Product>) productRepository.findAll();
		model.addAttribute("products", products);
		log.info("Showing all products");
		return "productlist";
	}

	@RequestMapping(value = "/addproduct")
	public String addProduct(Model model) {
		model.addAttribute("product", new Product());
		log.info("Adding a new product");
		return "addProduct";
	}

	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable("id") Long productId) {
		productRepository.deleteById(productId);
		log.info("The product has been succesfully deleted");
		return "redirect:/productlist";
	}

	@RequestMapping(value = "/edit/{id}")
	public String editProduct(@PathVariable("id") Long productId, Model model) {
		model.addAttribute("product", productRepository.findById(productId));
		log.info("The product has been succesfully edited");
		return "editproduct";
	}

	// SAVESSA (ei enää häikkää :^) ) (hyvä!)
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product,
			Model model) {
		productRepository.save(product);
		return "redirect:/productlist";
	}

}