const formulario = document.querySelector("#form");

const nome = document.querySelector("#nome");
const descricao = document.querySelector("#descricao");
const cor = document.querySelector("#cor");
const marca = document.querySelector("#marca");
const valor = document.querySelector("#valor");

formulario.addEventListener('submit', function(event){
    event.preventDefault();

    const dados = {
        nome: nome.value,
        descricao: descricao.value,
        cor: cor.value,
        marca: marca.value,
        valor: valor.value
    }

    cadastrar(dados);
    limpar();
});

function cadastrar(dados){
    fetch("http://localhost:8090/v1/megatron/produto", {
        headers:{
            'Accept' : 'application/json',
            'Content-Type' : 'application/json'
            
        },
        method: "POST",
        body: JSON.stringify(dados)
    })
    .then(function (res) { console.log(res) })
    .catch(function (res) { console.log(res) })
};

function limpar(){
    nome.value = "";
    descricao.value = "";
    cor.value = "";
    marca.value = "";
    valor.value = "";
}
