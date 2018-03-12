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

package com.pizzaordering.order;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.pizzaordering.order.data.OrderCustomizationRepository;
import com.pizzaordering.order.data.OrderRepository;
import com.pizzaordering.order.entity.Order;
import com.pizzaordering.order.entity.OrderCustomization;
import com.pizzaordering.order.entity.OrderCustomizationId;
import com.pizzaordering.order.service.IngredientClientService;
import com.pizzaordering.order.service.OrderService;

/**
 * Unit test of order service layer.
 * 
 * @author Rafael Lima Costa
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {
	
	/**
	 * Interface of order service layer.
	 */
	@Autowired
	OrderService orderService;
	
	/**
	 * Interface of ingredient client service layer mocked.
	 */
	@MockBean
	IngredientClientService ingredientClientService;
	
	/**
	 * Interface of order repository layer mocked.
	 */
	@MockBean
	OrderRepository orderRepository;
	
	/**
	 * Interface of order customization repository layer mocked.
	 */
	@MockBean
	OrderCustomizationRepository orderCustomizationRepository;
	
	/**
	 * Test add order method when a closed recipe is used on request without customized ingredients:
	 * 
	 * > Mock database and external calls of this flow.
	 * > Test method sending input and comparing returned output with expected output.
	 */
	@Test
	public void addOrderClosedRecipeTest() {
		Order order = null;
		BigDecimal price = BigDecimal.valueOf(30L);
		Order orderSaved = null;
		Order orderExpected = null;
		
		// Mock ingredientClientService.calculateOrderPrice(order) call.
		order = new Order();
		order.setClosedRecipeId(1);
		order.setSize('M');
		order.setBreadThickness('S');
		
		Mockito.when(ingredientClientService.calculateOrderPrice(order)).thenReturn(price);
		
		// Mock orderRepository.save(order) call.
		order = new Order();
		order.setClosedRecipeId(1);
		order.setSize('M');
		order.setBreadThickness('S');
		order.setPrice(price);
		
		orderSaved = new Order();
		orderSaved.setId(1L);
		orderSaved.setClosedRecipeId(1);
		orderSaved.setSize('M');
		orderSaved.setBreadThickness('S');
		orderSaved.setPrice(price);
		
		Mockito.when(orderRepository.save(order)).thenReturn(orderSaved);
		
		// Input.
		order = new Order();
		order.setClosedRecipeId(1);
		order.setSize('M');
		order.setBreadThickness('S');
		
		// Output.
		orderExpected = new Order();
		orderExpected.setId(1L);
		orderExpected.setClosedRecipeId(1);
		orderExpected.setSize('M');
		orderExpected.setBreadThickness('S');
		orderExpected.setPrice(price);
		
		// Test.
		assertThat(orderService.addOrder(order)).isEqualTo(orderExpected);
	}
	
	/**
	 * Test add order method when customized ingredients are used on request without a closed recipe:
	 * 
	 * > Mock database and external calls of this flow.
	 * > Test method sending input and comparing returned output with expected output.
	 */
	@Test
	public void addOrderCustomizedIngredientsTest() {
		Order order = null;
		BigDecimal price = BigDecimal.valueOf(30L);
		Order orderSaved = null;
		OrderCustomization orderCustomization = null;
		Map<Long, OrderCustomization> orderCustomizationMap = null;
		Order orderExpected = null;
		
		// Mock ingredientClientService.calculateOrderPrice(order) call.
		order = new Order();
		order.setSize('M');
		order.setBreadThickness('S');
		
		Mockito.when(ingredientClientService.calculateOrderPrice(order)).thenReturn(price);
		
		// Mock orderRepository.save(order) call.
		order = new Order();
		order.setSize('M');
		order.setBreadThickness('S');
		order.setPrice(price);
		
		orderSaved = new Order();
		orderSaved.setId(1L);
		orderSaved.setSize('M');
		orderSaved.setBreadThickness('S');
		orderSaved.setPrice(price);
		
		Mockito.when(orderRepository.save(order)).thenReturn(orderSaved);
		
		// Mock orderCustomizationRepository.saveAll(orderCustomizationMap.values()) call.
		orderSaved = new Order();
		orderSaved.setId(1L);
		orderSaved.setSize('M');
		orderSaved.setBreadThickness('S');
		orderSaved.setPrice(price);
		
		orderCustomization = new OrderCustomization();
		orderCustomization.setOrderCustomizationId(new OrderCustomizationId());
		orderCustomization.getOrderCustomizationId().setIngredientId(1L);
		orderCustomization.getOrderCustomizationId().setOrder(orderSaved);
		orderCustomization.setType('A');
		orderCustomization.setPortionQuantity(3);
		orderCustomization.setObservation("A little bit melted.");
		
		orderCustomizationMap = new HashMap<Long, OrderCustomization>();
		orderCustomizationMap.put(1L, orderCustomization);
		
		orderCustomization = new OrderCustomization();
		orderCustomization.setOrderCustomizationId(new OrderCustomizationId());
		orderCustomization.getOrderCustomizationId().setIngredientId(2L);
		orderCustomization.getOrderCustomizationId().setOrder(orderSaved);
		orderCustomization.setType('R');
		orderCustomization.setPortionQuantity(1);
		orderCustomization.setObservation("I have allergy to cheese.");
		
		orderCustomizationMap.put(2L, orderCustomization);
		
		Mockito.when(orderCustomizationRepository.saveAll(orderCustomizationMap.values())).thenReturn(orderCustomizationMap.values());
		
		// Input.
		order = new Order();
		order.setSize('M');
		order.setBreadThickness('S');
		
		orderCustomization = new OrderCustomization();
		orderCustomization.setType('A');
		orderCustomization.setPortionQuantity(3);
		orderCustomization.setObservation("A little bit melted.");
		
		orderCustomizationMap = new HashMap<Long, OrderCustomization>();
		orderCustomizationMap.put(1L, orderCustomization);
		
		orderCustomization = new OrderCustomization();
		orderCustomization.setType('R');
		orderCustomization.setPortionQuantity(1);
		orderCustomization.setObservation("I have allergy to cheese.");
		
		orderCustomizationMap.put(2L, orderCustomization);
		
		order.setOrderCustomizationMap(orderCustomizationMap);
		
		// Output.
		orderExpected = new Order();
		orderExpected.setId(1L);
		orderExpected.setSize('M');
		orderExpected.setBreadThickness('S');
		orderExpected.setPrice(price);
		
		orderCustomization = new OrderCustomization();
		orderCustomization.setOrderCustomizationId(new OrderCustomizationId());
		orderCustomization.getOrderCustomizationId().setIngredientId(1L);
		orderCustomization.getOrderCustomizationId().setOrder(orderExpected);
		orderCustomization.setType('A');
		orderCustomization.setPortionQuantity(3);
		orderCustomization.setObservation("A little bit melted.");
		
		orderCustomizationMap = new HashMap<Long, OrderCustomization>();
		orderCustomizationMap.put(1L, orderCustomization);
		
		orderCustomization = new OrderCustomization();
		orderCustomization.setOrderCustomizationId(new OrderCustomizationId());
		orderCustomization.getOrderCustomizationId().setIngredientId(2L);
		orderCustomization.getOrderCustomizationId().setOrder(orderExpected);
		orderCustomization.setType('R');
		orderCustomization.setPortionQuantity(1);
		orderCustomization.setObservation("I have allergy to cheese.");
		
		orderCustomizationMap.put(2L, orderCustomization);
		
		orderExpected.setOrderCustomizationMap(orderCustomizationMap);
		
		// Test.
		assertThat(orderService.addOrder(order)).isEqualTo(orderExpected);
	}
	
	/**
	 * Test add order method when a closed recipe is used on request with customized ingredients:
	 * 
	 * > Mock database and external calls of this flow.
	 * > Test method sending input and comparing returned output with expected output.
	 */
	@Test
	public void addOrderClosedRecipeCustomizedIngredientsTest() {
		Order order = null;
		BigDecimal price = BigDecimal.valueOf(30L);
		Order orderSaved = null;
		OrderCustomization orderCustomization = null;
		Map<Long, OrderCustomization> orderCustomizationMap = null;
		Order orderExpected = null;
		
		// Mock ingredientClientService.calculateOrderPrice(order) call.
		order = new Order();
		order.setClosedRecipeId(1);
		order.setSize('M');
		order.setBreadThickness('S');
		
		Mockito.when(ingredientClientService.calculateOrderPrice(order)).thenReturn(price);
		
		// Mock orderRepository.save(order) call.
		order = new Order();
		order.setClosedRecipeId(1);
		order.setSize('M');
		order.setBreadThickness('S');
		order.setPrice(price);
		
		orderSaved = new Order();
		orderSaved.setId(1L);
		orderSaved.setClosedRecipeId(1);
		orderSaved.setSize('M');
		orderSaved.setBreadThickness('S');
		orderSaved.setPrice(price);
		
		Mockito.when(orderRepository.save(order)).thenReturn(orderSaved);
		
		// Mock orderCustomizationRepository.saveAll(orderCustomizationMap.values()) call.
		orderSaved = new Order();
		orderSaved.setId(1L);
		orderSaved.setSize('M');
		orderSaved.setBreadThickness('S');
		orderSaved.setPrice(price);
		
		orderCustomization = new OrderCustomization();
		orderCustomization.setOrderCustomizationId(new OrderCustomizationId());
		orderCustomization.getOrderCustomizationId().setIngredientId(1L);
		orderCustomization.getOrderCustomizationId().setOrder(orderSaved);
		orderCustomization.setType('A');
		orderCustomization.setPortionQuantity(3);
		orderCustomization.setObservation("A little bit melted.");
		
		orderCustomizationMap = new HashMap<Long, OrderCustomization>();
		orderCustomizationMap.put(1L, orderCustomization);
		
		orderCustomization = new OrderCustomization();
		orderCustomization.setOrderCustomizationId(new OrderCustomizationId());
		orderCustomization.getOrderCustomizationId().setIngredientId(2L);
		orderCustomization.getOrderCustomizationId().setOrder(orderSaved);
		orderCustomization.setType('R');
		orderCustomization.setPortionQuantity(1);
		orderCustomization.setObservation("I have allergy to cheese.");
		
		orderCustomizationMap.put(2L, orderCustomization);
		
		Mockito.when(orderCustomizationRepository.saveAll(orderCustomizationMap.values())).thenReturn(orderCustomizationMap.values());
		
		// Input.
		order = new Order();
		order.setClosedRecipeId(1);
		order.setSize('M');
		order.setBreadThickness('S');
		
		orderCustomization = new OrderCustomization();
		orderCustomization.setType('A');
		orderCustomization.setPortionQuantity(3);
		orderCustomization.setObservation("A little bit melted.");
		
		orderCustomizationMap = new HashMap<Long, OrderCustomization>();
		orderCustomizationMap.put(1L, orderCustomization);
		
		orderCustomization = new OrderCustomization();
		orderCustomization.setType('R');
		orderCustomization.setPortionQuantity(1);
		orderCustomization.setObservation("I have allergy to cheese.");
		
		orderCustomizationMap.put(2L, orderCustomization);
		
		order.setOrderCustomizationMap(orderCustomizationMap);
		
		// Output.
		orderExpected = new Order();
		orderExpected.setId(1L);
		orderExpected.setClosedRecipeId(1);
		orderExpected.setSize('M');
		orderExpected.setBreadThickness('S');
		orderExpected.setPrice(price);
		
		orderCustomization = new OrderCustomization();
		orderCustomization.setOrderCustomizationId(new OrderCustomizationId());
		orderCustomization.getOrderCustomizationId().setIngredientId(1L);
		orderCustomization.getOrderCustomizationId().setOrder(orderExpected);
		orderCustomization.setType('A');
		orderCustomization.setPortionQuantity(3);
		orderCustomization.setObservation("A little bit melted.");
		
		orderCustomizationMap = new HashMap<Long, OrderCustomization>();
		orderCustomizationMap.put(1L, orderCustomization);
		
		orderCustomization = new OrderCustomization();
		orderCustomization.setOrderCustomizationId(new OrderCustomizationId());
		orderCustomization.getOrderCustomizationId().setIngredientId(2L);
		orderCustomization.getOrderCustomizationId().setOrder(orderExpected);
		orderCustomization.setType('R');
		orderCustomization.setPortionQuantity(1);
		orderCustomization.setObservation("I have allergy to cheese.");
		
		orderCustomizationMap.put(2L, orderCustomization);
		
		orderExpected.setOrderCustomizationMap(orderCustomizationMap);
		
		// Test.
		assertThat(orderService.addOrder(order)).isEqualTo(orderExpected);
	}
}