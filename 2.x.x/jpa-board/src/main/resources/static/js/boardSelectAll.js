// const boardSelectAllBtn = document.querySelectorAll(".boardSelectAll")[0];
// const boardSelect = document.querySelectorAll(".boardSelect");

// boardSelectAllBtn.addEventListener("click", () => {
//     if(boardSelectAllBtn.checked == true) {
//         boardSelect.forEach((b) => {
//             b.checked = true;
//         });
//     }
//     else {
//         boardSelect.forEach((b) => {
//             b.checked = false;
//         });
//     }
// })

const boardSelectAll = (allBtnElClassName, listElBtnClassName) => {
    const allBtn = document.querySelectorAll(`.${allBtnElClassName}`)[0];
    const listElBtn = document.querySelectorAll(`.${listElBtnClassName}`);

    allBtn.addEventListener("click", () => {
        if(allBtn.checked == true) {
            listElBtn.forEach((l) => {
                l.checked = true;
            });
        }
        else {
            listElBtn.forEach((l) => {
                l.checked = false;
            });
        }
    })
}

const selectedBoardDelete = (listElBtnClassName) => {
    const listElBtn = document.querySelectorAll(`.${listElBtnClassName}`);
    let deleteList = [];

    for(let i = 0; i < listElBtn.length; i++) {
        if(listElBtn[i].checked == true) {
            deleteList.push(Number(listElBtn[i].value));
        }
    }
    // alert(deleteList);
    const params = deleteList;
    const url = "/board/deleteAction?boardIdxList=" + params;
        
        try {
            fetch(url, {
                method: "PATCH",
                headers: { 
                    "Content-Type": "application/json",
                }        
            })
            .then((res) => res.json())
            .then((data) => {
                console.log(data);
                location.reload(true);
            })
        }
        catch(e) {
            console.error("글 삭제 실패");
            console.error(e);
        }

}