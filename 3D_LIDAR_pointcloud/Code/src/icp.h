#ifndef ICP_H
#define ICP_H

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <iostream>
#include <vector>

#include "matrix.h"
#include "kdtree.h"

class Icp {

public:

  // constructor
  // input: M ....... pointer to first model point
  //        M_num ... number of model points
  //        dim   ... dimensionality of model points (2 or 3)
  Icp (double *M,const long M_num,const long dim);
  
  // deconstructor
  virtual ~Icp ();
  
  // set maximum number of iterations (1. stopping criterion)
  void setMaxIterations   (long val) { max_iter  = val; }
  
  // set minimum delta of rot/trans parameters (2. stopping criterion)
  void setMinDeltaParam   (double  val) { min_delta = val; }
  
  // fit template to model yielding R,t (M = R*T + t)
  // input:  T ....... pointer to first template point
  //         T_num ... number of template points
  //         R ....... initial rotation matrix
  //         t ....... initial translation vector
  //         indist .. inlier distance (if <=0: use all points)
  // output: R ....... final rotation matrix
  //         t ....... final translation vector
  void fit(double *T,const long T_num,Matrix &R,Matrix &t,const double indist);
  
private:
  
  // iterative fitting
  void fitIterate(double *T,const long T_num,Matrix &R,Matrix &t,const std::vector<long> &active);
  
  // inherited classes need to overwrite these functions
  virtual double               fitStep(double *T,const long T_num,Matrix &R,Matrix &t,const std::vector<long> &active) = 0;
  virtual std::vector<long> getInliers(double *T,const long T_num,const Matrix &R,const Matrix &t,const double indist) = 0;
  
protected:
  
  // kd tree of model points
  kdtree::KDTree*     M_tree;
  kdtree::KDTreeArray M_data;
  
  long dim;       // dimensionality of model + template data (2 or 3)
  long max_iter;  // max number of iterations
  double  min_delta; // min parameter delta
};

#endif // ICP_H
