from lxml import html
import requests

page = requests.get('https://internetowykantor.pl/kursy-walut/')
tree = html.fromstring(page.content)

currencies_buy_pln = tree.xpath('*//tr/td[2]/text()')
currencies_sell_pln = tree.xpath('*//tr/td[3]/text()')
currencies_short_pln = tree.xpath('*//tr/td[1]/div/div[2]/a/text()')
print(currencies_buy_pln)
print(currencies_sell_pln)
print(currencies_short_pln)
#return {curr: (buy, sell) for curr, buy, sell in zip(currencies_short_pln_to, currencies_buy_pln_to, currencies_sell_pln_to)}

page = requests.get('https://www.xe.com/currency/usd-us-dollar')
tree = html.fromstring(page.content)
currencies_buy_usd = tree.xpath('/html/body/main/div[2]/div[1]/div[2]/div[2]/div/div[1]/table/tbody/tr[4]/td[2]/a/text()')
print(currencies_buy_usd)