# elasticsearch-playground
Playing around with Elasticsearch 2.1
# Installation
## Elasticsearch
Download Elasticsearch from [www.elastic.co] (https://www.elastic.co/downloads/elasticsearch) and follow the installation steps.

Start elasticsearch by running *./bin/ealsticsearch* on Unix or *bin/ealsitcsearch.bat* on Windows machines.
## Sample Data
To play around with Elasticsearch you need some data. A good starting point is the data from [this GitHub repository] (https://github.com/royrusso/elasticsearch-sample-index). After following the instructions and importing the documents in Elasticsearch you will have an index *comicbook* with a document type *superhero* and roughly 150 superheros with a *name* and a *summary* to search for. 

If this is not enough data you can download free ebooks from [Project Gutenberg](https://www.gutenberg.org/), movie data from the [Internet Movie Database](http://www.imdb.com/interfaces) or even more data from a [data dump from StackExchange](https://archive.org/details/stackexchange).

# CRUD
First we will use the sample data from the previously stated GitHub repository. 
## Create an index
If you havn't done it before, create the *comicbook* index with the following command:
```bash
curl -XPUT 'http://localhost:9200/comicbook/' -d '{
    "settings" : {
        "index" : {
            "number_of_shards" : 3,
            "number_of_replicas" : 1
        }
    }
}'
```
Windows users can download cURL ([here](http://curl.haxx.se/download.html)) or use Sense for Chrome.









