# Playing around with Elasticsearch 2.1
The intent of this project is to get some experience with Elasticsearch

## Generating test data
To play around with Elasticsearch you need first some sample data. A good source for generating such data is the website
[json-generator.com] (http://www.json-generator.com) and a little program from me :-), which transform the outut from json-generator.com to a bulk import file for Elasticsearch.

### Input data for json-generator.com
With json-generator.com, you write some generic input data in the left box (see the website for details). Such data looks like

````
{
    "indizes": [
        "{{repeat(4)}}",
        {
            "index": {
                "_id": "{{index(1)}}"
            }
        }
    ],
    "data": [
        "{{repeat(4)}}",
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




klick Generate and get JSON data 


Take also a look at the [wiki](https://github.com/rfelker/elasticsearch-playground/wiki)
