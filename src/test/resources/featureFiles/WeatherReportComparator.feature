Feature: Weather Reporting Comparator of Accuweather and OpenWeather

  Scenario Outline:: Compare Temprature,humidity of Accuweather website and OpenWeather API
    Given Navigate to url  "https://www.accuweather.com/"
    And User enter city name as "<City>" to search Weather
    And User select the city from dropdown as "<City>" for weather search
    When click on MORE_DETAILS link
    And Save the current temp and humidity
    And User hit openweather api and get current temp and humidity
    Then User compare the difference of temp with variance '4' and humidity with variance '10'

    Examples:
    |City|
    |Pune|

