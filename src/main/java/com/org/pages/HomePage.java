package com.org.pages;

import java.util.List;

import com.org.util.DataManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.org.util.CommonUtility;
import com.org.util.ElementUtil;

public class HomePage {
	WebDriver driver;
	ElementUtil elementutil;

	 private By searchCity=By.xpath("//input[@placeholder='Search Location']");
	 private By SelectFromSuggestion=By.xpath("//div[@class='results-container']//descendant::div");
	 private By acceptCookiesPopUp=By.xpath("//div[@class='banner-button policy-accept']");
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		elementutil=new ElementUtil(driver);
	}
 
	public void  lauchAppUrl(String url)
	{
		elementutil.launchUrl(url);
		elementutil.doClick(acceptCookiesPopUp);
	}

	public void enterCity(String city){
		elementutil.doSendKeys(searchCity,city);
	}

	public CurrentWeatherPage selectCityFromSuggestion(String city){
		elementutil.selectItemWhenContainsInElementList(SelectFromSuggestion, city);
		return new CurrentWeatherPage(driver);
	}
}
