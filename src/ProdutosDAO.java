/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public int cadastrarProduto (ProdutosDTO produto){
        
        
        conn = new conectaDAO().connectDB();
        
        int status;
        try {
            prep = conn.prepareStatement("INSERT INTO produtos (nome, valor, status) VALUES (?,?,?)");
            
            prep.setString(1,produto.getNome());
            prep.setInt(2,produto.getValor());
            prep.setString(3,produto.getStatus());
            
            status = prep.executeUpdate();
            return status; //retornar 1
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        conn = new conectaDAO().connectDB();
        
        try {
        String sqlFiltro = "select * from produtos";
             
             PreparedStatement st = conn.prepareStatement(sqlFiltro);
             
             ResultSet rs = st.executeQuery();
             
             while (rs.next()) {
                 ProdutosDTO produtoEncontrado = new ProdutosDTO();
                 produtoEncontrado.setId(rs.getInt("id"));
                 produtoEncontrado.setNome(rs.getString("nome"));
                 produtoEncontrado.setValor(rs.getInt("valor"));
                 produtoEncontrado.setStatus(rs.getString("status"));
                 listagem.add(produtoEncontrado);
             }
             
             return listagem;
         } catch (SQLException ex) {
             System.out.println("Erro ao conectar: " + ex.getMessage());
            return null;
         }
        
    }
    
    public void venderProduto(Integer id) {
    conn = new conectaDAO().connectDB();
    
    try { 
        String query = "UPDATE produtos SET status = 'Vendido' WHERE id = ?"; 
        prep = conn.prepareStatement(query);
        prep.setInt(1, id); prep.executeUpdate(); 
    } catch (SQLException e) 
    { e.printStackTrace(); } 
}
    
    
        
}

