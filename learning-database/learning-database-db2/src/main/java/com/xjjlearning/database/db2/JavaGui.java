package com.xjjlearning.database.db2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JavaGui extends Frame {
    public JavaGui(String s) {
        super(s);
    }

    public static void main(String[] args) {
        new GridBagLayoutDemo().init();
    }
    public static void frame() {
        JavaGui fr = new JavaGui("Hello"); //构造方法
        fr.setSize(240,240);  //设置Frame的大小
        fr.setBackground(Color.blue); //设置Frame的背景色
        fr.setVisible(true); //设置Frame为可见，默认不可见
    }
    public static void panel() {
        // TODO Auto-generated method stub
        Frame fr = new Frame("Hello");
        fr.setSize(240,240);
        fr.setBackground(Color.green);
        fr.setLayout(null); //取消默认的布局BorderLayout
        Panel pan = new Panel(); //创建面板
        pan.setSize(100,100);
        pan.setBackground(Color.yellow);
        fr.add(pan);
        fr.setVisible(true);
    }
    public static void mylayout() {
        Frame frame = new Frame("FlowLayout"); //Frame默认的布局管理器为BorderLayout
        frame.setBounds(100, 100, 400, 300);
        frame.setLayout(new FlowLayout()); //设置布局管理器为FlowLayout

        Button but1 = new Button("button1");
        Button but2 = new Button("button2");
        Button but3 = new Button("button3");
        Button but4 = new Button("button4");
        Button but5 = new Button("button5");

        but1.setBackground(Color.blue);
        but2.setBackground(Color.yellow);
        but3.setBackground(Color.red);
        but4.setBackground(Color.green);
        but5.setBackground(Color.pink);

        frame.add(but1);
        frame.add(but2);
        frame.add(but3);
        frame.add(but4);
        frame.add(but5);

        frame.setVisible(true);
    }
    public static void myborderlayout() {

        Frame frame = new Frame("BorderLayt");
        frame.setBounds(100, 100, 400, 300);
        //frame.setLayout(new BorderLayout()); //设置 frame的布局为BorderLayout,默认也是此布局

        Button btn1 = new Button("button1");
        Button btn2 = new Button("button2");
        Button btn3 = new Button("button3");
        Button btn4 = new Button("button4");
        Button btn5 = new Button("button5");

        btn1.setBackground(Color.blue);
        btn2.setBackground(Color.yellow);
        btn3.setBackground(Color.pink);
        btn4.setBackground(Color.green);
        btn5.setBackground(Color.red);

        frame.add(btn1,BorderLayout.EAST);
        frame.add(btn2,BorderLayout.NORTH);
        frame.add(btn3,BorderLayout.SOUTH);
        frame.add(btn4,BorderLayout.WEST);
        frame.add(btn5);

        frame.setVisible(true);
    }
    public static void mygridlayout() {

        Frame frame = new Frame("GridLayout");
        frame.setBounds(100, 100, 400, 300);

        GridLayout gl = new GridLayout(3,2,5,5); //设置表格为3行两列排列，表格横向间距为5个像素，纵向间距为5个像素
        frame.setLayout(gl);

        Button but1 = new Button("button1");
        Button but2 = new Button("button2");
        Button but3 = new Button("button3");
        Button but4 = new Button("button4");
        Button but5 = new Button("button5");

        but1.setBackground(Color.blue);
        but2.setBackground(Color.yellow);
        but3.setBackground(Color.red);
        but4.setBackground(Color.green);
        but5.setBackground(Color.pink);

        frame.add(but1);
        frame.add(but2);
        frame.add(but3);
        frame.add(but4);
        frame.add(but5);

        frame.setVisible(true);
    }

    static class CardLayoutDemo {
        Frame f = new Frame("测试窗口");
        String[] names = {"第一张", "第二张", "第三张", "第四张", "第五张"};
        Panel p1 = new Panel(); //显示的面板

        public void init() {
            final CardLayout c = new CardLayout(); //卡片局部
            p1.setLayout(c); //面板布局使用卡片布局
            for (int i = 0; i < names.length; i++) {
                p1.add(names[i], new Button(names[i])); //设置面板的名字和组件
            }
            Panel p = new Panel(); //创建一个放按钮的面板

            // 控制显示上一张的按钮
            Button previous = new Button("上一张");
            //为按钮添加监听
            previous.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    c.previous(p1);
                }
            });

            // 控制显示下一张的按钮
            Button next = new Button("下一张");
            next.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    c.next(p1);
                }
            });

            // 控制显示第一张的按钮
            Button first = new Button("第一张");
            first.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    c.first(p1);
                }
            });

            // 控制显示最后一张的按钮
            Button last = new Button("最后一张");
            last.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    c.last(p1);
                }
            });

            // 控制根据Card显示的按钮
            Button third = new Button("第三张");
            third.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    c.show(p1, "第三张");
                }
            });

            p.add(previous);
            p.add(next);
            p.add(first);
            p.add(last);
            p.add(third);
            f.add(p1);
            f.add(p, BorderLayout.SOUTH);

            f.pack(); //紧凑排列
            f.setVisible(true);
        }

    }

    static class GridBagLayoutDemo {
        private Frame f = new Frame("GridBagLayout Test");
        private GridBagLayout gbl = new GridBagLayout();
        private GridBagConstraints gbc = new GridBagConstraints();

        private Button[] btns = new Button[10];

        private void addButton(Button btn) {
            gbl.setConstraints(btn, gbc);
            f.add(btn);
        }


        public void init() {
            for (int i = 0; i < 10; i++) { // 先初始化10个按钮
                btns[i] = new Button("button" + i);
            }
            f.setLayout(gbl); // 设定框架的布局模式

            //为了设置如果组件所在的区域比组件本身要大时的显示情况
            gbc.fill = GridBagConstraints.BOTH; // 使组件完全填满其显示区域
            //NONE：不调整组件大小。
            //HORIZONTAL：加宽组件，使它在水平方向上填满其显示区域，但是不改变高度。
            //VERTICAL：加高组件，使它在垂直方向上填满其显示区域，但是不改变宽度。
            //BOTH：使组件完全填满其显示区域。

            gbc.weighty = 1; // 该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个,为1则只占一格

            // 第1行的4个按钮
            gbc.weightx = 1; // 该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
            addButton(btns[0]);
            addButton(btns[1]);
            addButton(btns[2]);
            gbc.gridwidth = GridBagConstraints.REMAINDER; // 该组件是该行的最后一个，第4个添加后就要换行了
            addButton(btns[3]);

            // 第2行1个按钮，仍然保持REMAINDER换行状态
            addButton(btns[4]);

            //第3行
            gbc.gridwidth = 2; //按钮分别横跨2格
            gbc.weightx = 1;  //该方法设置组件水平的拉伸幅度
            addButton(btns[5]);
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            addButton(btns[6]);

            // 按钮7纵跨2个格子，8、9一上一下
            gbc.gridheight = 2; //按钮7纵跨2格
            gbc.gridwidth = 1;  //横跨1格
            gbc.weightx = 1;    //该方法设置组件水平的拉伸幅度
            addButton(btns[7]); // 由于纵跨2格因此纵向伸缩比例不需要调整，默认为1*2格，比例刚好

            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.gridheight = 1;
            gbc.weightx = 1;
            addButton(btns[8]);
            addButton(btns[9]);

            f.pack();
            f.setVisible(true);
        }
    }

    static class ComponentTest {

        public static void main(String[] args) {
            // TODO Auto-generated method stub
            Frame frame = new Frame("基本组件测试");
            frame.setBounds(100, 100, 600, 300);
            GridLayout gl = new GridLayout(4,2,5,5); //设置表格为3行两列排列，表格横向间距为5个像素，纵向间距为5个像素
            frame.setLayout(gl);

            //按钮组件
            Button but1 = new Button("测试按钮");
            Panel pn0 = new Panel();
            pn0.setLayout(new FlowLayout());
            pn0.add(but1);
            frame.add(pn0);

            //复选框组件
            Panel pn1 = new Panel();
            pn1.setLayout(new FlowLayout());
            pn1.add(new Checkbox("one",null,true));
            pn1.add(new Checkbox("two"));
            pn1.add(new Checkbox("three"));
            frame.add(pn1);

            //复选框组（单选）
            Panel pn2 = new Panel();
            CheckboxGroup cg = new CheckboxGroup();
            pn2.setLayout(new FlowLayout());
            pn2.add(new Checkbox("one",cg,true));
            pn2.add(new Checkbox("two",cg,false));
            pn2.add(new Checkbox("three",cg,false));
            frame.add(pn2);

            //下拉菜单
            Choice cC = new Choice();
            cC.add("red");
            cC.add("green");
            cC.add("yellow");
            frame.add(cC);

            //单行文本框
            Panel pn3 = new Panel();
            pn3.setLayout(new FlowLayout());
            TextField tf = new TextField("",30); //30列长度
            pn3.add(tf);
            frame.add(pn3);

            //多行文本框
            TextArea ta = new TextArea();
            frame.add(ta);

            //列表
            List ls = new List();
            ls.add("a");
            ls.add("b");
            ls.add("c");
            ls.add("d");
            frame.add(ls);
            frame.setVisible(true);
        }

    }
}
