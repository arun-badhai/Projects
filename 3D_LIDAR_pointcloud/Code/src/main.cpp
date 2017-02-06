// Demo program
#include <iostream>
# include <fstream>
#include "icpPointToPlane.h"

using namespace std;

int main (int argc, char** argv) {
	if (argc < 2) {
      printf("Please provide some arguments\n");
      printf("Use -h/--help for reference\n");
      exit(EXIT_FAILURE);
   }
   
   //Get the model file as input
	char* filename1;
	
	//Get the template file as input]
	char* filename2;
	
	long numOfRows = 0;
	
  //Command line parsing
	for (int i=1; i< argc; i=i+2) 
	{
		if(0 == strcmp(argv[i], "--numOfRows"))
		{
			numOfRows = atoi(argv[i+1]);
		}
		else if(0 == strcmp(argv[i], "--filename1"))
		{
			filename1 = (char*)argv[i+1];
		}
		else if(0 == strcmp(argv[i], "--filename2"))
		{
			filename2 = (char*)argv[i+1];
		}
		else
		{
			printf("Use of icp executable:\n");
			printf("\t./icp\n");
			printf("\t\t --filename1 pointcloud1.fuse\n");
			printf("\t\t --filename2 pointcloud2.fuse\n");
			printf("\t\t --numOfRows 1046232\n");
			exit(EXIT_FAILURE);
		}
	}
  // define a 3 dim problem with 10000 model points
  // and 10000 template points:
  long dim = 3;
  long num = numOfRows;

  // allocate model and template memory
  double* M = (double*)calloc(3*num,sizeof(double));
  double* T = (double*)calloc(3*num,sizeof(double));
  double temp;
  
  ifstream file;
  file.open(filename1);
  
  if(file.is_open())
  {
	  cout << "file1 is open" << endl;
	  for(int i = 0; i < 1046232*3; i=i+3)
	  {  
		file >> M[i];
		file >> M[i+1];
		file >> M[i+2];
		file >> temp;
	  }
  }
  file.close();
  
  ifstream file1;
  file1.open(filename2);
  
  if(file1.is_open())
  {
	  cout << "file2 is open" << endl;
	  for(int i = 0; i < 1046232*3; i=i+3)
	  {  
		file1 >> T[i];
		file1 >> T[i+1];
		file1 >> T[i+2];
		file1 >> temp;
	  }
  }
  file1.close();
  // start with identity as initial transformation
  // in practice you might want to use some kind of prediction here
  Matrix R = Matrix::eye(3);
  Matrix t(3,1);

  // run point-to-plane ICP (-1 = no outlier threshold)
  cout << endl << "Running ICP (point-to-plane, no outliers)" << endl;
  IcpPointToPlane icp(M,num,dim);
  icp.fit(T,num,R,t,-1);

  // results
  cout << endl << "Transformation results:" << endl;
  cout << "R:" << endl << R << endl << endl;
  cout << "t:" << endl << t << endl << endl;

  // free memory
  free(M);
  free(T);

  // success
  return 0;
}
