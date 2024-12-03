#!/bin/bash
NOW_DIR=$(cd "$(dirname $0)";pwd)
cd $NOW_DIR

set -e

task_compile() {
    rustc main.rs
}

task_run() {
    ./main
}





case $1 in
c)
    task_compile $*
    task_run  $*
    ;;
*)
    task_run  $*
    ;;
esac