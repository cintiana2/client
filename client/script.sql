CREATE TABLE "SYSTEM"."ADDRESS" 
   (	"ADR_ID" NUMBER(12,0) NOT NULL ENABLE, 
	"ADR_CITY" VARCHAR2(200 BYTE) NOT NULL ENABLE, 
	"ADR_ESTATE" VARCHAR2(2 BYTE) NOT NULL ENABLE, 
	"ADR_NUMBER" NUMBER(10,0), 
	"ADR_COMPLEMENT" VARCHAR2(200 BYTE), 
	"ADR_COUNTRY" VARCHAR2(100 BYTE) NOT NULL ENABLE, 
	"ADR_ZIPCODE" VARCHAR2(8 BYTE) NOT NULL ENABLE, 
	 CONSTRAINT "ADDRESS_PK" PRIMARY KEY ("ADR_ID")
  USING INDEX (CREATE UNIQUE INDEX "SYSTEM"."ADRESS_PK" ON "SYSTEM"."ADDRESS" ("ADR_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" )  ENABLE
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

CREATE TABLE "SYSTEM"."CLIENT" 
   (	
   "CLI_ID" NUMBER(12,0) NOT NULL ENABLE, 
	"CLI_NAME" VARCHAR2(200 BYTE) NOT NULL ENABLE, 
	"CLI_EMAIL" VARCHAR2(200 BYTE), 
	"CLI_BIRTHDAY" DATE NOT NULL ENABLE, 
	"CLI_STATUS" VARCHAR2(1 BYTE) DEFAULT 1 NOT NULL ENABLE, 
	"CLI_ADDRESS_ID" NUMBER(12,1), 
	 CONSTRAINT "CLIENT_PK" PRIMARY KEY ("CLI_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE, 
	 CONSTRAINT "CLIENT_ADDRESS_FK" FOREIGN KEY ("CLI_ADDRESS_ID")
	  REFERENCES "SYSTEM"."ADDRESS" ("ADR_ID") ENABLE
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
  
  
 CREATE SEQUENCE  "SYSTEM"."CLIENT_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
 CREATE SEQUENCE  "SYSTEM"."ADDRESS_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;