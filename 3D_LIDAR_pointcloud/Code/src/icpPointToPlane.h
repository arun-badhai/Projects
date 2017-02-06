#ifndef ICP_POINT_TO_PLANE_H
#define ICP_POINT_TO_PLANE_H

#include "icp.h"

class IcpPointToPlane : public Icp {

public:
  
  IcpPointToPlane (double *M,const long M_num,const long dim,const long num_neighbors=10,const double flatness=5.0) : Icp(M,M_num,dim) {
    M_normal = computeNormals(num_neighbors,flatness);
  }

  virtual ~IcpPointToPlane () {
    free(M_normal);
  }

private:

  double fitStep (double *T,const long T_num,Matrix &R,Matrix &t,const std::vector<long> &active);
  std::vector<long> getInliers (double *T,const long T_num,const Matrix &R,const Matrix &t,const double indist);
  
  // utility functions to compute normals from the model tree
  void computeNormal (const kdtree::KDTreeResultVector &neighbors,double *M_normal,const double flatness);
  double* computeNormals (const long num_neighbors,const double flatness);
  
  // normals of model points
  double *M_normal;
};

#endif // ICP_POINT_TO_PLANE_H
