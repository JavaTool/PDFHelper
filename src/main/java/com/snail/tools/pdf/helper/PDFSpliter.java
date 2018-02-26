package com.snail.tools.pdf.helper;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;

public class PDFSpliter {
	
    public static void main( String[] args ) {
        try {
        	PDDocument doc = PDDocument.load(new File(args[0] + ".pdf"));
        	int count = doc.getPages().getCount();
        	String arg = args.length > 2 ? args[2] : "";
        	int split = Integer.parseInt(args[1]);
        	int pages = count / split;
        	int totalPage = 0;
        	PDDocument[] docs = new PDDocument[split];
        	File[] files = new File[split];
        	for (int i = 0;i < split;i++) {
        		docs[i] = new PDDocument();
        		for (int page = 0;page < pages;page++, totalPage++) {
        			docs[i].addPage(doc.getPage(totalPage));
        		}
        		files[i] = new File(args[0] + "_" + i + ".pdf");
        		docs[i].save(files[i]);
        	}
        	if (totalPage < count) {
        		PDDocument tailDoc = "+".equals(arg) ? new PDDocument() : docs[split - 1];
        		for (;totalPage < count;totalPage++) {
        			tailDoc.addPage(doc.getPage(totalPage));
        		}
        		tailDoc.save("+".equals(arg) ? new File(args[0] + "_tail.pdf") : files[split - 1]);
        	}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
}
