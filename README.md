# O-MI_Lufthansa
Publishing Lufthansa Open API in O-MI / O-DF.
This a demo Camel implementation of O-MI / O-DF and it applies to 

To use this register to Lufthansa's Open API (https://developer.lufthansa.com/page)

Known problem is that OAuth2 and BearerToken generation fails in some versions of CXF.
I can be bypassed by adding to CamelApp Bearer Token manually header in line
      org.apache.cxf.jaxrs.client.Client client = WebClient.client(api)
->
      org.apache.cxf.jaxrs.client.Client client = WebClient.client(api).header("Authorization","Bearer bearertokenhere");


To build this:
mvn install
Run Java Application CamelApp

Expanding this with a new Open API and mapping to O-DF.
First download swagger document
Then generate Apachec CXF Client
Use generated client to get reply JSON payloads.
Feed reply payloads to form http://www.jsonschema2pojo.org/, 
where source type = JSON (or if you have typed Swagger reply use JSON Schema and feed it to the box)
Annotation style is Jackson 2.x. Choose Include getters and setters + allow addtional properties.
Press zip and download. 
Import to project and add JAXB part to create mapping JSON -> O-DF.
No straight forward approach is available. 

