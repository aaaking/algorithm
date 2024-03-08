rm -rf build
mkdir build

# cmake [options] <path-to-source> #源码目录，也就是上面的../ 即CMakeLists.txt所在文件夹路径
# cmake [options] -S <path-to-source> -B <path-to-build> #用于大型工程，有很多CMakeLists.txt所以需要具体指定，避免混乱

cmake -S ./ -B build