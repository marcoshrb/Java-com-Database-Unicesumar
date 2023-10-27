package com.mycompany.atividademapa_programacao2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class AtividadeMapa_Programacao2 extends JFrame implements ActionListener{
    
    private JLabel rotulologin, rotulosenha;  
    private JTextField login, senha;  
    private JButton botaoEntrar, botaoCadastrar;
    
    private void TelaLogin(){
        
        setTitle("Login");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        
        rotulologin = new JLabel("Login:");
        rotulologin.setBounds(10, 50, 90, 30);
        this.add(rotulologin);
        
        login = new JTextField();
        login.setBounds(100, 50, 300, 30);
        this.add(login);
        
        rotulosenha = new JLabel("Senha:");
        rotulosenha.setBounds(10, 100, 90, 30);
        this.add(rotulosenha);
        
        senha = new JTextField();
        senha.setBounds(100, 100, 300, 30);
        this.add(senha);
        
        botaoEntrar = new JButton("Entrar");
        botaoEntrar.setBounds(100,150,200,30);
        this.add(botaoEntrar);
        
        botaoCadastrar = new JButton("Cadastrar Novo Usuario");
        botaoCadastrar.setBounds(100,200,200,30);
        this.add(botaoCadastrar);
        
        botaoEntrar.addActionListener(this);
        botaoCadastrar.addActionListener(this);
    }
    
    @Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == botaoEntrar) {
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        
        String Login = login.getText();
        String Senha = senha.getText();
        String SqlLogin = null;
        String SqlSenha = null;

        try {
            
            con = DatabaseService.getConnection();           
            ps = con.prepareStatement("SELECT id, nome, login, senha, email from usuario where login = ? and senha = ?");
            ps.setString(1, Login);
            ps.setString(2, Senha);                 
            rs = ps.executeQuery();
            
            while (rs.next()) {
                SqlLogin = rs.getString("nome");
                SqlSenha = rs.getString("senha");           
            }
            
            if ((Login == null ? SqlLogin == null : Login.equals(SqlLogin)) && (Senha == null ? SqlSenha == null : Senha.equals(SqlSenha))) {
                    JOptionPane.showMessageDialog(null, "Acesso Autorizado");
                } else {
                    JOptionPane.showMessageDialog(null, "Acesso Negado", "Credenciais não encontradas",
                    JOptionPane.ERROR_MESSAGE);
                }
            
        } catch (SQLException ex) {
            Logger.getLogger(AtividadeMapa_Programacao2.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
                if(rs != null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(AtividadeMapa_Programacao2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(ps != null){
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(AtividadeMapa_Programacao2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(con != null){
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(AtividadeMapa_Programacao2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            
        }      
        
    } else if (e.getSource() == botaoCadastrar) {
        Cadastro cadastro = new Cadastro();
        cadastro.TelaCadastro();
    }
}
    
    public static void main(String[] args) {
        
        AtividadeMapa_Programacao2 login  = new AtividadeMapa_Programacao2();
        
        login.TelaLogin();
    }   
}
