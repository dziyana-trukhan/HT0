package com.mpatric.mp3agic;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;


public class MAIN_MP3 {
	public static void main (String args[]) throws UnsupportedTagException, InvalidDataException {
		try {
		PrintWriter catalog = new PrintWriter(new FileWriter("catalog.html"));
		for (int i=0; i < args.length; i++) {
			File dir = new File(args[i]);
			if (dir.isDirectory()) {
				for (File item: dir.listFiles()) {
					String name = item.getName();
					 if (name.substring(name.lastIndexOf(".") + 1 ) == "mp3" ) {
						 Mp3File  mp3file = new Mp3File(item.getName());
							 if (mp3file.hasId3v2Tag()) {
								 ID3v2 id3v2Tag = mp3file.getId3v2Tag();
								 catalog.println(id3v2Tag.getArtist());
								 catalog.println(id3v2Tag.getAlbum());
							
								 for (File artistSearch: dir.listFiles()) {
									 if (name.substring(name.lastIndexOf(".") + 1 ) == "mp3" ) {
										 Mp3File artistMp3 = new Mp3File(artistSearch.getName());
										 if (artistMp3.hasId3v2Tag()) {
											 ID3v2 id3v2Tag1 = artistMp3.getId3v2Tag();
											 if ((id3v2Tag1.getAlbum() == id3v2Tag.getAlbum()) && (id3v2Tag1.getArtist() == id3v2Tag.getArtist())){
												 catalog.println(id3v2Tag.getTitle());
												 catalog.println(id3v2Tag.getLength());
												 catalog.println(id3v2Tag1.getTitle());
												 catalog.println(id3v2Tag1.getLength());
											 }
										 }
									 }
							 }
						 }
					 }
				}
			}
			
		} catalog.close();
		} catch (IOException err) {
			 System.out.print(err.getMessage());
		}
	}
}
