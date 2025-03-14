package src.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
public class MusicPlayer {
	public static void playSound(String filePath) {
		try (InputStream soundFile = MusicPlayer.class.getResourceAsStream(filePath)) {
			if (soundFile == null) {
				System.out.println("File not found: " + filePath);
				return;
			}

			AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

			// ตรวจสอบว่าเสียงต้องแปลงหรือไม่
			AudioFormat format = audioStream.getFormat();
			if (format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
				AudioFormat newFormat = new AudioFormat(
						AudioFormat.Encoding.PCM_SIGNED,
						format.getSampleRate(),
						16, // 16-bit sample size
						format.getChannels(),
						format.getChannels() * 2, // สอง bytesต่อ frame
						format.getSampleRate(),
						false // big-endian
				);
				audioStream = AudioSystem.getAudioInputStream(newFormat, audioStream);
			}

			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.start();
			Thread.sleep(clip.getMicrosecondLength() / 1000);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
			e.printStackTrace();
			System.out.println("Error occurred while playing the sound.");
		}
	}
}

