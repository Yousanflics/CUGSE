cmake_minimum_required(VERSION 3.6)
project(BinaryTreeSearch)

set(CMAKE_CXX_STANDARD 11)

set(SOURCE_FILES BTSearch.h BTSearchTest.cpp)
add_executable(BinaryTreeSearch ${SOURCE_FILES})