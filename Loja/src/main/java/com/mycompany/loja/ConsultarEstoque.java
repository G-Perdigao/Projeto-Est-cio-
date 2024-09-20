package com.mycompany.loja;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class ConsultarEstoque {
    private static EstoqueDAO estoqueDAO = new EstoqueDAO(); // Instância do DAO para consultar o estoque

    public static void display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Consultar Estoque");

        VBox layout = new VBox(10);

        // Busca todos os produtos do estoque
        List<Produto> produtos = estoqueDAO.listarTodosProdutos();

        // Exibe cada produto no estoque
        for (Produto produto : produtos) {
            Label label = new Label("ID: " + produto.getId() + ", Nome: " + produto.getNome() + 
                                    ", Quantidade: " + produto.getQuantidade() + ", Preço: " + produto.getPreco());
            layout.getChildren().add(label);
        }

        Button fecharButton = new Button("Fechar");
        fecharButton.setOnAction(e -> window.close());

        layout.getChildren().add(fecharButton);
        
        Scene scene = new Scene(layout, 400, 300);
        window.setScene(scene);
        window.showAndWait();
    }
}
