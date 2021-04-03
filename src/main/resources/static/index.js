'use strict';

function searchHandler(e) {
    e.preventDefault();
    const form = e.target;
    const data = new FormData(form);
    let themeId = data.get("themeId");
    let text=data.get("comment")
    console.log(themeId);
    if (name.length > 10) {
        fetch("http://localhost:8181/comment" + themeId)
            .then(resp => resp.json()
                .then(data => {
                    if (data.status === 201) {
                        console.log(data);
                    } else {
                        alert("wasn't added")
                    }
                }));
    } else {
        alert("min length must be 10")
    }
}


document.getElementsByClassName("add-form")[0]
    .addEventListener('submit', searchHandler);
