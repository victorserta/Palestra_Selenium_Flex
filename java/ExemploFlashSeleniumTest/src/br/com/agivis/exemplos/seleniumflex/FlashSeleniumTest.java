package br.com.agivis.exemplos.seleniumflex;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.FlashSelenium;
import com.thoughtworks.selenium.Selenium;

import static org.junit.Assert.*;

public class FlashSeleniumTest {

	private final static String BASE_URL = "file:///Users/victor/Documents/Adobe%20Flash%20Builder%204.5/ExemploFlashSelenium/bin-debug/";
	private final static String PAGE = "CalculoIMC.html";

	private FlashSelenium flashApp;
	private Selenium selenium;

	@Before
	public void setUp() {
		selenium = new DefaultSelenium("localhost", 4444, "*firefox", BASE_URL);
		selenium.start();

		flashApp = new FlashSelenium(selenium, "CalculoIMC");

		selenium.open(BASE_URL + PAGE);
		assertEquals(100, flashApp.PercentLoaded());
		
		selenium.waitForCondition("selenium.browserbot.getCurrentWindow().isAppCreated", "10000");
	}

	@After
	public void tearDown() {
		selenium.stop();
	}

	@Test
	public void testEstadoInicial() {
		assertEquals("", flashApp.call("getResultado"));
	}
	
	@Test
	public void testCalcularSemPreecher() {
		flashApp.call("clickEmCalcular");
		assertEquals("", flashApp.call("getResultado"));
	}
	
	@Test
	public void testCalcularIdeal() {
		flashApp.call("preencherPeso", "75");
		flashApp.call("preencherAltura", "1,80");
		flashApp.call("clickEmCalcular");
		
		assertEquals("23,15 (Peso normal)", flashApp.call("getResultado"));
	}
	
	@Test
	public void testCalcularAbaixo() {
		flashApp.call("preencherPeso", "58");
		flashApp.call("preencherAltura", "1,80");
		flashApp.call("clickEmCalcular");
		
		assertEquals("17,9 (Abaixo do peso)", flashApp.call("getResultado"));
	}

}
