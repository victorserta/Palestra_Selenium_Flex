package br.com.agivis.exemplos.seleniumflex;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.FlexUISelenium;
import com.thoughtworks.selenium.Selenium;

import static org.junit.Assert.*;

public class FlexUISeleniumTest {

	private final static String BASE_URL = "file:///Users/victor/Documents/Adobe%20Flash%20Builder%204.5/ExemploFlexUISelenium/bin-debug/";
	private final static String PAGE = "CalculoIMC.html";

	private FlexUISelenium flexUITester;
	private Selenium selenium;
	
	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*firefox", BASE_URL);
		selenium.start();
		selenium.open(BASE_URL + PAGE);
		
		flexUITester = new FlexUISelenium(selenium, "CalculoIMC");
		//flexUITester.waitUntilLoaded();

		selenium.waitForCondition("selenium.browserbot.getCurrentWindow().isAppCreated", "10000");
	}

	@After
	public void tearDown() {
		selenium.stop();
	}

	@Test
	public void testEstadoInicial() {
		assertEquals("", flexUITester.readFrom("lbResultado"));
	}
	
	@Test
	public void testCalcularSemPreecher() {
		flexUITester.click("btCalcular");
		assertEquals("", flexUITester.readFrom("lbResultado"));
	}
	
	@Test
	public void testCalcularIdeal() {
		flexUITester.type("75").at("txPeso");
		flexUITester.type("1,80").at("txAltura");
		flexUITester.click("btCalcular");
		
		assertEquals("23,15 (Peso normal)", flexUITester.readFrom("lbResultado"));
	}
	
	@Test
	public void testCalcularAbaixo() {
		flexUITester.type("58").at("txPeso");
		flexUITester.type("1,80").at("txAltura");
		flexUITester.click("btCalcular");
		
		assertEquals("17,9 (Abaixo do peso)", flexUITester.readFrom("lbResultado"));		
	}

}
