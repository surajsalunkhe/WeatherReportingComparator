package stepDefination;

import java.text.DecimalFormat;
import com.org.model.FetchAPIData;
import com.org.model.WeatherInfo;
import com.org.pages.*;
import org.junit.Assert;
import com.org.driverFactory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
/*
Author: Suraj Salunkhe
Date:12th July 2021
*/
public class StepDefination {
	HomePage homepage = new HomePage(DriverFactory.getDriver());
	CurrentWeatherPage currentWeatherPage;
	WeatherInfo weatherInfo;
	WeatherInfo weatherInfo1;
	FetchAPIData fetchAPIData;

	@Given("Navigate to url  {string}")
	public void login_to_url(String url) {
		homepage.lauchAppUrl(url);
	}


	@Given("User enter city name as {string} to search Weather")
	public void enter_city_name(String cityName){
		homepage.enterCity(cityName);
	}

	@Given("User select the city from dropdown as {string} for weather search")
	public void select_City_FromSuggestion(String cityName){
		currentWeatherPage=homepage.selectCityFromSuggestion(cityName);
	}

	@When("click on MORE_DETAILS link")
	public void click_on_more_details_button() {
		currentWeatherPage.moreDetailLink();
	}

	@When("Save the current temp and humidity")
	public void save_CurrentTemp_Humidity(){
		this.weatherInfo1=currentWeatherPage.getCurrentTempAndHumidity();
	}
	@When("User hit openweather api and get current temp and humidity")
	public void hitOpenweatherapi_CurrentTemp_Humidity(){
		fetchAPIData=new FetchAPIData();
		this.weatherInfo=fetchAPIData.callApiAndStoreData();
	}

	@Then("User compare the difference of temp with variance {string} and humidity with variance {string}")
	public void difference_of_temp_with_variance_and_humidity_with_variance(String tempvairance, String humidvariance) {
		double tempratureVairance = Double.parseDouble(tempvairance);
		long humidtyvariance = Long.parseLong(humidvariance);
		if (weatherInfo.getTemp() != weatherInfo1.getTemp()) {
			double diff = weatherInfo1.getTemp() - weatherInfo.getTemp();
			DecimalFormat df = new DecimalFormat("#.##");
			double difference = Double.valueOf(df.format(diff));
			System.out.println("Difference in Temperature:"+Math.abs(difference));
			if (difference < tempratureVairance) {
				Assert.assertTrue("Current temperature difference of OpenWeather with Accuweather website is in variance in range="+tempratureVairance,true);
			} else {
				Assert.assertTrue("Current temperature difference of OpenWeather with Accuweather website is out of variance range="+tempratureVairance,false);
			}
		} if (weatherInfo.getHumidity() != weatherInfo1.getHumidity()) {
			double diff = weatherInfo1.getHumidity() - weatherInfo.getHumidity();
			DecimalFormat df = new DecimalFormat("#.##");
			double difference = Double.valueOf(df.format(diff));
			System.out.println("Difference in Humidity:"+Math.abs(difference));
			if (difference < humidtyvariance) {
				Assert.assertTrue("Current Humidity difference of OpenWeather with Accuweather website is in variance in range="+humidtyvariance,true);
			} else {
				Assert.assertTrue("Current Humidity difference of OpenWeather with Accuweather website is out of variance range="+humidtyvariance,false);
			}
		} else {
			Assert.assertEquals(String.valueOf(weatherInfo.getTemp()), String.valueOf(weatherInfo1.getTemp()), "Temperature matched from OpenWeather with Accuweather website");
			Assert.assertEquals(String.valueOf(weatherInfo.getHumidity()), String.valueOf(weatherInfo1.getHumidity()), "Humidity matched from OpenWeather with Accuweather website");
		}

	}
}


