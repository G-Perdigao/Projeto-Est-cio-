package com.mycompany.loja;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class MainLoja extends Application {

    @Override
    public void start(Stage primaryStage) {
        
        primaryStage.setTitle("Sistema de Loja");

        
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));

        // Criação dos botões da interface
        Button cadastrarClienteButton = new Button("Cadastrar Cliente");
        Button registrarVendaButton = new Button("Registrar Venda");
        Button consultarEstoqueButton = new Button("Consultar Estoque");
        Button relatorioButton = new Button("Gerar Relatório de Vendas");
        Button cadastrarProdutoButton = new Button("Cadastrar Produto");

        // Adicionar os botões ao layout VBox
        vbox.getChildren().addAll(cadastrarClienteButton, registrarVendaButton, consultarEstoqueButton, relatorioButton, cadastrarProdutoButton);

        // Crie uma instância de ClienteDAO
        ClienteDAO clienteDAO = new ClienteDAO();

        // Ações dos botões para abrir as telas correspondentes
        cadastrarClienteButton.setOnAction(e -> CadastroCliente.display());
        registrarVendaButton.setOnAction(e -> RegistrarVenda.display(clienteDAO)); 
        consultarEstoqueButton.setOnAction(e -> ConsultarEstoque.display());
        relatorioButton.setOnAction(e -> RelatorioVendas.display());
        cadastrarProdutoButton.setOnAction(e -> CadastroProduto.display());

        // Configurar a cena (Scene) e definir a janela principal (Stage)
        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Método principal para rodar o programa
    public static void main(String[] args) {
        launch(args); // Inicializa o JavaFX
    }
}