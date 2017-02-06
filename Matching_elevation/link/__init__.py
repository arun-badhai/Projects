# Getting haversine function for calculating distance between two lat longs
from probe import haversine

# Defining a class to store link details in a structured manner
# Fields of this class store all the attributes of a link
class Link(object):
	def __init__(self, line):
		'''
			linkPVID		is the published versioned identifier for the link.
			refNodeID		is the internal identifier for the link's reference node.
			nrefNodeID		is the internal identifier for the link's non-reference node.
			length			is the length of the link (in decimal meters).
			functionalClass		is the functional class for the link (1-5).
			directionOfTravel	is the allowed direction of travel for the link (F is from ref node, T is towards ref node, B - both)
			speedCategory		is the speed category for the link (1-8).
			fromRefSpeedLimit	is the speed limit for the link (in kph) in the direction of travel from the reference node.
			toRefSpeedLimit		is the speed limit for the link (in kph) in the direction of travel towards the reference node.
			fromRefNumLanes		is the number of lanes for the link in the direction of travel from the reference node.
			toRefNumLanes		is the number of lanes for the link in the direction of travel towards the reference node.
			multiDigitized		is a flag to indicate whether or not the link is multiply digitized (T is is multiply digitized, F is singly digitized).
			urban			is a flag to indicate whether or not the link is in an urban area (T is in urban area, F is in rural area).
			timeZone		is the time zone offset (in decimal hours) from UTC.
			shapeInfo		contains an array of shape entries consisting of the latitude and longitude (in decimal degrees) and elevation (in decimal meters) for the link's nodes and shape points ordered as reference node, shape points, non-reference node. The array entries are delimited by a vertical bar character and the latitude, longitude, and elevation values for each entry are delimited by a forward slash character (e.g. lat/lon/elev|lat/lon/elev). The elevation values will be null for link's that don't have 3D data.
			curvatureInfo		contains an array of curvature entries consisting of the distance from reference node (in decimal meters) and curvature at that point (expressed as a decimal value of 1/radius in meters). The array entries are delimited by a vertical bar character and the distance from reference node and curvature values for each entry are separated by a forward slash character (dist/curvature|dist/curvature). This entire field will be null if there is no curvature data for the link.
			slopeInfo		contains an array of slope entries consisting of the distance from reference node (in decimal meters) and slope at that point (in decimal degrees). The array entries are delimited by a vertical bar character and the distance from reference node and slope values are separated by a forward slash character (dist/slope|dist/slope). This entire field will be null if there is no slope data for the link.
		'''
		self.linkPVID		  ,\
		self.refNodeID		  ,\
		self.nrefNodeID		  ,\
		self.length			  ,\
		self.functionalClass  ,\
		self.directionOfTravel,\
		self.speedCategory	  ,\
		self.fromRefSpeedLimit,\
		self.toRefSpeedLimit  ,\
		self.fromRefNumLanes  ,\
		self.toRefNumLanes	  ,\
		self.multiDigitized	  ,\
		self.urban			  ,\
		self.timeZone		  ,\
		self.shapeInfo		  ,\
		self.curvatureInfo	  ,\
		self.slopeInfo		  = line.strip().split(',')
		self.ReferenceNodeLat,self.ReferenceNodeLong,_  = self.shapeInfo.split('|')[0].split('/')
		self.ReferenceNode = map(float, (self.ReferenceNodeLat,self.ReferenceNodeLong))
		self.ProbePoints   = []
		#self.NonReferenceNodeLat,self.NonReferenceNodeLong,_  = self.shapeInfo.split('|')[-1].split('/')
		#self.NonReferenceNode = map(float, (self.NonReferenceNodeLat,self.NonReferenceNodeLong))

	# Returns all lat long details associated with the link		
	def getShapeInfo(self):
		return self.shapeInfo

	# Returns length of the link
	def getLength(self):
		return self.length
		
	# Returns ID of the link's reference node
	def getRefNodeID(self):
		return self.refNodeID
	
	# Returns lat longs and elevations associated with edges of the link		
	def getAllThePartsOfStreet(self):
		'''
			51.4965800/9.3862299/|51.4966899/9.3867100/|51.4968000/9.3873199/|51.4970100/9.3880399/,,
		'''
		street = self.shapeInfo
		nodes = street.split("|")   
		partsOfStreet = []
		for i in range(0, len(nodes)-1):
			lat1, lon1, ele1 = nodes[i].split('/')
			lat2, lon2, ele2 = nodes[i+1].split('/')
			start = map(float, [lat1, lon1, ele1])
			end   = map(float, [lat2, lon2, ele2])
			partsOfStreet.append((start,end))
		return partsOfStreet		

	# Returns reference node of the link
	def getReferenceNode(self):
		return self.ReferenceNode
	
	# Returns distance between two points, given their lat long values
	def calcDistanceBetweenPoints(self, start, end):
		'''lon1, lat1, lon2, lat2'''
		start = map(float, start)
		end = map(float, end)
		return haversine.haversine(start[1],start[0],end[1],end[0])
		
	# Returns distance from refernce node to the map matched point
	def getDistFromRef(self, indexOfEdge, mapMatchedPoint):
		parts = self.getAllThePartsOfStreet()
		distFromRef = 0
		for i in range(indexOfEdge-1):
			distFromRef += self.calcDistanceBetweenPoints(parts[i][0], parts[i][1])
		distFromRef += self.calcDistanceBetweenPoints(parts[indexOfEdge][0],mapMatchedPoint)
		return distFromRef*1000
			
		
