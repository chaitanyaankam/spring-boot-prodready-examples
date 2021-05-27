--- land insert statements
insert into land (survey_no, area, unit, address_line1, land_mark, city, state, postal_zip_code, number_of_farms, description)
values('240/D/1', 1, 'acre', 'Raidurgam','near bio-diversity park', 'Hyderabad', 'Telangana', '500032', 5, 'This is sample description');

-- land owner insert statements
insert into land_owner (owner_national_identifier, id_type, first_name, middle_name, last_name, mobile_number, alt_mobile_number, email)
values('azrpa3405k','PAN','harsh', 'kumar', 'chitte', '967454878', '430982922', 'harsh.chitte@gmail.com');

--mapping table of land and land owner
insert into land_landOwner (land_id, owner_id) values(1,'azrpa3405k');