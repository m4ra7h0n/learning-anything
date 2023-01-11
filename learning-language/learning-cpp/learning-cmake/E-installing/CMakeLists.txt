cmake_minimum_required(VERSION 3.5)

project(cmake_examples_install)

add_library(cmake_examples_inst SHARED src/Hello.cpp)
target_include_directories(cmake_examples_inst PUBLIC ${PROJECT_SOURCE_DIR}/include)

add_executable(cmake_examples_inst_bin src/main.cpp)
target_link_libraries(cmake_examples_inst_bin PRIVATE cmake_examples_inst)

# ${CMAKE_INSTALL_PREFIX} -> /usr/local by default

# Binaries
# Install the binary generated from the target cmake_examples_inst_bin target
# to the destination ${CMAKE_INSTALL_PREFIX}/bin
install(TARGETS cmake_examples_inst_bin DESTINATION bin)

# Shared libraries
# Install the shared library generated from the target cmake_examples_inst target
# to the destination ${CMAKE_INSTALL_PREFIX}/lib
install(TARGETS cmake_examples_inst LIBRARY DESTINATION lib)

# Install the header files for developing against the cmake_examples_inst library
# into the ${CMAKE_INSTALL_PREFIX}/include directory.
install(DIRECTORY ${PROJECT_SOURCE_DIR}/include/ DESTINATION include)

# Install a configuration file
# to the destination ${CMAKE_INSTALL_PREFIX}/etc
install(FILES cmake-examples.conf DESTINATION etc)


# after run the cmd
# see the install_manifest.txt to find the locate of installing

set(CMAKE_INSTALL_PREFIX_INITIALIZED_TO_DEFAULT TRUE)
# change the locate to install
if( CMAKE_INSTALL_PREFIX_INITIALIZED_TO_DEFAULT )
    message(STATUS "Setting default CMAKE_INSTALL_PREFIX path to ${CMAKE_BINARY_DIR}/install")
    set(CMAKE_INSTALL_PREFIX "${CMAKE_BINARY_DIR}/install" CACHE STRING "The path to use for make install" FORCE)
endif()

# or use -> make install DESTDIR=/tmp/stage