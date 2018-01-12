import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import javafx.scene.control.Button;

public class MenuPanel extends JPanel {

	private String[] names = { "Populacja", "Chorzy", "Zarazeni", "Odporni", "Szansa na infekcje" };

	public MenuPanel(int width, int height, Simulation simulation, int[] initialParameters) {
		JLabel[] labelsArray = new JLabel[initialParameters.length];
		JTextField[] textsArray = new JTextField[initialParameters.length];

		setPreferredSize(new Dimension(width, height));
		this.setLayout(null);

		for (int i = 0; i < initialParameters.length; i++) {
			labelsArray[i] = new JLabel(names[i]);
			labelsArray[i].setBounds(10, 47 + i * 30, 150, 15);
			add(labelsArray[i]);

			Integer value = new Integer(initialParameters[i]);
			textsArray[i] = new JTextField(value.toString());
			textsArray[i].setColumns(10);
			textsArray[i].setBounds(150, 44 + i * 30, 30, 20);
			add(textsArray[i]);
		}

		JButton btnStart = new JButton("Start");
		btnStart.setBounds(10, 270, 90, 20);
		JButton btnPause = new JButton("Pauza");
		btnPause.setBounds(100, 270, 90, 20);
		JButton btnResume = new JButton("Wznow");
		btnResume.setBounds(190, 270, 90, 20);

		btnStart.getModel().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] newParameters = new int[5];
				for (int i = 0; i < newParameters.length; i++) {
					newParameters[i] = Integer.parseInt(textsArray[i].getText());
				}
				simulation.setParameters(newParameters);
				simulation.startNewSimulation();
			}
		});
		btnPause.getModel().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				simulation.stopSimulation();
			}
		});
		btnResume.getModel().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				simulation.resumeSimulation();
			}
		});
		add(btnStart);
		add(btnPause);
		add(btnResume);

	}
}
