package com.mycompany.loja;import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CadastroProduto {

    public static void display() {
        Stage window = new Stage();
        window.setTitle("Cadastro de Produto");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        // Campos de texto para os dados do produto
        TextField nomeField = new TextField();
        nomeField.setPromptText("Nome do Produto");

        TextField precoField = new TextField();
        precoField.setPromptText("Preço do Produto");

        TextField quantidadeField = new TextField();
        quantidadeField.setPromptText("Quantidade em Estoque");

        // Botão de cadastro
        Button cadastrarButton = new Button("Cadastrar Produto");
        cadastrarButton.setOnAction(e -> {
            try {
                String nome = nomeField.getText();
                double preco = Double.parseDouble(precoField.getText());
                int quantidade = Integer.parseInt(quantidadeField.getText());

                Produto produto = new Produto(0, nome, preco, quantidade);

                // Inserir o produto no banco de dados
                ProdutoDAO produtoDAO = new ProdutoDAO();
                produtoDAO.inserirProduto(produto);

                System.out.println("Produto cadastrado com sucesso!");
                window.close(); // Fecha a janela após o cadastro

            } catch (NumberFormatException ex) {
                System.out.println("Erro ao inserir o produto: " + ex.getMessage());
            }
        });

        layout.getChildren().addAll(nomeField, precoField, quantidadeField, cadastrarButton);

        Scene scene = new Scene(layout, 300, 200);
        window.setScene(scene);
        window.showAndWait();
    }
}
