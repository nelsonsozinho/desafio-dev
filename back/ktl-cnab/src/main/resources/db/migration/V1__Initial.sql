CREATE TABLE public.users_roles (
    user_id character varying(255) NOT NULL,
    role_id character varying(255) NOT NULL
);

CREATE TABLE public.oauth_role (
    id character varying(255) NOT NULL,
    description character varying(255),
    role_name character varying(255)
);

CREATE TABLE public.oauth_user (
    id character varying(255) NOT NULL,
    first_name character varying(255),
    last_name character varying(255),
    password character varying(255),
    username character varying(255)
);


ALTER TABLE ONLY public.oauth_user
    ADD CONSTRAINT uk_egqusy937sbn54ed75mhknm71 UNIQUE (username);

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT users_roles_pkey PRIMARY KEY (user_id, role_id);

ALTER TABLE ONLY public.oauth_role
    ADD CONSTRAINT cnab_role_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.oauth_user
    ADD CONSTRAINT cnab_user_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT fk3b0gkanmye6xr4cgeu14xyvcy FOREIGN KEY (user_id) REFERENCES public.oauth_user(id);

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT fkk1lygo9axoq4nb9i2q0kbv1h1 FOREIGN KEY (role_id) REFERENCES public.oauth_role(id);
