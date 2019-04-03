

INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_PM');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');


INSERT INTO `company` (`id`, `name`, `org_nr`, `phone_number`) VALUES ('1', 'Dibbert and Sons', '', '083.588.3028x948');
INSERT INTO `company` (`id`, `name`, `org_nr`, `phone_number`) VALUES ('2', 'Labadie LLC', '2771', '(334)085-9876x6639');

INSERT INTO `region` (`id`, `name`) VALUES ('1', 'NorthDakota');
INSERT INTO `region` (`id`, `name`) VALUES ('2', 'Arkansas');

INSERT INTO `company_region` (`company_id`, `region_id`) VALUES ('1', '1');
INSERT INTO `company_region` (`company_id`, `region_id`) VALUES ('2', '2');


INSERT INTO `brand` (`id`, `name`, `origin`) VALUES ('1', 'Tesla', 'USA');
INSERT INTO `brand` (`id`, `name`, `origin`) VALUES ('2', 'Audi', 'Germany');
INSERT INTO `brand` (`id`, `name`, `origin`) VALUES ('3', 'Citroën', 'France');
INSERT INTO `brand` (`id`, `name`, `origin`) VALUES ('4', 'Škoda', 'Czech Republic');


INSERT INTO `model` (`id`, `name`, `m_year`, `brand_id`) VALUES ('1', 'Skoda Octavia', 2017, '4');
INSERT INTO `model` (`id`, `name`, `m_year`, `brand_id`) VALUES ('2', 'Skoda Octavia', 2013, '4');


INSERT INTO `type_data` (`id`, `type_name`, `body_type`, `engine_effect`, `engine_volume`, `fuel`, `co2`,
                        `transmission`, `wheel_drive`, `size_of_boot`, `weight`, `no_of_doors`, `no_of_seats`, `model_id`)
             VALUES ('1', '1.4 TSI 6MT (150 HP)', 'liftback sedan', '150', '1395', 'Gasoline', '121', '6-speed manual', 'Front wheel drive',
                    '589', '1255', '5', '5', '1');

INSERT INTO `type_data` (`id`, `type_name`, `body_type`, `engine_effect`, `engine_volume`, `fuel`, `co2`,
                        `transmission`, `wheel_drive`, `size_of_boot`, `weight`, `no_of_doors`, `no_of_seats`, `model_id`)
             VALUES ('2', '2.0 TDI 6MT (150 HP)', 'liftback sedan', '150', '1968', 'Diesel', '113', '6-speed manual',
              'Front wheel drive', '589', '1332', '5', '5', '2');


INSERT INTO `vehicle` (`id`, `reg_no`, `warranty`,
                      `warranty_duration`, `warranty_distance`, `warranty_url`, `car_premium`, `car_premium_link`,
                      `exterior_color_main`, `interior_color`, `tires`,`chassis_id`,`mileage`,
                      `service_history`, `service_plan_followed`, `finance_partner`, `insurance_partner`,
                      `company_id`, `model_id`)
              VALUES ('1', '123456XZ', 'basic', '22', '200', '123.no', 'blabla', 'blabla.no',
                         'Blue', 'Black', '205/55 R16 91V', '123456789', '112', True, True, 'Storebrand', 'Storebrand',
                         '1', '1');

INSERT INTO `vehicle` (`id`, `reg_no`, `warranty`,
                      `warranty_duration`, `warranty_distance`, `warranty_url`, `car_premium`, `car_premium_link`,
                      `exterior_color_main`, `interior_color`, `tires`,`chassis_id`,`mileage`,
                      `service_history`, `service_plan_followed`, `finance_partner`, `insurance_partner`,
                      `company_id`, `model_id`)
              VALUES ('2', '123456XY', 'basic', '22', '200', '123.no', 'blabla', 'blabla.no',
                         'Black', 'Beige', '205/55 R16 91V', '54321578', '112', True, True, 'DNB', 'DNB',
                         '1', '1');