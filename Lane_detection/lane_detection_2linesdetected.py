
#Using Numpy, Scipy and Opencv libraries for python

import os, sys, argparse
import numpy as np
import imutils, cv2, math
from skimage.filters import threshold_adaptive

#defining global variables
debug=True 

THRESHOLD_THETA = 0.2
THRESHOLD_RHO = 30

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
		lineCount = 0
		set = False
		for line in lines:
			#import pdb; pdb.set_trace()
			for rho,theta in line:
				#import pdb; pdb.set_trace()
				if rho  < 315.0 + THRESHOLD_RHO and rho > 291.0 - THRESHOLD_RHO :
					if not set:
						rho_first = rho
						set = True
					if theta  < 0.34906584 + THRESHOLD_THETA and theta > 0.34906584 - THRESHOLD_THETA :
						a = np.cos(theta)
						b = np.sin(theta)
						x0 = a*rho
						y0 = b*rho
						x1 = int(x0 + 1000*(-b))
						y1 = int(y0 + 1000*(a))
						x2 = int(x0 - 1000*(-b))
						y2 = int(y0 - 1000*(a))

						if (lineCount==1 or lineCount==5):
							centre_lines.append((x1,y1,x2,y2))
							max_line_length = math.sqrt((x2-x1)**2+(y2-y1)**2)
						
						#if rho < rho_first + 30 and rho > rho_first - 30:
						k = cv2.line(img,(x1,y1),(x2,y2),(0,0,255),2)
						
						#cv2.imshow(im,img); cv2.waitKey(0)
							
							
						
						#line_length = math.sqrt((x2-x1)**2+(y2-y1)**2)
						#if line_length == max_line_length:
						#import pdb; pdb.set_trace()
			lineCount+=1
		#cv2.imshow(im,img); cv2.waitKey(0)
		cv2.imwrite("_"+im,img)
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
