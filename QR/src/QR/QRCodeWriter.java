/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QR;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.sql.*;



/**
 *
 * @author LigaS
 */
public class QRCodeWriter {
    public void GeneratingQR (String nim) {
        try {
            String qrCodeData = nim;
            String filePath = "C:\\Users\\Lenovo\\Documents\\GitHub\\E-RA\\QR\\" + nim + ".png";
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel> ();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
                new String(qrCodeData.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, 200, 200, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
            .lastIndexOf('.') + 1), new File(filePath));
            System.out.println("QR Code image created successfully!");
        } catch (WriterException | IOException e) {
            System.err.println(e);
        }
    }
    
    public static void main (String[] args) throws SQLException {
        Connect koneksi = new Connect();
        Connection connect = koneksi.getConnection();
        String query = "Select * from mahasiswa";
        Statement stat = connect.createStatement();
        ResultSet hasil = stat.executeQuery(query);
        while (hasil.next()){
            String nim = hasil.getString("nim");
            QRCodeWriter QR = new QRCodeWriter();
            QR.GeneratingQR(nim);
        }
        
    }
}
