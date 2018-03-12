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

package com.pizzaordering.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pizzaordering.order.entity.Order;
import com.pizzaordering.order.service.OrderService;

/**
 * Resource to expose order operations and handle order requests.
 * 
 * @author Rafael Lima Costa
 *
 */
@RestController
@RequestMapping("/orders")
public class OrderController {
	
	/**
	 * Interface of order service layer.
	 */
	@Autowired
	OrderService orderService;
	
	/**
	 * Operation for adding an order with customized ingredients.
	 * 
	 * @param order Order with customized ingredients to be inserted on database.
	 * @return Order with customized ingredients inserted on database.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Order addOrder(@RequestBody Order order) {
		return orderService.addOrder(order);
	}
	
	/**
	 * Operation for getting an order with customized ingredients.
	 * 
	 * @param id Id of order with customized ingredients to be gotten from database.
	 * @return Order with customized ingredients gotten from database.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Order getOrder(@PathVariable Long id) {
		return orderService.getOrder(id);
	}
	
	/**
	 * Operation for deleting an order with customized ingredients.
	 * 
	 * @param id Id of order with customized ingredients to be deleted from database.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteOrder(@PathVariable Long id) {
		orderService.deleteOrder(id);
	}
}