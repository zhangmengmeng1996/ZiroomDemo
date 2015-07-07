package com.ziroom.test.user;


import com.paypal.selion.platform.grid.Grid;
import com.paypal.selion.platform.utilities.WebDriverWaitUtils;
import com.paypal.selion.reports.runtime.SeLionReporter;
import com.ziroom.test.IZiroomPage;
import com.ziroom.test.LoginPage;
import com.ziroom.test.config.ZiroomConfig;

/**
 * @author louis
 *
 */
public class LoginPageExt extends LoginPage {


	public LoginPageExt() {
		super("CN");
	}

	public LoginPageExt(String siteLocale) {
		super(siteLocale);
	}

	public LoginPage fillLoginPage(String userphone, String password) {
		
		getLoginTextField().type(userphone);
		getPasswordTextField().type(password);
		if (getCaptchaTextField().isElementPresent() && getCaptchaTextField().isVisible()) {
			getCaptchaTextField().type(ZiroomConfig.CAPTCHA);
		}
		return this;
	}
	
	public IZiroomPage Login() {
		IZiroomPage iZiroomPage = new IZiroomPage();
		clickLoginButton();
		Grid.driver().get(ZiroomConfig.IZIROOM);
		WebDriverWaitUtils.waitUntilElementIsVisible(iZiroomPage.getMycontractLink().getLocator());
		SeLionReporter.log(Grid.driver().getTitle(), true,true);
		return iZiroomPage;
		
	}
}
