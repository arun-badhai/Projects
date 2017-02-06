
# coding: utf-8

# Recommendation System

# In[1]:

from collections import Counter, defaultdict
import math
import numpy as np
import os
import pandas as pd
import re
from scipy.sparse import csr_matrix
import urllib.request
import zipfile

def download_data():
    """ DONE. Download and unzip data.
    """
    url = 'https://www.dropbox.com/s/h9ubx22ftdkyvd5/ml-latest-small.zip?dl=1'
    urllib.request.urlretrieve(url, 'ml-latest-small.zip')
    zfile = zipfile.ZipFile('ml-latest-small.zip')
    zfile.extractall()
    zfile.close()


def tokenize_string(my_string):
    """ DONE. You should use this in your tokenize function.
    """
    return re.findall('[\w\-]+', my_string.lower())


# In[2]:

def tokenize(movies):
    """
    Append a new column to the movies DataFrame with header 'tokens'.
    This will contain a list of strings, one per token, extracted
    from the 'genre' field of each movie. Use the tokenize_string method above.

    Note: you may modify the movies parameter directly; no need to make
    a new copy.
    Params:
      movies...The movies DataFrame
    Returns:
      The movies DataFrame, augmented to include a new column called 'tokens'.

    >>> movies = pd.DataFrame([[123, 'Horror|Romance'], [456, 'Sci-Fi']], columns=['movieId', 'genres'])
    >>> movies = tokenize(movies)
    >>> movies['tokens'].tolist()
    [['horror', 'romance'], ['sci-fi']]
    """
    ###TODO
    mylist = []
    dfList = movies['genres'].tolist()
    for i in dfList:
        mylist.append(tokenize_string(i))
    se = pd.Series(mylist)
    movies['tokens'] = se.values
    return movies
    pass


# In[3]:

def featurize(movies):
    """
    Append a new column to the movies DataFrame with header 'features'.
    Each row will contain a csr_matrix of shape (1, num_features). Each
    entry in this matrix will contain the tf-idf value of the term, as
    defined in class:
    tfidf(i, d) := tf(i, d) / max_k tf(k, d) * log10(N/df(i))
    where:
    i is a term
    d is a document (movie)
    tf(i, d) is the frequency of term i in document d
    max_k tf(k, d) is the maximum frequency of any term in document d
    N is the number of documents (movies)
    df(i) is the number of unique documents containing term i

    Params:
      movies...The movies DataFrame
    Returns:
      The movies DataFrame, which has been modified to include a column named 'features'.
    """
    ###TODO
    vocab = {}
    final_list = []
    N = len(movies.index)
    mylist = movies['tokens'].tolist()
    result = sorted(set(x for l in mylist for x in l))
    
    n = 0
    for i in result:
        vocab[i] = n
        n = n+1
        
    for movie in movies['tokens'].tolist():
        count = Counter(movie)
        max_k = max(count.values())
        row = []
        col = []
        data = []
        for i in range(len(movie)):
            row.append(0)
        for term in movie:
            col.append(vocab[term])
            freq = movie.count(term)
            count_val = 0
            for x in movies['tokens'].tolist():
                if term in x:
                    count_val = count_val + 1
            final_val = freq/max_k*math.log10(N/count_val)
            data.append(final_val)
        x = csr_matrix((data,(row,col)), shape=(1,len(vocab)))
        final_list.append(x)
        
    se = pd.Series(final_list)
    movies['features'] = se.values
    return (movies,vocab)
    pass


# In[4]:

def train_test_split(ratings):
    """DONE.
    Returns a random split of the ratings matrix into a training and testing set.
    """
    test = set(range(len(ratings))[::1000])
    train = sorted(set(range(len(ratings))) - test)
    test = sorted(test)
    return ratings.iloc[train], ratings.iloc[test]


# In[5]:

def cosine_sim(a, b):
    """
    Compute the cosine similarity between two 1-d csr_matrices.
    Each matrix represents the tf-idf feature vector of a movie.
    Params:
      a...A csr_matrix with shape (1, number_features)
      b...A csr_matrix with shape (1, number_features)
    Returns:
      The cosine similarity, defined as: dot(a, b) / ||a|| * ||b||
      where ||a|| indicates the Euclidean norm (aka L2 norm) of vector a.
    """
    ###TODO
    a = a.A
    b = b.A
    num = (np.dot(a,b.T)).sum()
    d1 = np.sqrt(np.sum(np.square(a)))
    d2 = np.sqrt(np.sum(np.square(b)))
    return num / (d1 * d2) 
    pass


# In[6]:

def make_predictions(movies, ratings_train, ratings_test):
    """
    Using the ratings in ratings_train, predict the ratings for each
    row in ratings_test.

    To predict the rating of user u for movie i: Compute the weighted average
    rating for every other movie that u has rated.  Restrict this weighted
    average to movies that have a positive cosine similarity with movie
    i. The weight for movie m corresponds to the cosine similarity between m
    and i.

    Params:
      movies..........The movies DataFrame.
      ratings_train...The subset of ratings used for making predictions. These are the "historical" data.
      ratings_test....The subset of ratings that need to predicted. These are the "future" data.
    Returns:
      A numpy array containing one predicted rating for each element of ratings_test.
    """
    ###TODO    
    final_val = []
    for v,mv in zip(ratings_test['userId'], ratings_test['movieId']):
        i = movies['features'][movies[movies.movieId==mv].index[0]]
        rating = []
        num = 0
        den = 0
        for v1,vv in ratings_train[ratings_train.userId==v].iterrows():
            rate = vv.rating
            rating.append(rate)
            m = movies['features'][movies[movies.movieId==vv.movieId].index[0]]
            cosine_val = cosine_sim(m, i)
            if cosine_val > 0:
                den = den + cosine_val
                value = cosine_val * rate
                num = num + value
        if num > 0 and den > 0:
            final_val.append(num/den)
        else:
            final_val.append(sum(rating) / float(len(rating)))
            
    return (np.array(final_val))
    pass


# In[7]:

def mean_absolute_error(predictions, ratings_test):
    """DONE.
    Return the mean absolute error of the predictions.
    """
    return np.abs(predictions - np.array(ratings_test.rating)).mean()


# In[8]:

def main():
    download_data()
    path = 'ml-latest-small'
    ratings = pd.read_csv(path + os.path.sep + 'ratings.csv')
    movies = pd.read_csv(path + os.path.sep + 'movies.csv')
    movies = tokenize(movies)
    movies, vocab = featurize(movies)
    print('vocab:')
    print(sorted(vocab.items())[:10])
    ratings_train, ratings_test = train_test_split(ratings)
    print('%d training ratings; %d testing ratings' % (len(ratings_train), len(ratings_test)))
    predictions = make_predictions(movies, ratings_train, ratings_test)
    print('error=%f' % mean_absolute_error(predictions, ratings_test))
    print(predictions[:10])


if __name__ == '__main__':
    main()


# In[ ]:



