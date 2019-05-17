/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QR;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 *
 * @author LigaS
 */
public abstract class Camera {
    Webcam webcam = Webcam.getDefault();
    WebcamPanel panel = new WebcamPanel(webcam);
    JFrame window = new JFrame("Genuine Coder");
    String data;
    
    public void OpenCamera () {
        // Buka Kamera
        //webcam.setViewSize(WebcamResolution.VGA.getSize());
        
        panel.setFPSDisplayed(true);
        panel.setDisplayDebugInfo(true);
        panel.setImageSizeDisplayed(true);
        panel.setMirrored(true);
        
        window.add(panel);
        window.setResizable(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setLocale(null);
    }
    
    public void CloseCamera () {
        window.dispose();
        webcam.close();
        //window.addKeyListener(listener);
    }

    public abstract boolean CatchImage ();
}
