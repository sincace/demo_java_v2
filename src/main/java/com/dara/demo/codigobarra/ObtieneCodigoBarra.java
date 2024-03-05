package com.dara.demo.codigobarra;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

@Service
public class ObtieneCodigoBarra {
	
	public List<CodigoBarra> leeCodigoBarras(String rutacarpeta) throws Exception {
        File directorio       = new File(rutacarpeta);
        File[] listaImagenes  = directorio.listFiles(); /*obtenemos la lista de imagenes del directorio*/   
        List<CodigoBarra> Listcodigobarra = new ArrayList<>();//creamos la lista para el resultado
        
        
		/*validamos que la lista no sea nula*/
		if (listaImagenes != null) {			
            for (File child : listaImagenes) { /*leemos cada item de la lista*/           	
                if (child.isFile() && child.getName().endsWith(".jpg")) { /*valimos que las imagenes sea .jpg*/
                	
                	/*el método ImageIO para leer la imagen y guardamos en memoria*/
                	BufferedImage imagen = ImageIO.read(child);
               try {
            	   /*el método LuminanceSource es para poner en escala de grises la imagen*/
                	LuminanceSource img_luminanceS = new BufferedImageLuminanceSource(imagen);
                    BinaryBitmap img_bitmap = new BinaryBitmap(new HybridBinarizer(img_luminanceS));
                    /*decodifica el CB y devuelve el valor*/
                    Result resultado = new MultiFormatReader().decode(img_bitmap);
                    CodigoBarra codigobarra = new CodigoBarra(child.getName(), resultado.getText());
                   
                    Listcodigobarra.add(codigobarra);                                       
                	
               } catch (Exception e) {
            	   CodigoBarra codigobarra = new CodigoBarra(child.getName(), "Error al decodificar CB"+ e.getMessage());
            	   Listcodigobarra.add(codigobarra); 
               }	
                    
                }
            }
        }

		return Listcodigobarra;
    }

}
