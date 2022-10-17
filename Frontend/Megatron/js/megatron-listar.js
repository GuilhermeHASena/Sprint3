fetch("http://localhost:8090/v1/megatron/produto", {method: "GET"}).then((data)=>{
    return data.json();
}).then((objectData)=>{
    let tableData = "";
    objectData.map((values)=>{
        tableData += `<tr class="viagem-content">
                        <td>${values.nome}</td>
                        <td>${values.descricao}</td>
                        <td>${values.cor}</td>
                        <td>${values.marca}</td>
                        <td>${"R$ " + values.valor}</td>			
                      </tr>`;
    });
    document.querySelector("#tableContent").innerHTML = tableData;
});