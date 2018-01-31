import pymysql

# Create a connection object
dbServerName = "127.0.0.1"
dbUser = "root"
dbPassword = "pass1234"
dbName = "Locations_Database"
charSet = "utf8mb4"
cusrorType = pymysql.cursors.DictCursor
connectionObject = pymysql.connect(host=dbServerName,
                                   user=dbUser,
                                   password=dbPassword,
                                   db=dbName,
                                   charset=charSet,
                                   cursorclass=cusrorType)

try:

    # Create a cursor object
    cursorObject = connectionObject.cursor()
    # SQL query string
    sqlQuery = "CREATE TABLE Locations" \
               "(ID int NOT NULL AUTO_INCREMENT, " \
               "latitude float(8, 6), " \
               "longitude float(9, 6), "\
               "local_name varchar(255), "\
               "date varchar(8), "\
               "PRIMARY KEY (ID))"

    # Execute the sqlQuery
    cursorObject.execute(sqlQuery)

    # SQL query string

    sqlQuery = "show tables"

    # Execute the sqlQuery

    cursorObject.execute(sqlQuery)

    # Fetch all the rows

    rows = cursorObject.fetchall()

    for row in rows:
        print(row)

except Exception as e:

    print("Exeception occured:{}".format(e))

finally:

    connectionObject.close()