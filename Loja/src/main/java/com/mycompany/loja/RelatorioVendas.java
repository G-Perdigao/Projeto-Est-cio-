package com.mycompany.loja;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class RelatorioVendas {
    private static VendaDAO vendaDAO = new VendaDAO(); // Instância do DAO para buscar vendas

    public static void display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Relatório de Vendas");

        VBox layout = new VBox(10);

        // Campos de data para definir o período do relatório
        DatePicker dataInicioPicker = new DatePicker();
        dataInicioPicker.setPromptText("Data de Início");

        DatePicker dataFimPicker = new DatePicker();
        dataFimPicker.setPromptText("Data de Fim");

        Button gerarRelatorioButton = new Button("Gerar Relatório");
        gerarRelatorioButton.setOnAction(e -> {
            LocalDate inicio = dataInicioPicker.getValue();
            LocalDate fim = dataFimPicker.getValue();

            if (inicio != null && fim != null) {
                // Converte LocalDate para java.util.Date
                Date dataInicio = java.sql.Date.valueOf(inicio);
                Date dataFim = java.sql.Date.valueOf(fim);

                // Busca as vendas no período
                List<Venda> vendas = vendaDAO.buscarVendasPorPeriodo(dataInicio, dataFim);

                // Exibe as vendas no layout
                for (Venda venda : vendas) {
                    Label label = new Label("ID Venda: " + venda.getId() + ", Cliente ID: " + venda.getClienteId() +
                            ", Data: " + venda.getDataVenda() + ", Valor: " + venda.getValor() + 
                            ", Paga: " + (venda.isPaga() ? "Sim" : "Não"));
                    layout.getChildren().add(label);
                }
            } else {
                Label erroLabel = new Label("Por favor, selecione as datas de início e fim.");
                layout.getChildren().add(erroLabel);
            }
        });

        Button fecharButton = new Button("Fechar");
        fecharButton.setOnAction(e -> window.close());

        layout.getChildren().addAll(dataInicioPicker, dataFimPicker, gerarRelatorioButton, fecharButton);

        Scene scene = new Scene(layout, 400, 300);
        window.setScene(scene);
        window.showAndWait();
    }
}
