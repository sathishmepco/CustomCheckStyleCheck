package com.sathish.checkstyle.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class MethodLimitCheck extends AbstractCheck {
	private static final int DEFAULT_MAX = 40;
	private int max = DEFAULT_MAX;
	
	public void setMax(int limit){
		max = limit;
	}

	@Override
	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF };
	}

	@Override
	public void visitToken(DetailAST ast) {
		// find the OBJBLOCK node below the CLASS_DEF/INTERFACE_DEF
		DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);

		// count the number of direct children of the OBJBLOCK
		// that are METHOD_DEFS
		int methodDefs = objBlock.getChildCount(TokenTypes.METHOD_DEF);

		// report violation if limit is reached
		if (methodDefs > this.max) {
			String message = "too many methods, only " + this.max + " are allowed";
			log(ast.getLineNo(), message);
		}
	}

	@Override
	public int[] getAcceptableTokens() {
		return null;
	}

	@Override
	public int[] getRequiredTokens() {
		return null;
	}
}