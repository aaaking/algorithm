#!/bin/bash
NOW_DIR=$(cd "$(dirname $0)";pwd)
cd $NOW_DIR

set -e

task_check() {
    cargo check
}

task_build() {
    cargo build
}

task_run() {
    cargo run
}





case $1 in
check)
    task_check $*
    ;;
build)
    task_build $*
    ;;
*)
    task_run  $*
    ;;
esac