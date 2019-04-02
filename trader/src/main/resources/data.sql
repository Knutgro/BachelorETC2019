INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_PM');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');


INSERT INTO `company` (`id`, `name`, `org_nr`, `phone_number`) VALUES ('1', 'Dibbert and Sons', '', '083.588.3028x948');
INSERT INTO `company` (`id`, `name`, `org_nr`, `phone_number`) VALUES ('2', 'Labadie LLC', '2771', '(334)085-9876x6639');

INSERT INTO `region` (`id`, `name`) VALUES ('1', 'NorthDakota');
INSERT INTO `region` (`id`, `name`) VALUES ('2', 'Arkansas');

INSERT INTO `company_region` (`company_id`, `region_id`) VALUES ('1', '1');
INSERT INTO `company_region` (`company_id`, `region_id`) VALUES ('2', '2');