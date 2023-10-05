--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2 (Debian 15.2-1.pgdg110+1)
-- Dumped by pg_dump version 15.2

-- Started on 2023-10-05 13:41:53 UTC

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 217 (class 1259 OID 24580)
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cliente (
    id bigint NOT NULL,
    nascimento date NOT NULL,
    nome character varying(100) NOT NULL,
    endereco character varying(255) NOT NULL,
    cpf character varying(14) NOT NULL,
    telefone character varying(14),
    email character varying(100),
    data_cadastro date
);


ALTER TABLE public.cliente OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 24579)
-- Name: cliente_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cliente_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cliente_id_seq OWNER TO postgres;

--
-- TOC entry 3363 (class 0 OID 0)
-- Dependencies: 216
-- Name: cliente_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cliente_id_seq OWNED BY public.cliente.id;


--
-- TOC entry 221 (class 1259 OID 32817)
-- Name: item_venda; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.item_venda (
    id bigint NOT NULL,
    id_venda bigint NOT NULL,
    id_produto bigint NOT NULL,
    quantidade integer NOT NULL
);


ALTER TABLE public.item_venda OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 32816)
-- Name: item_venda_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.item_venda_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.item_venda_id_seq OWNER TO postgres;

--
-- TOC entry 3364 (class 0 OID 0)
-- Dependencies: 220
-- Name: item_venda_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.item_venda_id_seq OWNED BY public.item_venda.id;


--
-- TOC entry 215 (class 1259 OID 16388)
-- Name: produto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.produto (
    id bigint NOT NULL,
    nome character varying(100) NOT NULL,
    descricao character varying(255),
    preco numeric(16,2),
    sku character varying(20),
    data_cadastro date
);


ALTER TABLE public.produto OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 16387)
-- Name: produto_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.produto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.produto_id_seq OWNER TO postgres;

--
-- TOC entry 3365 (class 0 OID 0)
-- Dependencies: 214
-- Name: produto_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.produto_id_seq OWNED BY public.produto.id;


--
-- TOC entry 219 (class 1259 OID 32803)
-- Name: venda; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.venda (
    id bigint NOT NULL,
    id_cliente bigint NOT NULL,
    forma_pagamento character varying(20) NOT NULL,
    total numeric(16,2) NOT NULL,
    data_venda timestamp without time zone DEFAULT now(),
    CONSTRAINT venda_forma_pagamento_check CHECK (((forma_pagamento)::text = ANY (ARRAY[('DINHEIRO'::character varying)::text, ('PIX'::character varying)::text, ('CARTAO_CREDITO'::character varying)::text, ('BOLETO'::character varying)::text])))
);


ALTER TABLE public.venda OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 32802)
-- Name: venda_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.venda_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.venda_id_seq OWNER TO postgres;

--
-- TOC entry 3366 (class 0 OID 0)
-- Dependencies: 218
-- Name: venda_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.venda_id_seq OWNED BY public.venda.id;


--
-- TOC entry 3192 (class 2604 OID 24583)
-- Name: cliente id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente ALTER COLUMN id SET DEFAULT nextval('public.cliente_id_seq'::regclass);


--
-- TOC entry 3195 (class 2604 OID 32820)
-- Name: item_venda id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_venda ALTER COLUMN id SET DEFAULT nextval('public.item_venda_id_seq'::regclass);


--
-- TOC entry 3191 (class 2604 OID 16391)
-- Name: produto id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produto ALTER COLUMN id SET DEFAULT nextval('public.produto_id_seq'::regclass);


--
-- TOC entry 3193 (class 2604 OID 32806)
-- Name: venda id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.venda ALTER COLUMN id SET DEFAULT nextval('public.venda_id_seq'::regclass);


--
-- TOC entry 3353 (class 0 OID 24580)
-- Dependencies: 217
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cliente (id, nascimento, nome, endereco, cpf, telefone, email, data_cadastro) FROM stdin;
6	1987-01-27	BRUNO MOREIRA DE ARAUJO	Rua Varandas Residencial Meridian BL 10 AP 202	987.654.987-96	(61)8754-6565	bruno130187@gmail.com	2023-08-19
8	1987-01-25	TEste3	endereço	987.654.987-99	(61)98989-8989	Email@email.com	2023-08-18
9	1984-05-27	Nome atualizado	Rua cabeça feita com ouro na carteira e saúde	787.878.456-44	(61)3589-7845	teste@email.com	2023-08-18
10	1987-01-27	BRUNO MOREIRA DE ARAUJO	Rua Varandas Residencial Meridian BL 10 AP 202	987.654.987-96	(61)8754-6565	bruno130187@gmail.com	2023-08-19
1	1987-01-27	BRUNO MOREIRA DE ARAUJO	Rua Varandas Residencial Meridian BL 10 AP 202	987.654.987-96	(61)8754-6565	bruno130187@gmail.com	2023-08-19
2	1987-01-25	TEste3	endereço	987.654.987-99	(61)98989-8989	Email@email.com	2023-08-18
3	1984-05-27	Nome atualizado	Rua cabeça feita com ouro na carteira e saúde	787.878.456-44	(61)3589-7845	teste@email.com	2023-08-18
11	1987-01-27	BRUNO MOREIRA DE ARAUJO	Rua Varandas Residencial Meridian BL 10 AP 202	987.654.987-96	(61)8754-6565	bruno130187@gmail.com	2023-08-19
22	1987-01-25	TEste3	endereço	987.654.987-99	(61)98989-8989	Email@email.com	2023-08-18
12	1984-05-27	Nome atualizado	Rua cabeça feita com ouro na carteira e saúde	787.878.456-44	(61)3589-7845	teste@email.com	2023-08-18
13	1987-01-27	BRUNO MOREIRA DE ARAUJO	Rua Varandas Residencial Meridian BL 10 AP 202 e3	987.654.987-96	(61)8754-6565	bruno130187@gmail.com	2023-08-22
4	1987-01-27	BRUNO 2 MOREIRA DE ARAUJO	Rua Varandas Residencial Meridian BL 10 AP 202	987.654.987-96	(61)8754-6565	bruno130187@gmail.com	2023-08-19
\.


--
-- TOC entry 3357 (class 0 OID 32817)
-- Dependencies: 221
-- Data for Name: item_venda; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.item_venda (id, id_venda, id_produto, quantidade) FROM stdin;
1	5	23	2
2	6	15	3
3	7	25	2
4	8	19	4
5	8	25	2
6	9	25	2
7	10	25	10
8	11	25	10
9	12	15	4
10	13	23	2
\.


--
-- TOC entry 3351 (class 0 OID 16388)
-- Dependencies: 215
-- Data for Name: produto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.produto (id, nome, descricao, preco, sku, data_cadastro) FROM stdin;
19	nome 5	descrição 5	10.00	sku 5	2023-08-01
16	Produto 18	Descrição Y asdas dasd edit 18	89.52	09130910rtes	2023-08-01
15	asdasd asdasd as da sd as	asdasd das as dasdasd	5655.61	asdasd123123	2023-08-02
23	asdasd	asdasdasasasdasd asdlkjalksdj	46546.54	asdasdq313	2023-08-17
17	nome 4 descrição são só	descrição 4 do produto que é o melhor da categoria.	1584.00	sku 4	2023-08-25
25	Teclado mecânico	Teclado mecânico ABNT2 com iluminação chinês	250.80	asdasd2313	2023-09-15
26	Cabo usb tipo c	Cabo usb tipo c para computador ou smartphone	89.90	65464dsfd	2023-09-24
27	teclado microsoft 	teclado microsoft  abnt2 completo	100.00	asdas89	2023-09-24
28	Monitor Gamer LG 32	Monitor Gamer LG 32 144Hz 4k	1987.00	asdas234	2023-10-05
\.


--
-- TOC entry 3355 (class 0 OID 32803)
-- Dependencies: 219
-- Data for Name: venda; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.venda (id, id_cliente, forma_pagamento, total, data_venda) FROM stdin;
10	22	BOLETO	2508.00	2023-09-15 16:15:22.044032
11	4	BOLETO	0.00	2023-09-15 16:35:38.102286
12	9	CARTAO_CREDITO	22622.44	2023-09-15 16:36:10.179964
5	4	DINHEIRO	93093.08	2023-08-10 15:04:36.745561
6	4	BOLETO	16966.83	2023-07-20 15:44:34.355193
7	9	CARTAO_CREDITO	501.60	2023-09-01 16:10:52.024414
8	9	PIX	541.60	2023-06-09 16:12:44.975918
9	22	DINHEIRO	501.60	2023-05-30 16:14:10.37026
13	4	CARTAO_CREDITO	93093.08	2023-09-24 12:39:17.124263
\.


--
-- TOC entry 3367 (class 0 OID 0)
-- Dependencies: 216
-- Name: cliente_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cliente_id_seq', 7, true);


--
-- TOC entry 3368 (class 0 OID 0)
-- Dependencies: 220
-- Name: item_venda_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.item_venda_id_seq', 10, true);


--
-- TOC entry 3369 (class 0 OID 0)
-- Dependencies: 214
-- Name: produto_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.produto_id_seq', 28, true);


--
-- TOC entry 3370 (class 0 OID 0)
-- Dependencies: 218
-- Name: venda_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.venda_id_seq', 13, true);


--
-- TOC entry 3200 (class 2606 OID 24585)
-- Name: cliente cliente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id);


--
-- TOC entry 3204 (class 2606 OID 32822)
-- Name: item_venda item_venda_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_venda
    ADD CONSTRAINT item_venda_pkey PRIMARY KEY (id);


--
-- TOC entry 3198 (class 2606 OID 16393)
-- Name: produto produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produto
    ADD CONSTRAINT produto_pkey PRIMARY KEY (id);


--
-- TOC entry 3202 (class 2606 OID 32810)
-- Name: venda venda_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.venda
    ADD CONSTRAINT venda_pkey PRIMARY KEY (id);


--
-- TOC entry 3206 (class 2606 OID 32828)
-- Name: item_venda item_venda_id_produto_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_venda
    ADD CONSTRAINT item_venda_id_produto_fkey FOREIGN KEY (id_produto) REFERENCES public.produto(id);


--
-- TOC entry 3207 (class 2606 OID 32823)
-- Name: item_venda item_venda_id_venda_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_venda
    ADD CONSTRAINT item_venda_id_venda_fkey FOREIGN KEY (id_venda) REFERENCES public.venda(id);


--
-- TOC entry 3205 (class 2606 OID 32811)
-- Name: venda venda_id_cliente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.venda
    ADD CONSTRAINT venda_id_cliente_fkey FOREIGN KEY (id_cliente) REFERENCES public.cliente(id);


-- Completed on 2023-10-05 13:41:53 UTC

--
-- PostgreSQL database dump complete
--

