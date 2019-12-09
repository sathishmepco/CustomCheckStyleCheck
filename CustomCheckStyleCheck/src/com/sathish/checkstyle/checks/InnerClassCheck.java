package com.sathish.checkstyle.checks;

import java.io.File;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.SeverityLevel;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class InnerClassCheck extends AbstractCheck {

	public InnerClassCheck() {
		setSeverity(SeverityLevel.ERROR.getName());
	}

	@Override
	public int[] getAcceptableTokens() {
		return null;
	}

	@Override
	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF };
	}

	@Override
	public void visitToken(DetailAST ast) {
		String fileName = this.getFileContents().getFileName();
		File file = new File(fileName);
		String javaName = file.getName().substring(0, file.getName().lastIndexOf(".java")); // remove
		if (ast.branchContains(TokenTypes.IDENT)) {
			DetailAST id = ast.findFirstToken(TokenTypes.IDENT);
			if (!javaName.equals(id.getText())) {
				log(ast, "Inner class is not allowed!");
			}
		}
	}

	@Override
	public int[] getRequiredTokens() {
		return null;
	}
}