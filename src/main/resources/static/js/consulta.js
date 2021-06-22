codigo_selecionado = -1;

function excluir(text, cod) {
	codigo_selecionado = cod;

	document.getElementById("remover").className = "overlay";
	document.getElementById("prompt").style = "display: block";
	document.getElementById("prompt_texto").innerHTML = `Deseja remover o ${text} ${cod}?`;
}

function ok(type) { window.location.replace("/" + type + "/del/" + codigo_selecionado); }

function cancelar() {
	document.getElementById("remover").classList.remove("overlay");
	document.getElementById("prompt").style = "display: none";
	codigo_selecionado = -1;
}