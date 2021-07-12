package com.org.pages;

import com.org.model.WeatherInfo;
import com.org.util.DataManager;
import com.org.util.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CurrentWeatherPage {
    WebDriver driver;
    ElementUtil elementutil;
    WeatherInfo weatherInfo1;
    DataManager dataManager=new DataManager();

    private By moreDetailsLink=By.xpath("//span[@class='cur-con-weather-card__cta']");
    private By getCurrentTemp= By.className("display-temp");
    private By getHumidity= By.xpath("//*[text()='Humidity']//following-sibling::div");
    public CurrentWeatherPage(WebDriver driver)
    {
        this.driver=driver;
        elementutil=new ElementUtil(driver);
    }
    public void moreDetailLink()
    {
        elementutil.doClick(moreDetailsLink);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public WeatherInfo getCurrentTempAndHumidity(){
        weatherInfo1=new WeatherInfo();
        String CurrentTempWithUnit=elementutil.getReqText(getCurrentTemp);
        String HumidityWithUnit=elementutil.getReqText(getHumidity);
        String[] arrdata=CurrentTempWithUnit.split("°");
        String CurrentTemp=arrdata[0];
        String[] arrdata1=HumidityWithUnit.split("%");
        String Humidity=arrdata1[0];
        weatherInfo1.setTemp(Double.parseDouble(CurrentTemp));
        weatherInfo1.setHumidity(Long.parseLong(Humidity));
        System.out.println("Without Unit Temp:"+CurrentTemp+"\tHumidity:"+Humidity);
        dataManager.setDataProperty("TemperatureFromAccuweather",CurrentTemp);
        dataManager.setDataProperty("HumidityFromAccuweather",Humidity);
        return weatherInfo1;
    }

}
