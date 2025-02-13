import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.*;

public class SoundPlayer extends JFrame {
    private JButton playButton;

    public SoundPlayer() {
        // Create a button
        playButton = new JButton("Play Sound");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("Dome_Jaruwat_-_Rainfall_Official_MV.wav"); // Replace with your file path
            }
        });

        // Add button to the frame
        setLayout(new java.awt.FlowLayout());
        add(playButton);

        // Frame settings
        setTitle("Java Sound Player");
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void playSound(String soundFile) {
        try {
            File file = new File(soundFile);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start(); // Play the sound
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SoundPlayer();
    }
}
