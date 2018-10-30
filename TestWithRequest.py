import requests as rq

response = rq.get("http://localhost:8080/rest/users/add?name=ad&email=asd@asd.com&role=bla,bla")

print(response)

from urllib.request import Request, urlopen
from urllib.error import  URLError
req = Request("http://localhost:8080/rest/users/find")
try:
    response = urlopen(req)
except URLError as e:
    if hasattr(e, 'reason'):
        print('We failed to reach a server.')
        print('Reason: ', e.reason)
    elif hasattr(e, 'code'):
        print('The server couldn\'t fulfill the request.')
        print('Error code: ', e.code)
else:
    print(response)

response = rq.get("http://localhost:8080/rest/users/add?name=ad&email=asdd@asd.com&role=bla,bla")
response = rq.get("http://localhost:8080/rest/users/add?name=ad&email=asddd@asd.com&role=bla,bla")
response = rq.get("http://localhost:8080/rest/users/add?name=ad&email=asddddd@asd.com&role=bla,bla")
response = rq.get("http://localhost:8080/rest/users/add?name=ad&email=asdasdddd1d@asd.com&role=bla,bla")

req = Request("http://localhost:8080/rest/users/find")
try:
    response = urlopen(req)
except URLError as e:
    if hasattr(e, 'reason'):
        print('We failed to reach a server.')
        print('Reason: ', e.reason)
    elif hasattr(e, 'code'):
        print('The server couldn\'t fulfill the request.')
        print('Error code: ', e.code)
else:
    print(response)

response = rq.get("http://localhost:8080/rest/users/add?name=ad&email=assdddd1d@asd.com&role=bla,bla")
response = rq.get("http://localhost:8080/rest/users/add?name=SNAP&email=SNAP@asd.com&role=bla,bla")


exit(0)

