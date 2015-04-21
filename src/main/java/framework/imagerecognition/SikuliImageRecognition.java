package framework.imagerecognition;

import java.io.File;

import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.api.ImageTarget;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.Target;
import org.sikuli.api.robot.Mouse;
import org.sikuli.api.robot.desktop.DesktopMouse;

public class SikuliImageRecognition {
	
	private ScreenRegion sRegion;

	public SikuliImageRecognition(){
		sRegion = new DesktopScreenRegion();
	}
	
	public void clickOnImage(String imageName){
		File buttonPathFile = new File("src/test/resources/images/" + imageName);                
	    Target imageTarget = new ImageTarget(buttonPathFile);
	    ScreenRegion r = sRegion.wait(imageTarget, 7000);
	    Mouse mouse = new DesktopMouse();
	    mouse.click(r.getCenter());
	    mouse.move(r.getCenter());
	}
	public boolean moveOnImage(String imageName){
		try{
		File buttonPathFile = new File("src/test/resources/images/" + imageName);                
	    Target imageTarget = new ImageTarget(buttonPathFile);
	    ScreenRegion r = sRegion.wait(imageTarget, 7000);
	    Mouse mouse = new DesktopMouse();
	    mouse.move(r.getCenter());}
		catch (Exception e){
			return false;
		}
		return true;
	}
}
