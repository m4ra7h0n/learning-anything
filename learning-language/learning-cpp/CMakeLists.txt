cmake_minimum_required(VERSION 3.24)
project(learning_cpp)

set(CMAKE_CXX_STANDARD 17)

add_executable(learning_cpp main.cpp learning-cmake/D-shared-library/include/shared/Hello.h learning-cmake/F-build-type/main.cpp learning-cmake/G-compile-flags/main.cpp learning-cmake/H-third-party-library/main.cpp)
