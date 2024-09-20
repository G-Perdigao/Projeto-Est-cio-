package com.mycompany.loja;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class CadastroCliente {
    
    public static void display() {
        Stage window = new Stage();
        window.setTitle("Cadastro de Cliente");

        // Campos para inserir os dados do cliente
        TextField idField = new TextField();
        idField.setPromptText("ID do Cliente");
        
        TextField nomeField = new TextField();
        nomeField.setPromptText("Nome do Cliente");
        
        TextField saldoDevedorField = new TextField();
        saldoDevedorField.setPromptText("Saldo Devedor");

        Button cadastrarButton = new Button("Cadastrar Cliente");

        // Ação ao clicar no botão de cadastro
        cadastrarButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());  // Obtém o ID como inteiro
                String nome = nomeField.getText();             // Obtém o nome
                double saldoDevedor = Double.parseDouble(saldoDevedorField.getText()); // Obtém o saldo devedor
                
                // Como não temos vendas associadas inicialmente, podemos usar uma lista vazia
                List<Venda> vendas = new ArrayList<>();
                
                // Criação de um novo cliente com base no construtor
                Cliente cliente = new Cliente(id, nome, vendas, saldoDevedor);
                
                // Aqui você pode adicionar o código para salvar o cliente no banco de dados, etc.
                System.out.println("Cliente cadastrado: " + cliente.getNome());
                window.close();  // Fecha a janela após o cadastro
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(idField, nomeField, saldoDevedorField, cadastrarButton);

        Scene scene = new Scene(layout, 300, 200);
        window.setScene(scene);
        window.show();
    }
}
