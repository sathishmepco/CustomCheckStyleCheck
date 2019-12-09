package com.sathish.checkstyle.checks;

import java.util.regex.Pattern;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.SeverityLevel;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class HungarianNotationCheck extends AbstractCheck {

	private static final String CATCH_MSG = "Declaring varibale like \"%s\" is older style.";
	private Pattern pattern = Pattern.compile("m[A-Z0-9].*");
	
	public HungarianNotationCheck() {
		setSeverity(SeverityLevel.WARNING.getName());
	}

	@Override
	public int[] getAcceptableTokens() {
		return null;
	}

	@Override
	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.VARIABLE_DEF };
	}

	@Override
	public void visitToken(DetailAST ast) {
		String variableName = findVariableName(ast);
		if (itsAFieldVariable(ast) && detectsNotation(variableName)) {
			reportStyleError(ast, variableName);
		}
	}

	@Override
	public int[] getRequiredTokens() {
		return null;
	}

	private String findVariableName(DetailAST aAST) {
		DetailAST identifier = aAST.findFirstToken(TokenTypes.IDENT);
		return identifier.getText();
	}

	private boolean itsAFieldVariable(DetailAST aAST) {
		return aAST.getParent().getType() == TokenTypes.OBJBLOCK;
	}

	private void reportStyleError(DetailAST aAST, String variableName) {
		log(aAST.getLineNo(), String.format(CATCH_MSG, variableName));
	}

	private boolean detectsNotation(String variableName) {
		return pattern.matcher(variableName).matches();
	}
}