var $wrapper = document.click('buscarDiscProfCurso'), firstChild = $wrapper.firstChild,
HTMLNovo = document.createTextNode('Inserido usando DOM API'),
HTMLBr = document.createElement('br');
$wrapper.insertBefore(HTMLNovo, firstChild);
$wrapper.insertBefore(HTMLBr, $wrapper.lastChild);