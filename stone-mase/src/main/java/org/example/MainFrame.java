package org.example;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    //华容道主界面
    public MainFrame() {
        initUI();
        createAndShowGUI();
    }
    private JPanel panel;
    private JButton button;
    //初始化界面
    private void initUI() {
        //设置窗口标题
        setTitle("石头迷阵 V1.0");
        //设置窗口大小
        setSize(450, 575);
        //设置窗口关闭操作为退出程序
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗口位置
        setLocationRelativeTo(null);
        //设置窗口是否可调整大小
        setResizable(false);
        //设置窗口是否可见
        setVisible(true);
    }
    public void createAndShowGUI() {
        panel = new JPanel();
        panel.setLayout(null);//设置面板布局为绝对布局
        add(panel);
        //界面大标题
        JLabel titleLabel = new JLabel("石头迷阵 V1.0");
        titleLabel.setBounds(120, 100, 200, 30);//设置标题位置和大小
        titleLabel.setFont(new Font("楷体", Font.BOLD, 24));
        panel.add(titleLabel);
        //添加按钮
        button = new JButton("开始游戏");
        button.setBounds(130, 250, 140, 30);//设置按钮位置和大小
        button.setBackground(Color.BLUE);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("楷体", Font.BOLD, 16));
        panel.add(button);
        //添加按钮点击事件监听器
        addStartButtonListener();
    }

    private void addStartButtonListener() {
        button.addActionListener(e -> {
            //点击开始游戏按钮后，跳转到游戏界面
            GameFrame gameFrame = new GameFrame();
        });
    }
}
