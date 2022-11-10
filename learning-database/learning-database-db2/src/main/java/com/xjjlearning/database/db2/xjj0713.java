package com.xjjlearning.database.db2;

//1,xjj,J,jjx,JAVA,17808057361,2000.10.12,code,1,男,2000.10.12,25000,1,1
//1,XJJ,A,JJJ,A01,7361,2022-05-18,HUAWEI,22,M,2000-10-12,25000.0,1000.0,10000.0
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.sql.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;


class MyObject{
    private String EMPNO,FIRSTNAME,MIDINIT,LASTNAME,WORKDEPT,PHONENO,HIREDATE,JOB,EDLEVEL,SEX,BIRTHDATE,SALARY,BONUS,COMM;
    //private float  SALARY,BONUS,COMM;
    public String getEMPNO() {
        return EMPNO;
    }
    public void setEMPNO(String eMPNO) {
        EMPNO = eMPNO;
    }
    public String getLASTNAME() {
        return LASTNAME;
    }
    public void setLASTNAME(String lASTNAME) {
        LASTNAME = lASTNAME;
    }
    public String getWORKDEPT() {
        return WORKDEPT;
    }
    public void setWORKDEPT(String wORKDEPT) {
        WORKDEPT = wORKDEPT;
    }
    public String getPHONENO() {
        return PHONENO;
    }
    public void setPHONENO(String pHONENO) {
        PHONENO = pHONENO;
    }
    public String getHIREDATE() {
        return HIREDATE;
    }
    public void setHIREDATE(String HIREDATE) {
        this.HIREDATE = HIREDATE;
    }
    public String getJOB() {
        return JOB;
    }
    public void setJOB(String jOB) {
        JOB = jOB;
    }
    public String getEDLEVEL() {
        return EDLEVEL;
    }
    public void setEDLEVEL(String eDLEVEL) {
        EDLEVEL = eDLEVEL;
    }
    public String getSEX() {
        return SEX;
    }
    public void setSEX(String sEX) {
        SEX = sEX;
    }
    public String getBIRTHDATE() {
        return BIRTHDATE;
    }
    public void setBIRTHDATE(String bIRTHDATE) {
        BIRTHDATE = bIRTHDATE;
    }
    public String getSALARY() {
        return SALARY;
    }
    public void setSALARY(String sALARY) {
        SALARY = sALARY;
    }
    public String getBONUS() {
        return BONUS;
    }
    public void setBONUS(String bONUS) {
        BONUS = bONUS;
    }
    public String getCOMM() {
        return COMM;
    }
    public void setCOMM(String cOMM) {
        COMM = cOMM;
    }
    public String getFIRSTNAME() {
        return FIRSTNAME;
    }
    public void setFIRSTNAME(String FIRSTNAME) {
        this.FIRSTNAME = FIRSTNAME;
    }
    public String getMIDINIT() {
        return MIDINIT;
    }
    public void setMIDINIT(String mIDINIT) {
        MIDINIT = mIDINIT;
    }

}
class MyObjectDao{
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    public List<MyObject> queryALLMyObject() {
        return queryALLMyObject(null);
    }
    public List<MyObject> queryALLMyObject(String EMEPO){
        //查询所有对象
        String sql;
        List<MyObject> list= new ArrayList<>();
        try {
            conn = DbUtils.getConnection();
            if (EMEPO != null) {
                sql = "select * from UDBA.TEMPL where EMEPO = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, EMEPO);
            } else {
                sql = "select * from UDBA.TEMPL";
                ps = conn.prepareStatement(sql);
            }
            rs = ps.executeQuery();
            System.out.println(ps.toString());
            while (rs.next()) {
                MyObject myobject = new MyObject();
                myobject.setEMPNO(rs.getString(1));
                myobject.setFIRSTNAME(rs.getString(2));
                myobject.setMIDINIT(rs.getString(3));
                myobject.setLASTNAME(rs.getString(4));
                myobject.setWORKDEPT(rs.getString(5));
                myobject.setPHONENO(rs.getString(6));
                myobject.setHIREDATE(rs.getString(7));
                myobject.setJOB(rs.getString(8));
                myobject.setEDLEVEL(rs.getString(9));
                myobject.setSEX(rs.getString(10));
                myobject.setBIRTHDATE(rs.getString(11));
                myobject.setSALARY(rs.getString(12));
                myobject.setBONUS(rs.getString(13));
                myobject.setCOMM(rs.getString(14));

                list.add(myobject);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

public class xjj0713 extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTable table;
    private String head[] = null;
    private Object[][] data = null;
    private MyObjectDao myobject = new MyObjectDao();
    JButton putinS = new JButton("单行插入");
    JButton putinDuo = new JButton("多行插入");
    JButton putinDan = new JButton("子查询插入");
    JButton rollback = new JButton("回滚");

    public DefaultTableModel tableModel;

    //暂时使用文本框读入数据
    JTextField SingleText = new JTextField();//单行插入
    JTextArea DuoText = new JTextArea();//多行插入
    JTextField DanText = new JTextField();//子查询插入

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    xjj0713 frame = new xjj0713();
                    frame.setVisible(true);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    Container frame;
    double windowWidth = 1000; //获得窗口宽
    double windowHeight = 800; //获得窗口高

    public xjj0713() {

        super("55180713-肖子健");

        table = new JTable();
        table.setBorder(new LineBorder(new Color(0, 0, 0)));
        head = new String[]{
                "EMEPO", "FIRSTNAME", "MIDINIT", "LASTNAME", "WORKDEPT", "PHONENO", "HIREDATE", "JOB", "EDLEVEL", "SEX", "BIRTHDATE", "SALARY", "BONUS", "COMM"
        };
        tableModel = new DefaultTableModel(queryData(), head) {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setModel(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);//为JTable a建立一个区域
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);//显示水平滑动栏
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//显示竖直滑动栏

        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        FitTableColumns(table);
        scrollPane.setViewportView(table);

        frame = getContentPane();
        frame.setLayout(null);//自由布局

        //多行插入页面
        JScrollPane Duotext_ScrollPane = new JScrollPane();//文本域滚动条
        Duotext_ScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        Duotext_ScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);//水平和垂直滚动条自动出现
        //略

        DuoText.setLineWrap(true);    //设置文本域中的文本为自动换行
        frame.add(scrollPane);
        frame.add(putinS);
        frame.add(putinDuo);
//        frame.add(putinDan);
        frame.add(SingleText);
        frame.add(DuoText);
//        frame.add(DanText);
        frame.add(Duotext_ScrollPane);
        //设置监听器
        putinS.addActionListener(this);
        putinDuo.addActionListener(this);
//        putinDan.addActionListener(this);
        //b
        contentPane = new JPanel();

        //布局
        //putinDuo.setBounds(50,30,100,30);
        //putinDan.setBounds(800,30,100,30);

        SingleText.setBounds(50, (int) windowHeight - 310, 700, 30);
        putinS.setBounds(800, (int) windowHeight - 310, 100, 30);

        DuoText.setBounds(50, (int) windowHeight - 255, 700, 100);
        Duotext_ScrollPane.setBounds(50, (int) windowHeight - 255, 700, 100);
        Duotext_ScrollPane.setViewportView(DuoText);
        putinDuo.setBounds(800, (int) windowHeight - 220, 100, 30);

        DanText.setBounds(50, (int) windowHeight - 140, 700, 30);
        putinDan.setBounds(800, (int) windowHeight - 140, 100, 30);

        scrollPane.setBounds(50, 75, (int) windowWidth - 125, (int) windowHeight - 400);


        frame.add(contentPane);
        setSize((int) windowWidth, (int) windowHeight);
        //windowWidth = this.getWidth(); //获得窗口宽
        //windowHeight = this.getHeight(); //获得窗口高
        setVisible(true);

    }

    //生成表格数据

    /**
     * @return
     */
    public Object[][] queryData() {
        List<MyObject> list = myobject.queryALLMyObject();
        data = new Object[list.size()][head.length];

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < head.length; j++) {
                data[i][0] = list.get(i).getEMPNO();
                data[i][1] = list.get(i).getFIRSTNAME();
                data[i][2] = list.get(i).getMIDINIT();
                data[i][3] = list.get(i).getLASTNAME();
                data[i][4] = list.get(i).getWORKDEPT();
                data[i][5] = list.get(i).getPHONENO();
                data[i][6] = list.get(i).getHIREDATE();
                data[i][7] = list.get(i).getJOB();
                data[i][8] = list.get(i).getEDLEVEL();
                data[i][9] = list.get(i).getSEX();
                data[i][10] = list.get(i).getBIRTHDATE();
                data[i][11] = list.get(i).getSALARY();
                data[i][12] = list.get(i).getBONUS();
                data[i][13] = list.get(i).getCOMM();
            }
        }
        return data;
    }

    void FitTableColumns(JTable myTable) {               //设置列表宽度随内容调整

        JTableHeader header = myTable.getTableHeader();

        int rowCount = myTable.getRowCount();

        Enumeration columns = myTable.getColumnModel().getColumns();

        while (columns.hasMoreElements()) {
            TableColumn column = (TableColumn) columns.nextElement();

            int col = header.getColumnModel().getColumnIndex(

                    column.getIdentifier());

            int width = (int) myTable.getTableHeader().getDefaultRenderer()

                    .getTableCellRendererComponent(myTable,

                            column.getIdentifier(), false, false, -1, col)

                    .getPreferredSize().getWidth();

            for (int row = 0; row < rowCount; row++) {
                int preferedWidth = (int) myTable.getCellRenderer(row, col)

                        .getTableCellRendererComponent(myTable,

                                myTable.getValueAt(row, col), false, false,

                                row, col).getPreferredSize().getWidth();

                width = Math.max(width, preferedWidth);

            }

            header.setResizingColumn(column);

            column.setWidth(5 + width + myTable.getIntercellSpacing().width);

        }

    }

    void setobject(String sub[], PreparedStatement ps) throws SQLException {
        for (int i = 0; i < 6; i++) {
            ps.setString(i + 1, sub[i]);
        }
        ps.setDate(7, Date.valueOf(sub[6]));
        ps.setString(8, sub[7]);
        ps.setShort(9, Short.valueOf(sub[8]));
        ps.setString(10, sub[9]);
        ps.setDate(11, Date.valueOf(sub[10]));
        ps.setBigDecimal(12, BigDecimal.valueOf(Double.valueOf((sub[11]))));
        ps.setBigDecimal(13, BigDecimal.valueOf(Double.valueOf((sub[12]))));
        ps.setBigDecimal(14, BigDecimal.valueOf(Double.valueOf((sub[13]))));
    }

    public void actionPerformed(ActionEvent e) {
        Connection conn = null;
        try {
            conn = DbUtils.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return;
        }
        PreparedStatement ps = null;
        PreparedStatement psSelect = null;
        ResultSet rs = null;

        try {
            if (e.getSource() == putinDuo) { //多行
                //获取输入的数据
                String add = DuoText.getText();
                //String EMPNO="",FIRSTNAME="",MIDINIT="",LASTNAME="",WORKDEPT="",PHONENO="",HIREDATE="",JOB="",EDLEVEL="",SEX="",BIRTHDATE="",SALARY="",BONUS="",COMM="";
                String[] sub = add.split("\n");//数据清理
                for (String s : sub) {
                }

                for (String s : sub) {
                    List<MyObject> myObjects = new MyObjectDao().queryALLMyObject(String.valueOf(s.charAt(0)));
                    if (myObjects.size() != 0 && myObjects.get(0) != null) {
                        conn.rollback();
                        throw new Exception("不能插入id相同的数据");
                    }
                    String sql = "insert into UDBA.TEMPL (EMEPO, FIRSTNAME, MIDINIT, LASTNAME, WORKDEPT, PHONENO, HIREDATE, JOB, EDLEVEL, SEX, BIRTHDATE, SALARY, BONUS, COMM) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    String[] split = s.split(",");
                    ps = conn.prepareStatement(sql);
                    setobject(split, ps);
                    ps.executeUpdate();
                    tableModel.addRow(split);
                }
            } else if (e.getSource() == putinS) { //单行
                String sql;

                //获取输入的数据
                String add = SingleText.getText();
                String[] sub = add.split(",");//数据清理

                List<MyObject> myObjects = new MyObjectDao().queryALLMyObject(sub[0]);
                if (myObjects.size() != 0 && myObjects.get(0) != null) { //有数据则更新
                    sql = "update UDBA.TEMPL set EMEPO = ?, FIRSTNAME = ?, MIDINIT = ?, LASTNAME = ?, WORKDEPT = ?, PHONENO = ?, HIREDATE = ?, JOB = ?, EDLEVEL = ?, SEX = ?, BIRTHDATE = ?, SALARY = ?, BONUS = ?, COMM = ? where EMEPO = ?";
                    ps = conn.prepareStatement(sql);
                    setobject(sub, ps);
                    ps.setString(15, sub[0]);
                    ps.executeUpdate();
                    tableModel.getDataVector().clear();
                    tableModel.setDataVector(queryData(), head);
                } else {
                    //执行 insert 逻辑
                    sql = "insert into UDBA.TEMPL (EMEPO, FIRSTNAME, MIDINIT, LASTNAME, WORKDEPT, PHONENO, HIREDATE, JOB, EDLEVEL, SEX, BIRTHDATE, SALARY, BONUS, COMM)  values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    ps = conn.prepareStatement(sql);
                    setobject(sub, ps);
                    ps.executeUpdate();
                    tableModel.addRow(sub);
                }
            }

            FitTableColumns(table);
            conn.commit();
        }catch (Exception x) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                System.out.println("here 1");
                ex.printStackTrace();
            }
            System.out.println("here 2");
            System.out.println(x.getMessage());
        }
    }
}




