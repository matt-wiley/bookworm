#!/usr/bin/env bash

function main() {
    for cmd in "${@}"; do 
        case "${cmd}" in
            "clean") 
                rm -rf build dist *.egg-info
                ;;
            "build") 
                python -m pip install -r requirements.txt
                python setup.py bdist_wheel 
                ;;
            *);;
        esac
    done
}
main "${@}"
