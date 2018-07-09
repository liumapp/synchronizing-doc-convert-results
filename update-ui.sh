#!/bin/bash

echo '============================================================='
echo '$                                                           $'
echo '$                      liumapp                              $'
echo '$                                                           $'
echo '$                                                           $'
echo '$  email:    liumapp.com@gmail.com                          $'
echo '$  homePage: http://www.liumapp.com                         $'
echo '$  Github:   https://github.com/liumapp                     $'
echo '$                                                           $'
echo '============================================================='
echo '.'

rm -rf ./synch-service/src/main/resources/static/static

rm ./synch-service/src/main/resources/templates/index.html

cp -r ./synch-ui/dist/static ./synch-service/src/main/resources/static/

cp ./synch-ui/dist/index.html ./synch-service/src/main/resources/templates/index.html

chmod -R a+w ../synchronizing-doc-convert-results


