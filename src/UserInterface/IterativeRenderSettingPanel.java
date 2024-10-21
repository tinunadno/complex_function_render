package UserInterface;

import Render.IterativeRender;

import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IterativeRenderSettingPanel {

    public static JPanel generate(BufferedImage screen,BufferedImage miniMap, IterativeRender renderEngine){
        iterativeRenderSettingPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        iterationCountField = new javax.swing.JTextField();
        iterationCountButton = new javax.swing.JButton();

        iterationCountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renderEngine.setIterationCount(Integer.parseInt(iterationCountField.getText()));
                renderEngine.render(screen);
                renderEngine.render(miniMap);
            }
        });

        jLabel3 = new javax.swing.JLabel();
        functionRangeButton = new javax.swing.JButton();
        functionRangeField = new javax.swing.JTextField();
        functionRangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renderEngine.setRange(Integer.parseInt(functionRangeField.getText()));
                renderEngine.render(screen);
                renderEngine.render(miniMap);
            }
        });

        positionScreen = new javax.swing.JLabel(new ImageIcon(miniMap));
        positionScreen.addMouseWheelListener(new MouseWheelListener(){
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                // Вывод в консоль направления и количества шагов прокрутки
                if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
                    int steps = e.getUnitsToScroll()/3*(-1);
                    renderEngine.addScale(steps);
                    renderEngine.render(miniMap);
                    iterativeRenderSettingPanel.repaint();
                }
            }
        });


        ExecutorService mouseTrackingPool= Executors.newFixedThreadPool(1);
        MouseTracker mouseTracker=new MouseTracker(renderEngine, miniMap, positionScreen);
        positionScreen.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
                mouseTracker.setPressedButton(1);
                mouseTrackingPool.submit(mouseTracker);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                mouseTracker.setPressedButton(-1);
            }
            @Override
            public void mouseEntered(MouseEvent e){

            }
            @Override
            public void mouseExited(MouseEvent e){

            }
        });

        positionButton = new javax.swing.JButton();

        iterativeRenderSettingPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setText("iterationCount");

        iterationCountButton.setText("set iteration count");

        jLabel3.setText("Function range");

        functionRangeButton.setText("set function range");

        positionButton.setText("update position");
        positionButton.addActionListener(e->{
            renderEngine.render(screen);
        });

        javax.swing.GroupLayout IterativeRenderSettingPanelLayout = new javax.swing.GroupLayout(iterativeRenderSettingPanel);
        iterativeRenderSettingPanel.setLayout(IterativeRenderSettingPanelLayout);
        IterativeRenderSettingPanelLayout.setHorizontalGroup(
                IterativeRenderSettingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(IterativeRenderSettingPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(IterativeRenderSettingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(positionScreen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(iterationCountField)
                                        .addComponent(iterationCountButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(functionRangeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(positionButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(IterativeRenderSettingPanelLayout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(functionRangeField))
                                .addContainerGap())
        );
        IterativeRenderSettingPanelLayout.setVerticalGroup(
                IterativeRenderSettingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(IterativeRenderSettingPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(iterationCountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(iterationCountButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(functionRangeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(functionRangeButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(positionScreen, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(positionButton)
                                .addContainerGap())
        );
        return iterativeRenderSettingPanel;
    }

    private static javax.swing.JPanel iterativeRenderSettingPanel;
    private static javax.swing.JButton functionRangeButton;
    private static javax.swing.JTextField functionRangeField;
    private static javax.swing.JButton iterationCountButton;
    private static javax.swing.JTextField iterationCountField;
    private static javax.swing.JLabel jLabel2;
    private static javax.swing.JLabel jLabel3;
    private static javax.swing.JButton positionButton;
    private static javax.swing.JLabel positionScreen;
}
