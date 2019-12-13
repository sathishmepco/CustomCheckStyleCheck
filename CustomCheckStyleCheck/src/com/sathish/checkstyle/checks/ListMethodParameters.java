package com.sathish.checkstyle.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class ListMethodParameters extends AbstractCheck{

	@Override
	public int[] getAcceptableTokens() {
		return null;
	}

	@Override
	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.METHOD_DEF};
	}
	
	@Override
	public void visitToken(DetailAST ast) {
		String methodName = null;
		String returnType = null;
		int numberOfParameters = 0;
		
		methodName = ast.findFirstToken(TokenTypes.IDENT).getText();
		
		DetailAST typeElt = ast.findFirstToken(TokenTypes.TYPE);
		returnType = typeElt.getFirstChild().getText();
		
		DetailAST parametersElt = ast.findFirstToken(TokenTypes.PARAMETERS); 
		numberOfParameters = parametersElt.getChildCount(TokenTypes.PARAMETER_DEF);

		log(ast,"Method Name : "+methodName);
		log(ast,"Return Type : "+returnType);
		log(ast,"No Of Parameters : "+numberOfParameters);
		
		DetailAST paraElt = parametersElt.findFirstToken(TokenTypes.PARAMETER_DEF);

		int i=1;
		while(paraElt != null){
			if(paraElt.getType() == TokenTypes.PARAMETER_DEF){
				String dataType = paraElt.findFirstToken(TokenTypes.TYPE).getFirstChild().getText();
				String paraName = paraElt.findFirstToken(TokenTypes.IDENT).getText();
				log(ast,"Parameter "+i+" ("+dataType+" "+paraName+")");
				i++;	
			}
			paraElt = paraElt.getNextSibling();
		}
	}

	@Override
	public int[] getRequiredTokens() {
		return null;
	}
}