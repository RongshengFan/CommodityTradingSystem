#!/bin/bash
#check parameters
if [ $# -eq 0 ]
then
  echo -e "\nThe startup parameters are incorrect. Refer to the following instructions\n"
  echo "Usage: $0 excuteabfile na=? ns=? ip=? sn=? [options]"
  echo "Options:"
  echo "na: nacos server host and port. example na=http://47.94.0.110:8848/"
  echo "ns: nacos server namespace. example ns=1653f775-4782-46ad-9cd2-b60155a574c6"
  echo "ip: register ip. example ip=192.168.129.128"
  echo "sn: register service name. example sn=feign-cpp-sample"
  echo "sp: server port. example sp=8090"
  echo "du: mysql database username. example du=zo_wms"
  echo "dp: mysql database password. example dp=kekoukele"
  echo "dn: mysql database dbname. example dn=zo_wms"
  echo "dh: mysql database host. example dh=182.92.76.127"
  echo "dP: mysql database port. example dP=3369"
  echo "dm: mysql database pool maxsize. example dm=25"
  echo ""
  exit 1
fi
#add dynamic lib serach path
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:`pwd`/../lib
#start server
nohup ./$* >logs.log 2>&1 &
