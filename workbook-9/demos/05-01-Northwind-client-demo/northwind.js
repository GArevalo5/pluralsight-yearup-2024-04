
function loadCategories()
{

    let div = document.getElementById("categories-container");

    const url = "http://localhost:8080/categories";

    axios.get(url)
        .then( response => {
                div.innerText = "";
                let html = "";

                response.data.forEach(category => {
                    let item = `<div class="card mb-2">
                                         <div class="card-header">${category.name}</div>
                                         <div class="card-body">${category.description}</div>
                                       </div>`;
                    html += item;
                })

                div.innerHTML = html;
            }
        );
}
