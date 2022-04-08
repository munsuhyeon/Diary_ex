import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WinDiary extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtId;
	private JTextField txtWriter;
	private JTextField txtDate;
	private JTextField txtTitle;
	private JTextField txtPassword;
	private JTextField txtWeather;
	JTextArea txtContents;
	JLabel lblPic;
	int imageNum = 0; //맑음=0 흐림=1 눈=2 비=3

	/**
	 * Create the dialog.
	 */
	public void ClearAllText() {
		txtId.setText("");
		txtWriter.setText("");
		txtDate.setText("");
		txtWeather.setText("");
		txtTitle.setText("");
		txtPassword.setText("");
		txtContents.setText("");
	}
	public WinDiary(String name) {
		initGUI();
		ClearAllText();
		setTitle("ICI Diary(" + name + "의 일기)");
		txtWriter.setText(name);
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH)+1;
		int day = now.get(Calendar.DAY_OF_MONTH);
		txtDate.setText(year + "-" + month + "-" + day);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtWeather.setText(comboBox.getSelectedItem().toString());
				lblPic.setBounds(422,99,100,100);
				/*if(comboBox.getSelectedIndex()==0) 
				 image = new ImageIcon("img/맑음.jpg");			
				else if(comboBox.getSelectedIndex()==1) 
				 image = new ImageIcon("img/흐림.jpg");	
				else if(comboBox.getSelectedIndex()==2) 
				 image = new ImageIcon("img/눈.jpg");	
				else if(comboBox.getSelectedIndex()==3) 
				 image = new ImageIcon("img/비.jpg");*/	
				String weather[] = {"맑음","흐림","비","눈"};
				imageNum = comboBox.getSelectedIndex();
				ImageIcon image = new ImageIcon("img/" + weather[imageNum] +".jpg");
				
				Image pic = image.getImage();
				pic = pic.getScaledInstance(100, 100,Image.SCALE_SMOOTH); //이미지크기변경
				image = new ImageIcon(pic);			
				lblPic.setIcon(image);
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"맑음", "흐림", "비", "눈"}));
		comboBox.setBounds(313, 182, 92, 23);
		contentPanel.add(comboBox);
		
		lblPic = new JLabel("");
		lblPic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String weather[] = {"맑음","흐림","비","눈"};
				ImageIcon image = new ImageIcon("img/"+weather[imageNum]+".jpg");
				lblPic.setSize(image.getIconWidth(), image.getIconHeight());
				lblPic.setIcon(image);
				
			}
		});
		lblPic.setBounds(422, 99, 100, 100);
		contentPanel.add(lblPic);
	}
	private void initGUI() {
		setTitle("ICI Diary(일기)");
		setBounds(100, 100, 615, 814);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("일련번호:");
		lblNewLabel.setBounds(37, 44, 69, 15);
		contentPanel.add(lblNewLabel);
		
		txtId = new JTextField();
		txtId.setBounds(144, 41, 143, 21);
		contentPanel.add(txtId);
		txtId.setColumns(20);
		
		JLabel lblNewLabel_1 = new JLabel("글쓴이");
		lblNewLabel_1.setBounds(37, 90, 50, 15);
		contentPanel.add(lblNewLabel_1);
		
		txtWriter = new JTextField();
		txtWriter.setColumns(20);
		txtWriter.setBounds(144, 87, 143, 21);
		contentPanel.add(txtWriter);
		
		JLabel lblNewLabel_2 = new JLabel("날짜");
		lblNewLabel_2.setBounds(37, 139, 50, 15);
		contentPanel.add(lblNewLabel_2);
		
		txtDate = new JTextField();
		txtDate.setColumns(20);
		txtDate.setBounds(144, 136, 143, 21);
		contentPanel.add(txtDate);
		
		JLabel lblNewLabel_3 = new JLabel("제목");
		lblNewLabel_3.setBounds(37, 235, 50, 15);
		contentPanel.add(lblNewLabel_3);
		
		txtTitle = new JTextField();
		txtTitle.setColumns(20);
		txtTitle.setBounds(144, 232, 261, 21);
		contentPanel.add(txtTitle);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(20);
		txtPassword.setBounds(144, 280, 143, 21);
		contentPanel.add(txtPassword);
		
		JLabel lblNewLabel_1_1 = new JLabel("비밀번호");
		lblNewLabel_1_1.setBounds(37, 283, 50, 15);
		contentPanel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("내용");
		lblNewLabel_2_1.setBounds(37, 328, 50, 15);
		contentPanel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("날씨");
		lblNewLabel_2_2.setBounds(37, 186, 50, 15);
		contentPanel.add(lblNewLabel_2_2);
		
		txtWeather = new JTextField();
		txtWeather.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		txtWeather.setColumns(20);
		txtWeather.setBounds(144, 183, 143, 21);
		contentPanel.add(txtWeather);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 364, 387, 322);
		contentPanel.add(scrollPane);
		
		txtContents = new JTextArea();
		scrollPane.setViewportView(txtContents);
		
		JButton btnSave = new JButton("저장");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sampledb?user=root&password=1234");
					System.out.println("DB 연결...");
					String sql = "insert into diaryTBL values(null,'";
					sql = sql + txtWriter.getText() + "','";
					sql = sql + txtDate.getText() + "','";
					sql = sql + txtWeather.getText() + "','";
					sql = sql + txtTitle.getText() + "','";
					sql = sql + txtPassword.getText() + "','";
					sql = sql + txtContents.getText() + "')";		
					System.out.println(sql);
					Statement stmt = con.createStatement();
					stmt.executeUpdate(sql);
					
					if(stmt.executeUpdate(sql) > 0)
						System.out.println("데이터베이스에 저장되었습니다.");
					else
						System.out.println("저장오류");
					stmt.close();
					con.close();
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			   				
			}
		});
		btnSave.setBounds(43, 714, 91, 23);
		contentPanel.add(btnSave);
	}
}
