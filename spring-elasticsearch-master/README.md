# spring-elasticsearch
Add Elastic Head extension to chrome browser. 
Point to http://localhost:9200, 
we must see 2 nodes with 5 primary and replica shards each.

# Basic Operations

Create Index  
PUT http://localhost:9200/schools  
Response {"acknowledged": true} 

PUT http://localhost:9200/libraries   
Response {"acknowledged": true} 

Indexing Documents  
POST http://localhost:9200/schools/school/1
{
   "name":"Central School", "description":"CBSE Affiliation", "street":"Nagan",
   "city":"paprola", "state":"HP", "zip":"176115", "location":[31.8955385, 76.8380405],
   "fees":2000, "tags":["Senior Secondary", "beautiful campus"], "rating":"3.5"
}  
Response {_index": "schools","_type": "school","_id": "3","_version": 1,"_shards": {"total": 2,"successful": 2,"failed": 0},"created": true}

POST http://localhost:9200/libraries/library/1
{
   "name":"Central School Library", "description":"Estd. in 1956",
   "fees":2000, "tags":["Large collection of books", "Various Genre available"], "rating":"4.5"
}
Response {"_index": "libraries","_type": "library","_id": "1","_version": 1,"_shards": {"total": 2,"successful": 2,"failed": 0},"created": true}

POST http://localhost:9200/schools/_bulk  
{
   "index":{
      "_index":"schools", "_type":"school", "_id":"1"
   }
}  
{
   "name":"Central School", "description":"CBSE Affiliation", "street":"Nagan",
   "city":"paprola", "state":"HP", "zip":"176115", "location":[31.8955385, 76.8380405],
   "fees":2000, "tags":["Senior Secondary", "beautiful campus"], "rating":"3.5"
}  
{
   "index":{
      "_index":"schools", "_type":"school", "_id":"2"
   }
}  
{
   "name":"Saint Paul School", "description":"ICSE 
   Afiliation", "street":"Dawarka", "city":"Delhi", "state":"Delhi", "zip":"110075",
   "location":[28.5733056, 77.0122136], "fees":5000,
   "tags":["Good Faculty", "Great Sports"], "rating":"4.5"
}

For searching using /index/_search?pretty
