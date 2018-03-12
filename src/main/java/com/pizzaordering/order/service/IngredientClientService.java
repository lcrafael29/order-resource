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

package com.pizzaordering.order.service;

import java.math.BigDecimal;

import com.pizzaordering.order.entity.Order;

/**
 * Interface of ingredient client service layer.
 * 
 * @author Rafael Lima Costa
 *
 */
public interface IngredientClientService {
	
	/**
	 * Operation for calculating the price of an order with customized ingredients consuming ingredient
	 * 		resource via HTTP REST.
	 * 
	 * @param order Order with customized ingredients to be calculated.
	 * @return Price of the order with customized ingredients.
	 */
	public BigDecimal calculateOrderPrice(Order order);
	
	/**
	 * Operation for reversing ingredients from inventory of a canceled order with customized ingredients
	 * 		consuming ingredient resource via HTTP REST.
	 * 
	 * @param order Order with customized ingredients to be reversed.
	 */
	public void reverseOrderIngredients(Order order);
}