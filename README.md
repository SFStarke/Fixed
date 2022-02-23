# Sistema para cadastro de: Clientes, Usuários e Ordem de Serviços
---
## Declarações do Desenvolvedor

O projeto não tem propósito no uso “profissional”, entretanto didático, para exercício e treino ao implementar algumas tecnologias e ferramentas. Este vem no uso à assistência técnica, com nome fantasia “Fixed”.

Particularmente, tomo este como alto grau de dificuldade, contudo, o escolhi para primeiro projeto no repositório do GitHub, por entender que no dia a dia do desenvolvedor, sempre que possível é apropriado superar seus limites, agregando assim mais experiência e conhecimento.

O Software foi desenvolvido em Java com a IDE NetBeans. Para gerar Relatórios, uso a ferramenta iReport da framework JasperReports. Na persistência de dados, MYSQL.
Este projeto foi baseado nas aulas do youtuber José de Assis [Link das aulas](https://www.youtube.com/watch?v=eA4WjjkzK3c&list=PLbEOwbQR9lqxsTusvu8wfkUECrmcV81MU), entretanto, implementei upgrades quanto a sua funcionalidade e intuitividade.

Neste repositório há em anexo, o “Guia de Uso” para um panorama do funcionamento operacional do sistema, o dump do seu banco de dados e o backup do iReport.

## Propósito do Sistema

* Gerar cadastro para: clientes, ordem de serviços “OS”, usuários e exibir relatórios de clientes e OS.
* Haverá dois tipos de usuários, o de acesso livre e outro restrito.
* As informações da OS e dos usuários, devem ser ocultas ao usuário de acesso restrito, contudo, são liberadas ao usuário de acesso livre.
 
## Requisitos necessários para o sistema

* Java JRE “Java Runtime Environment” ou Java JDK “Java Development Kit” (v.8.2).
* Persistência de dados.
* Framework Jasper iReport (v.5.6.0.).

---

**IMPORTANTE**: Se faz necessário a atualização referencial, do local onde foi salvo seu backup do iReport nos respectivos métodos das classes onde é usada a ferramenta. Caso tenha dificuldades, o sistema foi desenvolvido para gerar as mesmas informações sem a obrigatoriedade desta ferramenta, contudo, haverá restrição para a impressão dos relatórios.
