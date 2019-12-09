package com.sathish.checkstyle.checks;

import java.io.File;
import com.puppycrawl.tools.checkstyle.api.AbstractFileSetCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.FileText;

public class LimitImplementationFiles extends AbstractFileSetCheck {
	private static final int DEFAULT_MAX = 100;
	private int fileCount;
	private int max = DEFAULT_MAX;

	public void setMax(int aMax) {
		this.max = aMax;
	}

	@Override
	public void beginProcessing(String aCharset) {
		super.beginProcessing(aCharset);
		// reset the file count
		this.fileCount = 0;
	}

	@Override
	protected void processFiltered(File file, FileText fileText) throws CheckstyleException {
		this.fileCount++;
		if (this.fileCount > this.max) {
			// log the message
			log(1, "max.files.exceeded", Integer.valueOf(this.max));
			// you can call log() multiple times to flag multiple
			// violations in the same file
		}
	}
}