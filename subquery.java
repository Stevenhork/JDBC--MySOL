package JDBC������;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

import com.mysql.jdbc.Connection;

public class subquery extends JFrame {
	private JButton submit = new JButton("�ύ");
	JTextArea area = new JTextArea();
	JScrollPane js = new JScrollPane(area);
	JPanel buttonPane = new JPanel();
	Container ContentPane = getContentPane();

	subquery(home2 aduc) {
		super("�Ӳ�ѯ����");
		ContentPane.add(js, BorderLayout.CENTER);
		buttonPane.add(submit);
		ContentPane.add(buttonPane, BorderLayout.SOUTH);
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton) e.getSource();
				run();
				aduc.getList();
				subquery.this.dispose();
			}
		});
	}

	public void run() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/text","root","");
			String sql=area.getText();
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.execute();
		}catch(SQLException e){
			int SQLCode;
			String SQLState;
			SQLCode =  e.getErrorCode();
			SQLState = e.getSQLState();
			String Message = e.getMessage();
			JOptionPane.showMessageDialog(null,
					"\nSQLCODE:  " + SQLCode + "\nSQLSTATE: " + SQLState + "\nSQLERRM:  " + Message, "��������",
					JOptionPane.ERROR_MESSAGE);
			
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}

	}

}
