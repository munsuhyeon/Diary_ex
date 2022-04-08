import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public class WinMain extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinMain frame = new WinMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WinMain() {
		setTitle("2022년4월8일프로젝트");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnuProjects = new JMenu("Projects");
		mnuProjects.setMnemonic('p');
		menuBar.add(mnuProjects);
		
		JMenuItem mnuLogin = new JMenuItem("Login...");
		mnuLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		mnuLogin.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
		mnuLogin.setMnemonic('l');
		mnuProjects.add(mnuLogin);
		
		JMenuItem mnuDiary = new JMenuItem("Diary...");
		mnuDiary.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
		mnuDiary.setMnemonic('d');
		mnuDiary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinDiary dlg = new WinDiary("홍길동");
				dlg.setModal(true);
				dlg.setVisible(true);
			}
		});
		mnuProjects.add(mnuDiary);
		
		JMenu mnuColor = new JMenu("색상(C)");
		mnuColor.setMnemonic('c');
		menuBar.add(mnuColor);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Red");
		mnuColor.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Green");
		mnuColor.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Blue");
		mnuColor.add(mntmNewMenuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
