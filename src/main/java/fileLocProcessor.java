import org.apache.log4j.Level;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtCatch;
import spoon.reflect.cu.SourcePosition;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtTypedElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Reports warnings when empty catch blocks are found.
 */
public class fileLocProcessor extends AbstractProcessor<CtClass> {
	private int targetLine = 0;
	private String targetFile = null;
	public int curLine = 0;
	public CtMethod method = null;

	fileLocProcessor(String[] buggyInfo) {
		if (buggyInfo != null) {
			this.targetLine = Integer.parseInt(buggyInfo[1]);
			this.targetFile = buggyInfo[0];
		}
	}

	@Override
	public boolean isToBeProcessed(CtClass candidate) {
		return true;
	}

	@Override
	public void process(CtClass element) {
		SourcePosition position = element.getPosition();
		if(position.isValidPosition()) {
			if (! position.getFile().toString().endsWith(targetFile)) {
			    return;
			}
			Set<CtMethod> methodSet = element.getAllMethods();
			for (CtMethod method : methodSet){
				SourcePosition methodPosition = method.getPosition();
				if(!methodPosition.isValidPosition()) continue;
				int methodLine = methodPosition.getLine();
				if (methodLine > curLine && methodLine < targetLine) {
					curLine = methodLine;
					this.method = method;
				}
			}
		}
	}
}
