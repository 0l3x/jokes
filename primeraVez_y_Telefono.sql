-- Crear secuencia para la tabla primera_vez
CREATE SEQUENCE public.seq_primera_vez
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
    CYCLE;

-- Crear tabla primera_vez
CREATE TABLE public.primera_vez (
    id INTEGER DEFAULT nextval('public.seq_primera_vez'::regclass) NOT NULL,
    programa VARCHAR NOT NULL,
    fecha_emision DATE NOT NULL,
    idjoke INTEGER UNIQUE,
    CONSTRAINT pk_primera_vez PRIMARY KEY (id),
    CONSTRAINT fk_primera_vez_joke FOREIGN KEY (idjoke) REFERENCES public.jokes(id)
);


-- Crear secuencia para la tabla telefonos
CREATE SEQUENCE public.seq_telefonos
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
    CYCLE;

ALTER TABLE public.seq_telefonos OWNER TO postgres;


-- Crear tabla telefonos
CREATE TABLE public.telefonos (
    id INTEGER DEFAULT nextval('public.seq_telefonos'::regclass) NOT NULL,
    numero VARCHAR NOT NULL,
    idprimeravez INTEGER NOT NULL,
    CONSTRAINT pk_telefonos PRIMARY KEY (id),
    CONSTRAINT fk_telefonos_primera_vez FOREIGN KEY (idprimeravez)
        REFERENCES public.primera_vez(id) ON DELETE CASCADE
);