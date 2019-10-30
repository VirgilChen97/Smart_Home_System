#!/bin/bash
mkdir out

# Compile Exception
javac -encoding utf-8 -d ./out Exception/OurXPathException.java

# Compile Listener
javac -encoding utf-8 -d ./out listener/common/HistoryButtonListener.java
javac -encoding utf-8 -d ./out listener/common/HistoryEnterListener.java
javac -encoding utf-8 -d ./out listener/common/InputControlListener.java
javac -encoding utf-8 -d ./out listener/common/JumpListener.java
javac -encoding utf-8 -d ./out listener/common/LoginButtonLisener.java
javac -encoding utf-8 -d ./out listener/common/LogoutListener.java
javac -encoding utf-8 -d ./out listener/common/SearchDayListener.java
javac -encoding utf-8 -d ./out listener/common/selectListener.java
javac -encoding utf-8 -d ./out listener/manager/AddActionListner.java
javac -encoding utf-8 -d ./out listener/manager/AddEnterListener.java
javac -encoding utf-8 -d ./out listener/manager/DeleteListener.java
javac -encoding utf-8 -d ./out listener/manager/TariffSubmitListener.java
javac -encoding utf-8 -d ./out listener/User/BudgetListener.java
javac -encoding utf-8 -d ./out listener/User/controlListener.java
javac -encoding utf-8 -d ./out listener/User/EnsureListener.java

# Compile model
javac -encoding utf-8 -d ./out model/dao/AccountDAO.java
javac -encoding utf-8 -d ./out model/dao/AccountHistoryDAO.java
javac -encoding utf-8 -d ./out model/dao/TariffDAO.java
javac -encoding utf-8 -d ./out model/dao/XMLDAO.java

javac -encoding utf-8 -d ./out model/dao/implimentation/AccountDAOImpl.java
javac -encoding utf-8 -d ./out model/dao/implimentation/AccountHistoryDAOImpl.java
javac -encoding utf-8 -d ./out model/dao/implimentation/TariffDAOImpl.java
javac -encoding utf-8 -d ./out model/dao/implimentation/XMLDAOImpl.java

javac -encoding utf-8 -d ./out model/Entity/Account.java
javac -encoding utf-8 -d ./out model/Entity/AccountHistory.java
javac -encoding utf-8 -d ./out model/Entity/Device.java
javac -encoding utf-8 -d ./out model/Entity/Meter.java
javac -encoding utf-8 -d ./out model/Entity/Tariff.java

javac -encoding utf-8 -d ./out model/Service/AccountHistoryService.java
javac -encoding utf-8 -d ./out model/Service/AccountService.java
javac -encoding utf-8 -d ./out model/Service/MeterService.java
javac -encoding utf-8 -d ./out model/Service/TariffService.java

javac -encoding utf-8 -d ./out model/Service/Implimentation/AccountHistoryServiceImpl.java
javac -encoding utf-8 -d ./out model/Service/Implimentation/AccountServiceImpl.java
javac -encoding utf-8 -d ./out model/Service/Implimentation/MeterServiceImpl.java
javac -encoding utf-8 -d ./out model/Service/Implimentation/TariffServiceImpl.java

# Compile Util
javac -encoding utf-8 -d ./out util/ArrayCutUtil.java
javac -encoding utf-8 -d ./out util/ColorUtil.java
javac -encoding utf-8 -d ./out util/DateUtil.java
javac -encoding utf-8 -d ./out util/ErrorUtil.java
javac -encoding utf-8 -d ./out util/FlyweightUtil.java
javac -encoding utf-8 -d ./out util/ProgressBarUtil.java
javac -encoding utf-8 -d ./out util/UpdateUtil.java
javac -encoding utf-8 -d ./out util/UserTableUtil.java

# Compile View
javac -encoding utf-8 -d ./out view/common/UpdateFrame.java
javac -encoding utf-8 -d ./out view/common/LoginUI.java
javac -encoding utf-8 -d ./out view/common/UserHistoryUI.java

javac -encoding utf-8 -d ./out view/manager/ManagementUI.java
javac -encoding utf-8 -d ./out view/manager/ManagerUI.java
javac -encoding utf-8 -d ./out view/manager/TariffUI.java

javac -encoding utf-8 -d ./out view/user/Mailbox.java
javac -encoding utf-8 -d ./out view/user/RechargeUI.java
javac -encoding utf-8 -d ./out view/user/UserBudgetUI.java
javac -encoding utf-8 -d ./out view/user/UserUI.java

mv out ../
echo "Compile finished"
echo 'Press any key to continue...'
