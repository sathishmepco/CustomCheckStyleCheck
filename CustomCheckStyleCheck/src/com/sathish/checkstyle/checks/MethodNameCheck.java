package com.sathish.checkstyle.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class MethodNameCheck extends AbstractCheck{

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
		DetailAST methodNameAst = ast.findFirstToken(TokenTypes.IDENT);
		if("test".equals(methodNameAst.getText()))
			log(ast, "Illegal method name (test)");
	}

	@Override
	public int[] getRequiredTokens() {
		return null;
	}
}