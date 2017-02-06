
# coding: utf-8

# In[1]:

from collections import Counter
import matplotlib.pyplot as plt
import networkx as nx
import sys
import time
import pickle
from twitter import *
from TwitterAPI import TwitterAPI
import urllib, json
import sys
import tweepy
from tweepy import OAuthHandler


consumer_key = 'U4DwPeOpKSKeoY8cL3Bsk8y59'
consumer_secret = 'Ryb0K6AN1mQ5E5QatFJGzwvUeruh4R1GRgNJGC3gAWFKUWP1CF'
access_token = '296141476-uBOyRLBHOEn3mS02ugKejqLs8CvfzXAzgeYI5YUO'
access_token_secret = 'WiPT6jXcztyK93NEKiicEYGhBzLawUHCQewjc65pPrvAw'


# In[2]:

def get_twitter():
    return TwitterAPI(consumer_key, consumer_secret, access_token, access_token_secret)


# In[3]:

def robust_request(twitter, resource, params, max_tries=5):
    for i in range(max_tries):
        request = twitter.request(resource, params)
        if request.status_code == 200:
            return request
        else:
            print('Got error %s \nsleeping for 15 minutes.' % request.text)
            sys.stderr.flush()
            time.sleep(61 * 15)


# In[4]:

def get_friends(twitter):
    count = 0
    request = robust_request(twitter, 'friends/list', {'screen_name':'LeoDiCaprio', 'count': 10})
    list_f = (request.json()["users"])
    file = open("edges.txt",'w')
    for i in list_f:
        count = count + 1
        file.write('LeoDiCaprio')
        file.write('\t')
        file.write(i['screen_name'])
        file.write('\n')   
    for i in list_f:
        request = robust_request(twitter, 'friends/list', {'screen_name':i['screen_name'], 'count': 50})
        f_list = (request.json()["users"])
        for j in f_list:
            count = count + 1
            file.write(i['screen_name'])
            file.write('\t')
            file.write(j['screen_name'])
            file.write('\n')
    file.close()
    return count
    


# In[5]:

def get_train_tweets(maxnumtweets = 200):    
    auth = tweepy.OAuthHandler(consumer_key,consumer_secret)
    auth.set_access_token(access_token,access_token_secret)    
    api  = tweepy.API(auth)
    
    tweety = []
    users = ['google','Microsoft','htc','nokia','SamsungMobile','GUESS','Dior','marcjacobs','MaisonValentino','armani','dolcegabbana','Prada','Fossil','CHANEL','Versace','gucci']

    tweetsfinal1 = open('tweetsfinal.pkl', 'wb')
        
    for i in users:        
        for tweet in tweepy.Cursor(api.user_timeline,id=i,include_rts = False, lang= "en").items(maxnumtweets):
            tweety.append(tweet.text)
            
    pickle.dump(tweety, tweetsfinal1)
    tweetsfinal1.close()
    return tweety


# In[6]:

def get_test_tweets(maxnumtweets = 200):    
    auth = tweepy.OAuthHandler(consumer_key,consumer_secret)
    auth.set_access_token(access_token,access_token_secret)    
    api  = tweepy.API(auth)
    
    tweety = []
    users = ['Audi','subaru_usa','Lexus','Porsche','BMW','MazdaUSA','LandRover','VW','MercedesBenz','Lamborghini','RollsRoyce']
    
    tweetsfinal = open('tweetstest.pkl', 'wb')
        
    for i in users:        
        for tweet in tweepy.Cursor(api.user_timeline,id=i,include_rts = False, lang= "en").items(maxnumtweets):
            tweety.append(tweet.text)
            
    pickle.dump(tweety, tweetsfinal)
    tweetsfinal.close()
    return tweety


# In[7]:

def main():
    twitter = get_twitter()
    print('Established Twitter connection.')
    collect = open("collect.txt",'w')
    count = get_friends(twitter)
    collect.write('Final count is: %d' % count)
    collect.write("\n")
    print('Data Collected.')
    tweety = get_train_tweets(200)
    collect.write('Final train tweet count is: %d' % len(tweety))
    collect.write("\n")
    print('Train Tweets Collected.')
    tweety = get_test_tweets(200)
    collect.write('Final test tweet count is: %d' % len(tweety))
    collect.write("\n")
    print('Test Tweets Collected.')
    collect.close()
if __name__ == '__main__':
    main()

