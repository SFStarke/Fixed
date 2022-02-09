/*
 * Classe Login, responsável por restringir acesso à determinados dados e comandos.
 */
package view;

import java.sql.*;
import dao.Conexao;
import javax.swing.JOptionPane;

/**
 * @author Sérgio Felipe Starke
 */
public class Login extends javax.swing.JFrame {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void acessar() {
        MainFrame mainframe = new MainFrame();//Instância do objeto JFrame"MainFrame".

        try {
            ps = conn.prepareStatement(
                    "select* from usuarios where login= ? and senha= ?;"
            );
            ps.setString(1, txtLogin.getText());
            String senha = new String(pwdSenha.getPassword());
            ps.setString(2, senha);
            rs = ps.executeQuery();
//Condicional de Compatibilidade e Permição de acesso.
            if (rs.next()) {
                String acesso = rs.getString(6);
//Condicional [livre ou restrito] Consulta D.B/fixed/usuarios/acesso...
                if (acesso.equals("livre")) {
                    mainframe.setVisible(true);
                    MainFrame.mniUsuario.setEnabled(true);
                    MainFrame.mnRelatorio.setEnabled(true);
                    MainFrame.lblUsuario.setText(
"<html><font size=5><font color=green>"+rs.getString("nome")+"</font color></font size><br>"+
"Usuário com acesso <strong><font color=green>" + rs.getString(6)+"</font color>.</html>"
                    );
                } else {
                    mainframe.setVisible(true);
                    MainFrame.lblUsuario.setText(
"<html><font size=5><font color=orange>"+rs.getString(2)+"</font color></font size>.<br>"+
   "Usuário com acesso <strong><font color=orange>" + rs.getString(6)+"</font color>.</html>"
                    );
                }
                this.dispose();//Torna Frame do Login indisponível após confirmação.
                conn.close();//Encerrar conexão com database.
            } else {
                JOptionPane.showMessageDialog(null, "Login e ou Senha inválido(s)");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public Login() {
        initComponents();
        setLocationRelativeTo(null);
        conn = Conexao.connection();
//Distinção da frame quando conexão bem sucedida ou não.       
        if (conn != null) {
            lblStatus.setText("<html>Status da Conexão:<br><strong><font color= green>"
    + "Bem Sucedida...</strong><br>Preencha os campos<br>e confirme no botão ao lado</html>");
            btnOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Conn.Ok.png")));
        } else {
            lblStatus.setText("<html>Status da Conexão:<br><strong><font color= red>"
                    + "Indisponível</strong>.</html>");
            btnOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Conn.Fail.png")));
            txtLogin.setEnabled(false);
            pwdSenha.setEnabled(false);
            btnOk.setEnabled(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtLogin = new javax.swing.JTextField();
        pwdSenha = new javax.swing.JPasswordField();
        btnOk = new javax.swing.JButton();
        lblStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Fixed Assistência. Login");
        setResizable(false);

        jLabel1.setText("Login");

        jLabel2.setText("Senha");

        btnOk.setToolTipText("Confirme após preenchimento dos campos");
        btnOk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStatus.setText("Status da Conexão.");
        lblStatus.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pwdSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                            .addComponent(txtLogin))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(pwdSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        setBounds(0, 0, 301, 231);
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        acessar();
        txtLogin.setText("");
        pwdSenha.setText("");
    }//GEN-LAST:event_btnOkActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JPasswordField pwdSenha;
    private javax.swing.JTextField txtLogin;
    // End of variables declaration//GEN-END:variables
}
