package utils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {
	private Clip clip;
	private FloatControl volumeControl;

	public MusicPlayer(String filePath) {
		try {
			File soundFile = new File(filePath);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

			clip = AudioSystem.getClip();
			clip.open(audioStream);

			if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
				volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			}

		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
			System.out.println("Error: Cannot load the sound file.");
		}
	}

	public void playSound() {
		if (clip != null) {
			new Thread(() -> {
				clip.setFramePosition(0);
				clip.start();
				try {
					Thread.sleep(clip.getMicrosecondLength() / 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}).start();
		}
	}

	public void loopSound() {
		if (clip != null) {
			new Thread(() -> {
				try {
					clip.setFramePosition(0);
					clip.loop(Clip.LOOP_CONTINUOUSLY);
					clip.start();
					while (clip.isOpen()) {
						Thread.sleep(1000);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}).start();
		}
	}

	public void stopSound() {
		if (clip != null && clip.isRunning()) {
			clip.stop();
		}
	}

	public void setVolume(float volume) {
		if (volumeControl != null) {
			float min = volumeControl.getMinimum();
			float max = volumeControl.getMaximum();
			float value = Math.max(min, Math.min(max, volume));
			volumeControl.setValue(value);
		}
	}
}
