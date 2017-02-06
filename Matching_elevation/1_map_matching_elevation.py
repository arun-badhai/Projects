'''
	CS513 Assignment 3: Probe data map matching
'''
# Getting Link and Probe classes to store data in a structured manner
# Arg parse is to parse commandline arguments
from link import Link
from probe import Probe, haversine
import argparse
import math

if __name__ == "__main__":

	# Build the argument parser and split the arguments
	ap = argparse.ArgumentParser()
	ap.add_argument("-ld", "--linkdirectory", default = "data/Partition6467LinkData.csv",
		help = "Path to the directory that contains link data")
	ap.add_argument("-pd", "--probedirectory", default = "data/Partition6467MatchedPoints.csv",
		help = "Path to the directory that contains probe data")
	ap.add_argument("-rd", "--resultdirectory", default="result/",
		help = "Path to the directory that should contain result data")
	# Function vars returns a dictionary that represents a symbol table
	args = vars(ap.parse_args())

	# Initialize a list of link data and a probe file line count 
	link_data = []; flCount = 0 
	
	# Read data from link file, store them into Link objects and append them to list
	print "Creating Link objects"; 	
	with open(args["linkdirectory"]) as street_data:
		for line in street_data:
			link = Link(line)			
			link_data.append(link)
			
	# Clear out result directory, to flush previous content
	result_data = open(args["resultdirectory"]+"Elevation.csv",'w'); result_data.close()
	sout_data = open(args["resultdirectory"]+"SlopeOutput.csv",'w'); sout_data.close()
	
	# Open probe data file
	with open(args["probedirectory"]) as probe_data:
		result_data = open(args["resultdirectory"]+"Elevation.csv",'w+')
		header = '''sampleID,	
		            dateTime,	
		            sourceCode,	
		            latitude,	
		            longitude,	
		            altitude,	
		            speed,		
		            heading,		
		            linkPVID,	
		            direction,	
		            distFromRef,	
		            distFromLink,
					slope'''
		header = header.replace(" ", "")		
		header = header.replace("\n", "")		
		header = header.replace("\t", "")
		result_data.write(header+"\n")
		
		prev_probe = None
		print "Calculating the slope at each map matched point"; 		
		print "This will take some time.."; 		
		for line in probe_data:
			probe = Probe(line)
			if not prev_probe:
				probe.slope = 'X'
			elif probe.linkPVID != prev_probe.linkPVID:
				probe.slope = 'X'
			else:
				####################################################################
				#                 LOGIC TO CALCULATE SLOPE OF LINK                 #
				####################################################################
				opposite = float(probe.altitude) - float(prev_probe.altitude)
				start = map(float, [probe.longitude, probe.latitude])
				end = map(float, [prev_probe.longitude, prev_probe.latitude])
				#import pdb;pdb.set_trace()
				hypotenuse = haversine.haversine(start[0],start[1],end[0],end[1])/1000
				probe.slope = math.atan(opposite/hypotenuse)
				probe.slope = (2*math.pi*probe.slope)/360			

				for link in link_data:
					if probe.linkPVID == link.linkPVID and link.slopeInfo != '':
						link.ProbePoints.append(probe)    #You can append the probe's data here	
						break
				
				####################################################################	
			data = 	 probe.sampleID+","			\
					+probe.dateTime+","         \
					+probe.sourceCode+","       \
					+str(probe.latitude)+","    \
					+str(probe.longitude)+","   \
					+probe.altitude+","         \
					+probe.speed+","            \
					+probe.heading+","          \
					+probe.linkPVID+","      \
					+probe.direction+","        \
					+str(probe.distFromRef)+"," \
					+str(probe.distFromLink)+","   \
					+str(probe.slope)
			data = data.replace("\n","")
			result_data.write(data+"\n");
			flCount += 1 # Increment line number
			result_data.flush()
			prev_probe = probe
		result_data.close(); 

	print "Lets use the slopes at diffferent probe points to calculate slope of the link"; 
	# Iterate over link data to consolidate slope derived from probe points
	sOut = 'linkPVID,  GivenMeanSlope, CalculatedMeanSlope'
	sout_data = open(args["resultdirectory"]+"SlopeOutput.csv",'a') 
	sout_data.write(sOut+"\n");
	for lnk in link_data:
		# If link has some map matched points, find the mean and cumulative slope of all those points, provided 
		# they don't have undefined slope. Also negate slope, if probe direction is away from ref node
		if len(lnk.ProbePoints) > 0:
			sumVal = 0.0; nonZeroPrbCount = 0
			givenSlope = []; gSlopeSum = 0.0
			calMeanSlope = 0.0; gMeanSlope = []
			for prb in lnk.ProbePoints:
				if prb.direction == "T":
					prb.slope = -prb.slope
					
				if prb.slope != "X" and prb.slope != 0:
					sumVal = sumVal + prb.slope
					nonZeroPrbCount += 1	
			
			if nonZeroPrbCount != 0:
				calMeanSlope = sumVal/nonZeroPrbCount
				calCumSlope = sumVal
			else:
				calMeanSlope = 0

			# Now calculate the mean of given slope info for the link
			givenSlope = lnk.slopeInfo.strip().split('|')	
			for gslope in givenSlope:
				gSlopeSum += float(gslope.strip().split('/')[1])

			gMeanSlope = gSlopeSum/len(givenSlope)  	
			
			# Print the given and calculated slope values and write them to output file
			sOut =  "For linkID %s, given mean slope is %f; Calculated mean slope is %f" %(lnk.linkPVID,gMeanSlope,calMeanSlope)
			print sOut
			sOut = "%s, %f, %f" % (lnk.linkPVID,  gMeanSlope, calMeanSlope)
			sout_data.write(sOut+"\n");
	sout_data.close();
			
