package com.norton.tank;

import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {

	public static void main(String[] args) {

		Audio a=new Audio("audio/explode.wav");
		a.play();
	}

	byte[] b = new byte[1024 * 1024 * 15];
	private AudioFormat audioFormat = null;
	private AudioInputStream audioInputStream = null;
	private DataLine.Info dataLine_info = null;

	private SourceDataLine sourceDataline = null;

	public Audio(String fileName) {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(Audio.class.getClassLoader().getResource(fileName));
			audioFormat = audioInputStream.getFormat();
			dataLine_info = new DataLine.Info(SourceDataLine.class, audioFormat);
			sourceDataline = (SourceDataLine) AudioSystem.getLine(dataLine_info);
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			audioInputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void loop() {
		try {

			while (true) {
				int len = 0;
				sourceDataline.open(audioFormat, 1024 * 1024 * 15);
				sourceDataline.start();

				audioInputStream.mark(12358946);
				while ((len = audioInputStream.read(b)) > 0) {
					sourceDataline.write(b, 0, len);
				}
				audioInputStream.reset();
				sourceDataline.drain();
				sourceDataline.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void play() {
		try {

			byte[] b=new byte[1024*5];
			int len=0;
			sourceDataline.open(audioFormat,1024*5);
			sourceDataline.start();
			audioInputStream.mark(12398546);
			while((len=audioInputStream.read(b))>0) {
				sourceDataline.write(b, 0, len);
			}
			
			sourceDataline.drain();
			sourceDataline.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
