FROM mongo

COPY init.json init.json
CMD mongoimport --host mongo --username sweightgroup --password sweightgroup2019 --db colletta --collection users --authenticationDatabase admin --type json --file init.json --jsonArray
