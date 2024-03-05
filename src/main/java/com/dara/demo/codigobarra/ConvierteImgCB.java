package com.dara.demo.codigobarra;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

@Service
public class ConvierteImgCB {
	 
	public CodigoBarra leeCodigoBarras(MultipartFile imagenMultipartFile) throws Exception {
		
		InputStream imagenStream = imagenMultipartFile.getInputStream();
	
		/*leemos la imagen y guardamos en memoria*/
		BufferedImage imagen = ImageIO.read(imagenStream);
		try {
     	   /*el método LuminanceSource es para poner en escala de grises la imagen*/
         	LuminanceSource img_luminanceS = new BufferedImageLuminanceSource(imagen);
             BinaryBitmap img_bitmap = new BinaryBitmap(new HybridBinarizer(img_luminanceS));
             /*decodifica el CB y devuelve el valor*/
             Result resultado = new MultiFormatReader().decode(img_bitmap);
             CodigoBarra codigobarra = new CodigoBarra(imagenMultipartFile.getOriginalFilename(), resultado.getText());
             return codigobarra;
             
        } catch (Exception e) {
     	   CodigoBarra codigobarra = new CodigoBarra("Imagen1.jpg", "Error al decodificar CB"+ e.getMessage());
     	  return codigobarra;
        }
	}	
	
public CodigoBarra decodeCodigoBarras(BufferedImage imagen) throws Exception {
		
		//InputStream imagenStream = imagenMultipartFile.getInputStream();
	
		/*leemos la imagen y guardamos en memoria*/
		//BufferedImage imagen = ImageIO.read(imagenStream);
		try {
     	   /*el método LuminanceSource es para poner en escala de grises la imagen*/
         	LuminanceSource img_luminanceS = new BufferedImageLuminanceSource(imagen);
             BinaryBitmap img_bitmap = new BinaryBitmap(new HybridBinarizer(img_luminanceS));
             /*decodifica el CB y devuelve el valor*/
             Result resultado = new MultiFormatReader().decode(img_bitmap);
             CodigoBarra codigobarra = new CodigoBarra("nombre prueba", resultado.getText());
             return codigobarra;
             
        } catch (Exception e) {
     	   CodigoBarra codigobarra = new CodigoBarra("Imagen1.jpg", "Error al decodificar CB"+ e.getMessage());
     	  return codigobarra;
        }
	}
}
