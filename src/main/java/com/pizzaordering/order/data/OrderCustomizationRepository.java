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

package com.pizzaordering.order.data;

import org.springframework.data.repository.CrudRepository;

import com.pizzaordering.order.entity.OrderCustomization;
import com.pizzaordering.order.entity.OrderCustomizationId;

/**
 * Interface of order customization repository layer.
 * 
 * @author Rafael Lima Costa
 *
 */
public interface OrderCustomizationRepository extends CrudRepository<OrderCustomization, OrderCustomizationId> {
}