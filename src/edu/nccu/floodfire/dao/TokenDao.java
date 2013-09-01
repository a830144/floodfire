package edu.nccu.floodfire.dao;

import java.util.List;
import edu.nccu.floodfire.entity.Token;
@SuppressWarnings("rawtypes")
public interface TokenDao {
	public void addToken(Token token);		
	public List getTokensList();
	public List getTokensByExample(Token token);
	public Token getTokenById(String tokenId);
	public void updateTokenStatusById(String id,String jobSeq);
	public void updateTokenStatusByJobSeq(String jobSeq);
	public void updateTokenByIdExample(String id,Token token);

}
