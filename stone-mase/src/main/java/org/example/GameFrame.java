package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameFrame extends JFrame {
    //图片地址
    private static final String IMAGE_PATH = "stone-mase/src/main/resources/image/";
    private JPanel panel;
    private JButton button;
    private int[][] imageData = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };
    //定义胜利数组
    private int[][] winData = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };
    //当前零图片的行号和列号
    private int zeroRow = 0;
    private int zeroCol = 0;
    private int step = 0;

    //图像资源
    //华容道游戏界面
    public GameFrame() {
        initUI();//初始化界面
        //打乱图片位置
        shuffleImage();
        initImages();//初始化图像
        initMenu();//初始化菜单
        //当前窗口绑定上下左右方向键
        initKeyListener();
        this.setVisible(true);//设置窗口可见
    }
    //胜利判断
    private boolean isWin() {
        //判断当前图片是否等于胜利数组
        for (int i = 0; i < imageData.length; i++) {
            for (int j = 0; j < imageData[i].length; j++) {
                if (imageData[i][j] != winData[i][j]) {
                    return false;
                }
            }
        }
        //如果所有图片都等于胜利数组,则返回true
        return true;
    }

    private void initKeyListener() {
        /* 添加键盘事件监听器,用Direction枚举类表示方向键,当按下方向键时,根据方向键移动图片 */
        //用switch语句处理方向键事件
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        //上方向键
                        moveImage(Direction.UP);
                        step++;
                        break;
                    case KeyEvent.VK_DOWN:
                        //下方向键
                        moveImage(Direction.DOWN);
                        step++;
                        break;
                    case KeyEvent.VK_LEFT:
                        //左方向键
                        moveImage(Direction.LEFT);
                        step++;
                        break;
                    case KeyEvent.VK_RIGHT:
                        //右方向键
                        moveImage(Direction.RIGHT);
                        step++;
                        break;
                }
            }

        });
    }
    private void moveImage(Direction direction) {
        //根据方向键移动图片
        switch (direction) {
            case UP:
                //上方向键,将0上面的图片移动到0的位置，注意边界条件行<length-1（图片现在是打乱的）
                if (zeroRow < imageData.length - 1) {
                    int temp = imageData[zeroRow + 1][zeroCol];
                    imageData[zeroRow + 1][zeroCol] = imageData[zeroRow][zeroCol];
                    imageData[zeroRow][zeroCol] = temp;
                    zeroRow++;
                }

                break;
            case DOWN:
                //下方向键,将0下面的图片移动到0的位置，注意边界条件（图片现在是打乱的）
                if (zeroRow > 0) {
                    int temp = imageData[zeroRow - 1][zeroCol];
                    imageData[zeroRow - 1][zeroCol] = imageData[zeroRow][zeroCol];
                    imageData[zeroRow][zeroCol] = temp;
                    zeroRow--;
                }
                break;
            case LEFT:
                //左方向键,将0左边的图片移动到0的位置，注意边界条件（图片现在是打乱的）
                if (zeroCol < imageData[zeroRow].length - 1) {
                    int temp = imageData[zeroRow][zeroCol + 1];
                    imageData[zeroRow][zeroCol + 1] = imageData[zeroRow][zeroCol];
                    imageData[zeroRow][zeroCol] = temp;
                    zeroCol++;
                }
                break;
            case RIGHT:
                //右方向键,将0右边的图片移动到0的位置，注意边界条件（图片现在是打乱的）
                if (zeroCol > 0) {
                    int temp = imageData[zeroRow][zeroCol - 1];
                    imageData[zeroRow][zeroCol - 1] = imageData[zeroRow][zeroCol];
                    imageData[zeroRow][zeroCol] = temp;
                    zeroCol--;
                }
                break;
        }
        //更新图像
        initImages();


    }
    private void shuffleImage() {
        //打乱图片位置，使游戏开始时图片位置随机，且游戏可玩
        for (int i=0;i<imageData.length;i++){
            for (int j=0;j<imageData[i].length;j++){
                int randomRow = (int)(Math.random()*imageData.length);
                int randomCol = (int)(Math.random()*imageData[randomRow].length);
                //交换当前位置和随机位置的图片
                int temp = imageData[i][j];
                imageData[i][j] = imageData[randomRow][randomCol];
                imageData[randomRow][randomCol] = temp;
            }
        }
        //记录0的位置
        for (int i = 0; i < imageData.length; i++) {
            for (int j = 0; j < imageData[i].length; j++) {
                if (imageData[i][j] == 0) {
                    zeroRow = i;
                    zeroCol = j;
                    break;
                }
            }
        }


    }

    private void initMenu() {
        //添加菜单，菜单项；退出游戏、重新开始游戏：
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("菜单");
        //添加退出游戏菜单
        JMenuItem exitMenuItem = new JMenuItem("退出游戏");
        exitMenuItem.addActionListener(e -> {
            //点击退出游戏菜单后，关闭游戏界面
            dispose();
        });
        menu.add(exitMenuItem);
        //添加重新开始游戏菜单
        JMenuItem restartMenuItem = new JMenuItem("重新开始游戏");
        restartMenuItem.addActionListener(e -> {
            //点击重新开始游戏菜单后，重新开始游戏界面原游戏界面销毁
            dispose();
            new GameFrame();
            step = 0;
        });
        menu.add(restartMenuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }


    //初始化图像，图片资源在resources下的image文件夹
    private void initImages() {
        //清空面板
        this.getContentPane().removeAll();
        //判断是否胜利//判断是否胜利
        //判断是否胜利
        if (isWin()) {
            //如果胜利,则显示胜利提示，用image里的winImage图片
            JLabel winLabel = new JLabel(new ImageIcon(IMAGE_PATH + "winImage.png"));
            winLabel.setBounds(100, 100, 200, 200);
            add(winLabel);
        }
        //重新添加图像
        for (int i = 0; i < imageData.length; i++) {
            for (int j = 0; j < imageData[i].length; j++) {
                //获取当前位置的图片名称
                String imageName = imageData[i][j] + ".png";
                //加载图片
                ImageIcon image = new ImageIcon(IMAGE_PATH + imageName);
                //设置图片位置和大小
                JLabel label = new JLabel(image);
                label.setBounds(32 + j * 100, 68 + i * 100, 100, 100);
                add(label);
            }
        }
        //设置背景图片
        JLabel backgroundLabel = new JLabel(new ImageIcon(IMAGE_PATH + "background.png"));
        backgroundLabel.setBounds(10, 10, 450, 484);
        add(backgroundLabel);
        //添加步数标签
        JLabel stepLabel = new JLabel("当前步数：" + step);
        stepLabel.setBounds(10, 2, 150, 30);
        stepLabel.setFont(new Font("楷体", Font.BOLD, 16));
        add(stepLabel);
        //刷新界面
        repaint();
    }
    //初始化界面
    private void initUI() {
        //设置窗口标题
        setTitle("以更少步数完成游戏吧😀");
        //设置窗口大小
        setSize(485, 575);
        //设置窗口位置
        setLocationRelativeTo(null);
        //设置窗口是否可调整大小
        setResizable(false);
        //设置窗口布局为绝对布局
        setLayout(null);

    }
}