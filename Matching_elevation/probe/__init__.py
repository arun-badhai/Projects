# Getting haversine function for calculating distance between two lat longs
import haversine

# Defining a class to store probe details in a structured manner
# Fields of this class store all the attributes of a probe
class Probe(object):
	def __init__(self, line):
		'''
			sampleID	is a unique identifier for the set of probe points that were collected from a particular phone.
			dateTime	is the date and time that the probe point was collected.
			sourceCode	is a unique identifier for the data supplier (13 = COMPANY).
			latitude	is the latitude in decimal degrees.
			longitude	is the longitude in decimal degrees.
			altitude	is the altitude in meters.
			speed		is the speed in KPH.
			heading		is the heading in degrees.
		'''
		self.sampleID, self.dateTime, self.sourceCode, self.latitude, self.longitude, self.altitude, self.speed, self.heading, self.linkPVID, self.direction, self.distFromRef, self.distFromLink = line.split(',')
		self.assigned_street  = None
		self.elevation = None
		self.slope = None
		
	# Returns sample ID of the probe point
	def getSampleID(self):
		return self.sampleID

	# Returns lat long of the probe point
	def getPosition(self):
		return self.latitude, self.longitude
		
	# Assigns the nearest link ID to the probe point 
	def assignLinkPVID(self):
		self.assigned_street = assigned_street
		
	# Returns shortest distance to given line and nearest line point from given point 
	def calcDistanceToEdge(self, point, start, end):
		'''
			def pnt2line(pnt, start, end):
			b = [1,2]; e = [2,1]; o = [0,0]
			print pnt2line(o,b,e)
		'''
		return haversine.pnt2line(point, start, end)
		
