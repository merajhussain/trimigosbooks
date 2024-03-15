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
    skuId TEXT,
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
    id TEXT PRIMARY KEY  ,
    name TEXT UNIQUE
);

CREATE TABLE InventoryStock (
    id TEXT PRIMARY KEY,
    name TEXT UNIQUE,
        quantity INTEGER DEFAULT 0,
        rate REAL,
        saleprice REAL, 
		thresholdstock INTEGER DEFAULT 0,
        FOREIGN KEY (id) REFERENCES SKU(id)
);
