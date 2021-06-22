function validString(value, maxSize) { return value.length > 0 && value.length <= maxSize; }
function validNumber(value) { return Number(value) == value && value != '' && value >= 0; }
function validInteger(value) { return validNumber(value) && value % 1 === 0; }

function verificarCliente() {
	var cpf = document.getElementsByName("cpfCliente")[0];
	var nome = document.getElementsByName("nomeCliente")[0];
	var cep = document.getElementsByName("cepCliente")[0];
	
	var stateA = cpf.value.length == 11 && validNumber(cpf.value);
	var stateB = validString(nome.value, 50);
	var stateC = cep.value.length == 8 && validNumber(cep.value);
	var info = document.getElementById("infoErro");
	
	info.innerText = "";
	if (!stateA) info.innerText += ">> O CPF é composto somente por 11 caracteres numéricos, sem máscara!\n";
	if (!stateB) info.innerText += ">> O nome precisa ter no mínimo 1 e no máximo 50 caracteres!\n";
	if (!stateC) info.innerText += ">> O CEP é composto por 8 caracteres numéricos, sem máscara!\n";
	
	habilitar([cpf, nome, cep], [stateA, stateB, stateC]);
}

function verificarFornecedor() {
	var cnpj = document.getElementsByName("cnpjFornecedor")[0];
	var nome = document.getElementsByName("nomeFornecedor")[0];
	
	var stateA = cnpj.value.length == 14 && validNumber(cnpj.value);
	var stateB = validString(nome.value, 50);
	var info = document.getElementById("infoErro");
	
	info.innerText = "";
	if (!stateA) info.innerText += ">> O CNPJ é composto somente por 14 caracteres numéricos, sem máscara!\n";
	if (!stateB) info.innerText += ">> O nome precisa ter no mínimo 1 e no máximo 50 caracteres!\n";
	
	habilitar([cnpj, nome], [stateA, stateB]);
}

function verificarProduto() {
	var nome = document.getElementsByName("nomeProduto")[0];
	var fornecedor = document.getElementsByName("cnpjFornecedor")[0];
	var preco = document.getElementsByName("precoProduto")[0];
	var estoque = document.getElementsByName("qntProduto")[0];
	
	var stateA = validString(nome.value, 50);
	var stateB = fornecedor.value.length > 0;
	var stateC = validNumber(preco.value);
	var stateD = validInteger(estoque.value);
	var info = document.getElementById("infoErro");
	
	info.innerText = "";
	if (!stateA) info.innerText += ">> O nome precisa ter no mínimo 1 e no máximo 50 caracteres!\n";
	if (!stateB) info.innerText += ">> Selecione um fornecedor da lista!\n";
	if (!stateC) info.innerText += ">> O preço precisa ser um número decimal válido! (use '.' para as casas decimais)\n";
	if (!stateD) info.innerText += ">> O valor do estoque deve ser um número inteiro!\n";
	
	habilitar([nome, fornecedor, preco, estoque], [stateA, stateB, stateC, stateD]);
}

function verificarVenda() {
	var produto = document.getElementsByName("idProduto")[0];
	var quantidade = document.getElementsByName("qntCompra")[0];
	var cliente = document.getElementsByName("cpfCliente")[0];
	
	var stateA = produto.value.length > 0;
	var stateB = validInteger(quantidade.value);
	var stateC = cliente.value.length > 0;
	var info = document.getElementById("infoErro");
	
	info.innerText = "";
	if (!stateA) info.innerText += ">> Selecione um produto da lista!!\n";
	if (!stateB) info.innerText += ">> A quantidade da venda deve ser um número inteiro\n";
	if (!stateC) info.innerText += ">> Selecione um cliente da lista!\n";
	
	habilitar([produto, quantidade, cliente], [stateA, stateB, stateC]);
}

function habilitar(inputs, bools) {
	var state = true;
	for(var i = 0; i < inputs.length; i++) {
		state = state && bools[i];
		inputs[i].style.borderColor = bools[i] ? "black" : "red";
	}
	
	document.getElementById("btnSalvar").disabled = !state;
}