-- Users Table

CREATE TABLE public.users
(
    id serial NOT NULL,
    username character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    role character varying(255) NOT NULL,
    created_at TYPE timestamp with time zone NOT NULL,
    updated_at TYPE timestamp with time zone NOT NULL,
    PRIMARY KEY (id)
);

-- Ebook Table

CREATE TABLE public.ebook
(
    id serial NOT NULL,
    book_title character varying(255) NOT NULL,
    book_title_kana character varying(255) NOT NULL,
    level character varying(255) NOT NULL,
    created_at timestamp with time zone NOT NULL,
    updated_at timestamp with time zone NOT NULL,
    PRIMARY KEY (id)
);

-- Practice Table

CREATE TABLE public.practice
(
    id serial NOT NULL,
    question character varying(255) NOT NULL,
    choices character varying(255) NOT NULL,
    answer character varying(255) NOT NULL,
    level character varying(255) NOT NULL,
    PRIMARY KEY (id)
);

-- Result Table

CREATE TABLE public.result
(
    id serial NOT NULL,
    user_id integer NOT NULL,
    result integer NOT NULL,
    level character varying(255) NOT NULL,
    created_at timestamp with time zone NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);
