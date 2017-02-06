
# coding: utf-8

# In[2]:

files = ['collect.txt','cluster.txt','classify.txt']
out = open('summary.txt','w')
for file in files:
    f = open(file,"r")
    out.write(f.read())
    f.close()
out.close()

