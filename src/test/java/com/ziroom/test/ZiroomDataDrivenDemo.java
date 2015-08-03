package com.ziroom.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.paypal.selion.annotations.WebTest;
import com.paypal.selion.platform.dataprovider.DataResource;
import com.paypal.selion.platform.dataprovider.impl.FileSystemResource;
import com.paypal.selion.platform.dataprovider.impl.YamlDataProviderImpl;
import com.paypal.selion.platform.grid.Grid;
import com.paypal.selion.reports.runtime.SeLionReporter;
import com.ziroom.dataobject.Userinformation;
import com.ziroom.test.user.LoginPageExt;

public class ZiroomDataDrivenDemo {
	
	@DataProvider(parallel=true, name="multiplelogin")
	public Object[][] simpleDataProvider() throws Exception {
		DataResource dataResource = new FileSystemResource("src/test/resources/DataProvider/Userinformation.yaml");
		YamlDataProviderImpl yamlDataProvider = new YamlDataProviderImpl(dataResource);
		return yamlDataProvider.getAllData();
	}

	
	@WebTest
	@Test(dataProvider = "multiplelogin")
	public void ZiroomDataDrivenTest(Userinformation userinformation) throws InterruptedException{
		
		//初始化页面
		HomePage homePage= new HomePage();
		LoginPageExt loginPage = new LoginPageExt();
		IZiroomPage iZiroomPage = new IZiroomPage();
		ContractPage contractPage = new ContractPage();
		
		//打开ziroom主页
		Grid.driver().get("http://www.ziroom.com");
		SeLionReporter.log(Grid.driver().getTitle(), true, true);
		
		//登录ziroom账号
		homePage.clickLoginLink(loginPage);
		for(String winHandle : Grid.driver().getWindowHandles()){  
            //System.out.println("+++" + winHandle);  
            Grid.driver().switchTo().window(winHandle);  
            } 
		loginPage.fillLoginPage(userinformation.getUserphone(),userinformation.getPassword());
		loginPage.Login();
		
		//打开我的合同页面
		iZiroomPage.clickMycontractLink(contractPage);
		contractPage.getContactHeaderContainer().getContractLink().click();
		
		//输出合同相关信息
		System.out.println(contractPage.getContractNumberLabel().getText());
		System.out.println(contractPage.getPropertyLabel().getText());
		System.out.println("租金：" + contractPage.getPaymentContainer().getRentMoneyLabel().getText());
		System.out.println("付款方式: " + contractPage.getPaymentContainer().getPaymentMethodLabel().getText());
		System.out.println("本期需要缴费：" + contractPage.getPaymentContainer().getMoneytoPayLabel().getText());
	}
}
