# r2dbc-kotlin
Project for get to know r2dbc for reading/writing big data sets.


## Environment Variables
Set the following environment variables before running the application
```
DRIVER
HOST
PORT
SERVICE_NAME
USER
PASSWORD
```

## Package plain
This package contains some sample code for getting in touch with r2dbc and kotlin coroutines. This code can 
be used to read data from a database.

## Other packages and implementation
The other packages contain some sample implementation for reading data via r2dbc. This implementation uses
an exposed likewise implementation of a table, which can be used to select and map data. The connection is 
created in the transaction method.