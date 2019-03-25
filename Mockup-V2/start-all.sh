#bin/bash
cd Backend
mvn clean install exec:java
cd ..
cd Frontend
npm start
