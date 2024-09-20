package com.mycompany.loja;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Date;
import javafx.stage.Modality;

public class RegistrarVenda extends Application {
    
    private Loja loja;

    public RegistrarVenda() {
        this.loja = new Loja();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Registrar Venda");

        // Interface de usuário
        TextField idField = new TextField();
        idField.setPromptText("ID");
        
        TextField clienteIdField = new TextField();
        clienteIdField.setPromptText("ID do Cliente");

        TextField valorField = new TextField();
        valorField.setPromptText("Valor da Venda");

        CheckBox pagaCheckBox = new CheckBox("Venda Paga");

        Button registrarButton = new Button("Registrar Venda");
        registrarButton.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                int clienteId = Integer.parseInt(clienteIdField.getText());
                double valorVenda = Double.parseDouble(valorField.getText());
                boolean paga = pagaCheckBox.isSelected();

                loja.cadastrarVenda(id, clienteId, valorVenda, paga);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Venda Registrada");
                alert.setHeaderText(null);
                alert.setContentText("Venda registrada com sucesso!");
                alert.showAndWait();
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("Por favor, insira valores válidos.");
                alert.showAndWait();
            }
        });

        VBox vbox = new VBox(10, clienteIdField, valorField, pagaCheckBox, registrarButton);
        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

private static final VendaDAO vendaDAO = new VendaDAO(); // Instância do DAO para registrar a venda

    public static void display(ClienteDAO clienteDAO) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Registrar Venda");

        // Campos de entrada
        TextField idField = new TextField();
        idField.setPromptText("Id da venda");
        
        TextField clienteIdField = new TextField();
        clienteIdField.setPromptText("ID do Cliente");

        TextField valorField = new TextField();
        valorField.setPromptText("Valor da Venda");

        Button registrarButton = new Button("Registrar");
        registrarButton.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                int clienteId = Integer.parseInt(clienteIdField.getText());
                double valor = Double.parseDouble(valorField.getText());
                
                // Lógica para registrar a venda
                Venda venda = new Venda(id, clienteId, new java.util.Date(), true, valor); // Paga por padrão
                vendaDAO.adicionarVenda(venda);
                
                // Atualiza o saldo devedor do cliente
                Cliente cliente = clienteDAO.buscarClientePorId(clienteId);
                if (cliente != null) {
                    cliente.setSaldoDevedor(cliente.getSaldoDevedor() - valor);
                    clienteDAO.atualizarCliente(cliente);
                }

                // Fechar a janela após o registro
                window.close();
            } catch (NumberFormatException ex) {
                System.out.println("Por favor, insira valores válidos.");
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(clienteIdField, valorField, registrarButton);
        Scene scene = new Scene(layout, 300, 200);
        window.setScene(scene);
        window.showAndWait();
    }
}

