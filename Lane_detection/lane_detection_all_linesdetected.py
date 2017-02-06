
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

def convert_from_pixel_to_latlong():
	pass


def detect_edges(directory=os.getcwd(), extension="png"):
	# Access all PNG files in directory
	allfiles=os.listdir(directory)
	
	#Finding the list of images
	imlist=[filename for filename in allfiles if  filename[-4:] in [ extension, extension.upper()]]

	left_road_boundary   = []
	right_road_boundary  = []
	left_inside_boundary = []
	right_inside_boundary = []
	left_lane_marking = []
	right_lane_marking = []
	centre_lines = []
	line_length = 0
	max_line_length = 0
	
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
		
		#=================================================================
		a = np.cos(theta_list[pos_min_rho])
		b = np.sin(theta_list[pos_min_rho])
		x0 = a*rho_list[pos_min_rho]
		y0 = b*rho_list[pos_min_rho]
		x1 = int(x0 + 1000*(-b))
		y1 = int(y0 + 1000*(a))
		x2 = int(x0 - 1000*(-b))
		y2 = int(y0 - 1000*(a))
	
		#if rho < rho_first + 30 and rho > rho_first - 30:
		cv2.circle(img,(x0, 0), 5, (0,0,255), -1)
		k = cv2.line(img,(x1,y1),(x2,y2),(0,0,255),1)
		'''
		#=================================================================

		#Maximum rho case
		
		#=================================================================
		a = np.cos(theta_list[pos_max_rho])
		b = np.sin(theta_list[pos_max_rho])
		x0 = a*rho_list[pos_max_rho]
		y0 = b*rho_list[pos_max_rho]
		x1 = int(x0 + 1000*(-b))
		y1 = int(y0 + 1000*(a))
		x2 = int(x0 - 1000*(-b))
		y2 = int(y0 - 1000*(a))
		
		#if rho < rho_first + 30 and rho > rho_first - 30:
		k = cv2.line(img,(x1,y1),(x2,y2),(0,0,255),1)
		
		#=================================================================
		
		
		#Plot Right Hand Road Boundary
		
		#=================================================================
		a = np.cos(theta_list[pos_max_rho])
		b = np.sin(theta_list[pos_max_rho])
		x0 = a*(rho_list[pos_max_rho] + RIGHT_HAND_BOUNDARY_CONSTANT)
		y0 = b*(rho_list[pos_max_rho] + RIGHT_HAND_BOUNDARY_CONSTANT)
		x1 = int(x0 + 1000*(-b))
		y1 = int(y0 + 1000*(a))
		x2 = int(x0 - 1000*(-b))
		y2 = int(y0 - 1000*(a))
		
		#if rho < rho_first + 30 and rho > rho_first - 30:
		k = cv2.line(img,(x1,y1),(x2,y2),(0,0,255),1)
		
		#=================================================================
		
		#Plot Left Hand Road Boundary
		
		#=================================================================
		a = np.cos(theta_list[pos_max_rho])
		b = np.sin(theta_list[pos_max_rho])
		x0 = a*(rho_list[pos_max_rho] - 1.3*RIGHT_HAND_BOUNDARY_CONSTANT)
		y0 = b*(rho_list[pos_max_rho] - 1.3*RIGHT_HAND_BOUNDARY_CONSTANT)
		x1 = int(x0 + 1000*(-b))
		y1 = int(y0 + 1000*(a))
		x2 = int(x0 - 1000*(-b))
		y2 = int(y0 - 1000*(a))
		
		#if rho < rho_first + 30 and rho > rho_first - 30:
		k = cv2.line(img,(x1,y1),(x2,y2),(0,0,255),1)
		
		#=================================================================
		
		#Plot Right Hand Lane Boundary
		
		#=================================================================
		a = np.cos(theta_list[pos_max_rho])
		b = np.sin(theta_list[pos_max_rho])
		x0 = a*(rho_list[pos_max_rho] + LANE_BOUNDARY_CONSTANT)
		y0 = b*(rho_list[pos_max_rho] + LANE_BOUNDARY_CONSTANT)
		x1 = int(x0 + 1000*(-b))
		y1 = int(y0 + 1000*(a))
		x2 = int(x0 - 1000*(-b))
		y2 = int(y0 - 1000*(a))
		
		#if rho < rho_first + 30 and rho > rho_first - 30:
		k = cv2.line(img,(x1,y1),(x2,y2),(0,0,255),1)
		
		#=================================================================
		
		#Plot Left Hand Lane Boundary
		
		#=================================================================
		a = np.cos(theta_list[pos_max_rho])
		b = np.sin(theta_list[pos_max_rho])
		x0 = a*(rho_list[pos_max_rho] - 1.5*LANE_BOUNDARY_CONSTANT)
		y0 = b*(rho_list[pos_max_rho] - 1.5*LANE_BOUNDARY_CONSTANT)
		x1 = int(x0 + 1000*(-b))
		y1 = int(y0 + 1000*(a))
		x2 = int(x0 - 1000*(-b))
		y2 = int(y0 - 1000*(a))
		
		#if rho < rho_first + 30 and rho > rho_first - 30:
		k = cv2.line(img,(x1,y1),(x2,y2),(0,0,255),1)
		
		#=================================================================
		'''
		cv2.imshow(im,img); cv2.waitKey(0)
		
		
		
		#cv2.imwrite("_"+im,img)
		#cv2.imwrite("_"+im,img_w_edges)
		
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
