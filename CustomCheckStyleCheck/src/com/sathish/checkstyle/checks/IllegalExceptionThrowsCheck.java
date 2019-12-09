package com.sathish.checkstyle.checks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class IllegalExceptionThrowsCheck extends AbstractCheck {
	List<String> exceptionSearchList;

	@Override
	public int[] getAcceptableTokens() {
		return null;
	}

	@Override
	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.LITERAL_THROWS };
	}

	@Override
	public void visitToken(DetailAST ast) {
		List<String> exceptionList = getAllThrowsException(ast);
		for(String exception : exceptionList){
			if(exceptionSearchList.contains(exception)){
				log(ast, "Illegal Throws Clause  -> " + exception);
				break;
			}
		}
	}

	@Override
	public int[] getRequiredTokens() {
		return null;
	}

	public void setExceptionSearchList(String exceptionThrowsStr) {
		exceptionSearchList = Arrays.asList(exceptionThrowsStr.split(","));
	}
	
	private List<String> getAllThrowsException(DetailAST throwsElt){
		List<String> exceptionList = new ArrayList<String>();
		List<DetailAST> elements = new ArrayList<>();
		elements.add(throwsElt);
		while(!elements.isEmpty()){
			DetailAST child = elements.remove(0).getFirstChild();
			while(child != null){
				if(child.getType() == TokenTypes.IDENT){
					if(!exceptionList.contains(child.getText()))
						exceptionList.add(child.getText());
				}
				else
					elements.add(child);
				child = child.getNextSibling();
			}
		}
		return exceptionList;
	}
}