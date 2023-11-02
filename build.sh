#!/bin/bash

cd ${CODEBUILD_SRC_DIR}


#
PROJ_DIRECTORY=admin
echo "===== Prepare Build Package - START ===== ${PROJ_DIRECTORY} ====="
cd ${CODEBUILD_SRC_DIR}/${PROJ_DIRECTORY}
#npm install
yarn install
this_exit_code=$?
echo "===== Prepare Build Package -  END  ===== ${PROJ_DIRECTORY} ====="
if [ "${this_exit_code}" != "0" ]; then exit ${this_exit_code}; fi


echo ""
echo ""
echo ""

# Prepare package for code-scan
# required folder structure:
# /code-scan
#   /build-info.properties (auto-generated by devops build script)
#   /<modules>
#     /scan-config.properties
#     /dependencies (optional)
#     /src
#   /api-gw-default
#     /scan-config.properties
#     /src
#   /api-gw-rsa-aes
#     /scan-config.properties
#     /src
#   /api-gw-portal-application-v1
#     /scan-config.properties
#     /src

cd ${CODEBUILD_SRC_DIR}

#
PROJ_DIRECTORY=admin
echo "===== Copy files for code scanning - START ===== ${PROJ_DIRECTORY} ====="
mkdir -p ${CODEBUILD_SRC_DIR}/code-scan/${PROJ_DIRECTORY}/src/
rsync -ar ${CODEBUILD_SRC_DIR}/${PROJ_DIRECTORY} ${CODEBUILD_SRC_DIR}/code-scan/${PROJ_DIRECTORY}/src/ --exclude *.zip --exclude node_modules
echo "===== Copy files for code scanning -  END  ===== ${PROJ_DIRECTORY} ====="

