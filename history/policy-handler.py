from kafka import KafkaConsumer
import json
import mysql.connector


mydb = mysql.connector.connect(
    host="database-2.cqcjeh663ucm.ap-southeast-1.rds.amazonaws.com",
    user="admin",
    password="qwe1212!Q",
    database="mykafka"
)

mycursor = mydb.cursor()

mycursor.execute("SHOW TABLES")
isexist = False
for x in mycursor:
    if x[0] == "Claim":
        isexist = True

if not isexist:
    mycursor.execute('''CREATE TABLE Claim (id INT PRIMARY KEY AUTO_INCREMENT,
                                        customer_id INT,
                                        claim_id INT,
                                        price INT,
                                        status TEXT, 
                                        dtm DATETIME DEFAULT CURRENT_TIMESTAMP);''')

mydb.commit()


consumer = KafkaConsumer('bomtada', bootstrap_servers=['my-kafka.kafka.svc.cluster.local:9092'])

for message in consumer:
    print ("%s:%d:%d: key=%s value=%s" % (message.topic, message.partition,
                                            message.offset, message.key,
                                            message.value))

    dvalue = message.value.decode('utf8').replace("'", '"')
    data = json.loads(dvalue)
    
    mycursor.execute("INSERT INTO Claim(customer_id, claim_id, price, status) VALUES (%s,%s,%s,%s)", 
                (data['customerId'], data.get('claimId', data['id']), data['price'], data['status']))

    mydb.commit()


