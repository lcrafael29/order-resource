INSERT INTO ord_order (closed_recipe_id, size, bread_thickness, price) VALUES (2, 'B', 'M', 132.00);

INSERT INTO ord_order (closed_recipe_id, size, bread_thickness, price) VALUES (NULL, 'M', 'S', 22.00);

INSERT INTO ord_order_customization (order_id, ingredient_id, type, portion_quantity, observation) VALUES (2, 1, 'A', 2, 'I like cheese');
INSERT INTO ord_order_customization (order_id, ingredient_id, type, portion_quantity, observation) VALUES (2, 4, 'A', 6, 'A lot of tomato');

INSERT INTO ord_order (closed_recipe_id, size, bread_thickness, price) VALUES (3, 'M', 'S', 105.00);

INSERT INTO ord_order_customization (order_id, ingredient_id, type, portion_quantity, observation) VALUES (3, 1, 'A', 2, 'I like cheese');
INSERT INTO ord_order_customization (order_id, ingredient_id, type, portion_quantity, observation) VALUES (3, 4, 'A', 6, 'A lot of tomato');
INSERT INTO ord_order_customization (order_id, ingredient_id, type, portion_quantity, observation) VALUES (3, 3, 'R', 2, 'I hate cheddar');