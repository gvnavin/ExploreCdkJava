package com.gvnavin.service.explorecdk

const val aPIGatewayProxyRequestEvent = """
{
   "version":"2.0",
   "routeKey":"ANY /testapi/{proxy+}",
   "rawPath":"/testapi/tests/tests",
   "rawQueryString":"",
   "cookies":[
        "test"
   ],
   "headers":{
      "accept":"text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
      "accept-encoding":"gzip, deflate, br",
      "accept-language":"en,en-IN;q=0.9",
      "content-length":"0",
      "host":"",
      "sec-ch-ua":"\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"100\", \"Google Chrome\";v=\"100\"",
      "sec-ch-ua-mobile":"?0",
      "sec-ch-ua-platform":"\"macOS\"",
      "sec-fetch-dest":"document",
      "sec-fetch-mode":"navigate",
      "sec-fetch-site":"cross-site",
      "sec-fetch-user":"?1",
      "upgrade-insecure-requests":"1",
      "user-agent":"",
      "x-amzn-trace-id":"",
      "x-forwarded-for":"1",
      "x-forwarded-port":"443",
      "x-forwarded-proto":"https"
   },
   "queryStringParameters":null,
   "pathParameters":{
      "proxy":"tests/tests"
   },
   "stageVariables":null,
   "body":null,
   "isBase64Encoded":false,
   "requestContext":{
      "routeKey":"ANY /testapi/{proxy+}",
      "accountId":"",
      "stage":"default",
      "apiId":"",
      "domainName":"",
      "domainPrefix":"",
      "time":"",
      "timeEpoch":164926390544,
      "http":{
         "method":"POST",
         "path":"/testapi/tests/tests",
         "protocol":"HTTP/1.1",
         "sourceIp":"",
         "userAgent":""
      },
      "authorizer":null,
      "requestId":""
   }
}"""