#include "icp.h"

using namespace std;

Icp::Icp (double *M,const long M_num,const long dim) :
  dim(dim), max_iter(200), min_delta(1e-4) {
  
  // check for correct dimensionality
  if (dim!=2 && dim!=3) {
    cout << "ERROR: LIBICP works only for data of dimensionality 2 or 3" << endl;
    M_tree = 0;
    return;
  }
  
  // check for minimum number of points
  if (M_num<5) {
    cout << "ERROR: LIBICP works only with at least 5 model points" << endl;
    M_tree = 0;
    return;
  }

  // copy model points to M_data
  M_data.resize(boost::extents[M_num][dim]);
  for (long m=0; m<M_num; m++)
    for (long n=0; n<dim; n++)
      M_data[m][n] = (float)M[m*dim+n];

  // build a kd tree from the model point cloud
  M_tree = new kdtree::KDTree(M_data);
}

Icp::~Icp () {
  if (M_tree)
    delete M_tree;
}

void Icp::fit (double *T,const long T_num,Matrix &R,Matrix &t,const double indist) {
  
  // make sure we have a model tree
  if (!M_tree) {
    cout << "ERROR: No model available." << endl;
    return;
  }
  
  // check for minimum number of points
  if (T_num<5) {
    cout << "ERROR: Icp works only with at least 5 template points" << endl;
    return;
  }
  
  // set active points
  vector<long> active;
  if (indist<=0) {
    active.clear();
    for (long i=0; i<T_num; i++)
      active.push_back(i);
  } else {
    active = getInliers(T,T_num,R,t,indist);
  }
  
  // run icp
  fitIterate(T,T_num,R,t,active);
}

void Icp::fitIterate(double *T,const long T_num,Matrix &R,Matrix &t,const std::vector<long> &active) {
  
  // check if we have at least 5 active points
  if (active.size()<5)
    return;
  
  // iterate until convergence
  for (long iter=0; iter<max_iter; iter++)
    if (fitStep(T,T_num,R,t,active)<min_delta)
      break;
}
