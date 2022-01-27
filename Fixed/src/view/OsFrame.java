package view;

import java.sql.*;
import dao.Conexao;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sérgio Felipe Starke
 */
public class OsFrame extends javax.swing.JInternalFrame {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    DefaultTableModel tabcli = new DefaultTableModel();
    private String tipo;//Variavel p.Tipo de jRadioButton."rbOrcamento & rbOrdemServico"
    private String linkos = "";

    public OsFrame() {
        initComponents();
        conn = Conexao.connection();
        tabcli = (DefaultTableModel) jTabCli.getModel();
    }

    private void clean() {
        txtNomeCliente.setEditable(true);
        txtNomeCliente.setText(null);
        txtIdCliente.setText(null);
        txtEquipamento.setText(null);
        txtDefeito.setText(null);
        txtServico.setText(null);
        txtTecnico.setText(null);
        txtValor.setText("0.00");
        cbStatus.setSelectedIndex(0);//Reestabelece primeiro item. "jComboBox"
        ((DefaultTableModel) jTabCli.getModel()).setRowCount(0);//Limpa jTable.
        jTabCli.setVisible(true);
        txtOs.setText(null);
        txtData.setText(null);
        rbOrcamento.setSelected(true);
        btnCreate.setEnabled(true);
    }

    private void searchcli() {//Método de pesquisa objetiva. "pelo nome".
        try {
            ps = conn.prepareStatement(
"select id as ID, nome as Nome, fone as Fone from clientes where nome like ?"
            );
            ps.setString(1, txtNomeCliente.getText() + "%");
            rs = ps.executeQuery();
((DefaultTableModel) jTabCli.getModel()).setRowCount(0);//Limpa jTable para preenchimento atualizado.
            //Apresenta conteudo de pesquisa em jTable
            while (rs.next()) {
                  tabcli.insertRow(tabcli.getRowCount(), new Object[]{
           rs.getString("id"), rs.getString("nome"), rs.getString("fone")
                  });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar Cliente.\n" + e);
        }
    }

    private void setTxtField() {//Preenchimento dos campos txt. pela seleção do jTable
        int spot = jTabCli.getSelectedRow();//Estabelece posição"valor" do cliente delecionado.
        txtIdCliente.setText(jTabCli.getModel().getValueAt(spot, 0).toString());
    }

    private void create() {
        try {
            ps = conn.prepareStatement(
                    "insert into ordemservico values(default, default,?,?,?,?,?,?,?,?)"
            );
            ps.setString(1, tipo);
            ps.setString(2, cbStatus.getSelectedItem().toString());
            ps.setString(3, txtEquipamento.getText());
            ps.setString(4, txtDefeito.getText());
            ps.setString(5, txtServico.getText());
            ps.setString(6, txtTecnico.getText());
            ps.setString(7, txtValor.getText().replace(",", "."));//"replace" faz permitir, tanto o ponto como a virgula, no txtValor.
            ps.setString(8, txtIdCliente.getText());
            if ((txtIdCliente.getText().isEmpty()) || (txtEquipamento.getText().isEmpty())
|| (txtDefeito.getText().isEmpty()) || ("*Selecione Status" == cbStatus.getSelectedItem())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos.");
            } else {
                ps.executeUpdate();
                clean();
                JOptionPane.showMessageDialog(null, "Ordem de Serviço cadastrada.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar Ordem de Serviço.\n" + e);
        }
    }

    private void read() {
        String numeroOS = JOptionPane.showInputDialog("Numero da Ordem de Serviço");
        try {
            ps = conn.prepareStatement(
                    "select* from ordemservico where id = " + numeroOS
            );
            rs = ps.executeQuery();
            if (rs.next()) {
                txtOs.setText(rs.getString("id"));
                txtData.setText(rs.getString("data"));
                if ("Orçamento".equals(rs.getString("tipo"))) {
                    rbOrcamento.setSelected(true);
                    tipo = "Orçamento";
                } else {
                    rbOrdemServico.setSelected(true);
                    tipo = "Ordem de Serviço";
                }
                cbStatus.setSelectedItem(rs.getString("status"));
                txtEquipamento.setText(rs.getString("equipamento"));
                txtDefeito.setText(rs.getString("defeito"));
                txtServico.setText(rs.getString("servico"));
                txtTecnico.setText(rs.getString("tecnico"));
                txtValor.setText(rs.getString("valor"));
                txtIdCliente.setText(rs.getString("idcliente"));
                btnCreate.setEnabled(false);
                txtNomeCliente.setEditable(false);
                jTabCli.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Nº da O.S. não cadastrada.");
            }
        } catch (SQLSyntaxErrorException e) {
            JOptionPane.showMessageDialog(null, "Apenas Números.");
            // System.out.println(e);
        } catch (HeadlessException | SQLException e2) {
            JOptionPane.showMessageDialog(null, "Erro em Pesquisa.\n" + e2);
        }
    }

    private void update() {
        try {
            ps = conn.prepareStatement(
                    "update ordemservico set tipo=?, status=?, equipamento=?,"
                    + "defeito=?,servico=?, tecnico=?, valor=? where id=?"
            );
            ps.setString(1, tipo);
            ps.setString(2, (String) cbStatus.getSelectedItem());
            ps.setString(3, txtEquipamento.getText());
            ps.setString(4, txtDefeito.getText());
            ps.setString(5, txtServico.getText());
            ps.setString(6, txtTecnico.getText());
            ps.setString(7, txtValor.getText());
            ps.setString(8, txtOs.getText());
            if ((txtEquipamento.getText().isEmpty()) || (txtDefeito.getText().isEmpty())
                    || (cbStatus.getSelectedItem().equals("*Selecione Status"))) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos.");
            } else {
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null,
                        "Ordem de Serviço nº " + txtOs.getText() + " atualizado.>"
                );
                clean();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro em atualização\n." + e);
        }
    }

    private void delete() {
//Condicional questionativo de confirmação.
        int confirm = JOptionPane.showConfirmDialog(null,
"<html>Tem cesteza que deseja excluir?<br><font size=5>O.S. nº " + txtOs.getText() + ".</html>",
                "A T E N Ç Ã O", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                ps = conn.prepareStatement(
                        "delete from ordemservico where id = ?"
                );
                ps.setString(1, txtOs.getText());
                ps.executeUpdate();
                clean();
                JOptionPane.showMessageDialog(null,
                        "Ordem de Serviço nº '" + txtOs.getText() + "' excluida."
                );
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro em exclusão da O.S.\n." + e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtOs = new javax.swing.JTextField();
        txtData = new javax.swing.JTextField();
        rbOrcamento = new javax.swing.JRadioButton();
        rbOrdemServico = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbStatus = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        txtNomeCliente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtIdCliente = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabCli = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtEquipamento = new javax.swing.JTextField();
        txtServico = new javax.swing.JTextField();
        txtDefeito = new javax.swing.JTextField();
        txtTecnico = new javax.swing.JTextField();
        txtValor = new javax.swing.JTextField();
        btnCreate = new javax.swing.JButton();
        btnRead = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        btnClean = new javax.swing.JButton();
        btnLink = new javax.swing.JButton();

        setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Ordem de Serviço");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("nº OS");

        jLabel2.setText("Data");

        txtOs.setEditable(false);
        txtOs.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtOs.setEnabled(false);

        txtData.setEditable(false);
        txtData.setFont(new java.awt.Font("sansserif", 1, 10)); // NOI18N
        txtData.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtData.setEnabled(false);

        buttonGroup1.add(rbOrcamento);
        rbOrcamento.setFont(new java.awt.Font("sansserif", 0, 10)); // NOI18N
        rbOrcamento.setText("*Orçamento");
        rbOrcamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbOrcamentoActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbOrdemServico);
        rbOrdemServico.setFont(new java.awt.Font("sansserif", 0, 10)); // NOI18N
        rbOrdemServico.setText("*Ordem de Serviço");
        rbOrdemServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbOrdemServicoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(txtOs, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtData))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(57, 57, 57))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 15, Short.MAX_VALUE)
                        .addComponent(rbOrcamento)
                        .addGap(18, 18, 18)
                        .addComponent(rbOrdemServico)))
                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbOrdemServico)
                    .addComponent(rbOrcamento))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("*Equipamento");

        jLabel4.setFont(new java.awt.Font("sansserif", 0, 10)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 0, 0));
        jLabel4.setText("<html><strong><font size=6>*</strong> Campos Obrigatórios</html>");
        jLabel4.setToolTipText("");

        cbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "*Selecione Status", "Aguardando Aprovação", "Orçamento REPROVADO", "Aguardando Peças", "Retorno", "Abandonado pelo Cliente", "Em Manutenção", "Entrega OK", " " }));
        cbStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbStatusActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "<html>*Para criar Ordem de Serviço, é necesssário selecionar o Cliente e preencher todos os campos obrigatórios.</html>", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 0, 10), new java.awt.Color(0, 0, 153))); // NOI18N

        txtNomeCliente.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtNomeCliente.setToolTipText("Pesquisa Objetiva de Cliente");
        txtNomeCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtNomeCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNomeClienteKeyReleased(evt);
            }
        });

        jLabel5.setText("ID");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/read.png"))); // NOI18N

        txtIdCliente.setEditable(false);
        txtIdCliente.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtIdCliente.setEnabled(false);

        jTabCli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nome", "Fone"
            }
        ));
        jTabCli.setFocusable(false);
        jTabCli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabCliMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTabCli);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel6)
                        .addComponent(txtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
        );

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("*Defeito");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Serviço");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Técnico");

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("<html>Valor Total <strong><font color= blue><font size=4>R$</html>");

        txtEquipamento.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N

        txtDefeito.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N

        txtValor.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        txtValor.setForeground(new java.awt.Color(0, 0, 255));
        txtValor.setText("0.00");

        btnCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/create.png"))); // NOI18N
        btnCreate.setToolTipText("Adicionar");
        btnCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCreate.setPreferredSize(new java.awt.Dimension(60, 44));
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnRead.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/read.png"))); // NOI18N
        btnRead.setToolTipText("Pesquisar");
        btnRead.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReadActionPerformed(evt);
            }
        });

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/update.png"))); // NOI18N
        btnUpdate.setToolTipText("Atualisar");
        btnUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdate.setPreferredSize(new java.awt.Dimension(60, 44));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        btnDelete.setToolTipText("Excluir");
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/print_small.png"))); // NOI18N
        btnPrint.setToolTipText("Ordem de Serviço");
        btnPrint.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrint.setPreferredSize(new java.awt.Dimension(60, 44));

        jLabel12.setFont(new java.awt.Font("sansserif", 0, 10)); // NOI18N
        jLabel12.setText("<html>   Para gerar documento Ordem de Serviço, selecione botão pesquisar e digite o nº da O.S.</html>");

        btnClean.setText("Limpar Campos");
        btnClean.setToolTipText("Para nova adição de OS.");
        btnClean.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCleanActionPerformed(evt);
            }
        });

        btnLink.setText("Link nº OS");
        btnLink.setToolTipText("Link relacional entre nº O.S e Nome de Cliente");
        btnLink.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLinkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnClean)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLink))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtServico)
                                    .addComponent(txtEquipamento)
                                    .addComponent(txtDefeito, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(85, 85, 85)
                                    .addComponent(txtTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtValor))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(36, 36, 36)
                                    .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnRead, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(67, 67, 67)
                                    .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnClean)
                            .addComponent(btnLink))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEquipamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDefeito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnRead, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnCreate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(btnPrint, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jPanel2.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbStatusActionPerformed

    private void txtNomeClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeClienteKeyReleased
        searchcli();
    }//GEN-LAST:event_txtNomeClienteKeyReleased

    private void jTabCliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabCliMouseClicked
        setTxtField();
    }//GEN-LAST:event_jTabCliMouseClicked

    private void rbOrcamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbOrcamentoActionPerformed
        tipo = "Orçamento";
    }//GEN-LAST:event_rbOrcamentoActionPerformed

    private void rbOrdemServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbOrdemServicoActionPerformed
        tipo = "Ordem de Serviço";
    }//GEN-LAST:event_rbOrdemServicoActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        //Método que á exemplo do Construtor, inicia com rbOrcamento acionado.
        rbOrcamento.setSelected(true);
        tipo = "Orçamento";
    }//GEN-LAST:event_formInternalFrameOpened

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        create();
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCleanActionPerformed
        clean();
    }//GEN-LAST:event_btnCleanActionPerformed

    private void btnLinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLinkActionPerformed
       LinkOs link = new LinkOs();
        link.setVisible(true);
    }//GEN-LAST:event_btnLinkActionPerformed

    private void btnReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReadActionPerformed
        read();
    }//GEN-LAST:event_btnReadActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        update();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        delete();
    }//GEN-LAST:event_btnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClean;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnLink;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnRead;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTabCli;
    private javax.swing.JRadioButton rbOrcamento;
    private javax.swing.JRadioButton rbOrdemServico;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtDefeito;
    private javax.swing.JTextField txtEquipamento;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtNomeCliente;
    private javax.swing.JTextField txtOs;
    private javax.swing.JTextField txtServico;
    private javax.swing.JTextField txtTecnico;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
