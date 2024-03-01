document.addEventListener("DOMContentLoaded", function(event) {
  document.getElementById("save").disabled = true;
});


var sortArr;
function saveSort() {
    let headers = new Headers();
    headers.append('Content-Type', 'text/plain');
    headers.append('Accept', '*/*');
    fetch("http://localhost:8000/postSortRes", {
        mode: 'cors',
        method: "POST",
        body: sortArr,
        headers: headers
    })
}

async function getSort() {
    if (document.getElementById('id_sort').value.trim() !== "") {
        let id_sort = document.getElementById('id_sort').value.trim().replace(/\s+/g, " ");
        let headers = new Headers();

        headers.append('Content-Type', 'text/plain');
        headers.append('Accept', '*/*');
        let response = await fetch("http://localhost:8000/getSortRes?id_sort=" + id_sort,{
        method: "GET",
        headers: headers,
        });
        let result;
        if (response.ok) {
            result = await response.text();
        }else{
        document.getElementById('arr_sort').innerHTML = "Нет данных.";}
        if (result.length > 2){
            document.getElementById('arr_sort').innerHTML = "Результат сортировки "+id_sort+": " + result;
        }else{
            document.getElementById('arr_sort').innerHTML = "Нет данных.";
        }

        }else {
             document.getElementById('arr_sort').innerHTML = "Нет данных.";
         }

}

function bubbleSort() {

    if (document.getElementById('nums').value.trim() !== "") {
        let arr = document.getElementById('nums').value.trim().replace(/\s+/g, " ").split(" ");
        arr = arr.map(Number);
        for (let i = 0; i < arr.length - 1; i++) {
            let f = false;
            for (let j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    let temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    f = true;
                }
            }
            if (!f) {
                break;
            }
        }
        document.getElementById('result').innerHTML = "Результат сортировки: " + arr;
        sortArr = arr;
        document.getElementById("save").disabled = false;
    } else {
        document.getElementById('result').innerHTML = "Нет данных.";
    }

}