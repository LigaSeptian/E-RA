/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QR;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 *
 * @author LigaS
 */
public class ReadQRCode {
    Webcam webcam = Webcam.getDefault();
    WebcamPanel panel = new WebcamPanel(webcam);
    JFrame window = new JFrame("Genuine Coder");
    
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
    
    public boolean CatchImage () {
        window.validate();
        window.setVisible(true);
        
        try {
            ImageIO.write(webcam.getImage(), "PNG", new File("Coba.png"));
        } catch (IOException ex) {
            //Logger.getLogger(AmbilFoto.class.getName()).log(Level.SEVERE, null, ex);
        }
        //window.dispose();
        //webcam.close();
        
        return ReadQR();
    }
    
    public boolean ReadQR () {
        // ReadQRCode
        try {
            String filePath = "C:\\Users\\LigaS\\Documents\\NetBeansProjects\\TuBesPBO\\QR\\Coba.png";
            //String filePath = "C:\\Users\\LigaS\\Documents\\NetBeansProjects\\TuBesPBO\\Coba.png";
            String charset = "UTF-8";
            Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel> ();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            System.out.println("Data read from QR Code: " + ReadQRCode(filePath, charset, hintMap));
            return true;
        } catch (NotFoundException | IOException e) {
            // TODO: handle exception
            System.out.println("Gagal");
            return false;
        }
    }
    
    public void CloseCamera () {
        window.dispose();
        webcam.close();
        //window.addKeyListener(listener);
    }
    
    public static void main (String[] args) throws IOException {
        ReadQRCode R = new ReadQRCode();
        boolean berhasil;
        
        R.OpenCamera();
        
        do {
            berhasil = R.CatchImage();
        } while (berhasil == false);
        
        R.CloseCamera();
    }
    
    public String ReadQRCode (String filePath, String charset, Map hintMap)
    throws FileNotFoundException, IOException, NotFoundException {
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
            new BufferedImageLuminanceSource(
            ImageIO.read(new FileInputStream(filePath)))));
        Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap, hintMap);
        return qrCodeResult.getText();
    }
}
