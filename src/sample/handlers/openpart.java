 
package sample.handlers;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;


public class openpart {
	
	 @Inject
     EPartService partService;
     MApplication application;
     EModelService modelService;
     
	@Execute
    public void execute(ParameterizedCommand commandParameters, EPartService partService,MApplication application,EModelService modelService) throws CoreException {
		
		 if(null == commandParameters){
             return;
      }
      Map parameterMap = commandParameters.getParameterMap();
      boolean partExist = false;

      if(!partExist){
             MPart part = MBasicFactory.INSTANCE.createPart();
             part.setLabel(parameterMap.get("PartName").toString());
             part.setElementId("sample.part.samplepart");
             part.setCloseable(true);
             part.setToBeRendered(true);
             part.setContributionURI("bundleclass://sample/sample.parts.SamplePart");
             part.setContainerData("100");
             List<MPartStack> stacks = modelService.findElements(application, null,  MPartStack.class, null);
             stacks.get(1).getChildren().add(part);
             stacks.get(1).setVisible(true);
             partService.showPart(part, PartState.ACTIVATE);
             partService.activate(part, true);
      }
   
}


}