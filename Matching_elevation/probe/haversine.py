import csv
import math

def haversine(long1, latt1, long2, latt2):
    '''
    Calculate the great circle distance between two points 
    on the earth (specified in decimal degrees).
    Source: http://gis.stackexchange.com/a/56589/15183
    '''
    long1, latt1, long2, latt2 = map(math.radians, [long1, latt1, long2, latt2])
    dlong = long2 - long1 
    dlatt = latt2 - latt1 
    a = math.sin(dlatt/2)**2 + math.cos(latt1) * math.cos(latt2) * math.sin(dlong/2)**2
    c = 2 * math.asin(math.sqrt(a)) 
    km = 6367 * c
    return km

def haversine2(long1, latt1, ele1, long2, latt2, ele2):
    '''
    Calculate the great circle distance between two points 
    on the earth (specified in decimal degrees).
    Source: http://gis.stackexchange.com/a/56589/15183
    '''
    long1, latt1, long2, latt2 = map(math.radians, [long1, latt1, ele1, long2, latt2, ele2])
    dlong = long2 - long1 
    dlatt = latt2 - latt1 
    dele = ele2 - ele1 
    a = math.sin(dlatt/2)**2 + math.cos(latt1) * math.cos(latt2) * math.sin(dlong/2)**2
    c = 2 * math.asin(math.sqrt(a)) 
    km = 6367 * c
    return km
	
def dot(inpt1,inpt2):
    x,y = inpt1
    X,Y = inpt2
    return x*X + y*Y

def vector(inpt1,inpt2):
    x,y = inpt1
    X,Y = inpt2
    return (X-x, Y-y)

def add(inpt1,inpt2):
    x,y = inpt1
    X,Y = inpt2
    return (x+X, y+Y)

def length(inpt):
    x,y = inpt
    return math.sqrt(x*x + y*y)

def unit(inpt):
    x,y = inpt
    mag = length(inpt)
    return (x/mag, y/mag)

def distance(pt0,pt1):
    return length(vector(pt0,pt1))
  
def scale(vect,sclr):
    x,y = vect
    return (x * sclr, y * sclr)
  
def pnt2line(point, startpt, endpt):
    '''
    Calculate the shortest distance
    between point and a line
    '''
    line_vect = vector(startpt, endpt)
    point_vect = vector(startpt, point)
    line_len = length(line_vect)
    line_unitvect = unit(line_vect)
    point_vect_scaled = scale(point_vect, 1.0/line_len)
    x = dot(line_unitvect, point_vect_scaled)    
    if x < 0.0:
        x = 0.0
    elif x > 1.0:
        x = 1.0
    nearest = scale(line_vect, x)
    dist = distance(nearest, point_vect)
    nearest = add(nearest, startpt)
    return (dist, nearest)
