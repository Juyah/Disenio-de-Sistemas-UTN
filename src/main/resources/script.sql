create database rescatedepatitas;
use rescatedepatitas;

CREATE TABLE `contacto`
(
    `id`       int         NOT NULL,
    `apellido` varchar(32) NOT NULL,
    `email`    varchar(64) NOT NULL,
    `nombre`   varchar(32) NOT NULL,
    `telefono` varchar(32) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE `direccion`
(
    `id`       int NOT NULL,
    `latitud`  double DEFAULT NULL,
    `longitud` double DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE `documento`
(
    `id`     int         NOT NULL,
    `numero` int DEFAULT NULL,
    `tipo`   varchar(16) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE `hibernate_sequence`
(
    `next_val` bigint DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE `medioDeComunicacion`
(
    `tipo`        varchar(16) NOT NULL,
    `id`          int         NOT NULL,
    `esPreferido` bit(1)      NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE `preferencia`
(
    `id`     int         NOT NULL,
    `animal` varchar(32) NOT NULL,
    `sexo`   varchar(8)  NOT NULL,
    `tamaño` varchar(16) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE `contacto_medioDeComunicacion`
(
    `Contacto_id`             int NOT NULL,
    `mediosDeComunicacion_id` int NOT NULL,
    KEY `FKtiwsx8sbyka24byq66evie2c9` (`mediosDeComunicacion_id`),
    KEY `FKij8ciwlh0ie85idka53nfe6dt` (`Contacto_id`),
    CONSTRAINT `FKij8ciwlh0ie85idka53nfe6dt` FOREIGN KEY (`Contacto_id`) REFERENCES `contacto` (`id`),
    CONSTRAINT `FKtiwsx8sbyka24byq66evie2c9` FOREIGN KEY (`mediosDeComunicacion_id`) REFERENCES `medioDeComunicacion` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE `organizacion`
(
    `id`           int         NOT NULL,
    `calidadFoto`  varchar(16) NOT NULL,
    `nombre`       varchar(64) NOT NULL,
    `tamañoFoto`   varchar(16) NOT NULL,
    `direccion_id` int DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKpmg553n9jtb05q28eu7a8k5ol` (`direccion_id`),
    CONSTRAINT `FKpmg553n9jtb05q28eu7a8k5ol` FOREIGN KEY (`direccion_id`) REFERENCES `direccion` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE `usuario`
(
    `DTYPE`           varchar(31)  NOT NULL,
    `id`              int          NOT NULL,
    `contrasenia`     varchar(128) NOT NULL,
    `usuario`         varchar(32)  NOT NULL,
    `organizacion_id` int DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK6mhr5w8do1ptj5sour6oojh9u` (`organizacion_id`),
    CONSTRAINT `FK6mhr5w8do1ptj5sour6oojh9u` FOREIGN KEY (`organizacion_id`) REFERENCES `organizacion` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE `Organizacion_caracteristicas`
(
    `Organizacion_id` int NOT NULL,
    `caracteristicas` varchar(64) DEFAULT NULL,
    KEY `FKdhj68hff259clba7132nwv95o` (`Organizacion_id`),
    CONSTRAINT `FKdhj68hff259clba7132nwv95o` FOREIGN KEY (`Organizacion_id`) REFERENCES `organizacion` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE `Organizacion_preguntasDarEnAdopcion`
(
    `Organizacion_id`        int NOT NULL,
    `preguntasDarEnAdopcion` varchar(64) DEFAULT NULL,
    KEY `FKo0gtgcp835l5hyoxx15nwvhks` (`Organizacion_id`),
    CONSTRAINT `FKo0gtgcp835l5hyoxx15nwvhks` FOREIGN KEY (`Organizacion_id`) REFERENCES `organizacion` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE `Organizacion_preguntasQuieroAdoptar`
(
    `Organizacion_id`        int NOT NULL,
    `preguntasQuieroAdoptar` varchar(64) DEFAULT NULL,
    KEY `FKmleeltaqm8oxo29ncbknfwme6` (`Organizacion_id`),
    CONSTRAINT `FKmleeltaqm8oxo29ncbknfwme6` FOREIGN KEY (`Organizacion_id`) REFERENCES `organizacion` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE `persona`
(
    `id`                  int NOT NULL,
    `esAdoptante`         bit(1) DEFAULT NULL,
    `fechaNacimiento`     date   DEFAULT NULL,
    `radioHogares`        int    DEFAULT NULL,
    `contactoPersonal_id` int    DEFAULT NULL,
    `direccion_id`        int    DEFAULT NULL,
    `documento_id`        int    DEFAULT NULL,
    `preferencia_id`      int    DEFAULT NULL,
    `usuario_id`          int    DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK187jpd1k7pgrwrbay09wpuah4` (`contactoPersonal_id`),
    KEY `FK78198ggd6thip6qloht9ho248` (`direccion_id`),
    KEY `FKdmqxwct45r4vm4ygl24sn63n2` (`documento_id`),
    KEY `FKh3ajapv3v3u69w29jmv1gb26d` (`preferencia_id`),
    KEY `FKgedbtrc2ob95e7n8xt0vaaaa0` (`usuario_id`),
    CONSTRAINT `FK187jpd1k7pgrwrbay09wpuah4` FOREIGN KEY (`contactoPersonal_id`) REFERENCES `contacto` (`id`),
    CONSTRAINT `FK78198ggd6thip6qloht9ho248` FOREIGN KEY (`direccion_id`) REFERENCES `direccion` (`id`),
    CONSTRAINT `FKdmqxwct45r4vm4ygl24sn63n2` FOREIGN KEY (`documento_id`) REFERENCES `documento` (`id`),
    CONSTRAINT `FKgedbtrc2ob95e7n8xt0vaaaa0` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
    CONSTRAINT `FKh3ajapv3v3u69w29jmv1gb26d` FOREIGN KEY (`preferencia_id`) REFERENCES `preferencia` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE `persona_contacto`
(
    `Persona_id`   int NOT NULL,
    `contactos_id` int NOT NULL,
    KEY `FK1jtc4ywq5bb4je19c4v3pufuu` (`contactos_id`),
    KEY `FKueu4c46mtadxbo1oinfnbefv` (`Persona_id`),
    CONSTRAINT `FK1jtc4ywq5bb4je19c4v3pufuu` FOREIGN KEY (`contactos_id`) REFERENCES `contacto` (`id`),
    CONSTRAINT `FKueu4c46mtadxbo1oinfnbefv` FOREIGN KEY (`Persona_id`) REFERENCES `persona` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE `publicacionBusquedaAdopcion`
(
    `id`              int NOT NULL,
    `estaVisible`     bit(1) DEFAULT NULL,
    `fecha`           date   DEFAULT NULL,
    `persona_id`      int    DEFAULT NULL,
    `preferencia_id`  int    DEFAULT NULL,
    `organizacion_id` int    DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKsdn4nqki73yjx2sttg31j7ei6` (`preferencia_id`),
    KEY `FK2vt9s2nci9ot7jvc7bb5ul0b7` (`organizacion_id`),
    KEY `FK_q8hfcqaucw4c8riw4mf3euclu` (`persona_id`),
    CONSTRAINT `FK2vt9s2nci9ot7jvc7bb5ul0b7` FOREIGN KEY (`organizacion_id`) REFERENCES `organizacion` (`id`),
    CONSTRAINT `FK_q8hfcqaucw4c8riw4mf3euclu` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`),
    CONSTRAINT `FKsdn4nqki73yjx2sttg31j7ei6` FOREIGN KEY (`preferencia_id`) REFERENCES `preferencia` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE `publicacionMascotaEncontrada`
(
    `id`                  int          NOT NULL,
    `estaVisible`         bit(1) DEFAULT NULL,
    `fecha`               date   DEFAULT NULL,
    `persona_id`          int    DEFAULT NULL,
    `estadoMascota`       varchar(128) NOT NULL,
    `ubicacionMascota_id` int    DEFAULT NULL,
    `organizacion_id`     int    DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKp0vlj24e9pt1ebuhlhc6ipm4w` (`ubicacionMascota_id`),
    KEY `FK813s1xs7fo3c6v4feb5r9xofe` (`organizacion_id`),
    KEY `FK_en6hhhfoxvibxgdiiunny68wv` (`persona_id`),
    CONSTRAINT `FK813s1xs7fo3c6v4feb5r9xofe` FOREIGN KEY (`organizacion_id`) REFERENCES `organizacion` (`id`),
    CONSTRAINT `FK_en6hhhfoxvibxgdiiunny68wv` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`),
    CONSTRAINT `FKp0vlj24e9pt1ebuhlhc6ipm4w` FOREIGN KEY (`ubicacionMascota_id`) REFERENCES `direccion` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE `PublicacionBusquedaAdopcion_comodidades`
(
    `PublicacionBusquedaAdopcion_id` int NOT NULL,
    `comodidades`                    varchar(64) DEFAULT NULL,
    KEY `FK8pk23cwos4urjbsi82ufuaa6e` (`PublicacionBusquedaAdopcion_id`),
    CONSTRAINT `FK8pk23cwos4urjbsi82ufuaa6e` FOREIGN KEY (`PublicacionBusquedaAdopcion_id`) REFERENCES `publicacionBusquedaAdopcion` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE `PublicacionMascotaEncontrada_fotosMascota`
(
    `PublicacionMascotaEncontrada_id` int NOT NULL,
    `fotosMascota`                    mediumtext,
    KEY `FKbty5vnfe999fnls62t7ya40t8` (`PublicacionMascotaEncontrada_id`),
    CONSTRAINT `FKbty5vnfe999fnls62t7ya40t8` FOREIGN KEY (`PublicacionMascotaEncontrada_id`) REFERENCES `publicacionMascotaEncontrada` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE `mascota`
(
    `id`                int          NOT NULL,
    `animal`            varchar(32)  NOT NULL,
    `apodo`             varchar(32) DEFAULT NULL,
    `descripcionFisica` varchar(512) NOT NULL,
    `edad`              int          NOT NULL,
    `nombre`            varchar(32)  NOT NULL,
    `sexo`              varchar(8)   NOT NULL,
    `tamaño`            varchar(16)  NOT NULL,
    `duenio_id`         int         DEFAULT NULL,
    `organizacion_id`   int         DEFAULT NULL,
    `persona_id`        int         DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK6eivwqtrgl18u7y2akpp9inqc` (`duenio_id`),
    KEY `FKgc77riia3dnivwctxpdoin4fh` (`organizacion_id`),
    KEY `FKceulsmrswvcx6q4byphuhe8px` (`persona_id`),
    CONSTRAINT `FK6eivwqtrgl18u7y2akpp9inqc` FOREIGN KEY (`duenio_id`) REFERENCES `persona` (`id`),
    CONSTRAINT `FKceulsmrswvcx6q4byphuhe8px` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`),
    CONSTRAINT `FKgc77riia3dnivwctxpdoin4fh` FOREIGN KEY (`organizacion_id`) REFERENCES `organizacion` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE `organizacion_persona`
(
    `Organizacion_id` int NOT NULL,
    `adoptantes_id`   int NOT NULL,
    PRIMARY KEY (`Organizacion_id`, `adoptantes_id`),
    KEY `FK9xwqeiqipl5nk2a40p82ya909` (`adoptantes_id`),
    CONSTRAINT `FK9xwqeiqipl5nk2a40p82ya909` FOREIGN KEY (`adoptantes_id`) REFERENCES `persona` (`id`),
    CONSTRAINT `FKtnn38gbaf07w77vouk823ejti` FOREIGN KEY (`Organizacion_id`) REFERENCES `organizacion` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE `publicacionMascotaEnAdopcion`
(
    `id`              int NOT NULL,
    `estaVisible`     bit(1) DEFAULT NULL,
    `fecha`           date   DEFAULT NULL,
    `persona_id`      int    DEFAULT NULL,
    `mascota_id`      int    DEFAULT NULL,
    `organizacion_id` int    DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKhi655p20058jvdou0386cx78j` (`mascota_id`),
    KEY `FKrwlqrsimo0yuxddggmnp8t55f` (`organizacion_id`),
    KEY `FK_bhafjbe4y4m12qip97jw38k5c` (`persona_id`),
    CONSTRAINT `FK_bhafjbe4y4m12qip97jw38k5c` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`),
    CONSTRAINT `FKhi655p20058jvdou0386cx78j` FOREIGN KEY (`mascota_id`) REFERENCES `mascota` (`id`),
    CONSTRAINT `FKrwlqrsimo0yuxddggmnp8t55f` FOREIGN KEY (`organizacion_id`) REFERENCES `organizacion` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE `Mascota_caracteristicas`
(
    `Mascota_id`          int          NOT NULL,
    `caracteristicas`     varchar(255) DEFAULT NULL,
    `caracteristicas_KEY` varchar(255) NOT NULL,
    PRIMARY KEY (`Mascota_id`, `caracteristicas_KEY`),
    CONSTRAINT `FK9031cpfkkpo8ywdpr33krk675` FOREIGN KEY (`Mascota_id`) REFERENCES `mascota` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE `Mascota_fotos`
(
    `Mascota_id` int NOT NULL,
    `fotos`      mediumtext,
    KEY `FKl8giuyygtl0iqygn6tph2lhat` (`Mascota_id`),
    CONSTRAINT `FKl8giuyygtl0iqygn6tph2lhat` FOREIGN KEY (`Mascota_id`) REFERENCES `mascota` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE `PublicacionMascotaEnAdopcion_preguntasRespuestas`
(
    `PublicacionMascotaEnAdopcion_id` int          NOT NULL,
    `preguntasRespuestas`             varchar(64) DEFAULT NULL,
    `preguntasRespuestas_KEY`         varchar(255) NOT NULL,
    PRIMARY KEY (`PublicacionMascotaEnAdopcion_id`, `preguntasRespuestas_KEY`),
    CONSTRAINT `FKbsmv8tbboke1uab996mov9l8s` FOREIGN KEY (`PublicacionMascotaEnAdopcion_id`) REFERENCES `publicacionMascotaEnAdopcion` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;