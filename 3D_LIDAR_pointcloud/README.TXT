Follow the instructions to compile and run the program:

Prerequisites needed for compiling:
- CMake (available at: http://www.cmake.org/)
	sudo apt-get install cmake

- Boost (available at: http://www.boost.org/)
	sudo add-apt-repository ppa:boost-latest/ppa
	sudo apt-get update
	sudo apt-get install libboost1X.55-all-dev

Compilation and Run
1) Type 'cmake .' in current directory
2) Type 'make'
3) Run './icp' (demo program)

The code is tested and is working on 100000 lines of input.

Please shorten the input with following commands:
	head -100000 pointcloud1.fuse > file1
	head -100000 pointcloud2.fuse > file2

Then run the program as follows:
./icp --filename1 file1 --filename2 file2 --numOfRows 100000