insert into credencial (nome) values ('admin');
insert into autorizacao (nome) values ('admin');
insert into credencial_autorizacoes values (1,1);

insert into credencial (nome) values ('usuario');
insert into autorizacao (nome) values ('cadastra_usuario');
insert into autorizacao (nome) values ('atualiza_usuario');
insert into autorizacao (nome) values ('remove_usuario');
insert into autorizacao (nome) values ('consulta_usuario');
insert into credencial_autorizacoes values (2,2);
insert into credencial_autorizacoes values (2,3);
insert into credencial_autorizacoes values (2,4);
insert into credencial_autorizacoes values (2,5);

insert into credencial (nome) values ('categoria');
insert into autorizacao (nome) values ('cadastra_categoria');
insert into autorizacao (nome) values ('atualiza_categoria');
insert into autorizacao (nome) values ('remove_categoria');
insert into autorizacao (nome) values ('consulta_categoria');
insert into credencial_autorizacoes values (3,6);
insert into credencial_autorizacoes values (3,7);
insert into credencial_autorizacoes values (3,8);
insert into credencial_autorizacoes values (3,9);

insert into credencial (nome) values ('produto');
insert into autorizacao (nome) values ('cadastra_produto');
insert into autorizacao (nome) values ('atualiza_produto');
insert into autorizacao (nome) values ('remove_produto');
insert into autorizacao (nome) values ('consulta_produto');
insert into credencial_autorizacoes values (4,10);
insert into credencial_autorizacoes values (4,11);
insert into credencial_autorizacoes values (4,12);
insert into credencial_autorizacoes values (4,13);

insert into credencial (nome) values ('credencial');
insert into autorizacao (nome) values ('cadastra_credencial');
insert into autorizacao (nome) values ('atualiza_credencial');
insert into autorizacao (nome) values ('remove_credencial');
insert into autorizacao (nome) values ('consulta_credencial');
insert into credencial_autorizacoes values (5,14);
insert into credencial_autorizacoes values (5,15);
insert into credencial_autorizacoes values (5,16);
insert into credencial_autorizacoes values (5,17);

insert into credencial (nome) values ('autorizacao');
insert into autorizacao (nome) values ('cadastra_autorizacao');
insert into autorizacao (nome) values ('atualiza_autorizacao');
insert into autorizacao (nome) values ('remove_autorizacao');
insert into autorizacao (nome) values ('consulta_autorizacao');
insert into credencial_autorizacoes values (6,18);
insert into credencial_autorizacoes values (6,19);
insert into credencial_autorizacoes values (6,20);
insert into credencial_autorizacoes values (6,21);

insert into credencial (nome) values ('imagem');
insert into autorizacao (nome) values ('cadastra_imagem');
insert into autorizacao (nome) values ('atualiza_imagem');
insert into autorizacao (nome) values ('remove_imagem');
insert into autorizacao (nome) values ('consulta_imagem');
insert into credencial_autorizacoes values (7,22);
insert into credencial_autorizacoes values (7,23);
insert into credencial_autorizacoes values (7,24);
insert into credencial_autorizacoes values (7,25);

insert into credencial (nome) values ('arquivo');
insert into autorizacao (nome) values ('cadastra_arquivo');
insert into autorizacao (nome) values ('atualiza_arquivo');
insert into autorizacao (nome) values ('remove_arquivo');
insert into autorizacao (nome) values ('consulta_arquivo');
insert into credencial_autorizacoes values (8,26);
insert into credencial_autorizacoes values (8,27);
insert into credencial_autorizacoes values (8,28);
insert into credencial_autorizacoes values (8,29);

insert into credencial (nome) values ('pedido');
insert into autorizacao (nome) values ('cadastra_pedido');
insert into autorizacao (nome) values ('atualiza_pedido');
insert into autorizacao (nome) values ('remove_pedido');
insert into autorizacao (nome) values ('consulta_pedido');
insert into credencial_autorizacoes values (9,30);
insert into credencial_autorizacoes values (9,31);
insert into credencial_autorizacoes values (9,32);
insert into credencial_autorizacoes values (9,33);

insert into credencial (nome) values ('pagina');
insert into autorizacao (nome) values ('cadastra_pagina');
insert into autorizacao (nome) values ('atualiza_pagina');
insert into autorizacao (nome) values ('remove_pagina');
insert into autorizacao (nome) values ('consulta_pagina');
insert into credencial_autorizacoes values (10,34);
insert into credencial_autorizacoes values (10,35);
insert into credencial_autorizacoes values (10,36);
insert into credencial_autorizacoes values (10,37);

insert into usuario (username, password, first_name, last_name, email, enabled, locked) values ('kleber', '$2a$04$q8j56WcbSTMViRYqBOfGPOVbUKqSR0tdUoa9awU/cA/TRdPA/krRW', 'Kleber', 'Mota', 'kleber@mail.com', true, false);
insert into usuario_credenciais values (1,1);
insert into usuario_credenciais values (1,2);
insert into usuario_credenciais values (1,3);
insert into usuario_credenciais values (1,4);
insert into usuario_credenciais values (1,5);
insert into usuario_credenciais values (1,6);
insert into usuario_credenciais values (1,7);
insert into usuario_credenciais values (1,8);
insert into usuario_credenciais values (1,9);
insert into usuario_credenciais values (1,10);

insert into titulo (conteudo, idioma) values ('hum', 'pt-BR,Portuguese (Brazil)');
insert into titulo (conteudo, idioma) values ('one', 'en-US');
insert into titulo (conteudo, idioma) values ('uno', 'es-ES,Spanish (Spain)');

insert into resumo (conteudo, idioma) values ('exemplo', 'pt-BR,Portuguese (Brazil)');
insert into resumo (conteudo, idioma) values ('example', 'en-US,English (United States)');
insert into resumo (conteudo, idioma) values ('ejemplo', 'es-ES,Spanish (Spain)');

insert into texto (conteudo, idioma) values ('teste', 'pt-BR,Portuguese (Brazil)');
insert into texto (conteudo, idioma) values ('test', 'en-US,English (United States)');
insert into texto (conteudo, idioma) values ('teste', 'es-ES,Spanish (Spain)');

insert into categoria (id) values (1);

insert into categoria_nome values (1,1);
insert into categoria_nome values (1,2);
insert into categoria_nome values (1,3);
insert into categoria_resumo values (1,1);
insert into categoria_resumo values (1,2);
insert into categoria_resumo values (1,3);

insert into forum (id) values (1);

insert into produto (preco, categoria_id, forum_id) values (12, 1, 1);

insert into produto_nome values (1,1);
insert into produto_nome values (1,2);
insert into produto_nome values (1,3);
insert into produto_resumo values (1,1);
insert into produto_resumo values (1,2);
insert into produto_resumo values (1,3);
insert into produto_descricao values (1,1);
insert into produto_descricao values (1,2);
insert into produto_descricao values (1,3);
