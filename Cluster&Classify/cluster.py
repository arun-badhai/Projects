
# coding: utf-8

# In[17]:

from collections import Counter, defaultdict, deque
import copy
import math
import matplotlib.pyplot as plt
import networkx as nx
import urllib.request


# In[24]:

def read_graph():
    return nx.read_edgelist('edges.txt', delimiter='\t')


# In[19]:

#Lecture 5
def find_best_edge(G0):
        eb = nx.edge_betweenness_centrality(G0)
        return sorted(eb.items(), key=lambda x: x[1], reverse=True)[0][0]


# In[20]:

#Lecture 5
def girvan_newman(G, depth=0):
    if G.order() == 1:
        return [G.nodes()]
    components = [c for c in nx.connected_component_subgraphs(G)]
    indent = '   ' * depth
    #Restricted to 4 clusters
    while len(components) < 4:
        edge_to_remove = find_best_edge(G)
        G.remove_edge(*edge_to_remove)
        components = [c for c in nx.connected_component_subgraphs(G)]
    result = [c for c in components]
    #Will keep clustering till there are clusters formed with just one node in each of them
    '''
    Recursion:
    for c in components:
        result.extend(girvan_newman(c, depth + 1))
    '''
    return result


# In[21]:

def draw_network(graph, filename):
    labels = {node: node if not isinstance(node,int) else '' for node in graph.nodes()}
    plt.figure(figsize=(40,40))
    nx.draw_networkx(graph, edge_color = 'r', labels = labels, font_size = 15, node_color = 'y', node_size = 30)
    plt.axis("off")
    plt.savefig(filename)
    plt.show()


# In[25]:

def main():
    cluster = open("cluster.txt", 'w')
    val = 0
    val_new = 0
    graph = read_graph()
    print ('Graph before clustering:')
    draw_network(graph,'graph.png')
    print('graph has %d nodes and %d edges' %
          (graph.order(), graph.number_of_edges()))
    print ("Clustering has started.....!!!")
    clusters = girvan_newman(graph, depth=3)
      
    #All the cluster elements
    '''
    file = open("clusternew.txt",'w')
    for i in clusters:
        if len(i) > 10:
            val = val + 1
            file.write('\n')
            file.write('Cluster Number: ')
            file.write('%d' % val)
            file.write('\n')
            for j in i.nodes():
                file.write(j)
                file.write('\n')
    file.close()
    '''
    for i in clusters:
        if len(i) > 10:
            val = val + 1
    cluster.write('Final Cluster Number: %d' %val)
    cluster.write("\n")
    cluster.write('Average number of users per community: %f' % (graph.order()/len(clusters)))
    cluster.write("\n")
    cluster.close()
    print('Number of communities = %d' % len(clusters))
    print('Final graph has %d nodes' % graph.order())
    print('Final graph has %d edges' % graph.size())
    print ('Graph after clustering:')
    draw_network(graph,'graphnew.png')
    

if __name__ == '__main__':
    main()

