# Receipt Processor project

Demo code for Fetch.

Trying to use ktor framework for representing endpoints. 

# Docker

docker build -t my-application .

docker run -p 8080:8080 my-application

# Curl

```sh
curl -X POST --location "http://localhost:8080/receipts/process" \
    -H "Content-Type: application/json" \
    -d '{
          "retailer": "M&M Corner Market",
          "purchaseDate": "2022-03-20",
          "purchaseTime": "14:33",
          "items": [
            {
              "shortDescription": "Gatorade",
              "price": "2.25"
            },{
              "shortDescription": "Gatorade",
              "price": "2.25"
            },{
              "shortDescription": "Gatorade",
              "price": "2.25"
            },{
              "shortDescription": "Gatorade",
              "price": "2.25"
            }
          ],
          "total": "9.00"
        }'
```

```sh
curl -X GET --location "http://localhost:8080/receipts/3db9b42c-392a-4531-9c7c-b97fabb27298/points" \
-H "Content-Type: application/json"
```


