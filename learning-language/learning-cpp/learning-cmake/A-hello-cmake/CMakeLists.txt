cmake_minimum_required(VERSION 3.5)
project(hello_cmake) # create a variable named $PROJECT_NAME

# add_executable(hello_cmake main.cpp)
add_executable(${PROJECT_NAME} main.cpp)
