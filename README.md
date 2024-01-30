Application supports CRUD operations
It includes APIs for getting, deleting and updating product

All APIs starts with "http://localhost:8080/product-app/api"
1) GET (/products) and includes 3 parameters:
  -status:    IN_STOCK,
              OUT_OF_STOCK,
              ON_SALE,
              COMING_SOON
   -type:    FOOD,
             ELECTRONICS,
             FURNITURE,
             CLOTHES
   -id: id of the product
2) POST (/products) and object will look like:
     {
      "name":"bread",
      "price": 3000,
      "quantity": 120,
      "status": "IN_STOCK",
      "type": "FOOD"

  }
3) UPDATE (/products/{id}):
  -id: id of product as @PathParam
    and object which should be sent will look like:
    {
      "name":"bread",
      "price": 28000,
      "quantity": 0,
      "status": "IN_STOCK",
      "type": "ELECTRONICS"
}

4) DELETE (/products/{id}):
   -id: id of product as @PathParam
