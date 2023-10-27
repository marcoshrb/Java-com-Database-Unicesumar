package com.mycompany.atividademapa_programacao2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class Cadastro extends JFrame implements ActionListener{
        
        private JLabel rotulonome, rotulologin, rotulosenha, rotuloemail;  
        private JTextField nome, login, senha, email;  
        private JButton botaoSalvar;
    
        public void TelaCadastro(){
        
        setTitle("Cadastrar Novo Usuário");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        
        rotulonome = new JLabel("Nome:");
        rotulonome.setBounds(10, 50, 90, 30);
        this.add(rotulonome);
        
        nome = new JTextField();
        nome.setBounds(100, 50, 300, 30);
        this.add(nome);
        
        rotulologin = new JLabel("Login:");
        rotulologin.setBounds(10, 100, 90, 30);
        this.add(rotulologin);
        
        login = new JTextField();
        login.setBounds(100, 100, 300, 30);
        this.add(login);
        
        rotulosenha = new JLabel("Senha:");
        rotulosenha.setBounds(10, 150, 90, 30);
        this.add(rotulosenha);
        
        senha = new JTextField();
        senha.setBounds(100, 150, 300, 30);
        this.add(senha);
        
        rotuloemail = new JLabel("Email:");
        rotuloemail.setBounds(10, 200, 90, 30);
        this.add(rotuloemail);
        
        email = new JTextField();
        email.setBounds(100, 200, 300, 30);
        this.add(email);
        
        botaoSalvar = new JButton("Salvar");
        botaoSalvar.setBounds(100,250,200,30);
        this.add(botaoSalvar);   
        
        botaoSalvar.addActionListener(this);
    }
      
    @Override
    public void actionPerformed(ActionEvent e){
            try {
                String Nome = nome.getText();
                String Senha = senha.getText();
                String Login = login.getText();
                String Email = email.getText();
                
                Connection con = DatabaseService.getConnection();
                
                String sql = "insert into usuario(nome, login, senha, email) values (?,?,?,?)";
                
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, Nome);
                    
                    ps.setString(2, Login);
                    ps.setString(3, Senha);
                    ps.setString(4, Email);
                    
                    ps.execute();
                }
                
                dispose();
            } catch (SQLException ex) {
                Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
