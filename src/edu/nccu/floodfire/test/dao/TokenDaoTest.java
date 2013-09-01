package edu.nccu.floodfire.test.dao;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.nccu.floodfire.dao.TokenDao;
import edu.nccu.floodfire.entity.Token;


public class TokenDaoTest extends TestCase{
	
	
	
	public void testAddUser()
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		TokenDao tokenDao = (TokenDao)applicationContext.getBean("TokenDao");
		Token token = new Token();
		token.setTokenId("006");
		token.setTokenName("test");
		token.setAccessToken("1");
		token.setAccessTokenSecret("2");
		token.setConsumerKey("3");
		token.setConsumerSecret("4");
		token.setLocked("0");
		token.setJobSeq("");
		tokenDao.addToken(token);
	}
}
