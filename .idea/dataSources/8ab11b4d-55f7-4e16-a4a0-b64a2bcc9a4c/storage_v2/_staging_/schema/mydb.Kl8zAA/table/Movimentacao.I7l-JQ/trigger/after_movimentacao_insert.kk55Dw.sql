create definer = root@`%` trigger after_movimentacao_insert
    after insert
    on HistoricoMovimentacao
    for each row
BEGIN
    DECLARE qtd_antes INT;

    -- Buscar quantidade de estoque antes da movimentação
    SELECT QtdEstoque INTO qtd_antes
    FROM Produto
    WHERE IdProduto = NEW.idProduto;

    -- Inserir no histórico
    INSERT INTO HistoricoMovimentacao (
        idProduto,
        NomeProduto,
        QtdAntes,
        QtdDepois,
        TipoMovimentacao,
        Quantidade
    )
    VALUES (
               NEW.idProduto,
               (SELECT NomeProduto FROM Produto WHERE IdProduto = NEW.idProduto),
               qtd_antes,
               NEW.QtdEstoque,
               CASE
                   WHEN NEW.EntradaDeProduto > 0 THEN 'Entrada'
                   ELSE 'Saída'
                   END,
               CASE
                   WHEN NEW.EntradaDeProduto > 0 THEN NEW.EntradaDeProduto
                   ELSE NEW.SaidaDeProduto
                   END
           );
END;

