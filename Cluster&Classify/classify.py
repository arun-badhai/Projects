
# coding: utf-8

# In[44]:

import requests
from pprint import pprint
from collections import Counter
import matplotlib.pyplot as plt
import networkx as nx
import sys
import time
import configparser
from TwitterAPI import TwitterAPI
from collections import defaultdict
from io import BytesIO
from zipfile import ZipFile
import re
import numpy as np
import pickle
from urllib.request import urlopen
from collections import defaultdict
from scipy.sparse import lil_matrix
from sklearn.cross_validation import KFold
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import accuracy_score, confusion_matrix

consumer_key = 'M8tppsmHAIS2DMApVoZupkTgS'
consumer_secret = 'xEBGF0xdQFn3svoCn9gwlJjRHzktW5mip2sRrFs6pdqdA7Y0xP'
access_token = '296141476-hIIrnKf5Sqtah5iF8qdGJpGT3yhFlvxTLUC5Bkzi'
access_token_secret = 'hnJfgnvZiQr0GnBstvvpvB8gjSXB0KcfR9NbjncUa44UV'


# In[45]:

def get_twitter():
    return TwitterAPI(consumer_key, consumer_secret, access_token, access_token_secret)


# In[46]:

def download():
    url = urlopen('http://www2.compute.dtu.dk/~faan/data/AFINN.zip')
    zipfile = ZipFile(BytesIO(url.read()))
    afinn_file = zipfile.open('AFINN/AFINN-111.txt')
    afinn = dict()
    for line in afinn_file:
        parts = line.strip().split()
        if len(parts) == 2:
            afinn[parts[0].decode("utf-8")] = int(parts[1])
    return afinn


# In[47]:

#Lecture 13
def make_vocabulary(tokens_list):
    vocabulary = defaultdict(lambda: len(vocabulary))
    for tokens in tokens_list:
        for token in tokens:
            vocabulary[token]
    print('%d unique terms in vocabulary' % len(vocabulary))
    return vocabulary


# In[48]:

#Lecture 13
def make_feature_matrix(tokens_list, vocabulary):
    X = lil_matrix((len(tokens_list), len(vocabulary)))
    for i, tokens in enumerate(tokens_list):
        for token in tokens:
            if token in vocabulary.keys():
                j = vocabulary[token]
                X[i,j] += 1
    return X.tocsr()


# In[23]:

def load_tweets(twitter):
    pkl_file = open('tweetsfinal.pkl', 'rb')
    tweets = pickle.load(pkl_file)
    pkl_file.close()
    return tweets


# In[24]:

def load_test_tweets(twitter):
    pkl_file = open('tweetstest.pkl', 'rb')
    tweets = pickle.load(pkl_file)
    pkl_file.close()
    return tweets


# In[53]:

def get_data(tweets, vocab = None):
    afinn = download()
    new_val = []
    positives = []
    negatives = []
    non_pos_non_neg = []
    tokens_list = [set(tokenize(tw)).intersection(afinn.keys()) for tw in tweets]
    tokens_list = [val for val in tokens_list if val!= set()]
    if vocab == None:
        vocab = make_vocabulary(tokens_list)
    val = make_feature_matrix(tokens_list, vocab)
    for token_list, tweet in zip(tokens_list, tweets):
        pos, neg = afinn_sentiment2(token_list, afinn)
        if pos > neg:
            new_val.append(1)
            positives.append(tweet)
        elif neg > pos:
            new_val.append(-1)
            negatives.append(tweet)
        else:
            new_val.append(0)
            non_pos_non_neg.append(tweet)

    #val = np.asarray(val)
    new_val = np.asarray(new_val)      
    return val.A, new_val, vocab, positives, negatives, non_pos_non_neg  


# In[33]:

#Lecture 13
def do_cross_val(X, y, nfolds):
    cv = KFold(len(y), nfolds)
    accuracies = []
    for train_idx, test_idx in cv:
        clf = LogisticRegression()
        clf.fit(X[train_idx], y[train_idx])
        predicted = clf.predict(X[test_idx])
        acc = accuracy_score(y[test_idx], predicted)
        accuracies.append(acc)
    avg = np.mean(accuracies)
    return avg


# In[28]:

def afinn_sentiment2(terms, afinn, verbose=False):
    pos = 0
    neg = 0
    for t in terms:
        if t in afinn:
            if verbose:
                print('\t%s=%d' % (t, afinn[t]))
            if afinn[t] > 0:
                pos += afinn[t]
            else:
                neg += -1 * afinn[t]
    return pos, neg


# In[30]:

#Lecture 13
def tokenize(string):
    if not string:
        return []
    string = string.lower()
    tokens = []
    string = re.sub('http\S+', '', string)
    string = re.sub('@\S+', '', string)
    tokens = string.split()
  
    return tokens


# In[77]:

def main():
    twitter = get_twitter()
    tweets = load_tweets(twitter)
    val, new_val, vocab, positives, negatives, non_pos_non_neg = get_data(tweets)
    classify = open("classify.txt",'w')
    classify.write("Positive Instance Count: %d" % len(positives))
    classify.write("\n")
    classify.write("Negative Instance Count: %d" % len(negatives))
    classify.write("\n")
    classify.write("Non-negative-non-positive Instance Count: %d" % len(non_pos_non_neg))
    classify.write("\n")
    classify.write("Positive Instance: %s" %positives[0].encode('utf-8'))
    classify.write("\n")
    classify.write("Negative Instance: %s" %negatives[0].encode('utf-8'))
    classify.write("\n")
    classify.write("Non-negative-non-positive Instance: %s" %non_pos_non_neg[0].encode('utf-8'))
    classify.write("\n")
    acc = do_cross_val(val, new_val, 30)
    print('accuracy on training data=%.3f \n' % acc)
    tweets1 = load_test_tweets(twitter)
    val, new_val, vocab, positives, negatives, non_pos_non_neg  = get_data(tweets1, vocab)
    classify.write("Positive Instance Count: %d" % len(positives))
    classify.write("\n")
    classify.write("Negative Instance Count: %d" % len(negatives))
    classify.write("\n")
    classify.write("Non-negative-non-positive Instance Count: %d" % len(non_pos_non_neg))
    classify.write("\n")
    classify.write("Positive Instance: %s" %positives[0].encode('utf-8'))
    classify.write("\n")
    classify.write("Negative Instance: %s" %negatives[0].encode('utf-8'))
    classify.write("\n")
    classify.write("Non-negative-non-positive Instance: %s" %non_pos_non_neg[0].encode('utf-8'))
    classify.close()
    print('Instances for training data')
    print('Positive Instances: \n', positives[:4], '\n Negative Instances: \n', negatives[:4], '\n Non-negative or Non-positive instances: \n', non_pos_non_neg[5:10])
    acc = do_cross_val(val, new_val, 30)
    print('\n accuracy on testing data=%.3f \n' % acc)
    print('Instances for testing data')
    print('Positive Instances: \n', positives[:4], '\n Negative Instances: \n', negatives[:4], '\n Non-negative or Non-positive instances: \n', non_pos_non_neg[5:10])
if __name__ == '__main__':
    main()

