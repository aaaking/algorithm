cmake_minimum_required (VERSION 3.10)

# 指定 c++ 标准
set(CMAKE_CXX_STANDARD 17)

# set the project name
project (helloworldproject VERSION 1.0)

# 设置源码根路径，即 cpp 目录
set(SRC_ROOT_PATH ${CMAKE_CURRENT_SOURCE_DIR})
message(WARNING "src root path=${SRC_ROOT_PATH}")
message("PROJECT_NAME=====${PROJECT_NAME} CMAKE_PROJECT_NAME=====${CMAKE_PROJECT_NAME}")

# 定义要编译的文件列表变量
file(GLOB CPP_FILES_IN_UTILS "${SRC_ROOT_PATH}/util/*")

# 设置要编译的源文件
set(
        SOURCE_FILES
        ${CPP_FILES_IN_UTILS}
        ${SRC_ROOT_PATH}/*
)

# include 头文件目录
include_directories(${SRC_ROOT_PATH}/)


# add the executable， 告诉 CMake 使用指定的源代码文件创建可执行文件，这里使用 main.cpp 来构建名为 hello 的可执行文件。
# 如果编译动态库可以用：target_link_libraries
aux_source_directory(. src_list)
aux_source_directory(./model src_list)
add_executable(hello ${src_list})
