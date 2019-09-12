# Running api at local 
mvn clean package spring-boot:run

# Running api as docker 
mvn clean package
docker build -t api .
docker-compose up

