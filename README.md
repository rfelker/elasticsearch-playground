# Playing around with Elasticsearch 2.1
The intent of this project is to get some experience with Elasticsearch

## Generating test data
To play around with Elasticsearch you need first some sample data. A good source for generating such data is the website
[json-generator.com] (http://www.json-generator.com) and a little program from me :-), which transform the outut from json-generator.com to a bulk import file for Elasticsearch.

### Input data for json-generator.com
With json-generator.com, you write some generic input data and get generated output data (see the website for details). 

With a given imput data...

````
{
    "indizes": [
        "{{repeat(2)}}",
        {
            "index": {
                "_id": "{{index(1)}}"
            }
        }
    ],
    "data": [
        "{{repeat(2)}}",
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

... the output looks like

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
        }
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
        }
    ]
}
````



Take also a look at the [wiki](https://github.com/rfelker/elasticsearch-playground/wiki)

