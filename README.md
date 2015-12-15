# Playing around with Elasticsearch 2.1
The intent of this project is to get some experience with Elasticsearch
Take also a look at the [wiki](https://github.com/rfelker/elasticsearch-playground/wiki)

## Generating test data
To play around with Elasticsearch you need first some sample data. A good source for generating
such data is the website [json-generator.com] (http://www.json-generator.com) and a little program
from me :-), which transform the outut from json-generator.com to a bulk import file for Elasticsearch.

### Input data for json-generator.com
With json-generator.com, you write some generic input data and get generated output data
(see the website for details).

With a given input data (see file [json-generator-input.json](src/main/resources/json-generator-input.json))...

````
{
    "indices": [
        "{{repeat(10000)}}",
        {
            "index": {
                "_id": "{{index(1)}}"
            }
        }
    ],
    "data": [
        "{{repeat(10000)}}",
        {
            "firstname": "{{firstName()}}",
            "surname": "{{surname()}}",
            "gender": "{{gender()}}",
            "birthday": "{{date(new Date(1930,0,1), new Date(), 'YYYY-MM-dd')}}",
            "company": "{{company()}}",
            "email": "{{email()}}",
            "phone": "+1 {{phone()}}",
            "address": {
                "street": "{{integer(1, 50)}} {{street()}}",
                "city": "{{city()}}",
                "state": "{{state()}}",
                "latitude": "{{floating(-90.000001, 90)}}",
                "longitude": "{{floating(-180.000001, 180)}}"
            },
            "hobby": "{{random('swimming', 'running', 'biking', 'hiking', 'football', 'baseball', 'soccer', 'cooking', 'music', 'idle')}}",
            "comment": function (tags) {
                            return this.firstname + " is " + (new Date((Date.now()-new Date(this.birthday).getTime())).getUTCFullYear()-1970) +" years old and lives in " + this.address.state + ", has " + tags.integer(2, 5) + " children and a great hobby called " + this.hobby;
            }
        }
    ]
}
````
### Output data from json-generator.com
... the generated output data looks like (see file [json-generator-output.json](src/main/resources/json-generator-output.json))

````json
{
    "indices": [
        {
            "index": {
                "_id": 1
            }
        },
        {
            "index": {
                "_id": 2
            }
        },

        "---> up to 10000 indices <---"


    ],
   "data": [
        {
            "firstname": "Huber",
            "surname": "Sweeney",
            "gender": "male",
            "birthday": "1941-06-30",
            "company": "Pearlessa",
            "email": "hubersweeney@pearlessa.com",
            "phone": "+1 (842) 495-2793",
            "address": {
                "street": "15 Oliver Street",
                "city": "Barronett",
                "state": "South Carolina",
                "latitude": 8.255781,
                "longitude": 109.603304
            },
            "hobby": "biking",
            "comment": "Huber is 74 years old and lives in South Carolina, has 5 children and a great hobby called biking"
        },
        {
            "firstname": "Dejesus",
            "surname": "Walker",
            "gender": "male",
            "birthday": "1944-01-21",
            "company": "Magneato",
            "email": "dejesuswalker@magneato.com",
            "phone": "+1 (946) 446-3735",
            "address": {
                "street": "27 Degraw Street",
                "city": "Staples",
                "state": "Mississippi",
                "latitude": -0.027564,
                "longitude": -137.18162
            },
            "hobby": "hiking",
            "comment": "Dejesus is 71 years old and lives in Mississippi, has 2 children and a great hobby called hiking"
        },

        "---> up to 10000 data sets <---"

    ]
}
````

### Generating bulk data
With the output data from json-generator.com you can generate a data import file
for bulk import in Elasticsearch with the *ImportDataGenerator.jar* from this repository:

```bash
> $ java -jar ImportDataGenerator.jar json-generator-output.json import-data-elasticsearch.json
> reading from file json-generator-output.json...
> writing 10000 data sets to file import-data-elasticsearch.json...
> done!
```

The resulting file with 10.000 datasets is the file [import-data-elasticsearch.json](import-data-elasticsearch.json) which
looks like


````
{"index":{"_id":1}}
{"birthday":"1941-06-30","firstname":"Huber","address":{"city":"Barronett","street":"15 Oliver Street","latitude":8.255781,"state":"South Carolina","longitude":109.603304},"gender":"male","phone":"+1 (842) 495-2793","surname":"Sweeney","company":"Pearlessa","comment":"Huber is 74 years old and lives in South Carolina, has 5 children and a great hobby called biking","email":"hubersweeney@pearlessa.com","hobby":"biking"}
{"index":{"_id":2}}
{"birthday":"1944-01-21","firstname":"Dejesus","address":{"city":"Staples","street":"27 Degraw Street","latitude":-0.027564,"state":"Mississippi","longitude":-137.18162},"gender":"male","phone":"+1 (946) 446-3735","surname":"Walker","company":"Magneato","comment":"Dejesus is 71 years old and lives in Mississippi, has 2 children and a great hobby called hiking","email":"dejesuswalker@magneato.com","hobby":"hiking"}
...
````

### Import test data in Elasticsearch
Last but not least you can load the test data into the Elasticsearch cluster with the command

````bash
curl -XPOST 'localhost:9200/person/data/_bulk?pretty' --data-binary @import-data-elasticsearch.json
````

Let's take a look on the indices:

````bash
> $ curl 'localhost:9200/_cat/indices?v'
> health status index       pri rep docs.count docs.deleted store.size pri.store.size
> yellow open   person        5   1      10000            0      4.6mb          4.6mb
````




