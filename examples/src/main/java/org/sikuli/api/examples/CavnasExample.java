package org.sikuli.api.examples;
import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.api.ImageTarget;
import org.sikuli.api.Relative;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.Target;
import org.sikuli.api.visual.ScreenRegionCanvas;

public class CavnasExample {

	static ScreenSimulator simulator = new ScreenSimulator(){
		public void run(){
			showImage(Images.OSXDockPreferences);
		}
	};

	public static void main(String[] args) throws IOException {
		simulator.start();

		ScreenRegion s = new DesktopScreenRegion();
		
		Target imageTarget = new ImageTarget(Images.ThumbIcon);

		ScreenRegion thumb = s.find(imageTarget);

		ScreenRegionCanvas canvas = new ScreenRegionCanvas(s);
		canvas.addBox(thumb);
		canvas.addLabel(Relative.to(thumb).topLeft().above(20).getScreenLocation(), "ImageTarget:");
		canvas.addImage(Relative.to(thumb).topLeft().above(25).right(85).getScreenLocation(), ImageIO.read(Images.ThumbIcon));
		canvas.display(3);

		Target target = new ImageTarget(Images.UncheckedCheckbox);		
		List<ScreenRegion> checkboxes = s.findAll(target);

		canvas.clear();		
		int no = 1;
		for (ScreenRegion r : checkboxes){			
			String labelText = String.format("(%d):%1.3f", no, r.getScore());
			canvas.addBox(r).withLineColor(Color.green).withLineWidth(1);
			canvas.addLabel(Relative.to(r).topLeft().left(70).getScreenLocation(),labelText).withColor(Color.blue);
			no++;
		}
		canvas.display(3);
		
		simulator.close();
		
	}
}
