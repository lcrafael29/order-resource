/**
 * Pizza Ordering Application
 * 
 * HTTP REST Microservices that handle ordering, deals and inventory
 * 
 * FastSpring Coding Challenge
 * 
 * Rafael Lima Costa
 * March of 2018
 * Santa Barbara, CA, USA
 */

package com.pizzaordering.order.serviceImpl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pizzaordering.order.entity.Order;
import com.pizzaordering.order.service.IngredientClientService;

/**
 * Implementation of ingredient client service layer interface.
 * 
 * @author Rafael Lima Costa
 *
 */
@Service
public class IngredientClientServiceImpl implements IngredientClientService {
	
	/**
	 * URI of ingredient resource.
	 */
	private static final String URI_INGREDIENT_RESOURCE = "http://localhost:8081/ingredients";
	
	/**
	 * URI of calculate order price operation.
	 */
	private static final String URI_CALCULATE_ORDER_PRICE = "/calculateOrderPrice";
	
	/**
	 * URI of reverse order ingredients operation.
	 */
	private static final String URI_REVERSE_ORDER_INGREDIENTS = "/reverseOrderIngredients";
	
	/**
	 * Spring implementation for HTTP RESTful resources consummation.
	 */
	private RestTemplate restTemplate = new RestTemplate();
	
	/**
	 * Consume ingredient resource sending order with customized ingredients to be calculated.
	 * 
	 * @param order Order with customized ingredients to be calculated.
	 * @return Price of the order with customized ingredients.
	 */
	@Override
	public BigDecimal calculateOrderPrice(Order order) {
		return restTemplate.postForObject(URI_INGREDIENT_RESOURCE + URI_CALCULATE_ORDER_PRICE, order, BigDecimal.class);
	}
	
	/**
	 * Consume ingredient resource sending order with customized ingredients to be reversed.
	 * 
	 * @param order Order with customized ingredients to be reversed.
	 */
	@Override
	public void reverseOrderIngredients(Order order) {
		restTemplate.put(URI_INGREDIENT_RESOURCE + URI_REVERSE_ORDER_INGREDIENTS, order);
	}
}