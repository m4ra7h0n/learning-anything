# encoding=utf8
from yapf.yapflib.py3compat import raw_input

from application import username, password

import time
import requests
from bs4 import BeautifulSoup

Default_Header = {'X-Requested-With': 'XMLHttpRequest',
                  'Referer': 'http://www.zhihu.com',
                  'User-Agent': 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; '
                                'rv:39.0) Gecko/20100101 Firefox/39.0',
                  'Host': 'www.zhihu.com'}
_session = requests.session()
_session.headers.update(Default_Header)

BASE_URL = 'https://www.zhihu.com'
CAPTURE_URL = BASE_URL + '/captcha.gif?r=' + str(int(time.time()) * 1000) + '&type=login'
PHONE_LOGIN = BASE_URL + '/login/phone_num'


def login():
    '''登录知乎'''
    cap_content = _session.get(CAPTURE_URL).content
    cap_file = open('/Users/xjj/Desktop/cap.gif', 'wb')
    cap_file.write(cap_content)
    cap_file.close()
    captcha = input()
    data = {"phone_num": username, "password": password, "captcha": captcha}
    r = _session.post(PHONE_LOGIN, data)
    # print(r.json())['msg']


def zhuanlan_info():
    # Default_Header = {
    #     'Referer': 'https://zhuanlan.zhihu.com/passer',
    #     'User-Agent': 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; '
    #                   'rv:39.0) Gecko/20100101 Firefox/39.0',
    #     'Host': 'zhuanlan.zhihu.com'}
    # _session = requests.session()
    # _session.headers.update(Default_Header)
    HtmlContent = _session.get('https://zhuanlan.zhihu.com/api/columns/passer')
    HtmlContent = HtmlContent.json()
    print(HtmlContent)
    # print('专栏名称   ：' + HtmlContent['name'].encode('utf-8'))
    # print('专栏关注人数：' + str(HtmlContent['followersCount']))
    # print('专栏文章数量：' + str(HtmlContent['postsCount']))
    # print('专栏介绍   ：' + HtmlContent['description'].encode('utf-8'))
    # print('专栏创建者相关信息：')
    # print('1、地址：:' + HtmlContent['creator']['profileUrl'].encode('utf-8'))
    # print('2、个签：:' + HtmlContent['creator']['bio'].encode('utf-8'))
    # print('3、昵称：:' + HtmlContent['creator']['name'].encode('utf-8'))
    # print('4、hash：:' + HtmlContent['creator']['hash'].encode('utf-8'))
    # print('5、介绍：:' + HtmlContent['creator']['description'].encode('utf-8'))


if __name__ == '__main__':
    # login()
    headers={
        "accept": "xxx",
        "cookie": "xxx",
        "User-Agent": "xxx"
    }
    # zhuanlan_info()

    url = 'https://www.zhihu.com/people/asura-3-28/answers?page='
    test = _session.get(url + str(1))
    print(test.text)
    # try:
    #     for i in range(2, 111):
    #         html = requests.get(url + str(i))
    #         # print(html)
    # except:
    #     print("error")
    #
    # strhtml = requests.get(url)        #Get方式获取网页数据
    # print(strhtml.text)
