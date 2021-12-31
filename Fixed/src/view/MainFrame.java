package view;

import java.text.DateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 * * @author Sérgio Felipe Starke
 */
public class MainFrame extends javax.swing.JFrame {

    public MainFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktop = new javax.swing.JDesktopPane();
        lblUsuario = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        MenuBar = new javax.swing.JMenuBar();
        mnCadastro = new javax.swing.JMenu();
        mniCliente = new javax.swing.JMenuItem();
        mniOs = new javax.swing.JMenuItem();
        mniUsuario = new javax.swing.JMenuItem();
        mnRelatorio = new javax.swing.JMenu();
        mniRelCli = new javax.swing.JMenuItem();
        mniRelServ = new javax.swing.JMenuItem();
        mnAjuda = new javax.swing.JMenu();
        mniSobre = new javax.swing.JMenuItem();
        mnOpcoes = new javax.swing.JMenu();
        mniSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tela Principal");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jDesktopLayout = new javax.swing.GroupLayout(jDesktop);
        jDesktop.setLayout(jDesktopLayout);
        jDesktopLayout.setHorizontalGroup(
            jDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1114, Short.MAX_VALUE)
        );
        jDesktopLayout.setVerticalGroup(
            jDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 662, Short.MAX_VALUE)
        );

        getContentPane().add(jDesktop, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, 662));

        lblUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsuario.setText("Usuário");
        getContentPane().add(lblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 530, 209, 78));

        lblData.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        lblData.setForeground(new java.awt.Color(102, 102, 102));
        lblData.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblData.setText("Data");
        getContentPane().add(lblData, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 620, 148, 34));

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 10)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Assistência Técnica de Reparos");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 100, 160, -1));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Fixed.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1126, 6, 238, 662));

        mnCadastro.setText("Cadastro");

        mniCliente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        mniCliente.setText("Clientes");
        mnCadastro.add(mniCliente);

        mniOs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK));
        mniOs.setText("OS");
        mnCadastro.add(mniOs);

        mniUsuario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK));
        mniUsuario.setText("Usuários");
        mniUsuario.setEnabled(false);
        mniUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniUsuarioActionPerformed(evt);
            }
        });
        mnCadastro.add(mniUsuario);

        MenuBar.add(mnCadastro);

        mnRelatorio.setText("Relatório");
        mnRelatorio.setEnabled(false);

        mniRelCli.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK));
        mniRelCli.setText("Clientes");
        mnRelatorio.add(mniRelCli);

        mniRelServ.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        mniRelServ.setText("Serviços");
        mnRelatorio.add(mniRelServ);

        MenuBar.add(mnRelatorio);

        mnAjuda.setText("Ajuda");

        mniSobre.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.ALT_MASK));
        mniSobre.setText("Sobre");
        mniSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniSobreActionPerformed(evt);
            }
        });
        mnAjuda.add(mniSobre);

        MenuBar.add(mnAjuda);

        mnOpcoes.setText("Opções");

        mniSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        mniSair.setText("Sair");
        mniSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniSairActionPerformed(evt);
            }
        });
        mnOpcoes.add(mniSair);

        MenuBar.add(mnOpcoes);

        setJMenuBar(MenuBar);

        setSize(new java.awt.Dimension(1386, 736));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void mniUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniUsuarioActionPerformed
        UserFrame uf = new UserFrame();
        uf.setVisible(true);
        jDesktop.add(uf);
    }//GEN-LAST:event_mniUsuarioActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
//Método que gera data corrente, ao abrir MainFrame. 
        Date date = new Date();
        DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
        lblData.setText(format.format(date));
    }//GEN-LAST:event_formWindowActivated

    private void mniSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniSairActionPerformed
//Método que exibe confirmação para encerramento"Fechar" o Sistema.        
        int sair = JOptionPane.showConfirmDialog(
    null, "Tem certeza que deseja sair?", "ATENÇÃO", JOptionPane.YES_NO_OPTION
        );
        
        if (sair == JOptionPane.YES_OPTION) {
            System.exit(sair);
        }
        System.out.println(sair);
    }//GEN-LAST:event_mniSairActionPerformed

    private void mniSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniSobreActionPerformed
        Sobre sobre = new Sobre();
        sobre.setVisible(true);
    }//GEN-LAST:event_mniSobreActionPerformed

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JDesktopPane jDesktop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblData;
    public static javax.swing.JLabel lblUsuario;
    private javax.swing.JMenu mnAjuda;
    private javax.swing.JMenu mnCadastro;
    private javax.swing.JMenu mnOpcoes;
    public static javax.swing.JMenu mnRelatorio;
    private javax.swing.JMenuItem mniCliente;
    private javax.swing.JMenuItem mniOs;
    private javax.swing.JMenuItem mniRelCli;
    private javax.swing.JMenuItem mniRelServ;
    private javax.swing.JMenuItem mniSair;
    private javax.swing.JMenuItem mniSobre;
    public static javax.swing.JMenuItem mniUsuario;
    // End of variables declaration//GEN-END:variables
}
