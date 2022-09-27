
import java.sql.*;
import Project.DBConnection;
import java.awt.event.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pc
 */
public class QuizMenu extends javax.swing.JFrame {

    public int questionId = 1;
    public String answer;

    public int min = 0;
    public int sec = 0;
    public int marks = 0;
    public String aydii;
    public String q2;
    public String qid;
    public String kwest;
    public String ans;

    public int o1 = 0;
    public int o2 = 0;
    public int o3 = 0;
    public int o4 = 0;

    public int optn1;
    public int optn2;
    public int optn3;
    public int optn4;

    public String c0;
    public int c1;
    public int c2;
    public int c3;
    public int c4;

    public int fp;
    public String kwess;

    private boolean firstAttemptCorrect = true;
    private boolean isFinished = false;
    private String hintMessage;
    private String Studchoice;
    PreparedStatement pstt = null;

    public void answerCheck() {
        String studentAnswer = "";
        if (op1.isSelected()) {

            studentAnswer = op1.getText();

        } else if (op3.isSelected()) {

            studentAnswer = op3.getText();

        } else if (op2.isSelected()) {

            studentAnswer = op2.getText();

        } else {

            studentAnswer = op4.getText();

        }

        //if (studentAnswer.equals(answer)) {
        //marks = marks + 1;
        // String marks1 = String.valueOf(marks);
        //JMarks.setText(marks1);
        //} 
        if (studentAnswer.equals(answer)) {
            h1.setText("");
            h2.setText("");
            Studchoice = studentAnswer;
            JOptionPane.showMessageDialog(this, "You've got the correct answer!");
            if (firstAttemptCorrect) {
                marks++;
                String marks1 = String.valueOf(marks);
                JMarks.setText(marks1);
                h1.setText("");
                h2.setText("");
                Studchoice = studentAnswer;
            }

            if (questionId == 15) {
                jButton2.setVisible(false);
            }

//            lbCurrentScorer.setText(score + "");
            questionId++;
            firstAttemptCorrect = true;
        } else {
            firstAttemptCorrect = false;
            JOptionPane.showMessageDialog(this, "You've got the wrong answer!\nPlease choose a correct answer \nto proceed to the next question");
            h1.setText("");
            h2.setText("");
            Studchoice = studentAnswer;
        }
        op1.setSelected(false);
        op3.setSelected(false);
        op2.setSelected(false);
        op4.setSelected(false);

    }

    public void stud_Percentage() {

        try {

            Connection con = DBConnection.getCode();
            Statement st = con.createStatement();
            ResultSet rs1 = st.executeQuery("select * from questions where QiD='" + questionId + "'");
            while (rs1.next()) {
                q2 = rs1.getString(2);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        String q1 = ques.getText();
        if (q1.equals(q2)) {

            if (op1.isSelected()) {
                o1 = 1;

            } else if (op2.isSelected()) {
                o2 = 1;

            } else if (op3.isSelected()) {
                o3 = 1;

            } else if (op4.isSelected()) {
                o4 = 1;

            }
            try {
                Connection con = DBConnection.getCode();

                pstt = con.prepareStatement("update studentanswerpercentage set op1 = op1+ ?, op2 = op2+ ?, op3 = op3+ ?, op4 = op4+ ? where aydii =?");
                pstt.setInt(1, o1);
                pstt.setInt(2, o2);
                pstt.setInt(3, o3);
                pstt.setInt(4, o4);
                pstt.setString(5, Qnum.getText());

                pstt.executeUpdate();

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, e);
            }
        }
        o1 = 0;
        o2 = 0;
        o3 = 0;
        o4 = 0;
    }

    public void PercentageResult() {

        try {

            Connection con = DBConnection.getCode();
            Statement st = con.createStatement();
            ResultSet rs1 = st.executeQuery("select * from studentanswerpercentage where aydii='" + questionId + "'");
            while (rs1.next()) {
                c0 = rs1.getString(2);
                c1 = rs1.getInt(3);
                c2 = rs1.getInt(4);
                c3 = rs1.getInt(5);
                c4 = rs1.getInt(6);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void kwestyon() {
        h2.setText("");
        h1.setText("");
        try {

            Connection con = DBConnection.getCode();
            Statement st = con.createStatement();
            ResultSet rs1 = st.executeQuery("select * from questions where QiD='" + questionId + "'");
            while (rs1.next()) {

                Qnum.setText(questionId + "");
                ques.setText(rs1.getString(2));
                op1.setText(rs1.getString(3));
                op3.setText(rs1.getString(4));
                op2.setText(rs1.getString(5));
                op4.setText(rs1.getString(6));
                answer = rs1.getString(7);
                hintMessage = rs1.getString(8);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void submit() {

        stud_Percentage();

        //try {
        //Connection con = DBConnection.getCode();
        //Statement st = con.createStatement();
        // st.executeUpdate("update results set marks='" + marks + "' where idResults='" + regaydi + "' ");
        //String marks1 = String.valueOf(marks);
        //JOptionPane.showMessageDialog(null, marks1);
        //} catch (Exception e) {
        //JOptionPane.showMessageDialog(null, e);
        // }
        try {

            String tyme = tim1.getText() + " : " + tim2.getText();
            Connection con = DBConnection.getCode();
            Statement sttt1 = con.createStatement();
            ResultSet rst1 = sttt1.executeQuery("select * from questions where QiD='" + questionId + "'");
            while (rst1.next()) {
                qid = rst1.getString(1);

            }
            String q1 = Qnum.getText();
            if (q1.equals(q2)) {
                String sql = "UPDATE results set marks = '" + JMarks.getText() + "', fname = '" + jFname.getText() + "' WHERE idResults = '" + regID.getText() + "'";
                PreparedStatement pst = con.prepareStatement(sql);

                pst.execute();

            } else {

                PreparedStatement ps = con.prepareStatement("insert into results values(?,?,?,?,?)");
                ps.setString(1, regID.getText());
                ps.setString(2, jFname.getText());
                ps.setString(3, String.valueOf(marks));
                ps.setString(4, jDate.getText());
                ps.setString(5, tyme);

                ps.executeUpdate();
            }

            if (marks == 15) {

                JOptionPane.showMessageDialog(null, +marks + "\nCongrats! You got perfect score, you may now finish the quiz.");
                setVisible(false);
                new QuizResults(regID.getText()).setVisible(true);

            } else if (marks > 10 & marks < 15) {

                JOptionPane.showMessageDialog(null, +marks + "\nCongrats! You got a high score, you may now finish the quiz.");
                setVisible(false);
                new QuizResults(regID.getText()).setVisible(true);

            } else if (marks < 10) {

                JOptionPane.showMessageDialog(null, +marks + "\nYou got a low score...\n\n\tSince you got a low score, you need to take the quiz again.");
                setVisible(false);
                new TESTAGAIN(regID.getText()).setVisible(true);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    /**
     * Creates new form QuizMenu
     */
    public QuizMenu() {
        initComponents();

    }

    Timer time;

    public QuizMenu(String registerID1) {
        initComponents();

        pBook.setVisible(false);
        prev.setVisible(false);
        nxt.setVisible(false);
        jSeparator2.setVisible(false);
        jLabel18.setVisible(false);
        jLabel19.setVisible(false);
        jLabel20.setVisible(false);
        jLabel21.setVisible(false);
        dif.setVisible(false);
        imgg.setVisible(false);
        lbPage1.setVisible(false);
        lbPage2.setVisible(false);
        aydiii.setVisible(false);
        Jname.setVisible(false);

        SimpleDateFormat df = new SimpleDateFormat("MM-dd-yy");
        Date date = new Date();
        jDate.setText(df.format(date));
        regID.setText(registerID1);
        PercentageResult();
        h1.setText("");
        h2.setText("");
        try {

            Connection con = DBConnection.getCode();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from registeracc where r_ID='" + registerID1 + "'");

            while (rs.next()) {

                jFname.setText(rs.getString(2));

            }
            System.out.println(questionId);
            ResultSet rs1 = st.executeQuery("select * from questions where QiD='" + questionId + "'");
            while (rs1.next()) {

                Qnum.setText(questionId + "");
                ques.setText(rs1.getString(2));
                op1.setText(rs1.getString(3));
                op3.setText(rs1.getString(4));
                op2.setText(rs1.getString(5));
                op4.setText(rs1.getString(6));
                answer = rs1.getString(7);
                hintMessage = rs1.getString(8);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        setLocationRelativeTo(this);

        time = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tim2.setText(String.valueOf(sec));
                tim1.setText(String.valueOf(min));

                if (sec == 60) {
                    sec = 0;
                    min++;
                    if (min == 10) {
                        time.stop();
                        answerCheck();
                        submit();
                    }
                }

                sec++;
            }
        });

        time.start();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Qnum = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        JMarks = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        tim1 = new javax.swing.JLabel();
        tim2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        regID = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        perc = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jDate = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        ques = new javax.swing.JLabel();
        op1 = new javax.swing.JRadioButton();
        op2 = new javax.swing.JRadioButton();
        op3 = new javax.swing.JRadioButton();
        op4 = new javax.swing.JRadioButton();
        h1 = new javax.swing.JLabel();
        h2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jFname = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        pBook = new javax.swing.JPanel();
        prev = new javax.swing.JButton();
        nxt = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        dif = new javax.swing.JLabel();
        imgg = new javax.swing.JLabel();
        lbPage1 = new javax.swing.JLabel();
        lbPage2 = new javax.swing.JLabel();
        aydiii = new javax.swing.JLabel();
        Jname = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLayeredPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Question:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("Total Question:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 94, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("Marks:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 128, -1, -1));

        Qnum.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        Qnum.setForeground(new java.awt.Color(204, 204, 204));
        Qnum.setText("00");
        jPanel1.add(Qnum, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 60, 97, -1));

        jLabel11.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 204, 204));
        jLabel11.setText("15");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 94, 83, -1));

        JMarks.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        JMarks.setForeground(new java.awt.Color(204, 204, 204));
        JMarks.setText("00");
        jPanel1.add(JMarks, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 128, 92, -1));

        jLabel13.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(204, 204, 204));
        jLabel13.setText("Time Taken:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 162, -1, -1));

        tim1.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        tim1.setForeground(new java.awt.Color(204, 204, 204));
        tim1.setText("00");
        jPanel1.add(tim1, new org.netbeans.lib.awtextra.AbsoluteConstraints(81, 162, -1, -1));

        tim2.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        tim2.setForeground(new java.awt.Color(204, 204, 204));
        tim2.setText("00");
        jPanel1.add(tim2, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 162, -1, -1));

        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText(":");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(104, 163, -1, -1));

        regID.setForeground(new java.awt.Color(204, 204, 204));
        regID.setText("00");
        jPanel1.add(regID, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 28, -1, -1));

        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("ID:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 28, -1, -1));

        perc.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        perc.setForeground(new java.awt.Color(204, 204, 204));
        perc.setText("Student Answer Percentage");
        perc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                percMouseClicked(evt);
            }
        });
        jPanel1.add(perc, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 315, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 51, 51));
        jLabel14.setText("GUIDE:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 222, -1, -1));

        jLabel16.setForeground(new java.awt.Color(204, 204, 204));
        jLabel16.setText("Click to view");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 261, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(204, 204, 204));
        jLabel17.setText("Read Human Anatomy ");
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 288, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 204, 204));
        jLabel12.setText("HINT");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 342, -1, -1));

        jLayeredPane1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 108, -1, 370));

        jLabel4.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jLabel4.setText("Date:");
        jLayeredPane1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 75, -1, -1));

        jDate.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jDate.setText("jLabel6");
        jLayeredPane1.add(jDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 75, 87, -1));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setForeground(new java.awt.Color(0, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ques.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        ques.setText("Question");
        jPanel2.add(ques, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 93, 958, -1));

        op1.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        op1.setText("op1");
        op1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                op1ActionPerformed(evt);
            }
        });
        jPanel2.add(op1, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 200, -1, -1));

        op2.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        op2.setText("op2");
        op2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                op2ActionPerformed(evt);
            }
        });
        jPanel2.add(op2, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 260, -1, -1));

        op3.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        op3.setText("op3");
        op3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                op3ActionPerformed(evt);
            }
        });
        jPanel2.add(op3, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 324, -1, -1));

        op4.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        op4.setText("op4");
        op4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                op4ActionPerformed(evt);
            }
        });
        jPanel2.add(op4, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 389, -1, -1));

        h1.setBackground(new java.awt.Color(204, 0, 51));
        h1.setForeground(new java.awt.Color(51, 51, 51));
        h1.setText("HINT:");
        jPanel2.add(h1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        h2.setForeground(new java.awt.Color(255, 0, 51));
        h2.setText("jLabel19");
        jPanel2.add(h2, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 150, 870, -1));

        jLayeredPane1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 108, -1, 460));

        jButton1.setBackground(new java.awt.Color(153, 0, 255));
        jButton1.setText("Submit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 620, 213, 40));

        jButton2.setBackground(new java.awt.Color(153, 0, 255));
        jButton2.setText("Next");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 620, 213, 40));

        jLabel10.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        jLabel10.setText("X");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        jLayeredPane1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 20, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tw Cen MT", 1, 36)); // NOI18N
        jLabel3.setText("-");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jLayeredPane1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1300, 10, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jLabel15.setText("Total time:    10 mins");
        jLayeredPane1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 30, -1, -1));

        jFname.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jFname.setText("Name");
        jLayeredPane1.add(jFname, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jLabel1.setText("Welcome:");
        jLayeredPane1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));
        jLayeredPane1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 52, 1370, 10));

        pBook.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        prev.setBackground(new java.awt.Color(0, 102, 204));
        prev.setFont(new java.awt.Font("Tw Cen MT", 0, 50)); // NOI18N
        prev.setText("<");
        prev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevActionPerformed(evt);
            }
        });
        pBook.add(prev, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 657, 161, 40));

        nxt.setBackground(new java.awt.Color(0, 102, 204));
        nxt.setFont(new java.awt.Font("Tw Cen MT", 0, 50)); // NOI18N
        nxt.setText(">");
        nxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nxtActionPerformed(evt);
            }
        });
        pBook.add(nxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(709, 657, 161, 40));
        pBook.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 76, 1366, -1));

        jLabel18.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 51, 51));
        jLabel18.setText("THE HUMAN ANATOMY ");
        pBook.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 31, -1, -1));

        jLabel19.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        jLabel19.setText("X");
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });
        pBook.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(1348, 21, -1, -1));

        jLabel20.setFont(new java.awt.Font("Tw Cen MT", 1, 36)); // NOI18N
        jLabel20.setText("-");
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });
        pBook.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(1328, 11, -1, -1));

        jLabel21.setFont(new java.awt.Font("Tw Cen MT", 0, 36)); // NOI18N
        jLabel21.setText("Return to quiz");
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });
        pBook.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(1082, 652, -1, -1));
        pBook.add(dif, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 183, 770, 393));
        pBook.add(imgg, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 300, 320));

        lbPage1.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        lbPage1.setText("00");
        pBook.add(lbPage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, -1, -1));

        lbPage2.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        lbPage2.setText("/ 16");
        pBook.add(lbPage2, new org.netbeans.lib.awtextra.AbsoluteConstraints(716, 40, 37, -1));

        aydiii.setText("00");
        pBook.add(aydiii, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 745, -1, -1));

        Jname.setText("name");
        pBook.add(Jname, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 745, -1, -1));

        jLayeredPane1.add(pBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 770));

        jPanel3.add(jLayeredPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 770));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        answerCheck();
        h1.setText("");
        h2.setText("");
        stud_Percentage();
        kwestyon();


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int a = JOptionPane.showConfirmDialog(null, "Are you sure you want to submit?", "Select", JOptionPane.YES_NO_OPTION);

        if (a == 0) {

            submit();
            //stud_Percentage();

        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void op1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_op1ActionPerformed
        // TODO add your handling code here:
        if (op1.isSelected()) {
            op3.setSelected(false);
            op2.setSelected(false);
            op4.setSelected(false);

        }
    }//GEN-LAST:event_op1ActionPerformed

    private void op3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_op3ActionPerformed
        // TODO add your handling code here:

        if (op3.isSelected()) {
            op1.setSelected(false);
            op2.setSelected(false);
            op4.setSelected(false);

        }
    }//GEN-LAST:event_op3ActionPerformed

    private void op2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_op2ActionPerformed
        // TODO add your handling code here:
        if (op2.isSelected()) {
            op1.setSelected(false);
            op3.setSelected(false);
            op4.setSelected(false);

        }
    }//GEN-LAST:event_op2ActionPerformed

    private void op4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_op4ActionPerformed
        // TODO add your handling code here:

        if (op4.isSelected()) {
            op1.setSelected(false);
            op3.setSelected(false);
            op2.setSelected(false);

        }
    }//GEN-LAST:event_op4ActionPerformed

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:

        int a = JOptionPane.showConfirmDialog(null, "Do you want to close this application?", "Select", JOptionPane.YES_NO_OPTION);

        if (a == 0) {
            System.exit(0);
        }

    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        this.setState(ICONIFIED);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void percMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_percMouseClicked
        // TODO add your handling code here:
        PercentageResult();

        try {

            DefaultPieDataset pieDataset = new DefaultPieDataset();
            pieDataset.setValue("Option 1", new Integer(c1));
            pieDataset.setValue("Option 2", new Integer(c2));
            pieDataset.setValue("Option 3", new Integer(c3));
            pieDataset.setValue("Option 4", new Integer(c4));

            JFreeChart chart = ChartFactory.createPieChart3D(c0, pieDataset, true, true, true);
            PiePlot3D P = (PiePlot3D) chart.getPlot();
            //P.setForegroundAlpha(TOP_ALIGNMENT);
            ChartFrame frame = new ChartFrame("Student Answer Percentage", chart);
            frame.setVisible(true);
            frame.setSize(450, 500);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }


    }//GEN-LAST:event_percMouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        // TODO add your handling code here:

        h2.setText(hintMessage);
        h1.setText("HINT:");

    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        // TODO add your handling code here:
        jPanel1.setVisible(false);
        jPanel2.setVisible(false);
        pBook.setVisible(true);
        prev.setVisible(true);
        nxt.setVisible(true);
        jSeparator2.setVisible(true);
        jLabel18.setVisible(true);
        jLabel19.setVisible(true);
        jLabel20.setVisible(true);
        jLabel21.setVisible(true);
        dif.setVisible(true);
        imgg.setVisible(true);
        lbPage1.setVisible(true);
        lbPage2.setVisible(true);
        aydiii.setVisible(true);
        Jname.setVisible(true);

        ques.setVisible(false);
        op1.setVisible(false);
        op2.setVisible(false);
        op3.setVisible(false);
        op4.setVisible(false);
        h1.setVisible(false);
        h2.setVisible(false);
        jButton1.setVisible(false);
        jButton2.setVisible(false);
        jLabel10.setVisible(false);
        jLabel3.setVisible(false);
        jLabel15.setVisible(false);
        jFname.setVisible(false);
        jLabel1.setVisible(false);
        jSeparator1.setVisible(false);

        jLabel2.setVisible(false);
        jLabel7.setVisible(false);
        jLabel8.setVisible(false);
        Qnum.setVisible(false);
        jLabel11.setVisible(false);
        JMarks.setVisible(false);
        jLabel13.setVisible(false);
        tim1.setVisible(false);
        tim2.setVisible(false);
        jLabel5.setVisible(false);
        regID.setVisible(false);
        jLabel6.setVisible(false);
        perc.setVisible(false);
        jLabel14.setVisible(false);
        jLabel16.setVisible(false);
        jLabel17.setVisible(false);
        jLabel12.setVisible(false);
        jLabel4.setVisible(false);
        jDate.setVisible(false);


    }//GEN-LAST:event_jLabel17MouseClicked

    private void prevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevActionPerformed
        // TODO add your handling code here:
        int page = (Integer.valueOf(lbPage1.getText())) - 1;

        if (page < 1) {

            prev.setVisible(false);
        } else {
            nxt.setVisible(true);
            lbPage1.setText(page + "");
            dif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imggg/" + page + ".png")));
            imgg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/" + page + ".png")));
        }
    }//GEN-LAST:event_prevActionPerformed

    private void nxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nxtActionPerformed
        // TODO add your handling code here:
        int page = (Integer.valueOf(lbPage1.getText())) + 1;

        if (page > 16) {
            nxt.setVisible(false);
        } else {
            prev.setVisible(true);
            lbPage1.setText(page + "");
            dif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imggg/" + page + ".png")));
            imgg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/" + page + ".png")));
        }
    }//GEN-LAST:event_nxtActionPerformed

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        // TODO add your handling code here:

        int a = JOptionPane.showConfirmDialog(null, "Do you want to close this application? \n All the Open application will be closed!", "Select", JOptionPane.YES_NO_OPTION);

        if (a == 0) {
            System.exit(0);

        }
    }//GEN-LAST:event_jLabel19MouseClicked

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        // TODO add your handling code here:
        this.setState(ICONIFIED);
    }//GEN-LAST:event_jLabel20MouseClicked

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        // TODO add your handling code here:
        jPanel1.setVisible(true);
        jPanel2.setVisible(true);
        pBook.setVisible(false);
        ques.setVisible(true);
        op1.setVisible(true);
        op2.setVisible(true);
        op3.setVisible(true);
        op4.setVisible(true);
        h1.setVisible(true);
        h2.setVisible(true);
        jButton1.setVisible(true);
        jButton2.setVisible(true);
        jLabel10.setVisible(true);
        jLabel3.setVisible(true);
        jLabel15.setVisible(true);
        jFname.setVisible(true);
        jLabel1.setVisible(true);
        jSeparator1.setVisible(true);

        jLabel2.setVisible(true);
        jLabel7.setVisible(true);
        jLabel8.setVisible(true);
        Qnum.setVisible(true);
        jLabel11.setVisible(true);
        JMarks.setVisible(true);
        jLabel13.setVisible(true);
        tim1.setVisible(true);
        tim2.setVisible(true);
        jLabel5.setVisible(true);
        regID.setVisible(true);
        jLabel6.setVisible(true);
        perc.setVisible(true);
        jLabel14.setVisible(true);
        jLabel16.setVisible(true);
        jLabel17.setVisible(true);
        jLabel12.setVisible(true);
        jLabel4.setVisible(true);
        jDate.setVisible(true);
    }//GEN-LAST:event_jLabel21MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuizMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuizMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuizMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuizMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuizMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JMarks;
    private javax.swing.JLabel Jname;
    private javax.swing.JLabel Qnum;
    private javax.swing.JLabel aydiii;
    private javax.swing.JLabel dif;
    private javax.swing.JLabel h1;
    private javax.swing.JLabel h2;
    private javax.swing.JLabel imgg;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jDate;
    private javax.swing.JLabel jFname;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbPage1;
    private javax.swing.JLabel lbPage2;
    private javax.swing.JButton nxt;
    private javax.swing.JRadioButton op1;
    private javax.swing.JRadioButton op2;
    private javax.swing.JRadioButton op3;
    private javax.swing.JRadioButton op4;
    private javax.swing.JPanel pBook;
    private javax.swing.JLabel perc;
    private javax.swing.JButton prev;
    private javax.swing.JLabel ques;
    private javax.swing.JLabel regID;
    private javax.swing.JLabel tim1;
    private javax.swing.JLabel tim2;
    // End of variables declaration//GEN-END:variables
}
