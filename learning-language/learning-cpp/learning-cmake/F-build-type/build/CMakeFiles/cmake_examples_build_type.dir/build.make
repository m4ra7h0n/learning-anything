# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.19

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Disable VCS-based implicit rules.
% : %,v


# Disable VCS-based implicit rules.
% : RCS/%


# Disable VCS-based implicit rules.
% : RCS/%,v


# Disable VCS-based implicit rules.
% : SCCS/s.%


# Disable VCS-based implicit rules.
% : s.%


.SUFFIXES: .hpux_make_needs_suffix_list


# Command-line flag to silence nested $(MAKE).
$(VERBOSE)MAKESILENT = -s

#Suppress display of executed commands.
$(VERBOSE).SILENT:

# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /usr/local/Cellar/cmake/3.19.1/bin/cmake

# The command to remove a file.
RM = /usr/local/Cellar/cmake/3.19.1/bin/cmake -E rm -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /Users/xjj/IdeaProjects/learning-anything/learning-language/learning-cpp/learning-cmake/F-build-type

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /Users/xjj/IdeaProjects/learning-anything/learning-language/learning-cpp/learning-cmake/F-build-type/build

# Include any dependencies generated for this target.
include CMakeFiles/cmake_examples_build_type.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/cmake_examples_build_type.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/cmake_examples_build_type.dir/flags.make

CMakeFiles/cmake_examples_build_type.dir/main.cpp.o: CMakeFiles/cmake_examples_build_type.dir/flags.make
CMakeFiles/cmake_examples_build_type.dir/main.cpp.o: ../main.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Users/xjj/IdeaProjects/learning-anything/learning-language/learning-cpp/learning-cmake/F-build-type/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/cmake_examples_build_type.dir/main.cpp.o"
	/Library/Developer/CommandLineTools/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/cmake_examples_build_type.dir/main.cpp.o -c /Users/xjj/IdeaProjects/learning-anything/learning-language/learning-cpp/learning-cmake/F-build-type/main.cpp

CMakeFiles/cmake_examples_build_type.dir/main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/cmake_examples_build_type.dir/main.cpp.i"
	/Library/Developer/CommandLineTools/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /Users/xjj/IdeaProjects/learning-anything/learning-language/learning-cpp/learning-cmake/F-build-type/main.cpp > CMakeFiles/cmake_examples_build_type.dir/main.cpp.i

CMakeFiles/cmake_examples_build_type.dir/main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/cmake_examples_build_type.dir/main.cpp.s"
	/Library/Developer/CommandLineTools/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /Users/xjj/IdeaProjects/learning-anything/learning-language/learning-cpp/learning-cmake/F-build-type/main.cpp -o CMakeFiles/cmake_examples_build_type.dir/main.cpp.s

# Object files for target cmake_examples_build_type
cmake_examples_build_type_OBJECTS = \
"CMakeFiles/cmake_examples_build_type.dir/main.cpp.o"

# External object files for target cmake_examples_build_type
cmake_examples_build_type_EXTERNAL_OBJECTS =

cmake_examples_build_type: CMakeFiles/cmake_examples_build_type.dir/main.cpp.o
cmake_examples_build_type: CMakeFiles/cmake_examples_build_type.dir/build.make
cmake_examples_build_type: CMakeFiles/cmake_examples_build_type.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/Users/xjj/IdeaProjects/learning-anything/learning-language/learning-cpp/learning-cmake/F-build-type/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable cmake_examples_build_type"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/cmake_examples_build_type.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/cmake_examples_build_type.dir/build: cmake_examples_build_type

.PHONY : CMakeFiles/cmake_examples_build_type.dir/build

CMakeFiles/cmake_examples_build_type.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/cmake_examples_build_type.dir/cmake_clean.cmake
.PHONY : CMakeFiles/cmake_examples_build_type.dir/clean

CMakeFiles/cmake_examples_build_type.dir/depend:
	cd /Users/xjj/IdeaProjects/learning-anything/learning-language/learning-cpp/learning-cmake/F-build-type/build && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/xjj/IdeaProjects/learning-anything/learning-language/learning-cpp/learning-cmake/F-build-type /Users/xjj/IdeaProjects/learning-anything/learning-language/learning-cpp/learning-cmake/F-build-type /Users/xjj/IdeaProjects/learning-anything/learning-language/learning-cpp/learning-cmake/F-build-type/build /Users/xjj/IdeaProjects/learning-anything/learning-language/learning-cpp/learning-cmake/F-build-type/build /Users/xjj/IdeaProjects/learning-anything/learning-language/learning-cpp/learning-cmake/F-build-type/build/CMakeFiles/cmake_examples_build_type.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/cmake_examples_build_type.dir/depend

