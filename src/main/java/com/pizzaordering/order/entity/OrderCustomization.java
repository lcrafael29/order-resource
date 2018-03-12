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

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Order customization entity.
 * 
 * @author Rafael Lima Costa
 *
 */
@Entity
@Table(name = "ord_order_customization")
public class OrderCustomization implements Serializable {
	
	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Composite id of order customization.
	 */
	@EmbeddedId
	@JsonIgnore
	private OrderCustomizationId orderCustomizationId;
	
	/**
	 * Type of customization.
	 */
	private Character type;
	
	/**
	 * Quantity of portion.
	 */
	private Integer portionQuantity;
	
	/**
	 * Observation.
	 */
	private String observation;
	
	/**
	 * Constructor.
	 */
	public OrderCustomization() {
	}

	/**
	 * Get composite id of order customization.
	 * 
	 * @return Composite id of order customization.
	 */
	public OrderCustomizationId getOrderCustomizationId() {
		return orderCustomizationId;
	}

	/**
	 * Set composite id of order customization.
	 * 
	 * @param orderCustomizationId Composite id of order customization.
	 */
	public void setOrderCustomizationId(OrderCustomizationId orderCustomizationId) {
		this.orderCustomizationId = orderCustomizationId;
	}

	/**
	 * Get type of customization.
	 * 
	 * @return Type of customization.
	 */
	public Character getType() {
		return type;
	}

	/**
	 * Set type of customization.
	 * 
	 * @param type Type of customization.
	 */
	public void setType(Character type) {
		this.type = type;
	}

	/**
	 * Get quantity of portion.
	 * 
	 * @return Quantity of portion.
	 */
	public Integer getPortionQuantity() {
		return portionQuantity;
	}

	/**
	 * Set quantity of portion.
	 * 
	 * @param portionQuantity Quantity of portion.
	 */
	public void setPortionQuantity(Integer portionQuantity) {
		this.portionQuantity = portionQuantity;
	}

	/**
	 * Get observation.
	 * 
	 * @return Observation.
	 */
	public String getObservation() {
		return observation;
	}

	/**
	 * Set observation.
	 * 
	 * @param observation Observation.
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((observation == null) ? 0 : observation.hashCode());
		result = prime * result + ((orderCustomizationId == null) ? 0 : orderCustomizationId.hashCode());
		result = prime * result + ((portionQuantity == null) ? 0 : portionQuantity.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		OrderCustomization other = (OrderCustomization) obj;
		if (observation == null) {
			if (other.observation != null)
				return false;
		} else if (!observation.equals(other.observation))
			return false;
		if (orderCustomizationId == null) {
			if (other.orderCustomizationId != null)
				return false;
		} else if (!orderCustomizationId.equals(other.orderCustomizationId))
			return false;
		if (portionQuantity == null) {
			if (other.portionQuantity != null)
				return false;
		} else if (!portionQuantity.equals(other.portionQuantity))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
}