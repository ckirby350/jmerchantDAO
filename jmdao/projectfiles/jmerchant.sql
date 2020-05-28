create database jmerchant;

use jmerchant;

CREATE TABLE user (
  userid bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  username char(200) NOT NULL,
  password varchar(1000) NOT NULL,
  PRIMARY KEY (userid)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
create unique index useruname on user (username);

CREATE TABLE userinfo (
  uinfoid bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  userid bigint(20) NOT NULL,
  firstname varchar(100),
  lastname varchar(100),
  nickname varchar(100),
  email varchar(240),
  phone1 char(20),
  empid varchar(100),
  PRIMARY KEY (uinfoid)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE customer (
  custid bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  firstname varchar(100),
  lastname varchar(100),
  nickname varchar(100),
  email varchar(240),
  phone1 varchar(20),
  phone2 varchar(20),
  phone3 varchar(20),
  addr1 varchar(240),
  addr2 varchar(240),
  city varchar(240),
  stateprov char(2),
  country char(3),
  postcode char(10),  
  dob date,
  anniversary date,
  createdt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (custid)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

create index custname on customer(lastname, firstname);
create index custaddr1 on customer(addr1);
create index custphone1 on customer(phone1);
create index custemail on customer(email);
create index custfnln on customer(firstname, lastname); 

insert into customer values (0, "Robert", "Smith", "Bob", "bsmith123@gmail.com", "9729748888","","","100 Oak St.",
	"", "Grapevine","TX","USA","76233",null,null,null);
insert into customer values (0, "Patty", "Smith", "Patty", "pattys1977@gmail.com", "9729748888","","","100 Oak St.",
	"", "Grapevine","TX","USA","76233",null,null,null);
insert into customer values (0, "Karch", "Kiraly", "", "vballking777@gmail.com", "6192223333","","","1235 Ocean Way",
	"", "Hermosa Beach","CA","USA","21234",null,null,null);
insert into customer values (0, "Lisa", "Lopez", "", "llop803@yahoo.com", "8083334444","","","898 Kalakaua Blvd",
	"", "Honolulu","HI","USA","99833",null,null,null);
insert into customer values (0, "Mike", "Freeze", "", "mfcold776@yahoo.com", "5126869932","","","2626 Pine Dr.",
	"Apt. # 801", "Minneapolis","MN","USA","43122",null,null,null);
insert into customer values (0, "Harry", "Lorenzo", "", "harlowone@gmail.com", "9748887744","","","202 Pine St",
	"", "Grand Prarie","TX","USA","75028",null,null,null);


CREATE TABLE note (
  noteid bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  fkid bigint(20) NOT NULL,
  notetype char(10) NOT NULL,
  notetext text,
  createdt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  createdby varchar(200),
  PRIMARY KEY (noteid)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
create index notedex on note(notetype, fkid, createdt);

CREATE TABLE itemjoin (
  ijid bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  jointype char(10) NOT NULL,
  fkid1 bigint(20) NOT NULL,
  fkid2 bigint(20) NOT NULL,
  PRIMARY KEY (ijid)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*** single, box, assembly, non-inventory ***/
CREATE TABLE invtype (
	typecode varchar(60) NOT NULL,
	createdt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	createdby varchar(200),
	PRIMARY KEY (typecode)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;	
insert into invtype values ("SINGLE", null, "SYS");

/*** earring, loose diamond, watch, necklace ***/
CREATE TABLE invcat (
	catcode varchar(60) NOT NULL,
	createdt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	createdby varchar(200),
	PRIMARY KEY (catcode)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;	
insert into invcat values ("WATCH", null, "SYS");
insert into invcat values ("NECKLACE", null, "SYS");
insert into invcat values ("EARRING", null, "SYS");
insert into invcat values ("BRACELET", null, "SYS");

CREATE TABLE company (
  compid bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  name varchar(250),
  email varchar(240),
  phone1 varchar(20),
  phone2 varchar(20),
  phone3 varchar(20),
  addr1 varchar(240),
  addr2 varchar(240),
  city varchar(240),
  stateprov char(2),
  country char(3),
  postcode char(10), 
  weburl varchar(255),   
  createdt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (compid)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
create index compnamedex on company(name);

CREATE TABLE store (
  storeid bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  name varchar(250),
  email varchar(240),
  phone1 varchar(20),
  phone2 varchar(20),
  phone3 varchar(20),
  addr1 varchar(240),
  addr2 varchar(240),
  city varchar(240),
  stateprov char(2),
  country char(3),
  postcode char(10), 
  weburl varchar(255),   
  createdt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (storeid)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
create index storeunamedex on store(uname);


/** brand is also a drop down **/
CREATE TABLE invitem (
  invid bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  typecode varchar(60),
  discallowed char(1) NOT NULL DEFAULT 'Y',
  taxable char(1) NOT NULL DEFAULT 'Y', 
  taxclass varchar(60),
  upcbarcode varchar(60),
  serialized char(1) NOT NULL DEFAULT 'N', 
  serialnum varchar(100),
  modelnum varchar(100),
  mfgsku varchar(100),
  customsku varchar(100),
  vendid bigint(20) NOT NULL DEFAULT 0,
  onconsignment char(1) NOT NULL DEFAULT 'N',
  description varchar(300),
  cost DECIMAL(13,2) NOT NULL DEFAULT 0.00,
  price DECIMAL(13,2) NOT NULL DEFAULT 0.00,
  brand varchar(200),
  catcode varchar(60),
  createdt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  createdby varchar(200),
  PRIMARY KEY (invid)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
create index customskudex on invitem(customsku);
create index sernodex on invitem(serialnum);
create index invdescdex on invitem(description);
create index invupcbarcode on invitem(upcbarcode);
create index mfgskudex on invitem(mfgsku);

CREATE TRIGGER TR_INVITEM_INSERT
    BEFORE INSERT
    ON invitem FOR EACH ROW 
    SET NEW.upcbarcode = UPPER(NEW.upcbarcode),
		NEW.serialnum = UPPER(NEW.serialnum),
		NEW.modelnum = UPPER(NEW.modelnum),
		NEW.mfgsku = UPPER(NEW.mfgsku),
		NEW.customsku = UPPER(NEW.customsku);

CREATE TRIGGER TR_INVITEM_UPDATE
    BEFORE UPDATE
    ON invitem FOR EACH ROW 
    SET NEW.upcbarcode = UPPER(NEW.upcbarcode),
		NEW.serialnum = UPPER(NEW.serialnum),
		NEW.modelnum = UPPER(NEW.modelnum),
		NEW.mfgsku = UPPER(NEW.mfgsku),
		NEW.customsku = UPPER(NEW.customsku);

CREATE TABLE invqty (
	storeid bigint(20) NOT NULL,
	invid bigint(20) NOT NULL,
	physloc varchar(100) NOT NULL DEFAULT ' ',
	qty integer NOT NULL,	
	PRIMARY KEY (storeid, invid, physloc)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/***
keywords, images, cust fields,
instock amt, avg. cost
onorder, onlayaway, transfer - pendingin/out, 
sales history (amt by day, week, year, ever)
***/


  
  
  