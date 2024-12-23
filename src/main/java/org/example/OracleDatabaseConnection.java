package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class OracleDatabaseConnection {
    public static Connection getConnection() throws SQLException {
        // URL de conexão com o banco Oracle
        String url = "jdbc:oracle:thin:@localhost:1521:XE";  // Use a porta correta e o SID ou Service Name

        // Informações do usuário e senha
        String username = "system";   // Substitua com o nome do usuário que você criou
        String password = "#EDCvfr4";          // Substitua com a senha correspondente

        // Conectar ao banco de dados Oracle
        return DriverManager.getConnection(url, username, password);
    }

    public static void main(String[] args) {
        try {
            Connection connection = getConnection();
            if (connection != null) {
                Scanner scan= new Scanner(System.in);
                System.out.println("Digite o nome do aluno: ");
                String nome=scan.nextLine();
                System.out.println("Digite a idade do aluno: ");
                int idade =scan.nextInt();
                System.out.println("Digite a matricula do aluno: ");
                scan.nextLine();
                String matricula=scan.nextLine();
                System.out.println("Digite o curso do aluno: ");
                String curso=scan.nextLine();

                String sql="INSERT INTO Aluno (nome, idade, matricula, curso) VALUES(?,?,?,?)";
                try(PreparedStatement pstmt= connection.prepareStatement(sql)){
                    pstmt.setString(1,nome);
                    pstmt.setInt(2,idade);
                    pstmt.setString(3,matricula);
                    pstmt.setString(4,curso);

                    int rowsAffected=pstmt.executeUpdate();
                    if (rowsAffected>0){
                        System.out.println("Aluno cadastrado com sucesso!!");
                    }else {
                        System.out.println("Erro ao cadastrar o aluno");
                    }

                }


                System.out.println("Conexão bem-sucedida ao Oracle!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


