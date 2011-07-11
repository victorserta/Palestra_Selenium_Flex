package br.com.agivis.exemplos.seleniumflex;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.FlexUISelenium;
import com.thoughtworks.selenium.HttpCommandProcessor;
import com.thoughtworks.selenium.Selenium;

import static org.junit.Assert.*;

public class FlexMonkiumTest {

	private Selenium selenium;
	private HttpCommandProcessor proc;

	@Before
	public void setUp() throws Exception {
		proc = new HttpCommandProcessor("localhost", 4444, "*firefox", "http://www.google.com.br/");
		selenium = new DefaultSelenium(proc);
		selenium.start();
	}

	@After
	public void tearDown() throws Exception {
		if (selenium != null) {
			selenium.stop();
			selenium = null;
		}
	}

	@Test
	public void myTestMethod() throws Exception {
		selenium.open("file:///Users/victor/Documents/Adobe%20Flash%20Builder%204.5/ExemploFlexMonkium/bin-debug/CalculoIMC.html");
		for (int t = 0;; t++) {
			if (t >= 60) fail("timeout");
			try {
				if (proc.getBoolean("isFlexMonkey", new String[] {"<UIEvent command=\"SelectText\" value=\"txPeso\">   <arg value=\"0\"/>   <arg value=\"0\"/> </UIEvent>"})) break;
			} catch (Exception e) { }
			Thread.sleep(500);
		}

		for (int t = 0;; t++) {
			if (t >= 60) fail("timeout");
			try {
				if (proc.getBoolean("isFlexMonkey", new String[] {"<UIEvent command=\"Input\" value=\"txPeso\"><arg value=\"75\"/></UIEvent>"})) break;
			} catch (Exception e) { }
			Thread.sleep(500);
		}

		for (int t = 0;; t++) {
			if (t >= 60) fail("timeout");
			try {
				if (proc.getBoolean("isFlexMonkey", new String[] {"<UIEvent command=\"SelectText\" value=\"txAltura\"><arg value=\"0\"/><arg value=\"0\"/></UIEvent>"})) break;
			} catch (Exception e) { }
			Thread.sleep(500);
		}

		for (int t = 0;; t++) {
			if (t >= 60) fail("timeout");
			try {
				if (proc.getBoolean("isFlexMonkey", new String[] {"<UIEvent command=\"Input\" value=\"txAltura\"><arg value=\"1,80\"/></UIEvent>"})) break;
			} catch (Exception e) { }
			Thread.sleep(500);
		}

		for (int t = 0;; t++) {
			if (t >= 60) fail("timeout");
			try {
				if (proc.getBoolean("isFlexMonkey", new String[] {"<UIEvent command=\"Click\" value=\"Calcular\"/>"})) break;
			} catch (Exception e) { }
			Thread.sleep(500);
		}

		for (int t = 0;; t++) {
			if (t >= 60) fail("timeout");
			try {
				if (proc.getBoolean("isFlexMonkey", new String[] {"<VerifyProperty value=\"23,14 (Peso normal)\" propertyString=\"automationName\" expectedValue=\"23,14 (Peso normal)\"/>"})) break;
			} catch (Exception e) { }
			Thread.sleep(500);
		}

	}

}
