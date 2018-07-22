package JDBC������;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class add_one7 extends JFrame {// �����в������Ӽ�������
	// �����ͼ������������������
	private JButton submit = new JButton("�ύ");
	Container contentPane = getContentPane();
	JPanel buttonPanel = new JPanel();
	private JTextField text = new JTextField();
	JScrollPane js = new JScrollPane(text);

	add_one7(home2 aduc)// ���췽��
	{
		super("���в���");
		contentPane.add(js, BorderLayout.CENTER);
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.add(submit);
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton) e.getSource();
				run();
				aduc.getList();
				add_one7.this.dispose();
			}
		});
	}

	@SuppressWarnings("null")
	public void run() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/text", "root", "");
			String sql = "insert into staff"
					+ "(id,name,sex,work,salary,phone,address,num,note) values(?,?,?,?,?,?,?,?,?)";
			String sq = "select * from Staff";
			PreparedStatement stmt = conn.prepareStatement(sql);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sq);
			int i = 0;
			ArrayList<Integer> index = new ArrayList<Integer>();
			while (rs.next()) {
				index.add(rs.getInt(1));
				i++;
			}
			String[] values = text.getText().split("[ \t]");
			if (!values[0].equals("")) {
				if(Integer.parseInt(values[0])<=0){
					JOptionPane.showMessageDialog(null, "��ű���Ϊ������" , "���в���ʧ��",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				for (int j = 0; j < index.size(); j++) {
					if (Integer.parseInt(values[0]) == index.get(j)) {
						JOptionPane.showMessageDialog(null, "���" + index.get(j) + "�Ѵ���", "���в���ʧ��",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				stmt.setInt(1, Integer.parseInt(values[0]));
			} else {
				JOptionPane.showMessageDialog(null, "Ա����Ų���Ϊ��", "���в���", JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (!values[1].equals("")) {
				stmt.setString(2, values[1]);
			} else {
				stmt.setNull(2, Types.VARCHAR);
			}

			if (!values[2].equals("")) {
				stmt.setString(3, values[2]);
			} else {
				stmt.setNull(3, Types.VARCHAR);
			}

			if (!values[3].equals("")) {
				stmt.setString(4, values[3]);
			} else {
				stmt.setNull(4, Types.VARCHAR);
			}

			if (!values[4].equals("")) {
				if (Integer.parseInt(values[4]) <= 0) {
					JOptionPane.showMessageDialog(null, "���ʱ���Ϊ�����������", "���в���", JOptionPane.ERROR_MESSAGE);
					return;
				}
				stmt.setInt(5, Integer.parseInt(values[4]));

			} else {
				stmt.setNull(5, Types.INTEGER);
			}

			if (!values[5].equals("")) {
				stmt.setString(6, values[5]);
			} else {
				stmt.setNull(6, Types.VARCHAR);
			}

			if (!values[6].equals("")) {
				stmt.setString(7, values[6]);
			} else {
				stmt.setNull(7, Types.VARCHAR);
			}

			if (!values[7].equals("")) {
				stmt.setString(8, values[7]);
			} else {
				stmt.setNull(8, Types.VARCHAR);
			}

			if (!values[8].equals("")) {
				stmt.setString(9, values[8]);
			} else {
				stmt.setNull(9, Types.VARCHAR);
			}
			stmt.executeUpdate();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
			int SQLCode;
			String SQLState;
			SQLCode =  e1.getErrorCode();
			SQLState = e1.getSQLState();
			String Message = e1.getMessage();
			JOptionPane.showMessageDialog(null,
					"\nSQLCODE:  " + SQLCode + "\nSQLSTATE: " + SQLState + "\nSQLERRM:  " + Message, "SQL������",
					JOptionPane.ERROR_MESSAGE);
		} catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null,"Ա����ź͹��ʱ���Ϊ������", "��������",JOptionPane.ERROR_MESSAGE);
		} finally {
			

		}
	}

}