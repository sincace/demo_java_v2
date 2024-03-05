package com.dara.demo.controller;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dara.demo.codigobarra.CodigoBarra;
import com.dara.demo.codigobarra.ConvierteImgCB;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.Base64;

import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/decode/codigobarra")
public class ServicioController {
	 
	 @Autowired
	 private ConvierteImgCB convierteImgCB;
	 private CodigoBarra codigoBarra;
	 	 
	 @PostMapping("/leeCodigoBarras")
	 public CodigoBarra leeCodigoBarras(@RequestParam("imagenMultipartFile") MultipartFile imagenMultipartFile)  {

	        try {
	           codigoBarra = convierteImgCB.leeCodigoBarras(imagenMultipartFile);
	           return codigoBarra;

	        } catch (Exception e) {
	            e.printStackTrace();

	           CodigoBarra codigoBarra = new CodigoBarra("Error al leer directorio", "No se encuentra la carpeta"+ e.getMessage());
	           return codigoBarra;
	            		
	    }
	}
	 
	@GetMapping("/prueba")
	public CodigoBarra prueba(){
		CodigoBarra codigoBarra = new CodigoBarra("llega al servicio", "prueba get");
        return codigoBarra;
	}
	
	@PostMapping("/decode")
	 public CodigoBarra decodeCodigoBarras(@RequestParam("imagen") String imagenString)  {
		
		//ByteArrayInputStream stream = new   ByteArrayInputStream(file.getBytes());
		//String myString = IOUtils.toString(stream, "UTF-8");
        byte[] imageByte;
        
        Base64.Decoder decoder = Base64.getDecoder();
        imageByte = decoder.decode(imagenString);//   decodeBuffer(imagenString);
        //ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
                    		
	        try {
	        	 BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageByte));
	             codigoBarra = convierteImgCB.decodeCodigoBarras(image);
	             return codigoBarra;

	        } catch (Exception e) {
	            e.printStackTrace();

	           CodigoBarra codigoBarra = new CodigoBarra("Error al leer directorio", "No se encuentra la carpeta"+ e.getMessage());
	           return codigoBarra;
	            		
	    }
	}

}
