create
    definer = root@`%` procedure CadastrarProdutos(IN NomeProduto varchar(100), IN Descricao text,
                                                   IN QtdEstoque int unsigned, IN PrecoDeCompra decimal(10, 2),
                                                   IN PrecoDeVenda decimal(10, 2))
BEGIN
    INSERT INTO Produto (NomeProduto, Descricao, QtdEstoque, PrecoDeCompra, PrecoDeVenda)
    VALUES (NomeProduto, Descricao, QtdEstoque, PrecoDeCompra, PrecoDeVenda);
END;

