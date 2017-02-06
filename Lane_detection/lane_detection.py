
#Using Numpy, Scipy and Opencv libraries for python

import os, sys, argparse
import numpy as np
import imutils, cv2, math
from skimage.filters import threshold_adaptive
from geopy.distance import vincenty

#defining global variables
debug=True 

THRESHOLD_THETA = 0.2
THRESHOLD_RHO = 30
RIGHT_HAND_BOUNDARY_CONSTANT = 135
LANE_BOUNDARY_CONSTANT = 67.5

import math, geopy
from geopy.distance import VincentyDistance

def GetAngleOfLineBetweenTwoPoints(x1,y1,x2,y2):
    xDiff = x2 - x1
    yDiff = y2 - y1
    return math.degrees(math.atan2(yDiff, xDiff))

def getDestLatLong(x,y,centerLat,centerLong):
    tileCenterX = 320
    tileCenterY = 320
    dist = math.sqrt(((x-tileCenterX)**2)+((y-tileCenterY)**2))
    metPerPixel = 156543.03392 * math.cos(centerLat * math.pi / 180) / math.pow(2, 21)
    kDist = dist*metPerPixel/1000
    ang = GetAngleOfLineBetweenTwoPoints(x,y,tileCenterX,tileCenterY)
    origin = geopy.Point(centerLat, centerLong)
    destination = VincentyDistance(kilometers=kDist).destination(origin, ang)
    lat2, lon2 = destination.latitude, destination.longitude
    return lat2, lon2

#print getDestLatLong(200,200,46.395259,11.296116)

	
def sort_with_difference_from_first(list_of_lat_long):
	temp_lat_long_dist_tuple_list = []
	first_lat_longs = list_of_lat_long[0]
	for lat_long in list_of_lat_long[1:]:
		x2   = lat_long[0]
		x1   = first_lat_longs[0]
		y2   = lat_long[1]
		y1   = first_lat_longs[1]
		dist = vincenty((x2, y2),(x1, y1)).meters
		temp_lat_long_dist_tuple_list.append((x2, y2, dist))
	return sorted(temp_lat_long_dist_tuple_list, key=lambda x: x[2])

def detect_edges(directory=os.getcwd(), extension="png"):
	# Access all PNG files in directory
	allfiles=os.listdir(directory)
	
	#Finding the list of images
	imlist=[filename for filename in allfiles if  filename[-4:] in [ extension, extension.upper()]]
	
	list_of_centers = []
	with open("drive.traj") as trajectory_file:
		all_lines = trajectory_file.readlines()
		for line in all_lines:
			lat, long, _ = line.strip().split(" ")
			list_of_centers.append((lat, long))
				
	left_inside_boundary = []
	right_inside_boundary = []
	
	left_road_boundary   = []
	right_road_boundary  = []
	
	left_lane_marking = []
	right_lane_marking = []
	
	centre_lines = []
	line_length = 0
	max_line_length = 0
	
	counter = 0
	
	# Iterate over all the images
	for im in imlist:
		img 		= cv2.imread(os.path.join(directory, im))
		bnw_img 	= cv2.cvtColor(img,cv2.COLOR_BGR2GRAY)
		bnw_img 	= cv2.GaussianBlur(bnw_img, (5,5), 0)
		img_w_edges = cv2.Canny(bnw_img, 75, 200, apertureSize = 3)
		lines 		= cv2.HoughLines(img_w_edges, 1, np.pi/180, 110)
		set = False
		theta_list, rho_list = [], []
		for line in lines:
			#import pdb; pdb.set_trace()
			for rho,theta in line:
				#import pdb; pdb.set_trace()
				if rho  < 315.0 + THRESHOLD_RHO and rho > 291.0 - THRESHOLD_RHO :
					if theta  < 0.34906584 + THRESHOLD_THETA and theta > 0.34906584 - THRESHOLD_THETA :
						theta_list.append(theta)
						rho_list.append(rho)
						
		min_rho = min(rho_list)
		max_rho = max(rho_list)
		
		pos_min_rho = rho_list.index(min_rho)
		
		pos_max_rho = rho_list.index(max_rho)
		
		#Minimum rho case
		#Left Inside Road Boundary
		#=================================================================
		a = np.cos(theta_list[pos_min_rho])
		b = np.sin(theta_list[pos_min_rho])
		x0 = a*rho_list[pos_min_rho]
		y0 = b*rho_list[pos_min_rho]
		x1 = int(x0 + 575*(-b))
		y1 = int(y0 + 575*(a))
		x2 = int(x0 - 575*(-b))
		y2 = int(y0 - 575*(a))
		
		lirb_start 	= getDestLatLong( x1, y1, float(list_of_centers[counter][0]), float(list_of_centers[counter][1]))
		lirb_end 	= getDestLatLong( x2, y2, float(list_of_centers[counter][0]), float(list_of_centers[counter][1]))
		
		left_inside_boundary.append(lirb_start)
		left_inside_boundary.append(lirb_end)
		
		#print x1, y1, x2, y2
		#if rho < rho_first + 30 and rho > rho_first - 30:
		#cv2.circle(img,(x1, y1), 10, (0,0,255), -1)
		#cv2.circle(img,(x2, y2), 10, (0,0,255), -1)
		k = cv2.line(img,(x1,y1),(x2,y2),(0,0,255), 2)

	
		#=================================================================

		#Maximum rho case
		#Right Inside Road Boundary
		
		#=================================================================
		a = np.cos(theta_list[pos_max_rho])
		b = np.sin(theta_list[pos_max_rho])
		x0 = a*rho_list[pos_max_rho]
		y0 = b*rho_list[pos_max_rho]
		x1 = int(x0 + 575*(-b))
		y1 = int(y0 + 575*(a))
		x2 = int(x0 - 575*(-b))
		y2 = int(y0 - 575*(a))
			
		rirb_start 	= getDestLatLong( x1, y1, float(list_of_centers[counter][0]), float(list_of_centers[counter][1]))
		rirb_end 	= getDestLatLong( x2, y2, float(list_of_centers[counter][0]), float(list_of_centers[counter][1]))
		
		right_inside_boundary.append(rirb_start)
		right_inside_boundary.append(rirb_end)
		
		#if rho < rho_first + 30 and rho > rho_first - 30:
		#cv2.circle(img,(x1, y1), 10, (0,0,255), -1)
		#cv2.circle(img,(x2, y2), 10, (0,0,255), -1)
		k = cv2.line(img,(x1,y1),(x2,y2),(0,0,255), 2)
		
		#=================================================================
		
		#Plot Left Hand Road Boundary
		
		#=================================================================
		a = np.cos(theta_list[pos_max_rho])
		b = np.sin(theta_list[pos_max_rho])
		x0 = a*(rho_list[pos_max_rho] - 1.3*RIGHT_HAND_BOUNDARY_CONSTANT)
		y0 = b*(rho_list[pos_max_rho] - 1.3*RIGHT_HAND_BOUNDARY_CONSTANT)
		x1 = int(x0 + 575*(-b))
		y1 = int(y0 + 575*(a))
		x2 = int(x0 - 575*(-b))
		y2 = int(y0 - 575*(a))
		
		lrb_start 	= getDestLatLong( x1, y1, float(list_of_centers[counter][0]), float(list_of_centers[counter][1]))
		lrb_end 	= getDestLatLong( x2, y2, float(list_of_centers[counter][0]), float(list_of_centers[counter][1]))
		
		left_road_boundary.append(lrb_start)
		left_road_boundary.append(lrb_end)
		#if rho < rho_first + 30 and rho > rho_first - 30:
		#cv2.circle(img,(x1, y1), 10, (0,0,255), -1)
		#cv2.circle(img,(x2, y2), 10, (0,0,255), -1)
		k = cv2.line(img,(x1,y1),(x2,y2),(0,0,255), 2)
		
		
		#=================================================================
		
		#Plot Right Hand Road Boundary
		
		#=================================================================
		a = np.cos(theta_list[pos_max_rho])
		b = np.sin(theta_list[pos_max_rho])
		x0 = a*(rho_list[pos_max_rho] + RIGHT_HAND_BOUNDARY_CONSTANT)
		y0 = b*(rho_list[pos_max_rho] + RIGHT_HAND_BOUNDARY_CONSTANT)
		x1 = int(x0 + 575*(-b))
		y1 = int(y0 + 575*(a))
		x2 = int(x0 - 575*(-b))
		y2 = int(y0 - 575*(a))
		
		rrb_start 	= getDestLatLong( x1, y1, float(list_of_centers[counter][0]), float(list_of_centers[counter][1]))
		rrb_end 	= getDestLatLong( x2, y2, float(list_of_centers[counter][0]), float(list_of_centers[counter][1]))
		
		right_road_boundary.append(rrb_start)
		right_road_boundary.append(rrb_end)

		#if rho < rho_first + 30 and rho > rho_first - 30:
		#cv2.circle(img,(x1, y1), 10, (0,0,255), -1)
		#cv2.circle(img,(x2, y2), 10, (0,0,255), -1)
		k = cv2.line(img,(x1,y1),(x2,y2),(0,0,255), 2)
		
		#=================================================================
		
		#Plot Left Hand Lane Boundary
		
		#=================================================================
		a = np.cos(theta_list[pos_max_rho])
		b = np.sin(theta_list[pos_max_rho])
		x0 = a*(rho_list[pos_max_rho] - 1.5*LANE_BOUNDARY_CONSTANT)
		y0 = b*(rho_list[pos_max_rho] - 1.5*LANE_BOUNDARY_CONSTANT)
		x1 = int(x0 + 575*(-b))
		y1 = int(y0 + 575*(a))
		x2 = int(x0 - 575*(-b))
		y2 = int(y0 - 575*(a))
		
		llb_start 	= getDestLatLong( x1, y1, float(list_of_centers[counter][0]), float(list_of_centers[counter][1]))
		llb_end 	= getDestLatLong( x2, y2, float(list_of_centers[counter][0]), float(list_of_centers[counter][1]))
		
		left_lane_marking.append(llb_start)
		left_lane_marking.append(llb_end)
		
		#if rho < rho_first + 30 and rho > rho_first - 30:
		#cv2.circle(img,(x1, y1), 10, (0,0,255), -1)
		#cv2.circle(img,(x2, y2), 10, (0,0,255), -1)
		k = cv2.line(img,(x1,y1),(x2,y2),(0,0,255), 2)
		
		#=================================================================
		
		#Plot Right Hand Lane Boundary
		
		#=================================================================
		a = np.cos(theta_list[pos_max_rho])
		b = np.sin(theta_list[pos_max_rho])
		x0 = a*(rho_list[pos_max_rho] + LANE_BOUNDARY_CONSTANT)
		y0 = b*(rho_list[pos_max_rho] + LANE_BOUNDARY_CONSTANT)
		x1 = int(x0 + 575*(-b))
		y1 = int(y0 + 575*(a))
		x2 = int(x0 - 575*(-b))
		y2 = int(y0 - 575*(a))
		
		rlb_start 	= getDestLatLong( x1, y1, float(list_of_centers[counter][0]), float(list_of_centers[counter][1]))
		rlb_end 	= getDestLatLong( x2, y2, float(list_of_centers[counter][0]), float(list_of_centers[counter][1]))
		
		right_lane_marking.append(rlb_start)
		right_lane_marking.append(rlb_end)
		
		#if rho < rho_first + 30 and rho > rho_first - 30:
		#cv2.circle(img,(x1, y1), 10, (0,0,255), -1)
		#cv2.circle(img,(x2, y2), 10, (0,0,255), -1)
		k = cv2.line(img,(x1,y1),(x2,y2),(0,0,255), 2)
		
		#=================================================================
		
		#cv2.imshow(im,img); cv2.waitKey(0)
		counter += 1
		
		
		#cv2.imwrite("_"+im,img)
		#cv2.imwrite("_"+im,img_w_edges)
		
		#import pdb;pdb.set_trace()
		
		left_inside_boundary =	sort_with_difference_from_first(left_inside_boundary)
		right_inside_boundary = sort_with_difference_from_first(right_inside_boundary)
		
		left_road_boundary = sort_with_difference_from_first(left_road_boundary)
		right_road_boundary = sort_with_difference_from_first(right_road_boundary)
		
		left_lane_marking = sort_with_difference_from_first(left_lane_marking)
		right_lane_marking = sort_with_difference_from_first(right_lane_marking)
		
		#import pdb;pdb.set_trace()
		
	with open("output.txt", "w") as output:
		output.write("left_inside_boundary" + "|"+
					 "right_inside_boundary"  + "|" +
					 "left_road_boundary"  + "|" +
					 "right_road_boundary"  + "|" +
					 "left_lane_marking"  + "|" +
					 "right_lane_marking"+"\n")
		#import pdb;pdb.set_trace()
		for i in range(100):
			output.write(str(left_inside_boundary[i][0])+","+str(left_inside_boundary[i][1])+"|"+
						str(right_inside_boundary[i][0])+","+str(right_inside_boundary[i][1])+"|"+
						str(left_road_boundary[i][0])+","+ str(left_road_boundary[i][1]) + "|" +
						str(right_road_boundary[i][0])+","+ str(right_road_boundary[i][1])+ "|" +
						str(left_lane_marking[i][0])+","+str(left_lane_marking[i][1]) + "|" +
						str(right_lane_marking[i][0])+","+str(right_lane_marking[i][1])+"\n"
						)
		
	return True

if __name__ == "__main__":
	parser = argparse.ArgumentParser()
	
	#extension
	parser.add_argument("-e", "--extension", default='.png', help="file extension of images")
	
	#Directory
	parser.add_argument("-d", "--directory", help="directory of the images")
	
	#Execution Mode
	parser.add_argument("-r", "--release", type=bool, help="debug or release mode")
	
	#Others if required
	
	#Making the parse
	args = parser.parse_args()
	
	#Checking if the directory is provided
	if not args.directory:
		parser.print_help()
		sys.exit(1)
	
	#Assigning values to the variables
	extension = args.extension
	directory = args.directory
	mode = args.release
	if mode: debug=False
	
	print "Smear Detection started"
	
	#Calling the detect smear function
	smear_detected = detect_edges(directory, extension)
	'''	
	if(smear_detected):
		print("Smear is detected. Please find the mask_img.jpg to check the location of smear")
	else:
		print("Smear not detected!")
	'''
