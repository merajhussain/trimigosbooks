CREATE TABLE Orders (
    orderid TEXT PRIMARY KEY,
    customer_name TEXT,
    location TEXT,
    pending INTEGER,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);