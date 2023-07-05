CREATE TABLE public.cnab_transaction_type (
    id character varying(255) NOT NULL,
    type integer,
    description character varying(255),
    nature character varying(10),
    signal character varying(1),
    last_modified_at timestamp without time zone
);

CREATE TABLE public.cnab_transaction (
    id character varying(255) NOT NULL,
    data timestamp without time zone,
    value double precision NOT NULL,
    cpf character varying(15),
    card_number character varying(12),
    last_modified_at timestamp without time zone,
    id_type character varying(255)
);


ALTER TABLE ONLY public.cnab_transaction_type
    ADD CONSTRAINT cnab_transaction_type_type UNIQUE (type);

ALTER TABLE ONLY public.cnab_transaction_type
    ADD CONSTRAINT cnab_transaction_type_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.cnab_transaction
    ADD CONSTRAINT cnab_transaction_type_fkey FOREIGN KEY (id_type) REFERENCES public.cnab_transaction_type(id);