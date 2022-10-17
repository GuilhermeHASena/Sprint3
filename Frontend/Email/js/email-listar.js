const dataEnvio = document.querySelector("#dataEnvio");
const pesquisar = document.querySelector("#pesquisar");

listarTodos();

pesquisar.addEventListener('click', function(event){
    event.preventDefault();

    if (dataEnvio.value == ""){
        listarTodos();
    }
    else{
        listarPorData();
    }
});

function listarTodos(){
    fetch("http://localhost:8090/v1/email", {method: "GET", headers: {'Authorization': 'Basic ' + btoa("megatron:megatron")}}).then((data)=>{
        return data.json();
    }).then((objectData)=>{
        let tableData = "";
        objectData.map((values)=>{
            tableData += `<tr class="viagem-content">
                            <td>${values.corpoEmail}</td>
                            <td>${values.assuntoEmail}</td>
                            <td>${values.dataEnvio}</td>
                            <td>${values.destinatarios}</td>
                            <td>${values.destinatariosCopia}</td>			
                        </tr>`;
        });
        document.querySelector("#tableContent").innerHTML = tableData;
    });
}

function listarPorData(){
    fetch("http://localhost:8090/v1/email/data/?dataEnvio=" + dataEnvio.value, {method: "GET", headers: {'Authorization': 'Basic ' + btoa("megatron:megatron")}}).then((data)=>{
        return data.json();
    }).then((objectData)=>{
        let tableData = "";
            tableData += `<tr class="viagem-content">
                            <td>${objectData.corpoEmail}</td>
                            <td>${objectData.assuntoEmail}</td>
                            <td>${objectData.dataEnvio}</td>
                            <td>${objectData.destinatarios}</td>
                            <td>${objectData.destinatariosCopia}</td>			
                        </tr>`;
        document.querySelector("#tableContent").innerHTML = tableData;
    });
}