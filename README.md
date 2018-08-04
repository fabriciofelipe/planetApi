# Planet API

Api rest para realizar processamento de dados de Planetas citados ou não no filme Star Wars.

# Tecnologias Utilizadas:

Linguagem: Java 9 \
Gerenciador de Dependências: Maven

# FrameWorks' e Lib's:
spring-boot-starter 2.0.4.RELEASE \
spring-boot-starter-web 2.0.4.RELEASE \
spring-boot-starter-data-mongodb 2.0.4.RELEASE \
spring-cloud-starter-openfeign 2.0.1.RELEASE \
lombok 1.16.22 \
spring-boot-starter-test 2.0.3.RELEASE \

# Exemplos de Request's para chamar os serviços Rest's.

curl -X POST \
  http://localhost:8080/planet \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 3ef31ccb-d731-6a71-e3f4-c19ad1a4f644' \
  -d '{
	"name": "Coruscant",
	"climate": "temperate" ,
	"terrain": "cityscape, mountains"
}'

curl -X DELETE \
  http://localhost:8080/planet/Earth \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: a21f21ee-03de-541e-2549-ed018072cf17'

curl -X GET \
  http://localhost:8080/planet/id/5b662076cc70e64ef4d0aa3a \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: ba66ac4d-6443-f336-2066-116b685d494c'

curl -X GET \
  http://localhost:8080/planet/name/Kamino \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: e66893ee-b17f-8180-b0ef-8157ed269884'

curl -X GET \
  http://localhost:8080/planets \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: d5b59dc6-f15b-c910-c69a-d2ac6d3261d5'








