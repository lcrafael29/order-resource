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

package com.pizzaordering.order.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Order customization composite id.
 * 
 * @author Rafael Lima Costa
 *
 */
@Embeddable
public class OrderCustomizationId implements Serializable {
	
	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Id of ingredient.
	 */
	@Column(nullable = false)
	@JsonIgnore
	private Long ingredientId;
	
	/**
	 * Order.
	 */
	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	@JsonIgnore
	private Order order;
	
	/**
	 * Constructor.
	 */
	public OrderCustomizationId() {
	}

	/**
	 * Get id of ingredient.
	 * 
	 * @return Id of ingredient.
	 */
	public Long getIngredientId() {
		return ingredientId;
	}

	/**
	 * Set id of ingredient.
	 * 
	 * @param ingredientId Id of ingredient.
	 */
	public void setIngredientId(Long ingredientId) {
		this.ingredientId = ingredientId;
	}

	/**
	 * Get order.
	 * 
	 * @return Order.
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * Set order.
	 * 
	 * @param order Order.
	 */
	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ingredientId == null) ? 0 : ingredientId.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderCustomizationId other = (OrderCustomizationId) obj;
		if (ingredientId == null) {
			if (other.ingredientId != null)
				return false;
		} else if (!ingredientId.equals(other.ingredientId))
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		return true;
	}
}