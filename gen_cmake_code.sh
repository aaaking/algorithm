#!/bin/bash
NOW_DIR=$(cd "$(dirname $0)";pwd)
cd $NOW_DIR

RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[0;33m'
NC='\033[0m' # No Color

printGreen() {
    echo -e "${GREEN} $1 ${NC}"
}

printRed() {
    echo -e "${RED} $1 ${NC}"
}

printYellow() {
    echo -e "${YELLOW} $1 ${NC}"
}

task_help() {
    echo "[help]       ./gen_cmake_code.sh cmake              编译"
    echo "[help]       ./gen_cmake_code.sh make               编译"
    echo "[help]       ./gen_cmake_code.sh all               编译"
}

task_cmake() {
    pushd cmake
    rm -rf build
    mkdir build

    # cmake [options] <path-to-source> #源码目录，也就是上面的../ 即CMakeLists.txt所在文件夹路径
    # cmake [options] -S <path-to-source> -B <path-to-build> #用于大型工程，有很多CMakeLists.txt所以需要具体指定，避免混乱

    cmake -S ./ -B build
    popd

    if [ 0 -ne $? ];then
        echo "Failed to cmake"
        exit 1
    fi
}

task_make() {
    pushd cmake/build
    make
    ./hello
    popd
}


case $1 in
cmake)
    task_cmake $*
    ;;
make)
    task_make $*
    ;;
all)
    task_cmake $*
    task_make $*
    ;;
*)
    task_help 
    ;;
esac