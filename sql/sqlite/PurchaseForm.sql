CREATE TABLE PurchaseOrder (
    PurchaseOrderId	TEXT PRIMARY KEY,
    vehicleNumber TEXT,
    carrierName TEXT,
    ModeOfTransport TEXT,
    grNumber INTEGER,
    billDate DATE,
    totalBillValue REAL,
    totalItems INTEGER
);


CREATE TABLE PurchaseItem (
    itemId TEXT PRIMARY KEY,
    purchaseBillId TEXT,
    skuId INTEGER,
	skuname TEXT,
    quantity REAL,
    rate REAL,
    discount REAL,
    taxableAmount REAL,
    igstr REAL,
    igstv REAL,
    finalPrice REAL,
    FOREIGN KEY (purchaseBillId) REFERENCES PurchaseOrder(uid)
);


CREATE TABLE SKU (
    id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE,
    name TEXT UNIQUE
);


CREATE TABLE InventoryStock (
    id INTEGER PRIMARY KEY,
    name TEXT UNIQUE,
	quantity INTEGER 
);


