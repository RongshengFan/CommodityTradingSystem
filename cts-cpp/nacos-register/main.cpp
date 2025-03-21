/*
 Copyright Zero One Star. All rights reserved.

 @Author: awei
 @Date: 2022/10/24 23:02:34

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

	  https://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
*/
#include "StringUtil.h"
#include "ServerInfo.h"
#include "HttpServer.h"
#include "NacosClient.h"
#include <iostream>

/**
 * ������������
 * ע�⣺
 * ������������Ҫ����һ���ĸ�ʽ���磺sp=8090��sn=feign-cpp-sample
 * ǰ׺����ʵֵ֮��ʹ��=�ָ�
 */
void getStartArg(int argc, char* argv[]) {
	// �������˿�
	std::string serverPort = "8070";
	// Nacos���ò���
	std::string nacosAddr = "192.168.220.128:8848";
	std::string nacosNs = "4833404f-4b82-462e-889a-3c508160c6b4";
	std::string regFileId = "demo-nacos-cli";

	// ��ʼ����
	int currIndex = 1;
	bool isSetDb = false;
	while (currIndex < argc)
	{
		// ����ַ���
		auto args = StringUtil::split(argv[currIndex], "=");
		// �жϲ����Ƿ�Ϸ�
		if (args.size() != 2)
		{
			std::cout << "arg: " << argv[currIndex] << ", format error." << std::endl;
			exit(1);
		}

		// ���ݲ���ǰ׺�Բ�ͬ���Ը�ֵ
		std::string prefix = args[0];
		std::string val = args[1];
		if (prefix == "sp") serverPort = val;
		else if (prefix == "na") nacosAddr = val;
		else if (prefix == "ns") nacosNs = val;
		else if (prefix == "fi") regFileId = val;
		// ��������
		currIndex++;
	}

	// ��¼���������õ��ڴ��з���ʹ��
	ServerInfo::getInstance().setServerPort(serverPort);
	ServerInfo::getInstance().setNacosAddr(nacosAddr);
	ServerInfo::getInstance().setNacosNs(nacosNs);
	ServerInfo::getInstance().setNacosRegFileID(regFileId);
}

int main(int argc, char* argv[]) {
	// ������������ʼ��
	getStartArg(argc, argv);

	// ����Nacos�ͻ��˶���
	NacosClient nacosClient(
		ServerInfo::getInstance().getNacosAddr(),
		ServerInfo::getInstance().getNacosNs());

	// ��ȡ����
	auto configs = nacosClient.getConfig(ServerInfo::getInstance().getNacosRegFileID());

	// ע�����
	for (auto one : configs)
	{
		nacosClient.registerInstance(one["ip"].as<string>(), one["port"].as<int>(), one["name"].as<string>());
	}

	// ����HTTP������
	HttpServer::startServer(ServerInfo::getInstance().getServerPort(), [=](Endpoints* doc, HttpRouter* router) {});

	// ��ע�����
	for (auto one : configs)
	{
		nacosClient.deregisterInstance(one["ip"].as<string>(), one["port"].as<int>(), one["name"].as<string>());
	}
	return 0;
}
