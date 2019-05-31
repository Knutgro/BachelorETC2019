

INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_PM');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');


INSERT INTO `company` (`id`, `name`, `org_nr`, `phone_number`) VALUES ('1', 'Dibbert and Sons', '', '083.588.3028x948');
INSERT INTO `company` (`id`, `name`, `org_nr`, `phone_number`) VALUES ('2', 'Labadie LLC', '2771', '(334)085-9876x6639');

INSERT INTO `region` (`id`, `name`) VALUES ('1', 'Agder');
INSERT INTO `region` (`id`, `name`) VALUES ('2', 'Innlandet');
INSERT INTO `region` (`id`, `name`) VALUES ('3', 'Møre og Romsdal');
INSERT INTO `region` (`id`, `name`) VALUES ('4', 'Nordland');
INSERT INTO `region` (`id`, `name`) VALUES ('5', 'Oslo');
INSERT INTO `region` (`id`, `name`) VALUES ('6', 'Rogaland');
INSERT INTO `region` (`id`, `name`) VALUES ('7', 'Troms og Finnmark');
INSERT INTO `region` (`id`, `name`) VALUES ('8', 'Trøndelag');
INSERT INTO `region` (`id`, `name`) VALUES ('9', 'Vestfold og Telemark');
INSERT INTO `region` (`id`, `name`) VALUES ('10', 'Vestland');
INSERT INTO `region` (`id`, `name`) VALUES ('11', 'Viken');

INSERT INTO `company_region` (`company_id`, `region_id`) VALUES ('1', '1');
INSERT INTO `company_region` (`company_id`, `region_id`) VALUES ('2', '2');


INSERT INTO `brand` (`id`, `name`, `origin`) VALUES ('1', 'Tesla', 'USA');
INSERT INTO `brand` (`id`, `name`, `origin`) VALUES ('2', 'Audi', 'Germany');
INSERT INTO `brand` (`id`, `name`, `origin`) VALUES ('3', 'Citroën', 'France');
INSERT INTO `brand` (`id`, `name`, `origin`) VALUES ('4', 'Škoda', 'Czech Republic');


INSERT INTO `model` (`id`, `name`, `m_year`, `brand_id`) VALUES ('1', 'Skoda Octavia', 2017, '4');
INSERT INTO `model` (`id`, `name`, `m_year`, `brand_id`) VALUES ('2', 'Skoda Octavia', 2013, '4');


INSERT INTO `type_data` (`id`, `type_name`, `body_type`, `engine_effect`, `engine_volume`, `fuel`, `co_2`,
                        `transmission`, `wheel_drive`, `size_of_boot`, `weight`, `no_of_doors`, `no_of_seats`, `model_id`)
             VALUES ('1', '1.4 TSI 6MT (150 HP)', 'liftback sedan', '150', '1395', 'Gasoline', '121', '6-speed manual', 'Front wheel drive',
                    '589', '1255', '5', '5', '1');

INSERT INTO `type_data` (`id`, `type_name`, `body_type`, `engine_effect`, `engine_volume`, `fuel`, `co_2`,
                        `transmission`, `wheel_drive`, `size_of_boot`, `weight`, `no_of_doors`, `no_of_seats`, `model_id`)
             VALUES ('2', '2.0 TDI 6MT (150 HP)', 'liftback sedan', '150', '1968', 'Diesel', '113', '6-speed manual',
              'Front wheel drive', '589', '1332', '5', '5', '2');


INSERT INTO `vehicle` (`id`, `reg_no`, `warranty`,
                      `warranty_duration`, `warranty_distance`, `warranty_url`, `car_premium`, `car_premium_link`,
                      `exterior_color_main`, `interior_color`, `tires`,`chassis_id`,`mileage`,
                      `service_history`, `service_plan_followed`, `finance_partner`, `insurance_partner`,
                      `company_id`, `typeData_id`)
              VALUES ('1', '123456XZ', 'basic', '22', '200', '123.no', 'blabla', 'blabla.no',
                         'Blue', 'Black', '205/55 R16 91V', '123456789', '112', True, True, 'Storebrand', 'Storebrand',
                         '1', '1');

INSERT INTO `vehicle` (`id`, `reg_no`, `warranty`,
                      `warranty_duration`, `warranty_distance`, `warranty_url`, `car_premium`, `car_premium_link`,
                      `exterior_color_main`, `interior_color`, `tires`,`chassis_id`,`mileage`,
                      `service_history`, `service_plan_followed`, `finance_partner`, `insurance_partner`,
                      `company_id`, `typeData_id`)
              VALUES ('2', '123456XY', 'basic', '22', '200', '123.no', 'blabla', 'blabla.no',
                         'Black', 'Beige', '205/55 R16 91V', '54321578', '112', True, True, 'DNB', 'DNB',
                         '1', '1');

 INSERT INTO `listing` (`id`, `car_condition`, `currency`,`description`,`keywords`,`net_price`,`no_of_owners`,`registration`,
                        `registration_exemption`, `registration_tax_included`, `title`, `total_price`, `url_text`,
                        `vat_included`, `company_id`, `vehicle_id`)
              VALUES ('1', 'Good', 'NOK', 'This is a car description describing a car', 'keyword', '69000', '4', 'registration',
                      '1', '1', 'KJØP BILEN MIN ER DU SNILL!', '70000', 'www.vg.no', True, '1', '1');

INSERT INTO `users` (`id`, `activated`, `email`, `firstName`, `lastName`, `password`, `username`, `company_id`)
              VALUES (1, 1, 'admin@admin.no', 'firstname', 'lastname', '$2a$10$MyYmv7RxqCIqg5fq.PBhyetrEJUUHsuwqa.z4IdcSxLfXJpf0ysES',
              'admin', '1');

INSERT INTO `user_roles` (`user_id`, `role_id`)
              VALUES (1, 3);

INSERT INTO `users` (`id`, `activated`, `email`, `firstName`, `lastName`, `password`, `username`, `company_id`)
              VALUES (2, 1, 'user@user.no', 'firstname', 'lastname', '$2a$10$dxGcs64MvEsLDfdll5iBpO1bcVZqFbeZi/JbsL8dhclW.52wfrPVO',
              'user', '2');

INSERT INTO `user_roles` (`user_id`, `role_id`)
              VALUES (2, 1);

