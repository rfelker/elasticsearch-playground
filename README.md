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
    "indizes": [
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
            "hobby": "{{random('swimming', 'running', 'biking', 'hiking', 'football', 'baseball', 'soccer', 'cooking', 'music', 'idle')}}"
        }
    ]
}
````
### Output data from json-generator.com
... the generated output data looks like (see file [json-generator-output.json](src/main/resources/json-generator-output.json))

````json
{
    "indizes": [
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

        ---> up to 10000 indizes <---


    ],
    "data": [
        {
            "firstname": "Mitchell",
            "surname": "Morin",
            "gender": "male",
            "birthday": "1978-04-26",
            "company": "Zipak",
            "email": "mitchellmorin@zipak.com",
            "phone": "+1 (841) 518-3503",
            "address": {
                "street": "37 Sackett Street",
                "city": "Rivera",
                "state": "Maryland",
                "latitude": 46.104895,
                "longitude": -73.553425
            },
            "hobby": "football"
        },
        {
            "firstname": "Minnie",
            "surname": "Craft",
            "gender": "female",
            "birthday": "1976-06-21",
            "company": "Zanilla",
            "email": "minniecraft@zanilla.com",
            "phone": "+1 (800) 437-3702",
            "address": {
                "street": "5 Evans Street",
                "city": "Clarksburg",
                "state": "Arizona",
                "latitude": -63.937996,
                "longitude": -62.63248
            },
            "hobby": "running"
        },

        ---> up to 10000 data sets <---

    ]
}
````

### Generating bulk data
With the output data from json-generator.com you can generate a data import file
for bulk import in Elasticsearch with the ImportDataGenerator from this repository:

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
{"birthday":"1930-12-29","firstname":"Bonner","address":{"city":"Kerby","street":"4 Independence Avenue","latitude":10.032859,"state":"Nevada","longitude":87.830614},"gender":"male","phone":"+1 (845) 586-2656","surname":"Hurley","company":"Medifax","email":"bonnerhurley@medifax.com","hobby":"idle"}
{"index":{"_id":2}}
{"birthday":"2004-05-10","firstname":"Mills","address":{"city":"Kidder","street":"9 Devoe Street","latitude":-88.89292,"state":"Kansas","longitude":150.753392},"gender":"male","phone":"+1 (826) 587-3873","surname":"Robinson","company":"Suretech","email":"millsrobinson@suretech.com","hobby":"swimming"}
...
````



