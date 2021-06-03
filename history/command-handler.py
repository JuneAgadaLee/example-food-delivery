# -*- coding: utf-8 -*-
from flask import Flask, request
import os
import socket
import mysql.connector


app = Flask(__name__)

@app.route("/history")
def hello():
    params = request.args.to_dict()


    mydb = mysql.connector.connect(
        host="database-2.cqcjeh663ucm.ap-southeast-1.rds.amazonaws.com",
        user="admin",
        password="qwe1212!Q",
        database="mykafka"
    )

    mycursor = mydb.cursor()

    
    if len(params) == 0:

        mycursor.execute("SELECT * FROM Claim ORDER BY id DESC")
    else:
        sql = "SELECT * FROM Claim WHERE "
        for key in params.keys():
            if (key == "customer_id"):
                sql += "customer_id=" + request.args[key]
            if (key == "claim_id"):
                sql += "claim_id=" + request.args[key]
        sql += " ORDER BY id DESC;"

        mycursor.execute(sql)

    myresult = mycursor.fetchall()
    
    html = '''<style>
                table, th, td {
                    border: 1px solid #fff
                    border-collapse: collapse;
                }
                th, td {
                    padding: 15px;
                    text-align: left;
                }
                tr:nth-child(even) {
                    background-color: PowderBlue;
                }
                tr:nth-child(odd) {
                    background-color: #fff;
                }
                th {
                    background-color: MediumSeaGreen;
                }
            </style>'''
    html += "<h2>청구이력 조회</h2><br/>"
    html += "<table><tr><th>ID</th><th>고객번호</th>"
    html += "<th>청구번호</th><th>금액</th><th>상태</th><th>변경시각</th></tr>"
    for row in myresult:
        html += "<tr>"
        html += "<td>" + str(row[0]) + "</td>"
        html += "<td>" + str(row[1]) + "</td>"
        html += "<td>" + str(row[2]) + "</td>"
        html += "<td>" + str(row[3]) + "</td>"
        html += "<td>" + change_to_hangul(str(row[4])) + "</td>"
        html += "<td>" + str(row[5]) + "</td>"
        html += "</tr>"
    html += "</table>"
        
    mydb.close()
    return html

def change_to_hangul(eng):
    if eng == "Received Claim":
        return "보험금청구접수됨"
    elif eng == "Assigned Examiner":
        return "심사자배정됨"
    elif eng == "Approved Review":
        return "심사승인됨"
    elif eng == "Declined Review":
        return "심사거절됨"
    elif eng == "Assigned Payment":
        return "지급접수됨"
    elif eng == "Completed Payment":
        return "지급처리됨"
    elif eng == "Canceled Claim":
        return "보험금청구취소됨"
    elif eng == "Canceled Review":
        return "심사취소됨"
    return eng

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=8084)


