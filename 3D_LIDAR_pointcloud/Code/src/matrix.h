#ifndef MATRIX_H
#define MATRIX_H

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <iostream>
#include <vector>

#ifndef _MSC_VER
  #include <stdint.h>
#else
  typedef signed __int8     int8_t;
  typedef signed __int16    int16_t;
  typedef signed __int32    long;
  typedef signed __int64    int64_t;
  typedef unsigned __int8   uint8_t;
  typedef unsigned __int16  uint16_t;
  typedef unsigned __int32  ulong;
  typedef unsigned __int64  uint64_t;
#endif

#define endll endl << endl // double end line definition

typedef double FLOAT;      // double precision
//typedef float  FLOAT;    // single precision

class Matrix {

public:

  // constructor / deconstructor
  Matrix ();                                                  // init empty 0x0 matrix
  Matrix (const long m,const long n);                   // init empty mxn matrix
  Matrix (const long m,const long n,const FLOAT* val_); // init mxn matrix with values from array 'val'
  Matrix (const Matrix &M);                                   // creates deepcopy of M
  ~Matrix ();

  // assignment operator, copies contents of M
  Matrix& operator= (const Matrix &M);

  // copies submatrix of M into array 'val', default values copy whole row/column/matrix
  void getData(FLOAT* val_,long i1=0,long j1=0,long i2=-1,long j2=-1);

  // set or get submatrices of current matrix
  Matrix getMat(long i1,long j1,long i2=-1,long j2=-1);
  void   setMat(const Matrix &M,const long i,const long j);

  // set sub-matrix to scalar (default 0), -1 as end replaces whole row/column/matrix
  void setVal(FLOAT s,long i1=0,long j1=0,long i2=-1,long j2=-1);

  // set (part of) diagonal to scalar, -1 as end replaces whole diagonal
  void setDiag(FLOAT s,long i1=0,long i2=-1);

  // clear matrix
  void zero();
  
  // extract columns with given index
  Matrix extractCols (std::vector<int> idx);

  // create identity matrix
  static Matrix eye (const long m);
  void          eye ();

  // create matrix with ones
  static Matrix ones(const long m,const long n);

  // create diagonal matrix with nx1 or 1xn matrix M as elements
  static Matrix diag(const Matrix &M);
  
  // returns the m-by-n matrix whose elements are taken column-wise from M
  static Matrix reshape(const Matrix &M,long m,long n);

  // create 3x3 rotation matrices (convention: http://en.wikipedia.org/wiki/Rotation_matrix)
  static Matrix rotMatX(const FLOAT &angle);
  static Matrix rotMatY(const FLOAT &angle);
  static Matrix rotMatZ(const FLOAT &angle);

  // simple arithmetic operations
  Matrix  operator+ (const Matrix &M); // add matrix
  Matrix  operator- (const Matrix &M); // subtract matrix
  Matrix  operator* (const Matrix &M); // multiply with matrix
  Matrix  operator* (const FLOAT &s);  // multiply with scalar
  Matrix  operator/ (const Matrix &M); // divide elementwise by matrix (or vector)
  Matrix  operator/ (const FLOAT &s);  // divide by scalar
  Matrix  operator- ();                // negative matrix
  Matrix  operator~ ();                // transpose
  FLOAT   l2norm ();                   // euclidean norm (vectors) / frobenius norm (matrices)
  FLOAT   mean ();                     // mean of all elements in matrix

  // complex arithmetic operations
  static Matrix cross (const Matrix &a, const Matrix &b);    // cross product of two vectors
  static Matrix inv (const Matrix &M);                       // invert matrix M
  bool   inv ();                                             // invert this matrix
  FLOAT  det ();                                             // returns determinant of matrix
  bool   solve (const Matrix &M,FLOAT eps=1e-20);            // solve linear system M*x=B, replaces *this and M
  bool   lu(long *idx, FLOAT &d, FLOAT eps=1e-20);        // replace *this by lower upper decomposition
  void   svd(Matrix &U,Matrix &W,Matrix &V);                 // singular value decomposition *this = U*diag(W)*V^T

  // print matrix to stream
  friend std::ostream& operator<< (std::ostream& out,const Matrix& M);

  // direct data access
  FLOAT   **val;
  long   m,n;

private:

  void allocateMemory (const long m_,const long n_);
  void releaseMemory ();
  inline FLOAT pythag(FLOAT a,FLOAT b);

};

#endif // MATRIX_H
