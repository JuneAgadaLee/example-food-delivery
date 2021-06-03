from kafka import KafkaConsumer
import sqlite3
import json

con = sqlite3.connect('test.db')
cur = con.cursor()
# cur.execute("CREATE TABLE KafkaLog(id INTEGER PRIMARY KEY AUTOINCREMENT, log TEXT);")
cur.execute("SELECT * FROM sqlite_master WHERE type='table' AND name='Claim'")
rows = cur.fetchall()
if not rows:
    cur.execute('''CREATE TABLE Claim (id INTEGER PRIMARY KEY AUTOINCREMENT,
                                        customer_id INTEGER,
                                        claim_id INTEGER,
                                        price INTEGER,
                                        status TEXT, 
                                        time DATETIME DEFAULT (DATETIME('now', 'localtime')));''')
    con.commit()


# To consume latest messages and auto-commit offsets
consumer = KafkaConsumer('bomtada', bootstrap_servers=['my-kafka.kafka.svc.cluster.local:9092'])

for message in consumer:
    print ("%s:%d:%d: key=%s value=%s" % (message.topic, message.partition,
                                            message.offset, message.key,
                                            message.value))

    dvalue = message.value.decode('utf8').replace("'", '"')
    data = json.loads(dvalue)
    
    cur.execute("INSERT INTO Claim(customer_id, claim_id, price, status) VALUES (?,?,?,?);", 
            (data['customerId'], data.get('claimId', data['id']), data['price'], data['status']))
    con.commit()

