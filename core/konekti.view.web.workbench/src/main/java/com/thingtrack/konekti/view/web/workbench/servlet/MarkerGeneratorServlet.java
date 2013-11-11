package com.thingtrack.konekti.view.web.workbench.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class MarkerGeneratorServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {

			OutputStream out = response.getOutputStream();

			String parser = XMLResourceDescriptor.getXMLParserClassName();
		    SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);
		    Document document = factory.createDocument(SVGDOMImplementation.SVG_NAMESPACE_URI, getServletContext().getResourceAsStream("/WEB-INF/marker.svg"));
		    
		    Element colorElement = document.getElementById("svg_2");
		    String color = req.getParameter("color");
		    
			if(color instanceof String && !color.isEmpty()){
				colorElement.setAttribute("fill", "#"+color);
			}
			
			Node textElement = document.getElementById("tspan3007").getFirstChild();
			String text = req.getParameter("text");
			
			if(text instanceof String && !text.isEmpty()){
				textElement.setNodeValue(text);
			}
			
			Element shadowElement = document.getElementById("svg_3");
			Boolean noshadow = Boolean.valueOf(req.getParameter("noshadow"));
			
			if(noshadow){
				document.removeChild(shadowElement);
			}
		    
		    // Read input to TranscoderInput
			TranscoderInput input_svg_image = new TranscoderInput(document);
			// Define output
			TranscoderOutput output_jpg_image = new TranscoderOutput(out);
			// Create a JPEG Transcoder
			PNGTranscoder my_converter = new PNGTranscoder();
			 
			my_converter.addTranscodingHint(PNGTranscoder.KEY_HEIGHT, new Float(100));
			my_converter.addTranscodingHint(PNGTranscoder.KEY_WIDTH, new Float(100));
			
			
			// do the conversion
			response.setContentType("image/png");
			// return the output JPG image
			try {
				my_converter.transcode(input_svg_image, output_jpg_image);
			} catch (TranscoderException e) {
				 System.err.println(e.toString()); 				
			}finally{
				out.close();
			}		
	}	
}
