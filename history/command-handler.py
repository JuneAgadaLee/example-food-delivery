from flask import Flask, request
import sqlite3
import os
import socket
import subprocess

subprocess.call(['python', 'policy-handler.py'])

app = Flask(__name__)

@app.route("/history")
def hello():
    params = request.args.to_dict()
    con = sqlite3.connect('test.db')
    cur = con.cursor()
    if len(params) == 0:
        cur.execute("SELECT * FROM Claim ORDER BY id DESC;")
    else:
        sql = "SELECT * FROM Claim WHERE "
        for key in params.keys():
            if (key == "customer_id"):
                sql += "customer_id=" + request.args[key]
            if (key == "claim_id"):
                sql += "claim_id=" + request.args[key]
        sql += " ORDER BY id DESC;"
        cur.execute(sql)
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
    html += "<h2></h2><br/>"
    html += "<table><tr><th>ID</th><th></th>"
    html += "<th></th><th></th><th></th><th></th></tr>"
    for row in cur:
        html += "<tr>"
        html += "<td>" + str(row[0]) + "</td>"
        html += "<td>" + str(row[1]) + "</td>"
        html += "<td>" + str(row[2]) + "</td>"
        html += "<td>" + str(row[3]) + "</td>"
        html += "<td>" + change_to_hangul(str(row[4])) + "</td>"
        html += "<td>" + str(row[5]) + "</td>"
        html += "</tr>"
    html += "</table>"
        
    con.close()
    return html

    # html = "<h3>Hello {name}!</h3>" \
    #        "<b>Hostname:</b> {hostname}<br/>"

    # return html.format(name=os.getenv("NAME", "world"), hostname=socket.gethostname())

def change_to_hangul(eng):

    return eng

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=8084)


