/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  ander
 * Created: 01/06/2021
 */

INSERT INTO `ventas_db`.`articulos` (`cod_barras`, `descripcion`, `precio`, `stock`) VALUES ('11111111', 'Cerveza Brahma', '120', '40');
INSERT INTO `ventas_db`.`articulos` (`cod_barras`, `descripcion`, `precio`, `stock`) VALUES ('22222222', 'Coca Cola 2.25', '210', '55');
INSERT INTO `ventas_db`.`articulos` (`cod_barras`, `descripcion`, `precio`, `stock`) VALUES ('33333333', 'Pan Lactal 1kg', '85', '15');

INSERT INTO `user` (`email`, `first_name`, `last_name`, `password`, `username`) VALUES ('admin@admin.com', 'admin', 'admin', '$2a$04$n6WIRDQlIByVFi.5rtQwEOTAzpzLPzIIG/O6quaxRKY2LlIHG8uty', 'admin');


INSERT INTO `role` (`description`, `name`) VALUES ('ROLE_ADMIN', 'ADMIN');
INSERT INTO `role` (`description`, `name`) VALUES ('ROLE_USER', 'USER');

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES ('1', '1');