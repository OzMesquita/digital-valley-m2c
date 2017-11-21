/**
 * 
 */
var menuLeft = document.getElementById('cbp-spmenu-s1'), showLeftPush = document
		.getElementById('showLeftPush'), body = document.body;

showLeftPush.onclick = function() {
	classie.toggle(this, 'active');
	classie.toggle(body, 'cbp-spmenu-push-toright');
	classie.toggle(menuLeft, 'cbp-spmenu-open');
	disableOther('showLeftPush');
};

function disableOther(button) {
	if (button !== 'showLeftPush') {
		classie.toggle(showLeftPush, 'disabled');
	}
}
function selecionaTudo() {
	var disponiveis = document.getElementById('selectmultipleDisp');
	for (i = 0; i < disponiveis.length; i++) {
		disponiveis.options[i].selected = true;
	}
	document.getElementById('listaDisponivel').value = disponiveis;

	var selecionados = document.getElementById('selectmultipleCad');
	for (i = 0; i < selecionados.length; i++) {
		selecionados.options[i].selected = true;
	}
	document.getElementById('listaCadastrado').value = selecionados;

}
function mostraResposta(resposta) {
	var remove = document.getElementById('selectmultipleDisp');
	remove.removeChild(remove.options);

	$('selectmultiple').value.resposta.responseText;

}
function inclui() {
	var novoElemento = document.createElement('option');
	var remove = document.getElementById('selectmultipleDisp');
	var itemSelecionado = remove.options[remove.selectedIndex].text;
	novoElemento.textContent = itemSelecionado;
	var lista = document.getElementById('selectmultipleCad');
	lista.appendChild(novoElemento);
	remove.removeChild(remove.options[remove.selectedIndex]);
}
function remove() {
	var novoElemento = document.createElement('option');
	var remove = document.getElementById('selectmultipleCad');
	var itemSelecionado = remove.options[remove.selectedIndex].text;
	novoElemento.textContent = itemSelecionado;
	remove.removeChild(remove.options[remove.selectedIndex]);
	var lista = document.getElementById('selectmultipleDisp');
	lista.appendChild(novoElemento);
}

function mostra() {

	var x = document.getElementById('selectmultiple');
	var itemSelecionado = x.options[x.selectedIndex].value;

	document.getElementById('selecionado').value = itemSelecionado;

	document.location.href = 'pesquisaModulos?busca='
			+ document.getElementById('selecionado').value + '';
}