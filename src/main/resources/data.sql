INSERT INTO permissions (permission_name) values ("READ"), ("CREATE"), ("UPDATE"), ("DELETE");
INSERT INTO roles (role) values ("ADMIN"), ("AUTHOR"), ("USER");
INSERT INTO roles_permissions (permission_id, role_id) values (1,1), (2,1), (3,1), (4,1), (1,2), (2,2), (3,2), (4,2),(1,3);