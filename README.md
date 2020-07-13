# CadastroPedidos

Cadastro de pedidos utilizando spring boot

### 1. Scripts Banco De Dados


Scripts para povoar o banco de dados com dados de produtos e de clientes.


> INSERT INTO produto (nome, quantidade_estoque, valor) VALUES ('Camiseta', 100, 30); 

> INSERT INTO produto (nome, quantidade_estoque, valor) VALUES ('Camiseta Regata', 80, 30);

> INSERT INTO produto (nome, quantidade_estoque, valor) VALUES ('Camiseta Polo', 100, 45);

> INSERT INTO produto (nome, quantidade_estoque, valor) VALUES ('Camiseta Manga Longa', 50, 35);

> INSERT INTO produto (nome, quantidade_estoque, valor) VALUES ('Camisa', 120, 180);

> INSERT INTO produto (nome, quantidade_estoque, valor) VALUES ('Suéter', 25, 160);

> INSERT INTO produto (nome, quantidade_estoque, valor) VALUES ('Calça', 80, 120);

> INSERT INTO produto (nome, quantidade_estoque, valor) VALUES ('Bermuda', 60, 80);

> INSERT INTO produto (nome, quantidade_estoque, valor) VALUES ('Shorts', 60, 70);

> INSERT INTO produto (nome, quantidade_estoque, valor) VALUES ('Meia', 100, 30);

> INSERT INTO produto (nome, quantidade_estoque, valor) VALUES ('Gravata', 30, 150);

> INSERT INTO produto (nome, quantidade_estoque, valor) VALUES ('Cinto', 60, 90);

> INSERT INTO produto (nome, quantidade_estoque, valor) VALUES ('Carteira', 20,110);

> INSERT INTO CLIENTE(cpf, nome) VALUES ('111.111.111-11', 'Ana Paula');

> INSERT INTO CLIENTE(cpf, nome) VALUES ('222.222.222-22', 'Carlos Augusto');

> INSERT INTO CLIENTE(cpf, nome) VALUES ('333.333.333-33', 'José Valadão');


### 2. Cadastrar um pedido


* Método: POST
* Url: http://localhost:8080/pedido/
* Exemplo Corpo:
```
{

 "itensPedido": [
 
 {
 
 "produto": {
 
 "id": 1
 
 },
 
 "quantidade": 3
 
 },
 
 {
 
 "produto": {
 
 "id": 3
 
 },
 
 "quantidade": 2
 
 }
 
 ],
 
 "cliente": {
 
 "id": 1
 
 },
 
 "cancelado": false,
 
 "total": 150.

}
```

### 3. Buscar Todos Pedidos

* Método: GET
* Url: http://localhost:8080/pedido/
* Exemplo Corpo: (vazio)
* Retorno: Um array de pedido em json;

### 4. Buscar Por Id

* Método: GET
* Url: http://localhost:8080/pedido/id
* Exemplo Corpo: (vazio)
* Retorno: Um pedido em json;

### 5. Buscar Por Cancelados

* Método: GET
* Url: http://localhost:8080/pedido/cancelados
* Exemplo Corpo: (vazio)
* Retorno: Um array de pedido em json;

### 6. Buscar Pedidos Realizados Em Um Período

* Método: GET
* Url:
http://localhost:8080/pedido/datebetween/{dataInicio}/{dataFim}
* Exemplo URL: http://localhost:8080/pedido/datebetween/2020-06-12T11:09:00.692/2020-08-12T11:09:00.692
* Retorno: Um array de pedido em json;
* **OBS: os valores de dataInicio e dataFim devem ser LocalDateTime
como string, por exemplo : 2020-08-12T11:09:00.692**

### 7. Alterar um pedido

* Método: PUT
* Url: http://localhost:8080/pedido/
* Exemplo Corpo:

```
 {
 "id": 1,
 "dataPedido": "2020-07-12T11:09:00.692",
 "itensPedido": [
 {
 "id": 1,
 "produto": {
 "id": 1
 },
 "quantidade": 5,
 "subTotal": 90.0
 },
 {
 "id": 2,
 "produto": {
 "id": 3
 },
 "quantidade": 2,
 "subTotal": 90.0
 }
 ],
 "cliente": {
 "id": 1,
 "nome": "Ana Paula",
 "cpf": "111.111.111-11"
 },
 "cancelado": true,
 "total": 180.0
 }
 ```
 
* Retorno: Status 200 OK, com a mensagem Pedido atualizado com
sucesso), ou mensagem de erro caso o array não contenha um id;

### 8. Cancelar Um Pedido

* Método: PUT
* Url: http://localhost:8080/pedido/cancelar/{id}
* EX Url: http://localhost:8080/pedido/cancelar/1
* Exemplo Corpo: (vazio)
* Retorno: (vazio)

### 9. Adicionar um item ao pedido

* Método: POST
* Url: http://localhost:8080/pedido/{id}/add-item
* EX Url: http://localhost:8080/pedido/1/add-item
* Exemplo Corpo:

```
{
 "produto": {
 "id": 1
 },
 "quantidade": 5
}
```

* Retorno: mensagem + status HTTP

### 10. Remove um item do pedido

* Método: DELETE
* Url: http://localhost:8080/pedido/{id}/remover-item/{idItem}
* EX Url: http://localhost:8080/pedido/1/remover-item/1
* Exemplo Corpo: (vazio)
* Retorno: mensagem + status HTTP
