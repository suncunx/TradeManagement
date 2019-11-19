# TradeManagement
	这是一款贸易管理app，包含Android客户端、Java服务端、Sql Server数据库
	
# 主要功能
	
	1.企业客户端：登录、注册、供应商管理、客户管理、商品管理、进货账单管理、出货账单管理、财务报表、新闻看点等。
	
	2.送货员客户端：登录、查看当前需要送货的出货账单（即时推送）、确认货物送达、退出登录
	
登录页面

![loginimage](https://github.com/suncunx/TradeManagement/blob/master/ScreenShots/login.jpg)

主页面

![inBillimage](https://github.com/suncunx/TradeManagement/blob/master/ScreenShots/inBill.jpg)

# 环境配置
## 1.数据库配置
	本项目可以使用Sql Server 2012数据库，或者MySQL数据库，二者选其一即可。
（1）Sql Server 2012数据库配置
①安装Sql Server 2012
②打开[这个路径](https://github.com/suncunx/TradeManagement/tree/master/Database/sqlserver)下的Sql Server数据库文件
（2）MySQL数据库配置
①安装最新版本MySQL
②进入MySQL命令行界面
③复制、粘贴[此文件](/Database/mysql/sql.txt)中的sql脚本
	笔者建议采用MySQL数据库，安装方便，轻量简洁。

## 2.服务端配置
	服务端采用MyEclipse开发，开发者需要到Constant类中进行配置，如下图所示：  
![config server](https://github.com/suncunx/TradeManagement/blob/master/ScreenShots/configServer.png)
	笔者是用电脑连接手机开的热点，电脑作为服务端，其中的服务器地址即是电脑的ip

## 3.客户端配置
	客户端采用Android Studio开发
（1）企业客户端（CompanyClient文件夹）
开发者需要到BaseConstants类中进行配置，如下图所示：  
![config client](/ScreenShots/configCompany.png)
（2）送货员客户端（DeliverClient文件夹）
开发者需要到Constant类中进行配置，如下图所示：
![config client](https://github.com/suncunx/TradeManagement/blob/master/ScreenShots/configDeliverer.png)

# 注意
	1.服务端需要先作为Web项目在Tomcat上运行，再作为Java Application运行
	2.目前来说，项目的配置较为复杂，后面会将服务端放在云服务器上，只提供Android端的代码，这样就什么配置都不需要了。
	
# 联系方式
	QQ：1243357168