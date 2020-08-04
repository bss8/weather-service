package edu.txstate.bss64.weatherapi;

import static org.junit.jupiter.api.Assertions.*;

class WeatherBeanTest {
    WeatherBean weatherBean;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        weatherBean = new WeatherBean("Austin", "VRHOT", "101", ".10");
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void getCityName() {
        assertEquals("Austin", weatherBean.getCityName());
    }

    @org.junit.jupiter.api.Test
    void getTemperature() {
    }

    @org.junit.jupiter.api.Test
    void getDescription() {
    }

    @org.junit.jupiter.api.Test
    void getImage() {
        assertTrue(weatherBean.getImage().toString().contains("noinfo.jpg"));
    }
}