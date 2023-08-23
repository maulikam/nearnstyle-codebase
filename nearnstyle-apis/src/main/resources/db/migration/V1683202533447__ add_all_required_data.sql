INSERT INTO oauth_client_details(
            client_id, resource_ids, client_secret, scope, authorized_grant_types,
            web_server_redirect_uri, authorities,
            additional_information, autoapprove)
    VALUES ('nearnstyle-client', '', 'hnearnstyle-secret', 'write', 'password,refresh_token', '', '', '{}', '');


INSERT INTO public.ns_role_master (id, "name", state, created_by, created_on, modified_by, modified_on)
VALUES(1,'SUPER ADMIN', 'ACTIVE', -1, '2022-11-23 22:48:08.500', NULL, NULL);

INSERT INTO public.ns_role_master (id, "name", state, created_by, created_on, modified_by, modified_on)
VALUES(2,'ADMIN', 'ACTIVE', -1, '2022-11-23 22:48:08.500', NULL, NULL);

INSERT INTO public.ns_role_master (id, "name", state, created_by, created_on, modified_by, modified_on)
VALUES(3,'SALON ADMIN', 'ACTIVE', -1, '2022-11-23 22:48:08.500', NULL, NULL);

INSERT INTO public.ns_role_master (id, "name", state, created_by, created_on, modified_by, modified_on)
VALUES(4,'USER', 'ACTIVE', -1, '2022-11-23 22:48:08.500', NULL, NULL);


INSERT INTO ns_salon (name, code, address, ad_state, ad_district, ad_city, ad_pincode, state,  latitude, longitude, created_by, created_on, modified_by, modified_on)
VALUES ('RA', 'AR001', '1 Street, Gandhinagar', 'Gujarat', 'Gandhinagar', 'Gandhinagar', '382010', 'ACTIVE', 72.6369, 23.2156, 1, NOW(), 1, NOW());


INSERT INTO ns_salon (name, code, address, ad_state, ad_district, ad_city, ad_pincode, state,  latitude, longitude, created_by, created_on, modified_by, modified_on)
VALUES ('Gujarat Salon', 'GH001', '123 Main Street, Gandhinagar', 'Gujarat', 'Gandhinagar', 'Gandhinagar', '382010', 'ACTIVE', 72.6369, 23.2156, 1, NOW(), 1, NOW());

INSERT INTO ns_salon (name, code, address, ad_state, ad_district, ad_city, ad_pincode, state,  latitude, longitude, created_by, created_on, modified_by, modified_on)
VALUES ('Ahmedabad Salon', 'AC002', '456 Park Avenue, Ahmedabad', 'Gujarat', 'Ahmedabad', 'Ahmedabad', '380001', 'ACTIVE', 72.5714, 23.0225, 1, NOW(), 1, NOW());

INSERT INTO ns_salon ( name, code, address, ad_state, ad_district, ad_city, ad_pincode, state,  latitude, longitude, created_by, created_on, modified_by, modified_on)
VALUES ('Surat Salon Center', 'SHC003', '789 Ocean Drive, Surat', 'Gujarat', 'Surat', 'Surat', '395001', 'ACTIVE', 72.8083, 21.1702, 1, NOW(), 1, NOW());

INSERT INTO ns_salon (name, code, address, ad_state, ad_district, ad_city, ad_pincode, state,  latitude, longitude, created_by, created_on, modified_by, modified_on)
VALUES ('Rajkot Salon', 'RH004', '321 Elm Street, Rajkot', 'Gujarat', 'Rajkot', 'Rajkot', '360001', 'ACTIVE', 70.7984, 22.3052, 1, NOW(), 1, NOW());

INSERT INTO ns_salon (name, code, address, ad_state, ad_district, ad_city, ad_pincode, state,  latitude, longitude, created_by, created_on, modified_by, modified_on)
VALUES ('Vadodara Salon', 'VC005', '987 Maple Avenue, Vadodara', 'Gujarat', 'Vadodara', 'Vadodara', '390001', 'ACTIVE', 73.1812, 22.3072, 1, NOW(), 1, NOW());


INSERT INTO ns_user (user_name, role_id, mobile_number, date_of_birth, email_id, first_name, middle_name, last_name, gender, password, address, state, org_id, no_of_attempts, created_by, created_on, modified_by, modified_on)
VALUES ('mdave',
        (SELECT id FROM ns_role_master WHERE name = 'SUPER ADMIN'),
        '9722210225', '1980-01-01', 'superadmin@example.com', 'John', 'Doe', 'Smith', 'Male', 'KDqBY4fn7NEIsDCpFgttAxql5y+l96z9', '123 Admin Street, City', 'ACTIVE',
        (SELECT id FROM ns_salon WHERE name = 'Argusoft'),
        0, 1, NOW(), 1, NOW());



INSERT INTO
    oauth_client_details(client_id, resource_ids,   client_secret,
        scope,  authorized_grant_types, web_server_redirect_uri,
        authorities,    additional_information, autoapprove)
VALUES
    ('nearnstyle-agent','','nearnstyle-secret','write','password,refresh_token',
        '','','{}','' );

 


 