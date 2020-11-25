package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.*;
import static org.junit.Assert.*;

import src.HttpStringProvider;
import src.TagsParser;
import static org.mockito.Mockito.*;

public class TestTagsParser {


	@Test
	public void testCounter() {
		TagsParser parser = new TagsParser(HelperClass.getUrlForPage("TestPage.html"));
		assertEquals(6, parser.getTagsCount());
	}
	
	@Test
	public void testGetTagsCount(){
		TagsParser parser = new TagsParser(HelperClass.getUrlForPage("TestPage.html")) ;
		assertEquals("head", parser.getTagAt(2));
	}



/*
	@Test
	public void testCounterWithAnonimous() {
		TagsParser counter = new TagsParser("http://www.loose.upt.ro/vvswiki") ;
		counter.setProvider(new HttpStringProvider(){
			@Override
			public String getStringForAddress(String urlAddress) {
				return "";
			}
		});
		assertEquals(0, counter.getTagsCount());
	}

*/

	private static class HttpStringProviderOneTag extends HttpStringProvider {
		@Override
		public String getStringForAddress(String urlAddress) {
			return "<html></html>";
		}
	}

	private static class GenericHttpProviderMock extends HttpStringProvider {
		@Override
		public String getStringForAddress(String u) {
			return u;
		}


	}


	//adauga mock library
	@Test
	public void testCounterWithAnonimous() {
		HttpStringProvider mockStringProvide = mock(HttpStringProvider.class);
		when(mockStringProvide.getStringForAddress(anyString())).thenReturn("<b><b></b></b>");

		TagsParser counter = new TagsParser("http_address");
		counter.setProvider(mockStringProvide);

		assertEquals(2, counter.getTagsCount());
		verify(mockStringProvide).getStringForAddress("http_address");
	}


}
