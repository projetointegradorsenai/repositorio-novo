const modal = document.querySelector('.modal-container');
const tbody = document.querySelector('tbody');
const sNome = document.querySelector('#m-nome');
const sIdade = document.querySelector('#m-idade');
const sTime = document.querySelector('#m-time');
const sSelecao = document.querySelector('#m-selecao');
const sCamisa = document.querySelector('#m-camisa');
const btnSalvar = document.querySelector('#btnSalvar');

let itens = [];  // este é o array que armazena o jogador
let id;  // esta variavel armazena o id do jogador a ser editado

const apiBaseURL = 'https://jogadorsenai.azurewebsites.net/jogador';  // url da api

// Função para abrir o modal, seja para edição ou para adicionar um novo jogador
function openModal(edit = false, jogadorId = null) {
  modal.classList.add('active');

  modal.onclick = e => {
    if (e.target.className.indexOf('modal-container') !== -1) {
      modal.classList.remove('active');
    }
  };

  if (edit && jogadorId !== null) {
    // Carregar os dados do jogador para edição
    const jogador = itens.find(item => item.id === jogadorId);  // Encontra o jogador pelo ID
    if (jogador) {
      sNome.value = jogador.nome;
      sIdade.value = jogador.idade;
      sTime.value = jogador.time;
      sSelecao.value = jogador.selecao;
      sCamisa.value = jogador.camisa;
      id = jogadorId;  // Armazena o ID para usá-lo no PUT
    }
  } else {
    // Limpar os campos para adicionar um novo jogador
    sNome.value = '';
    sIdade.value = '';
    sTime.value = '';
    sSelecao.value = '';
    sCamisa.value = '';
    id = undefined;  // Reseta o ID para um novo jogador
  }
}

// Função para editar jogador
function editItem(jogadorId) {
  openModal(true, jogadorId);
}

// Função para excluir jogador
function deleteItem(jogadorId) {
  if (confirm('Tem certeza que deseja excluir este jogador?')) {
    fetch(`${apiBaseURL}/${jogadorId}`, {
      method: 'DELETE',
    })
    .then(response => {
      if (response.ok) {
        loadItens();  // Atualiza a lista de jogadores após a exclusão
      } else {
        alert('Erro ao excluir jogador');
      }
    })
    .catch(error => alert('Erro na requisição de exclusão: ' + error));
  }
}

// Função para inserir um jogador na tabela (para exibição)
function insertItem(item) {
  let tr = document.createElement('tr');
  tr.innerHTML = `
     <td>${item.nome}</td>
    <td>${item.idade}</td>
    <td>${item.time}</td>
    <td>${item.selecao}</td>
    <td>${item.camisa}</td>
    <td class="acao">
      <button onclick="editItem(${item.id})"><img src="./src/icones/7270001 3.svg" class='botaoexcluir'/></button>
    </td>
    <td class="acao">
      <button onclick="deleteItem(${item.id})"><img src="./src/icones/484611 2.svg" class='botaoexcluir'/></button>
    </td>
  `;
  tbody.appendChild(tr);
}

// Evento de clique no botão "Salvar" para adicionar ou editar um jogador
btnSalvar.onclick = e => {
  e.preventDefault();

  // Verificar se os campos obrigatórios foram preenchidos
  if (sNome.value == '' || sIdade.value == '' || sTime.value == '' || sSelecao.value == '' || sCamisa.value == '') {
    alert('Preencha todos os campos.');
    return;
  }

  const jogador = {
    nome: sNome.value,
    idade: sIdade.value,
    time: sTime.value,
    selecao: sSelecao.value,
    camisa: sCamisa.value,
  };

  if (id !== undefined) {
    // Se id estiver definido, significa que é uma edição (PUT)
    fetch(`${apiBaseURL}/${id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(jogador),
    })
    .then(response => {
      if (response.ok) {
        loadItens();  // Atualiza a lista de jogadores após a edição
        modal.classList.remove('active');
        id = undefined;  // Reseta o ID após a edição
      } else {
        alert('Erro ao atualizar jogador');
      }
    })
    .catch(error => alert('Erro na requisição de atualização: ' + error));
  } else {
    // Se id não estiver definido, é um novo jogador (POST)
    fetch(`${apiBaseURL}/adicionar`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(jogador),
    })
    .then(response => {
      if (response.ok) {
        loadItens();  // Atualiza a lista de jogadores após adicionar
        modal.classList.remove('active');
      } else {
        alert('Erro ao adicionar jogador');
      }
    })
    .catch(error => alert('Erro na requisição de adição: ' + error));
  }
};

// Função para carregar todos os jogadores da API
function loadItens() {
  fetch(apiBaseURL)
    .then(response => response.json())
    .then(data => {
      itens = data;  // Atualiza a lista de jogadores
      tbody.innerHTML = '';  // Limpa a tabela
      itens.forEach(item => insertItem(item));  // Insere todos os jogadores na tabela
    })
    .catch(error => alert('Erro ao carregar jogadores: ' + error));
}

// Carregar jogadores ao iniciar a página
loadItens();
