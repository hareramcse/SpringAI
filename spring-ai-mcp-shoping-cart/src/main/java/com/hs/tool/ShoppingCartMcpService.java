package com.hs.tool;

import java.util.List;
import java.util.Map;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.entity.CartItem;
import com.hs.repository.CartItemRepository;

import jakarta.transaction.Transactional;

@Service
public class ShoppingCartMcpService {

	@Autowired
	private CartItemRepository cartItemRepository;

	private static final Map<String, Double> PRODUCTS = Map.of("iPhone", 79999.0, "MacBook Air", 129999.0,
			"Boat Airdopes", 1999.0);

	@Tool(name = "addToCart", description = "Add a product to the shopping cart. Available products are iPhone, MacBook Air, Boat Airdopes. Requires product name and quantity.")
	@Transactional
	public String addToCart(
			@ToolParam(description = "Name of the product (iPhone, MacBook Air, Boat Airdopes)") String productName,
			@ToolParam(description = "Number of items to add") int quantity) {

		if (!PRODUCTS.containsKey(productName)) {
			return "product not found";
		}

		Double price = PRODUCTS.get(productName);

		CartItem cartItem = cartItemRepository.findByProductId(productName);

		if (cartItem == null) {
			cartItem = new CartItem();
			cartItem.setProductId(productName);
			cartItem.setProductName(productName);
			cartItem.setQuantity(quantity);
		} else {
			cartItem.setQuantity(cartItem.getQuantity() + quantity);
		}
		cartItem.setPrice(cartItem.getQuantity() * price);
		cartItemRepository.save(cartItem);
		return quantity + " " + productName + " added to cart. Total price: " + (cartItem.getPrice());
	}

	@Tool(name = "removeCart", description = "Remove a product from the shopping cart.")
	@Transactional
	public String removeCart(@ToolParam(description = "product name to remove") String productName) {
		cartItemRepository.deleteByProductId(productName);
		return productName + " removed from cart.";
	}

	@Tool(name = "getCarts", description = "Retrieve the current shopping cart items.")
	public List<CartItem> getCarts() {
		return cartItemRepository.findAll();
	}

	@Tool(name = "getCartTotal", description = "Calculate the total price of items in the shopping cart.")
	public double getCartTotal() {
		return cartItemRepository.findAll().stream().mapToDouble(CartItem::getPrice).sum();
	}

	@Tool(name = "ping", description = "Test MCP tool connectivity")
	public String ping() {
		return "pong";
	}

}
