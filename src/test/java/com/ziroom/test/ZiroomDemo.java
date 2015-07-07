package com.ziroom.test;

import org.testng.annotations.Test;

import com.paypal.selion.annotations.WebTest;
import com.paypal.selion.platform.grid.Grid;
import com.paypal.selion.reports.runtime.SeLionReporter;
import com.ziroom.test.user.LoginPageExt;

public class ZiroomDemo {
	
	//初始化页面
	HomePage homePage= new HomePage();
	LoginPageExt loginPage = new LoginPageExt();
	IZiroomPage iZiroomPage = new IZiroomPage();
	ContractPage contractPage = new ContractPage();
	
	public	String userphone = "13581530722";
	public	String password = "201314";
	
	@Test
	@WebTest
	public void ZiroomDemoTest() throws InterruptedException{
		
		//打开ziroom主页
		Grid.driver().get("http://www.ziroom.com");
		SeLionReporter.log(Grid.driver().getTitle(), true, true);
		
		//登录ziroom账号
		homePage.clickLoginLink(loginPage);
		loginPage.fillLoginPage(userphone,password);
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
