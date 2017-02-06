The main solution script is 1_map_matching_elevation.py. 

The script uses modules defined in link and probe folders. 

Packages required to run the code:

1. Python 2.7 

By default the script reads from files in data folder and writes result to file in result folder.

Input: 		

Link data: data/Partition6467LinkData.csv		

Probe data: data/Partition6467MatchedPoints.csv	

Output:	

Result: result/SlopeOutput.csv result/Elevation.csv

How to run:	

Run this command in commandline: 

python 1_map_matching_elevation.py	

Or 

customize the input and output paths with flags: 

python 1_map_matching_elevation.py --ld PATH-TO-LINKDATA --pd PATH-TO-PROBEDATA --rd PATH-TO-RESULTDATA



