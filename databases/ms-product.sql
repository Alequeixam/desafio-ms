DROP DATABASE IF EXISTS challenge03;

CREATE DATABASE challenge03

CREATE TABLE IF NOT EXISTS public.category
(
    id bigint NOT NULL DEFAULT nextval('category_id_seq'::regclass),
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT category_pkey PRIMARY KEY (id)
)
CREATE TABLE IF NOT EXISTS public.product
(
    price numeric(38,2) NOT NULL,
    date date NOT NULL,
    id bigint NOT NULL DEFAULT nextval('product_id_seq'::regclass),
    description character varying(255) COLLATE pg_catalog."default" NOT NULL,
    img_url character varying(255) COLLATE pg_catalog."default" NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT product_pkey PRIMARY KEY (id)
)
CREATE TABLE IF NOT EXISTS public.product_category
(
    category_id bigint NOT NULL,
    product_id bigint NOT NULL,
    CONSTRAINT product_category_pkey PRIMARY KEY (category_id, product_id),
    CONSTRAINT fk2k3smhbruedlcrvu6clued06x FOREIGN KEY (product_id)
        REFERENCES public.product (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkkud35ls1d40wpjb5htpp14q4e FOREIGN KEY (category_id)
        REFERENCES public.category (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
CREATE TABLE IF NOT EXISTS public.role
(
    id bigint NOT NULL DEFAULT nextval('role_id_seq'::regclass),
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT role_pkey PRIMARY KEY (id)
)
CREATE TABLE IF NOT EXISTS public.tb_user
(
    id bigint NOT NULL DEFAULT nextval('tb_user_id_seq'::regclass),
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    first_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    password character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT tb_user_pkey PRIMARY KEY (id),
    CONSTRAINT tb_user_email_key UNIQUE (email)
)
CREATE TABLE IF NOT EXISTS public.tb_user_roles
(
    roles_id bigint NOT NULL,
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    CONSTRAINT tb_user_roles_pkey PRIMARY KEY (roles_id, user_id),
    CONSTRAINT fk19t64ocsol5x06fy2cytp7gey FOREIGN KEY (user_id)
        REFERENCES public.tb_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk7y4nclm9kbxf0edm7mmtg99as FOREIGN KEY (roles_id)
        REFERENCES public.role (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fktiwtm5noy212x8tfmfwvptqd9 FOREIGN KEY (role_id)
        REFERENCES public.role (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)