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

import com.pizzaordering.order.entity.Order;

/**
 * Interface of order service layer.
 * 
 * @author Rafael Lima Costa
 *
 */
public interface OrderService {
	
	/**
	 * Operation for adding an order with customized ingredients.
	 * 
	 * @param order Order with customized ingredients to be added on database.
	 * @return Order with customized ingredients added on database.
	 */
	public Order addOrder(Order order);
	
	/**
	 * Operation for getting an order with customized ingredients.
	 * 
	 * @param id Id of order with customized ingredients to be gotten from database.
	 * @return Order with customized ingredients gotten from database.
	 */
	public Order getOrder(Long id);
	
	/**
	 * Operation for deleting an order with customized ingredients.
	 * 
	 * @param id Id of order with customized ingredients to be deleted from database.
	 */
	public void deleteOrder(Long id);
}