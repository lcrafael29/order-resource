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

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizzaordering.order.data.OrderCustomizationRepository;
import com.pizzaordering.order.data.OrderRepository;
import com.pizzaordering.order.entity.Order;
import com.pizzaordering.order.entity.OrderCustomization;
import com.pizzaordering.order.entity.OrderCustomizationId;
import com.pizzaordering.order.service.IngredientClientService;
import com.pizzaordering.order.service.OrderService;

/**
 * Implementation of order service layer interface.
 * 
 * @author Rafael Lima Costa
 *
 */
@Service
public class OrderServiceImpl implements OrderService {
	
	/**
	 * Interface of ingredient client service layer.
	 */
	@Autowired
	IngredientClientService ingredientClientService;
	
	/**
	 * Interface of order repository layer.
	 */
	@Autowired
	OrderRepository orderRepository;
	
	/**
	 * Interface of order customization repository layer.
	 */
	@Autowired
	OrderCustomizationRepository orderCustomizationRepository;
	
	/*
	 * > Calculate order price consuming ingredient resource via HTTP REST.
	 * > Save order on database.
	 * > Fill order customization composite keys with order id returned from order database insertion
	 * 		and ingredient id which came from request as map key.
	 * > Save order customizations on database.
	 */
	@Override
	public Order addOrder(Order order) {
		Map<Long, OrderCustomization> orderCustomizationMap = null;
		OrderCustomization orderCustomization = null;
		
		order.setPrice(ingredientClientService.calculateOrderPrice(order));
		
		orderCustomizationMap = order.getOrderCustomizationMap();
		
		order.setOrderCustomizationMap(null);
		order = orderRepository.save(order);
		
		if (orderCustomizationMap != null) {
			for (Long ingredientId : orderCustomizationMap.keySet()) {
				orderCustomization = orderCustomizationMap.get(ingredientId);
				orderCustomization.setOrderCustomizationId(new OrderCustomizationId());
				orderCustomization.getOrderCustomizationId().setIngredientId(ingredientId);
				orderCustomization.getOrderCustomizationId().setOrder(order);
			}
			
			orderCustomizationRepository.saveAll(orderCustomizationMap.values());
			
			order.setOrderCustomizationMap(orderCustomizationMap);
		}
		
		return order;
	}
	
	/*
	 * Return order from database.
	 */
	@Override
	public Order getOrder(Long id) {
		return orderRepository.findById(id).get();
	}
	
	/*
	 * > Reverse order ingredients from inventory consuming ingredient resource via HTTP REST.
	 * > Delete order from database.
	 */
	@Override
	public void deleteOrder(Long id) {
		ingredientClientService.reverseOrderIngredients(orderRepository.findById(id).get());
		
		orderRepository.deleteById(id);
	}
}