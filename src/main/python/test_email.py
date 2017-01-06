import smtplib

from email.mime.text import MIMEText

msg = MIMEText("testtestpython")

msg['Subject'] = 'test Subject'
msg['From'] = "yishuai.li@inrix.com"
msg['To'] = "yishuaili@hotmail.com"

