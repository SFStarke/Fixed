-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 23-Fev-2022 às 20:56
-- Versão do servidor: 10.4.19-MariaDB
-- versão do PHP: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `fixed`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `clientes`
--

CREATE TABLE `clientes` (
  `id` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `logradouro` text DEFAULT NULL,
  `fone` varchar(50) NOT NULL,
  `email` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `clientes`
--

INSERT INTO `clientes` (`id`, `nome`, `logradouro`, `fone`, `email`) VALUES
(1, 'Sérgio Felipe Starke', 'rua Regente Feijó nº 198 bairo VilaNova, Blumenau, SC', '47-99917 93 29', 'sergiostarke@gmail.com'),
(2, 'Amanda Paixão', 'rua Amorusso nº123, Centro', '22-22222 22 22', 'Amy@outlook.com'),
(3, 'Gisela Amarau', 'rua Gilmadesh nº 123, Vila Gis', '33-33333 33 33', 'Gi@gmail.com'),
(4, 'Beatriz Cristina Bublitz', 'rua Desconhecida nº321, Velha', '47-44444 44 44', 'Bia@outlook.com'),
(5, 'Gilberto Starke', 'rua Adventur nº 198, Itoupava Seca', '47-55555 55 55', 'gilberto@gmail.com'),
(6, 'Sandro Altivez', 'rua Sardus Ribeiro nº123, Santana', '51-66666 66 66', 'San@outlook.com'),
(7, 'Barbara Bragança', 'rua Bradesco nº123, bairo São Bento', '62-77777 77 77', 'Barby@outlook.com'),
(8, 'Amauri Slva', 'rua Ardinos nº123, bairo Centro', '88-88888 88 88', 'silva@outlook.com');

-- --------------------------------------------------------

--
-- Estrutura da tabela `ordemservico`
--

CREATE TABLE `ordemservico` (
  `id` int(11) NOT NULL,
  `data` timestamp NOT NULL DEFAULT current_timestamp(),
  `tipo` varchar(20) NOT NULL,
  `status` varchar(25) NOT NULL,
  `equipamento` varchar(150) NOT NULL,
  `defeito` varchar(150) NOT NULL,
  `servico` varchar(150) DEFAULT NULL,
  `tecnico` varchar(60) DEFAULT NULL,
  `valor` decimal(10,2) DEFAULT NULL,
  `idcliente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `ordemservico`
--

INSERT INTO `ordemservico` (`id`, `data`, `tipo`, `status`, `equipamento`, `defeito`, `servico`, `tecnico`, `valor`, `idcliente`) VALUES
(1, '2022-02-18 16:52:04', 'Orçamento', 'Aguardando Peças', 'Samsung Galaxy Tab S6 Lite', 'Distorção de luz em pixels', 'Troca de Aparelho \"Garantia\"', 'Alberto.', '0.00', 1),
(2, '2022-02-18 18:38:30', 'Orçamento', 'Retorno', 'Tv Samsung 32p', 'Muito boa', 'nenhum', 'Sergio', '0.00', 3),
(3, '2022-02-18 19:30:23', 'Orçamento', 'Abandonado pelo Cliente', 'tes', 'tes', '', '', '0.00', 3),
(4, '2022-02-23 19:29:58', 'Orçamento', 'Aguardando Aprovação', 'Notebook Dell', 'Perda repentina de energia', 'Troca de bateria', 'Norberto', '198.00', 4),
(5, '2022-02-23 19:33:13', 'Ordem de Serviço', 'Retorno', 'Celular Philco', 'Conexão usb', 'Limpeza', 'Celestina', '50.00', 2),
(6, '2022-02-23 19:36:35', 'Ordem de Serviço', 'Aguardando Peças', 'Moto Suzuki yes', 'Corre muito rapido', 'Instalar redutor de velocidade', 'Soander', '547.00', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `fone` varchar(30) DEFAULT NULL,
  `login` varchar(15) NOT NULL,
  `senha` varchar(15) NOT NULL,
  `acesso` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `usuarios`
--

INSERT INTO `usuarios` (`id`, `nome`, `fone`, `login`, `senha`, `acesso`) VALUES
(1, 'Primario', '000000000', 'a', '', 'livre'),
(2, 'Comum', '111111111', 'b', '', 'restrito'),
(3, 'Sérgio Felipe Starke', '47-99917 93 29', 'as', 'as', 'livre');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `ordemservico`
--
ALTER TABLE `ordemservico`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idcliente` (`idcliente`);

--
-- Índices para tabela `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `login` (`login`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de tabela `ordemservico`
--
ALTER TABLE `ordemservico`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de tabela `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `ordemservico`
--
ALTER TABLE `ordemservico`
  ADD CONSTRAINT `ordemservico_ibfk_1` FOREIGN KEY (`idcliente`) REFERENCES `clientes` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
