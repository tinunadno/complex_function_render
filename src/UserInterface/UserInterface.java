package UserInterface;

import FunctionExecution.MathematicalOperationExecute;
import FunctionExecution.ParsingTree;
import FunctionParsing.FunctionParser;
import Render.IterativeRender;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class UserInterface extends javax.swing.JFrame {
    public UserInterface(){
        initComponents();
    }

    private static String[] ableRenderEngines={"iterative", "vector", "three dimensional"};


    BufferedImage screen;
    IterativeRender iterativeRenderEngine;

    private void initComponents(){
        iterativeRenderEngine=new IterativeRender();

        screen=new BufferedImage(800, 400, BufferedImage.TYPE_INT_RGB);
        BufferedImage miniMap=new BufferedImage(100, 50, BufferedImage.TYPE_INT_RGB);

        //initing settings stuff
        mainSettingPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        functionTextField = new javax.swing.JTextField();
        compileButton = new javax.swing.JButton();
        helpButton = new javax.swing.JButton();
        helpButton.setText("?");
        helpButton.addActionListener(e->{
            String helpMessage="IMPORTANT: always use brackets, even for thee sum, a+b+c wont work, instead use (a+b)+c," +
                    " also in single argument operation always use +0, tan(c) wont work, instead use tan(c+0), im to lazy " +
                    "to fix it for til now\n\navailable functions and operations:\n\n";
            helpMessage+= MathematicalOperationExecute.getOperationsDescription();

            JTextArea textArea = new JTextArea(10, 30); // 10 строк, 30 символов в строке
            textArea.setText(helpMessage);
            textArea.setWrapStyleWord(true);
            textArea.setLineWrap(true);
            textArea.setEditable(false);

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            JOptionPane.showMessageDialog(null, scrollPane, "function syntax help", JOptionPane.INFORMATION_MESSAGE);
        });

        compileButton.addActionListener(e->{
            ParsingTree function= FunctionParser.parse(functionTextField.getText());
            iterativeRenderEngine.setFunction(function);
            iterativeRenderEngine.render(screen);
            iterativeRenderEngine.render(miniMap);
            repaint();
        });

        iterativeRenderSettingPanel = new javax.swing.JPanel();
        engineSelectionComboBox = new javax.swing.JComboBox<>();
        renderScreen = new javax.swing.JLabel(new ImageIcon(screen));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("insert function");

        compileButton.setText("compile  function");

        iterativeRenderSettingPanel = IterativeRenderSettingPanel.generate(screen, miniMap, iterativeRenderEngine);

        engineSelectionComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(ableRenderEngines));

        javax.swing.GroupLayout MainSettingPanelLayout = new javax.swing.GroupLayout(mainSettingPanel);
        mainSettingPanel.setLayout(MainSettingPanelLayout);
        MainSettingPanelLayout.setHorizontalGroup(
                MainSettingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(MainSettingPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(MainSettingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(iterativeRenderSettingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(functionTextField)
                                        .addComponent(engineSelectionComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(compileButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainSettingPanelLayout.createSequentialGroup()
                                                .addGap(0, 5, Short.MAX_VALUE)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(2, 2, 2)
                                                .addComponent(helpButton)))
                                .addContainerGap())
        );
        MainSettingPanelLayout.setVerticalGroup(
                MainSettingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(MainSettingPanelLayout.createSequentialGroup()
                                .addGroup(MainSettingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(helpButton)
                                        .addComponent(jLabel1))
                                .addGap(5, 5, 5)
                                .addComponent(functionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(compileButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(iterativeRenderSettingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(engineSelectionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(renderScreen, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mainSettingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(renderScreen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(mainSettingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );

        pack();
    }

    private javax.swing.JPanel iterativeRenderSettingPanel;
    private javax.swing.JPanel mainSettingPanel;
    private javax.swing.JLabel renderScreen;
    private javax.swing.JButton compileButton;
    private javax.swing.JComboBox<String> engineSelectionComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField functionTextField;
    private javax.swing.JButton helpButton;
}
