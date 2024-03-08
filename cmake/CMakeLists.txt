cmake_minimum_required (VERSION 3.10)

# 指定 c++ 标准
# set(CMAKE_CXX_STANDARD 11)

# set the project name
project (helloworldproject VERSION 1.0)

# 设置源码根路径，即 cpp 目录
set(SRC_ROOT_PATH ${CMAKE_CURRENT_SOURCE_DIR})
message(WARNING "src root path=${SRC_ROOT_PATH}")


# add the executable， 告诉 CMake 使用指定的源代码文件创建可执行文件，这里使用 main.cpp 来构建名为 hello 的可执行文件。
aux_source_directory(. src_list)
add_executable(hello ${src_list})
